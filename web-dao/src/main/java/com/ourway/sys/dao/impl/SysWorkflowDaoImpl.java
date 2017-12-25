package com.ourway.sys.dao.impl;

import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.TextUtils;
import com.ourway.sys.dao.SysWorkflowDao;
import com.ourway.sys.model.OurwaySysWorkflow;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository("sysWorkflowDao")
public class SysWorkflowDaoImpl extends BaseServiceImpl<OurwaySysWorkflow> implements SysWorkflowDao {

    @Override
    public OurwaySysWorkflow listCurrentNode(String owid, String className) {
        String hql = " from OurwaySysWorkflow where businessId=:businessId and className=:className order by taskTime desc";
        Map<String, Object> param = new HashMap<String, Object>(2);
        param.put("businessId", owid);
        param.put("className", className);
        List<OurwaySysWorkflow> flowList = listAllByHql(hql, param);
        if (null != flowList && flowList.size() > 0)
            return flowList.get(0);

        return null;
    }

    @Override
    public OurwaySysWorkflow listCurrentNode(String owid, String className, String taskId) {
        String hql = " from OurwaySysWorkflow where businessId=:businessId and className=:className and taskId=:taskId order by taskTime desc";
        Map<String, Object> param = new HashMap<String, Object>(2);
        param.put("businessId", owid);
        param.put("className", className);
        param.put("taskId", taskId);
        List<OurwaySysWorkflow> flowList = listAllByHql(hql, param);
        if (null != flowList && flowList.size() > 0)
            return flowList.get(0);
        return null;
    }

    @Override
    public List<OurwaySysWorkflow> listFlowHistory(String owid, String className) {
        String hql = " from OurwaySysWorkflow where businessId=:businessId and className=:className order by taskTime desc";
        Map<String, Object> param = new HashMap<String, Object>(2);
        param.put("businessId", owid);
        param.put("className", className);
        List<OurwaySysWorkflow> flowList = listAllByHql(hql, param);
        return flowList;
    }

    @Override
    public boolean updateFlowStatues(String operateId, String operateName, OurwaySysWorkflow workflow) {
//        String sql = "update OURWAY_SYS_WORKFLOW set TASK_PER_ID='" + operateId + "',TASK_PER_NAME='" + operateName + "' where VER=" + workflow.getVer() + " and OWID='" + workflow.getOwid() + "'";
        String sql = "update OURWAY_SYS_WORKFLOW set TASK_PER_ID='" + operateId + "',TASK_PER_NAME='" + operateName + "' where  OWID='" + workflow.getOwid() + "'";
        Integer rows = updateSqlByParams(sql, null);
        if (rows > 0)
            return true;
        return false;
    }

    @Override
    public boolean updateAuditStatus(String comment, OurwaySysWorkflow workflow, int status) {
        String result = "";
        if (status == 1)
            result = "yes";
        else
            result = "no";
        String sql = "update OURWAY_SYS_WORKFLOW set STATE=" + status + ",LASTUPDATE=now(),TASK_MEMO='" + comment + "',TASK_RESULT='" + result + "' where VER=" + workflow.getVer() + " and OWID='" + workflow.getOwid() + "'";
        Integer rows = updateSqlByParams(sql, null);
        if (rows > 0)
            return true;
        return false;
    }

    @Override
    public void updateObjectState(String owid, String className, String state) {
        if (TextUtils.isEmpty(state))
            return;
        String hql = " update " + className + " set state=:state,lasupdate=:lasupdate where owid=:owid";
        Map<String, Object> params = new HashMap<String, Object>(3);
        params.put("state", Integer.parseInt(state));
        params.put("lasupdate", new java.util.Date());
        params.put("owid", owid);
        updateBulk(hql, params);
    }
}
