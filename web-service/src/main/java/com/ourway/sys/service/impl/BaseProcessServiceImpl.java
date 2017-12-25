package com.ourway.sys.service.impl;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.sys.dao.SysFlowDao;
import com.ourway.sys.dao.SysWorkflowDao;
import com.ourway.sys.model.OurwaySysEmploys;
import com.ourway.sys.model.OurwaySysFlow;
import com.ourway.sys.model.OurwaySysWorkflow;
import com.ourway.sys.service.BaseProcessService;
import com.ourway.sys.service.MessageService;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;
import java.util.zip.ZipInputStream;

/**
 * Created by Administrator on 2017/7/20.
 */
@Service("baseProcessService")
public class BaseProcessServiceImpl implements BaseProcessService {
    private static Logger logger = LoggerFactory.getLogger(BaseProcessServiceImpl.class);
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    ProcessEngineConfiguration processEngineConfiguration;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;
    @Autowired
    IdentityService identityService;
    @Autowired
    ProcessEngine processEngine;
    @Autowired
    SysWorkflowDao sysWorkflowDao;
    @Autowired
    SysFlowDao sysFlowDao;
    @Autowired
    MessageService messageService;


    /**
     * 方法说明 ： 根据流程定义Key查询最新流程定义.
     *
     * @param processDefinitionKey 流程定义Key
     * @return
     */
    @Override
    public ProcessDefinition findLatestProcessDefinitionByPrcDefKey(String processDefinitionKey) throws Exception {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processDefinitionKey)
                .latestVersion()
                .singleResult();
        return processDefinition;
    }

    /**
     * 方法说明 ： 根据流程实例Id查询任务.
     *
     * @param processDefinitionId 流程实例Id
     * @return
     * @throws Exception
     */
    @Override
    public ProcessDefinitionEntity findProcessDefinitionEntityByProcDefId(String processDefinitionId) throws Exception {
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                .getDeployedProcessDefinition(processDefinitionId);
        return processDefinitionEntity;
    }

    /**
     * 方法说明 ： 根据流程定义Id查询流程定义.
     */
    @Override
    public ProcessInstance findProcessInstanceByProcInst(String processInstanceId) throws Exception {
        return runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
    }

    /**
     * 方法说明  ： 根据流程实例Id查询流程实例.
     *
     * @param processInstanceId 流程实例Id
     * @return
     */
    @Override
    public Execution findExecutionByProcInst(String processInstanceId) throws Exception {
        return runtimeService.createExecutionQuery().processInstanceId(processInstanceId).singleResult();
    }

    /**
     * 根据流程实例Id查询流程实例.
     *
     * @param processInstanceId
     * @return
     */
    @Override
    public Task findTaskByProcInstId(String processInstanceId) throws Exception {
        return taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
    }

    /**
     * 方法说明 ： 根据流程实例Id查询任务.
     *
     * @return
     */
    @Override
    public Task findTaskByExecutionId(String executionId) throws Exception {
        Task task = taskService.createTaskQuery().executionId(executionId).singleResult();
        return task;
    }

    @Override
    public TaskDefinition findTaskDefinitionByActivityImpl(ActivityImpl activityImpl) throws Exception {
        return ((UserTaskActivityBehavior) activityImpl.getActivityBehavior()).getTaskDefinition();
    }

    @Override
    public TaskDefinition beforeTaskDefinition(ActivityImpl activityImpl, String activityId, String elString) throws Exception {
        if ("userTask".equals(activityImpl.getProperty("type")) && !activityId.equals(activityImpl.getId())) {
            TaskDefinition taskDefinition = null;
            if (activityImpl != null) {
                taskDefinition = findTaskDefinitionByActivityImpl(activityImpl);
            }
            return taskDefinition;
        } else {
            List<PvmTransition> inTransitions = activityImpl.getIncomingTransitions();   //通过活动节点查询所有线路
            if (!TextUtils.isEmpty(inTransitions)) {
                List<PvmTransition> inTransitionsTemp = null;
                for (PvmTransition tr : inTransitions) {
                    PvmActivity ac = tr.getSource();      //获取线路的前节点
                    if ("exclusiveGateway".equals(ac.getProperty("type"))) {
                        inTransitionsTemp = ac.getIncomingTransitions();
                        if (inTransitionsTemp.size() == 1) {
                            return beforeTaskDefinition((ActivityImpl) inTransitionsTemp.get(0).getSource(), activityId, elString);
                        } else if (inTransitionsTemp.size() > 1) {
                            for (PvmTransition tr1 : inTransitionsTemp) {
                                Object s = tr1.getProperty("conditionText");
                                if (elString.equals(StringUtils.replacePattern(StringUtils.trim(s.toString()), " ", ""))) {
                                    return beforeTaskDefinition((ActivityImpl) tr1.getSource(), activityId, elString);
                                }
                            }
                        }
                    }
                }
            }
            return null;
        }
    }

    @Override
    public TaskDefinition nextTaskDefinition(ActivityImpl activityImpl, String activityId, String elString) throws Exception {
        if ("userTask".equals(activityImpl.getProperty("type")) && !activityId.equals(activityImpl.getId())) {
            TaskDefinition taskDefinition = null;
            if (activityImpl != null) {
                taskDefinition = findTaskDefinitionByActivityImpl(activityImpl);
            }
            return taskDefinition;
        } else {
            List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();   //通过活动节点查询所有线路
            if (!TextUtils.isEmpty(outTransitions)) {
                List<PvmTransition> outTransitionsTemp = null;
                for (PvmTransition tr : outTransitions) {
                    PvmActivity ac = tr.getDestination();         //获取线路的终点节点
                    if ("exclusiveGateway".equals(ac.getProperty("type"))) {
                        outTransitionsTemp = ac.getOutgoingTransitions();
                        if (outTransitionsTemp.size() == 1) {
                            return nextTaskDefinition((ActivityImpl) outTransitionsTemp.get(0).getDestination(), activityId, elString);
                        } else if (outTransitionsTemp.size() > 1) {
                            for (PvmTransition tr1 : outTransitionsTemp) {
                                Object s = tr1.getProperty("conditionText");
                                if (s != null && elString.equals(StringUtils.replacePattern(StringUtils.trim(s.toString()), " ", ""))) {
                                    return nextTaskDefinition((ActivityImpl) tr1.getDestination(), activityId, elString);
                                }
                            }
                        }
                    } else if ("userTask".equals(ac.getProperty("type"))) {
                        return findTaskDefinitionByActivityImpl((ActivityImpl) ac);
                    } else if ("startEvent".equals(ac.getProperty("type"))) {
                        return findTaskDefinitionByActivityImpl((ActivityImpl) ac);
                    } else {
                        logger.info(ac.getProperty("type").toString());
                    }
                }
            }
            return null;
        }
    }

    @Override
    public PvmActivity findPvmActivity(ActivityImpl activityImpl, String transitions) throws Exception {
        PvmActivity activity = null;
        List<PvmTransition> pvmTransitions = activityImpl.getOutgoingTransitions();   //获取所有线路

        for (Iterator iterator = pvmTransitions.iterator(); iterator.hasNext(); ) {
            PvmTransition pvmTransition = (PvmTransition) iterator.next();
            PvmActivity pvmActivity = pvmTransition.getDestination();           //获取下一个任务节点
            String transitionsVal = (String) pvmActivity.getProperty("name");
            if (transitions.equals(transitionsVal)) {
                activity = pvmActivity;
                break;
            }
        }
        return activity;
    }

    @Override
    public TaskDefinition findTaskDefinition(String processDefinitionId) throws Exception {
        //获取流程定义
        ProcessDefinitionEntity processDefinitionEntity = findProcessDefinitionEntityByProcDefId(processDefinitionId);
        TaskDefinition tdf = null;

        if (processDefinitionEntity != null) {
            List<ActivityImpl> activityImpls = processDefinitionEntity.getActivities();    //获取所有活动的节点
            for (int i = activityImpls.size() - 1; i > 0; i--) {
                ActivityImpl activityImpl = activityImpls.get(i);
                String startEventType = (String) activityImpl.getProperty("type");
                if ("startEvent".equals(startEventType)) {
                    tdf = nextTaskDefinition(activityImpl, activityImpl.getId(), null);
                }
            }
        }
        return tdf;
    }

    @Override
    public void addTaskComment(String taskId, String processInstanceId, String comment) throws Exception {
        taskService.addComment(taskId, processInstanceId, comment);
    }

    @Override
    public void claimTask(String taskId, String operator) throws Exception {
        taskService.claim(taskId, operator);
    }

    @Override
    public ProcessDefinition findProcessDefinitionByPrcDefId(String processDefinitionId) throws Exception {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId)
                .orderByProcessDefinitionVersion()
                .desc()
                .singleResult();

        return processDefinition;
    }

    @Override
    public void startProcess(String processDefinitionId) {
        runtimeService.startProcessInstanceByKey(processDefinitionId);
    }

    @Override
    public void removeDeployMent(String deployMentId) {
//        repositoryService.deleteDeployment(deployMentId);
        // 级联删除,会删除和当前规则相关的所有信息，包括历史
        repositoryService.deleteDeployment(deployMentId, true);
    }

    @Override
    public Deployment updateDeployWorkFlow(String zipFilePath, String flowName) {
        try {
            File _file = new File(zipFilePath);
            InputStream inputStream = new FileInputStream(_file);
            ZipInputStream zipInputStream = new ZipInputStream(inputStream); // 实例化zip输入流对象
            // 获取部署对象
            Deployment deployment = processEngine.getRepositoryService() // 部署Service
                    .createDeployment()  // 创建部署
                    .name(flowName)  // 流程名称
                    .addZipInputStream(zipInputStream)  // 添加zip是输入流
                    .deploy(); // 部署
            return deployment;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ProcessInstance startWorkFlow(Object flowInstance, OurwaySysWorkflow workflow) {
        OurwaySysFlow flow = sysFlowDao.listSysFlowByClass(workflow.getClassName());
        if (null == flow)
            return null;
        //判断，是否可以启动
        OurwaySysWorkflow _flow = sysWorkflowDao.listCurrentNode(workflow.getBusinessId(), workflow.getClassName());
        if (null != _flow)
            return null;
        //继续启动
        logger.info(flow.getFlowKey() + "{" + flow.getFlowCode() + "}" + workflow.getClassName() + "  流程启动");
//        OurwaySysEmploys employs = ShiroUtilsClient.getUserEntity();
//        if (null != employs)
//            identityService.setAuthenticatedUserId(employs.getEmpId());
        Map<String, Object> flowVarias = new HashMap<String, Object>();
        if (!TextUtils.isEmpty(flow.getOurwaySysFlowClass().getVars())) {
            String vars = flow.getOurwaySysFlowClass().getVars();
            String[] args = vars.split("\\,");
            for (String arg : args) {
                if (!TextUtils.isEmpty(arg)) {
                    flowVarias.put(arg, BeanUtil.getProperty(flowInstance, arg));
                }
            }
        }

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(flow.getFlowId(), workflow.getBusinessId(), flowVarias);
        workflow.setFlowDeploymentId(processInstance.getDeploymentId());
        workflow.setFlowInstanceId(processInstance.getId());
        List<Task> tasks = listNextTasks(processInstance);
        for (Task task : tasks) {
            OurwaySysWorkflow newWorkFlow = new OurwaySysWorkflow();
            BeanUtils.copyProperties(workflow, newWorkFlow, "owid");
            newWorkFlow.setTaskFromKey(task.getFormKey());
            newWorkFlow.setTaskName(task.getName());
            newWorkFlow.setTaskId(task.getId());
            newWorkFlow.setTaskParentId(task.getParentTaskId());
            newWorkFlow.setTaskAssign(task.getAssignee());
            newWorkFlow.setTaskCatagory(task.getCategory());
            newWorkFlow.setTaskTime(task.getCreateTime());
            newWorkFlow.setTaskKey(task.getTaskDefinitionKey());
            newWorkFlow.setTaskProcessId(task.getProcessInstanceId());
            newWorkFlow.setState(0);
            if (!TextUtils.isEmpty(task.getProcessDefinitionId())) {
                if (task.getProcessDefinitionId().indexOf(":") >= 0)
                    newWorkFlow.setFlowDeploymentId(task.getProcessDefinitionId().split("\\:")[0]);
                else
                    newWorkFlow.setFlowDeploymentId(task.getProcessDefinitionId());
            }
            sysWorkflowDao.saveOrUpdate(newWorkFlow);
            if (TextUtils.isEmpty(newWorkFlow.getTaskFromKey()))
                messageService.updateWorkFlowMsgs(newWorkFlow, "/test/flow/test1.do");
            else
                messageService.updateWorkFlowMsgs(newWorkFlow, newWorkFlow.getTaskFromKey());
        }
//        messageService.updateWorkFlowMsgs(flow.getFlowId(), task.getTaskDefinitionKey());//发送消息处理
        logger.info(flow.getFlowKey() + "{" + flow.getFlowCode() + "}" + workflow.getClassName() + "流程启动成功");
        return processInstance;
    }

    @Override
    public OurwaySysWorkflow updatePassTask(OurwaySysWorkflow workflow, String comment) {
        OurwaySysWorkflow result = updateHandleTask(workflow, comment, 1);
        return result;
    }

    @Override
    public OurwaySysWorkflow updateRejectTask(OurwaySysWorkflow workflow, String comment) {
        OurwaySysWorkflow result = updateHandleTask(workflow, comment, 2);
        return result;
    }

    private OurwaySysWorkflow updateHandleTask(OurwaySysWorkflow workflow, String comment, int passFlag) {
        boolean flag = sysWorkflowDao.updateAuditStatus(comment, workflow, passFlag);
        if (!flag)
            return null;
        Map<String, Object> flowVarias = new HashMap<String, Object>();
        if (passFlag == 1)
            flowVarias.put("result", "yes");
        else
            flowVarias.put("result", "no");

        //审批完成
        taskService.complete(workflow.getTaskId(), flowVarias);
        //更新主表的状态
        try {
            sysWorkflowDao.updateObjectState(workflow.getBusinessId(), workflow.getClassName(), workflow.getTaskCatagory());
        } catch (Exception e) {

        }
        //获取下一个节点
        OurwaySysWorkflow nextWorkFlow = new OurwaySysWorkflow();
        BeanUtils.copyProperties(workflow, nextWorkFlow, new String[]{"owid", "taskId", "taskName", "taskParentId", "taskAssign", "taskCatagory", "taskPerId", "taskPerName", "taskResult", "taskProcessId", "taskFromKey", "taskKey", "taskTime", "state"});

        List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(workflow.getBusinessId()).list();
        //判断流程是否结束
        if (null == processInstances || processInstances.size() <= 0) {
            nextWorkFlow.setTaskCatagory("99");
            nextWorkFlow.setState(3);
            nextWorkFlow.setTaskTime(new Date());
            nextWorkFlow.setLasupdate(new Date());
            nextWorkFlow.setTaskFromKey(workflow.getTaskFromKey());
            nextWorkFlow.setTaskName(workflow.getTaskName());
            nextWorkFlow.setTaskId(workflow.getTaskId());
            nextWorkFlow.setTaskParentId(workflow.getTaskParentId());
            nextWorkFlow.setTaskAssign(workflow.getTaskAssign());
            nextWorkFlow.setTaskKey(workflow.getTaskKey());
            nextWorkFlow.setTaskProcessId(workflow.getTaskProcessId());
            try {
                sysWorkflowDao.updateObjectState(workflow.getBusinessId(), workflow.getClassName(), "99");
            } catch (Exception e) {

            }
        } else {
            //流程没结束
            for (ProcessInstance processInstance : processInstances) {
                updateHandleList(processInstance, nextWorkFlow);
            }
        }
//        if (nextWorkFlow.getState() == 0) {
//            OurwaySysFlow flow = sysFlowDao.listSysFlowByClass(workflow.getClassName());
//            messageService.updateWorkFlowMsgs(flow.getFlowId(), nextWorkFlow.getTaskKey());//发送消息处理
//        }
        return nextWorkFlow;
    }

    private void updateHandleList(ProcessInstance processInstance, OurwaySysWorkflow nextWorkFlow) {
        List<Task> tasks = listNextTasks(processInstance);
        for (Task task : tasks) {
            OurwaySysWorkflow _nextFlow = new OurwaySysWorkflow();
            BeanUtils.copyProperties(nextWorkFlow, _nextFlow, "owid");
            _nextFlow.setTaskFromKey(task.getFormKey());
            _nextFlow.setTaskName(task.getName());
            _nextFlow.setTaskId(task.getId());
            _nextFlow.setTaskParentId(task.getParentTaskId());
            _nextFlow.setTaskAssign(task.getAssignee());
            _nextFlow.setTaskCatagory(task.getCategory());
            _nextFlow.setTaskTime(task.getCreateTime());
            _nextFlow.setTaskKey(task.getTaskDefinitionKey());
            _nextFlow.setTaskProcessId(task.getProcessInstanceId());
            _nextFlow.setState(0);
            if (!TextUtils.isEmpty(task.getProcessDefinitionId())) {
                if (task.getProcessDefinitionId().indexOf(":") >= 0)
                    _nextFlow.setFlowDeploymentId(task.getProcessDefinitionId().split("\\:")[0]);
                else
                    _nextFlow.setFlowDeploymentId(task.getProcessDefinitionId());
            }
            sysWorkflowDao.saveOrUpdate(_nextFlow);
            if (TextUtils.isEmpty(_nextFlow.getTaskFromKey()))
                messageService.updateWorkFlowMsgs(_nextFlow, "/test/flow/test1.do");
            else
                messageService.updateWorkFlowMsgs(_nextFlow, _nextFlow.getTaskFromKey());
        }
    }

    private List<Task> listNextTasks(ProcessInstance processInstance) {
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByTaskCreateTime().desc().list();
        if (null == tasks || tasks.size() <= 0)
            return null;
        else
            return tasks;
    }

    @Override
    public List<Task> listAllTask(OurwaySysEmploys employs) {
        List<Task> _tmpTasks = new ArrayList<Task>();
        List<Task> tasks = new ArrayList<Task>();
        tasks = taskService.createTaskQuery().taskAssignee(employs.getEmpId()).list();
        if (!TextUtils.isEmpty(employs.getDeptPositions())) {
            String[] strs = employs.getDeptPositions().split("\\,");
            for (String str : strs) {
                if (!TextUtils.isEmpty(str)) {
                    _tmpTasks = taskService.createTaskQuery().taskCandidateGroup(str).list();
                    for (Task s : _tmpTasks) {
                        tasks.add(s);

                    }
                }
            }
        }
        return tasks;
    }

    @Override
    public Map<String, Object> doGetTaskAttribute(Task task) {
        Map<String, Object> data = taskService.getVariables(task.getId());
        return data;
    }

    @Override
    public void completeTask(Task task) {
        taskService.complete(task.getId());
    }

    @Override
    public String displayFlowDiagram(OurwaySysWorkflow workflow, String filePath) {
        try {
            String fileUrl = "";

            //processInstanceId
            String processInstanceId = workflow.getTaskProcessId();
            HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
            //获取流程图
            BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
            processEngineConfiguration = processEngine.getProcessEngineConfiguration();
            Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);

            ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();

            ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());

            List<HistoricActivityInstance> highLightedActivitList = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();
            //高亮环节id集合
            List<String> highLightedActivitis = new ArrayList<String>();
            //高亮线路id集合
            List<String> highLightedFlows = getHighLightedFlows(definitionEntity, highLightedActivitList);

            for (HistoricActivityInstance tempActivity : highLightedActivitList) {
                String activityId = tempActivity.getActivityId();
                highLightedActivitis.add(activityId);
            }
            //中文显示的是口口口，设置字体就好了
            InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitis, highLightedFlows, "宋体", "宋体", "宋体", null, 1.0);
//            InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel,"png", highLightedActivitis,highLightedFlows, "宋体", "宋体", null, 1.0);
            //单独返回流程图，不高亮显示
            //        InputStream imageStream = diagramGenerator.generatePngDiagram(bpmnModel);
            // 输出资源内容到相应对象

            File file = new File(filePath + "workflow" + File.separator);
            if (!file.exists())
                file.mkdirs();
            fileUrl = "workflow" + File.separator + System.currentTimeMillis() + ".png";
            filePath = filePath + fileUrl;
            FileOutputStream out = new FileOutputStream(filePath);
            FileCopyUtils.copy(imageStream, out);
            return fileUrl;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Task updateClaimCurrentTask(OurwaySysWorkflow workflow, OurwaySysEmploys employs) {
        //获取任务
        boolean flag = sysWorkflowDao.updateFlowStatues(employs.getEmpId(), employs.getEmpName(), workflow);
        if (flag) {
            Task task = (Task) taskService.createTaskQuery().taskId(workflow.getTaskId()).singleResult();
            //设置用户
            try {
                if (TextUtils.isEmpty(task.getAssignee()))
                    taskService.claim(task.getId(), employs.getEmpId());
            } catch (Exception e) {

            }
            return task;
        } else
            return null;
    }

    @Override
    public String updateProcessWorkFlow(OurwaySysWorkflow workflow, OurwaySysEmploys employs, String path) {
        Task task = updateClaimCurrentTask(workflow, employs);
        if (null == task)
            return null;
//        String filePath = displayFlowDiagram(workflow, path);
        return task.getAssignee();
    }

    @Override
    public void removeAllTask(List<String> taskIds) {
        taskService.deleteTasks(taskIds, true);
    }

    @Override
    public void removeTask(String taskId) {
        try {
            runtimeService.deleteProcessInstance(taskId, "cancer");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        taskService.deleteTask(taskId, true);
    }

    private List<String> getHighLightedFlows(
            ProcessDefinitionEntity processDefinitionEntity,
            List<HistoricActivityInstance> historicActivityInstances) {
        List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId
        for (int i = 0; i < historicActivityInstances.size() - 1; i++) {// 对历史流程节点进行遍历
            ActivityImpl activityImpl = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i)
                            .getActivityId());// 得到节点定义的详细信息
            List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();// 用以保存后需开始时间相同的节点
            ActivityImpl sameActivityImpl1 = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i + 1)
                            .getActivityId());
            // 将后面第一个节点放在时间相同节点的集合里
            sameStartTimeNodes.add(sameActivityImpl1);
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
                HistoricActivityInstance activityImpl1 = historicActivityInstances
                        .get(j);// 后续第一个节点
                HistoricActivityInstance activityImpl2 = historicActivityInstances
                        .get(j + 1);// 后续第二个节点
                if (activityImpl1.getStartTime().equals(
                        activityImpl2.getStartTime())) {
                    // 如果第一个节点和第二个节点开始时间相同保存
                    ActivityImpl sameActivityImpl2 = processDefinitionEntity
                            .findActivity(activityImpl2.getActivityId());
                    sameStartTimeNodes.add(sameActivityImpl2);
                } else {
                    // 有不相同跳出循环
                    break;
                }
            }
            List<PvmTransition> pvmTransitions = activityImpl
                    .getOutgoingTransitions();// 取出节点的所有出去的线
            for (PvmTransition pvmTransition : pvmTransitions) {
                // 对所有的线进行遍历
                ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition
                        .getDestination();
                // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }
        return highFlows;
    }
}
