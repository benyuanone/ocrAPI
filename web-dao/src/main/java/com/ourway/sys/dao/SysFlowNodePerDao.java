package com.ourway.sys.dao;

import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysEmploys;
import com.ourway.sys.model.OurwaySysFlownode;
import com.ourway.sys.model.OurwaySysFlownodePer;

import java.util.List;

/**
 * <p>接口 SysFlowDao.java : <p>
 * <p>说明：</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:42
 * </pre>
 */
public interface SysFlowNodePerDao extends BaseService<OurwaySysFlownodePer> {

    List<OurwaySysFlownodePer> listAllPerByNode(OurwaySysFlownode flownode);

    //传入用户id，获取权限配置信息
    OurwaySysFlownodePer listOneFlowPer(String empId,String flowId,String nodeId);

    //获取flowNode
    OurwaySysFlownode listOneFlowNode(String flowId,String nodeId);

    //根据主键和过滤规则判断是否有权限审批
    boolean doCheckPrivs(OurwaySysEmploys employs,OurwaySysFlownodePer per, String owid, String className);

    //判断是否是自己创建的
    boolean doCheckSelfPrivs(OurwaySysEmploys employs,String owid,String className);

    /*获取指定节点发送信息的员工号*/
    List<OurwaySysEmploys> listAllEmploys(String flowid, String nodeId);
    
}
