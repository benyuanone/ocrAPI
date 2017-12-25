package com.ourway.sys.dao;

import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysWorkflow;

import java.util.List;


public interface SysWorkflowDao extends BaseService<OurwaySysWorkflow> {

    //获取最近的审批节点
    OurwaySysWorkflow listCurrentNode(String owid, String className);

    OurwaySysWorkflow listCurrentNode(String owid, String className, String taskId);

    //获取工作流历史
    List<OurwaySysWorkflow> listFlowHistory(String owid, String className);

    //处理获取流程
    boolean updateFlowStatues(String operateId, String operateName, OurwaySysWorkflow workflow);

    //审核流程，并发
    boolean updateAuditStatus(String comment, OurwaySysWorkflow workflow, int status);

    //更新对象的状态
    void updateObjectState(String owid,String className,String state);

}
