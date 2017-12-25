package com.ourway.sys.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.sys.dao.*;
import com.ourway.sys.model.*;
import com.ourway.sys.service.BaseProcessService;
import com.ourway.sys.service.FlowService;
import com.ourway.sys.utils.ShiroUtilsClient;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
@Service("flowService")
public class FlowServiceImpl implements FlowService {

    @Autowired
    private SysFlowDao sysFlowDao;
    @Autowired
    SysFlowClassDao sysFlowClassDao;
    @Autowired
    BaseProcessService baseProcessService;
    @Autowired
    SysWorkflowDao sysWorkflowDao;
    @Autowired
    SysFlowNodePerDao sysFlowNodePerDao;


    public PageInfo<OurwaySysFlow> listHqlForPage(List<FilterModel> filterModels, int pageNo, int pageSize) {
        return sysFlowDao.listFowByPage(filterModels, pageNo, pageSize);
    }

    @Override
    public List<Map<String, Object>> removeFlow(List<String> owids) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();
        for (String owid : owids) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            params.put("owid", owid);
            OurwaySysFlow ourwaySysFlow = sysFlowDao.getOneByParams(params, "");
            if (!TextUtils.isEmpty(ourwaySysFlow.getFlowCode())) {
                //表示要先取消部署
                baseProcessService.removeDeployMent(ourwaySysFlow.getFlowCode());
            }
            if (null != ourwaySysFlow) {
                objs.add(params);
                params = new HashMap<String, Object>(1);
                params.put("flowRefOwid", ourwaySysFlow.getOwid());
                sysFlowClassDao.removeByParams(params);
                sysFlowDao.removeEntity(ourwaySysFlow);
            }
        }
        return objs;
    }

    @Override
    public OurwaySysFlow listByParams(Map<String, Object> params) {
        List<OurwaySysFlow> flows = sysFlowDao.listAllByParam(params, "");
        if (null != flows && flows.size() > 0)
            return flows.get(0);
        return null;
    }

    @Override
    public void saveOrUpdate(OurwaySysFlow ourwaySysFlow, List<OurwaySysFlowClass> flowClasses) {
        if (null == ourwaySysFlow.getFlowStatus())
            ourwaySysFlow.setFlowStatus((byte) 0);
        sysFlowDao.saveOrUpdate(ourwaySysFlow);
        if (null != flowClasses && flowClasses.size() > 0)
            for (OurwaySysFlowClass flowClass : flowClasses) {
                if (null == flowClass.getUpdateFlag()) {
                    flowClass.setUpdateFlag(0);
                }
                switch (flowClass.getUpdateFlag()) {
                    //0 新增
                    case 0:
                        //什么也不做
                        break;
                    //修改
                    case 1:
                        flowClass.setFlowRefOwid(ourwaySysFlow.getOwid());
//                        flowClass.setClassRefOwid(flowClass.getSysObject().getClassName());
                        // 判断 owid
                        if (TextUtils.isEmpty(flowClass.getOwid())) {
                            //为空 新增
                            sysFlowClassDao.save(flowClass);
                        } else {
                            sysFlowClassDao.update(flowClass);
                        }
                        break;
                    //删除
                    case 2:
                        sysFlowClassDao.removeByIds(flowClass.getOwid());
                        break;
                }
            }
    }

    @Override
    public void updateDeployFlow(String owid, String zipFile) {
        OurwaySysFlow flow = sysFlowDao.getOneById(owid);
        if (null != flow.getFlowStatus() && flow.getFlowStatus() >= 1)
            return;
        Deployment deployment = baseProcessService.updateDeployWorkFlow(zipFile, flow.getFlowId());
        if (null != deployment) {
            flow.setFlowCode(deployment.getId());
            flow.setFlowBpmn(deployment.getName());
            flow.setFlowStatus((byte) 1);
            sysFlowDao.saveOrUpdate(flow);
        }
    }


    @Override
    public List<OurwaySysWorkflow> listMyWorkFlow(OurwaySysEmploys employs) {
        List<Task> tasks = baseProcessService.listAllTask(employs);
        List<OurwaySysWorkflow> workflows = new ArrayList<OurwaySysWorkflow>(tasks.size());
        for (Task task : tasks) {
            //Map<String,Object> map = baseProcessService.doGetTaskAttribute(task);
            Map<String, Object> map = new HashMap<String, Object>(1);
            map.put("flowInstanceId", task.getProcessInstanceId());
            OurwaySysWorkflow workflow = sysWorkflowDao.getOneByParams(map, "");
            workflow.setTaskFromKey(task.getFormKey());
            workflow.setTaskId(task.getId());
            workflow.setTaskName(task.getName());
            workflows.add(workflow);
        }
        return workflows;
    }

    @Override
    public List<OurwaySysFlow> listAllWorkFlow() {
        return sysFlowDao.listAll();
    }

    @Override
    public boolean doCheckAuditPrivs(String owid, String className) {
        OurwaySysEmploys employs = ShiroUtilsClient.getUserEntity();
        Map<String, Object> params = new HashMap<String, Object>();
        //1.判断是否有审批结点
        OurwaySysWorkflow workflow = sysWorkflowDao.listCurrentNode(owid, className);
        if (null == workflow)
            return false;
        //2.判断是否在当前权限内
        OurwaySysFlownode node = sysFlowNodePerDao.listOneFlowNode(workflow.getFlowDeploymentId(), workflow.getTaskKey());
        if (null != node.getNodeIsCreator() && node.getNodeIsCreator() == 1) {
            //表示允许创建人自己审核
            //判断是否是创建者是自己
            boolean flag = sysFlowNodePerDao.doCheckSelfPrivs(employs, owid, className);
            if (flag)
                return true;
        }
        OurwaySysFlownodePer nodePer = sysFlowNodePerDao.listOneFlowPer(employs.getOwid(), workflow.getFlowDeploymentId(), workflow.getTaskKey());
        if (null == nodePer)
            return false;
        //3.判断过滤
        boolean flag = sysFlowNodePerDao.doCheckPrivs(employs, nodePer, owid, className);
        return flag;

    }


    @Override
    public List<OurwaySysWorkflow> listFlowHistory(String owid, String className) {
        return sysWorkflowDao.listFlowHistory(owid, className);
    }

    @Override
    public OurwaySysWorkflow listCurrNode(String owid, String className) {
        OurwaySysWorkflow workflow = sysWorkflowDao.listCurrentNode(owid, className);
        return workflow;
    }

    @Override
    public OurwaySysWorkflow listCurrNode(String owid, String className, String taskId) {
        return sysWorkflowDao.listCurrentNode(owid, className, taskId);
    }

    @Override
    public void removeWorkFlow(String owid, String className) {
        List<OurwaySysWorkflow> flowList = sysWorkflowDao.listFlowHistory(owid, className);
        for (OurwaySysWorkflow workflow : flowList) {
            try {
                baseProcessService.removeTask(workflow.getTaskProcessId());
            } catch (Exception e) {

            }
            sysWorkflowDao.removeEntity(workflow);
        }
    }
}
