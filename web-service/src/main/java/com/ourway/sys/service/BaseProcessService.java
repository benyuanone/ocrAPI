package com.ourway.sys.service;

import com.ourway.sys.model.OurwaySysEmploys;
import com.ourway.sys.model.OurwaySysWorkflow;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;
import java.util.Map;

/**
 * <p>接口 FlowService.java : <p>
 * <p>说明：功能流</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 14:36
 * </pre>
 */
public interface BaseProcessService {
    /**
     * 方法说明 ： 根据流程定义Key查询最新流程定义.
     *
     * @param processDefinitionKey
     * @throws Exception
     */
    public ProcessDefinition findLatestProcessDefinitionByPrcDefKey(String processDefinitionKey) throws Exception;

    /**
     * 方法说明 ： 根据流程定义Id查询流程定义.
     *
     * @param processDefinitionId 流程定义Id
     */
    public ProcessDefinitionEntity findProcessDefinitionEntityByProcDefId(String processDefinitionId) throws Exception;

    /**
     * 方法说明  ： 根据流程实例Id查询流程实例.
     *
     * @param processInstanceId 流程实例Id
     * @return
     */
    public ProcessInstance findProcessInstanceByProcInst(String processInstanceId) throws Exception;

    /**
     * 根据流程实例Id查询流程实例.
     *
     * @param processInstanceId
     */
    public Execution findExecutionByProcInst(String processInstanceId) throws Exception;

    /**
     * 方法说明 ： 根据流程实例Id查询任务.
     *
     * @param processInstanceId 流程实例Id
     */
    public Task findTaskByProcInstId(String processInstanceId) throws Exception;

    /**
     * 方法说明 ： 根据实例Id查询任务.
     *
     * @param executionId 实例Id
     */
    public Task findTaskByExecutionId(String executionId) throws Exception;

    /**
     * 方法说明 ： 根据活动节点查询任务定义.
     *
     * @param activityImpl 活动节点
     */
    public TaskDefinition findTaskDefinitionByActivityImpl(ActivityImpl activityImpl) throws Exception;

    /**
     * 方法说明 : 查询上一个节点.
     *
     * @param activityImpl 活动节点
     * @param activityId   当前活动节点ID
     * @param elString
     */
    public TaskDefinition beforeTaskDefinition(ActivityImpl activityImpl, String activityId, String elString) throws Exception;

    /**
     * 方法说明 : 查询下一个节点.
     *
     * @param activityImpl 活动节点
     * @param activityId   当前活动节点ID
     * @param elString
     */
    public TaskDefinition nextTaskDefinition(ActivityImpl activityImpl, String activityId, String elString) throws Exception;

    /**
     * 方法说明： 根据活动节点、活动线路查询线路的连接线.
     */
    public PvmActivity findPvmActivity(ActivityImpl activityImpl, String transitions) throws Exception;

    /**
     * 方法说明 ：根据流程定义Id查询任务定义
     *
     * @param processDefinitionId 流程定义Id
     * @return
     */
    public TaskDefinition findTaskDefinition(String processDefinitionId) throws Exception;

    /**
     * 方法说明 ： 添加任务意见.
     *
     * @param taskId            任务Id
     * @param processInstanceId 流程实例Id
     * @param comment           意见
     */
    public void addTaskComment(String taskId, String processInstanceId, String comment) throws Exception;

    /**
     * 方法说明 ： 拾取任务.
     *
     * @param taskId   任务Id
     * @param operator 办理人
     */
    public void claimTask(String taskId, String operator) throws Exception;

    /**
     * 方法说明 ： 根据流程定义Id查询最新流程定义.
     *
     * @param processDefinitionId 流程定义Id
     * @return
     */
    public ProcessDefinition findProcessDefinitionByPrcDefId(String processDefinitionId) throws Exception;

    /**
     * <p>方法:startProcess 启动流程 </p>
     * <ul>
     * <li> @param processDefinitionId TODO</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/7/20 14:31  </li>
     * </ul>
     */
    void startProcess(String processDefinitionId);

    /**
     * <p>方法:removeDeployMent 删除部署内容  </p>
     * <ul>
     * <li> @param deployMentId </li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/7/20 15:24  </li>
     * </ul>
     */
    void removeDeployMent(String deployMentId);

    /**
     * <p>方法:updateDeployWorkFlow 部署工作流，用zip方式部署 </p>
     * <ul>
     * <li> @param zipFilePath TODO</li>
     * <li> @param flowName TODO</li>
     * <li>@return org.activiti.engine.repository.Deployment  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/7/20 16:16  </li>
     * </ul>
     */
    Deployment updateDeployWorkFlow(String zipFilePath, String flowName);

    /**
     * <p>方法:startWorkFlow 启动流程 </p>
     * <ul>
     * <li> @param flowKey TODO</li>
     * <li> @param owid TODO</li>
     * <li> @param variables TODO</li>
     * <li>@return org.activiti.engine.runtime.ProcessInstance  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/7/20 16:49  </li>
     * </ul>
     */
    ProcessInstance startWorkFlow(Object flowInstance, OurwaySysWorkflow workflow);
    //执行下一个节点
    String updateProcessWorkFlow(OurwaySysWorkflow workflow,OurwaySysEmploys employs,String path);
    //获取当前节点并设置处理人
    Task updateClaimCurrentTask(OurwaySysWorkflow workflow,OurwaySysEmploys employs);
    //显示当前节点的流程图片，返回显示路径
    String displayFlowDiagram(OurwaySysWorkflow workflow,String filePath);

    OurwaySysWorkflow updatePassTask(OurwaySysWorkflow workflow,String comment);

    OurwaySysWorkflow updateRejectTask(OurwaySysWorkflow workflow,String comment);

    List<Task> listAllTask(OurwaySysEmploys employs);
    //删除指定的任务
    void removeAllTask(List<String> taskIds);
    void removeTask(String taskId);

    Map<String, Object> doGetTaskAttribute(Task task);

    void completeTask(Task task);
}
