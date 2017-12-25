package com.ourway.sys.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.model.OurwaySysEmploys;
import com.ourway.sys.model.OurwaySysFlow;
import com.ourway.sys.model.OurwaySysFlowClass;
import com.ourway.sys.model.OurwaySysWorkflow;

import java.util.List;
import java.util.Map;

/**<p>接口 FlowService.java : <p>
 *<p>说明：功能流</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 14:36
</pre>
 */
public interface FlowService {

    PageInfo<OurwaySysFlow> listHqlForPage(List<FilterModel> filterModels, int pageNo, int pageSize);

    public List<Map<String, Object>> removeFlow(List<String> owids);

    OurwaySysFlow listByParams(Map<String,Object> params);

    void saveOrUpdate(OurwaySysFlow ourwaySysFlow, List<OurwaySysFlowClass> flowClasses);

    void updateDeployFlow(String owid,String zipFile);

    List<OurwaySysWorkflow> listMyWorkFlow(OurwaySysEmploys employs);

    List<OurwaySysFlow> listAllWorkFlow();

    //判断是否有审批权限
    boolean doCheckAuditPrivs(String owid,String className);

    //获取历史
    List<OurwaySysWorkflow> listFlowHistory(String owid,String className);

    OurwaySysWorkflow listCurrNode(String owid,String className);

    OurwaySysWorkflow listCurrNode(String owid,String className,String taskId);

    //删除节点
    void removeWorkFlow(String owid,String className);

}
