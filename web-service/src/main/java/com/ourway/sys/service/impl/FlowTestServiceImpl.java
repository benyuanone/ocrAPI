package com.ourway.sys.service.impl;


import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.sys.dao.SysFlowDao;
import com.ourway.sys.dao.SysFormDao;
import com.ourway.sys.dao.SysHistoryFormDao;
import com.ourway.sys.dao.SysWorkflowDao;
import com.ourway.sys.model.OurwaySysEmploys;
import com.ourway.sys.model.OurwaySysForm;
import com.ourway.sys.model.OurwaySysWorkflow;
import com.ourway.sys.service.BaseProcessService;
import com.ourway.sys.service.FlowTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 CssService.java : <p>
 * <p>说明：css样式</p>
 * <pre>
 * @author cc
 * @date 14:36 2017/4/12
 * </pre>
 */
@Service("flowTestService")
public class FlowTestServiceImpl implements FlowTestService {

    @Autowired
    SysFormDao sysFormDao;
    @Autowired
    SysHistoryFormDao sysHistoryFormDao;
    @Autowired
    BaseProcessService baseProcessService;
    @Autowired
    SysFlowDao sysFlowDao;
    @Autowired
    SysWorkflowDao sysWorkflowDao;


    @Override
    public PageInfo<OurwaySysForm> listHqlForPage(List<FilterModel> filterModels, int pageNo, int pageSize) {
        PageInfo<OurwaySysForm> forms = sysFormDao.listFowByPage(filterModels, pageNo, pageSize);
        Map<String, Object> nodeMap = new HashMap<String, Object>();
        if (null != forms && null != forms.getRecords() && forms.getRecords().size() > 0) {
            for (OurwaySysForm form : forms.getRecords()) {
                nodeMap = new HashMap<String, Object>();
                Map<String, Object> data = new HashMap<String, Object>(1);
                data.put("className", "com.ourway.sys.model.OurwaySysForm");
                data.put("businessId", form.getOwid());
                List<OurwaySysWorkflow> historyforms = sysWorkflowDao.listAllByParam(data, " createtime desc ");
                if (null != historyforms && historyforms.size() > 0) {
                    OurwaySysWorkflow flow = historyforms.get(0);
                    nodeMap.put("nextLevel", flow.getTaskName());
                    nodeMap.put("nextPer", flow.getTaskAssign());
                    nodeMap.put("nextTime", flow.getTaskTime());
                    if (historyforms.size() > 1) {
                        flow = historyforms.get(1);
                        nodeMap.put("preLevel", flow.getTaskName());
                        nodeMap.put("prePer", flow.getTaskPerName());
                        nodeMap.put("preResult", flow.getTaskResult());
                        nodeMap.put("preResult", flow.getTaskResult());
                        nodeMap.put("preMemo", flow.getTaskMemo());
                        nodeMap.put("preTime", flow.getTaskTime());
                    }
                    form.setNodeMap(nodeMap);
                }

            }
        }

        return forms;
    }

    @Override
    public OurwaySysForm listOneFormByOwid(String owid) {
        OurwaySysForm form = sysFormDao.getOneById(owid);

        return form;
    }

    @Override
    public OurwaySysWorkflow updateStartFlow(String owid) {
        OurwaySysForm form = sysFormDao.getOneById(owid);
        if (null != form.getStatus() && form.getStatus() > 0)
            return null;
        form.setState(1);
        sysFormDao.saveOrUpdate(form);
        //启动工作流
        OurwaySysWorkflow workflow = new OurwaySysWorkflow();
        workflow.setBusinessId(form.getOwid());
        workflow.setClassName("com.ourway.sys.model.OurwaySysForm");
        workflow.setFlowName(form.getContent());
        workflow.setFlowOwner(form.getCreator());
        workflow.setFlowType("测试");
        baseProcessService.startWorkFlow(form, workflow);
        return workflow;
    }

    @Override
    public void updateFlow(OurwaySysEmploys employs, OurwaySysForm form) {

        sysFormDao.saveOrUpdate(form);

    }

    @Override
    public void removeForm(OurwaySysForm form) {
        sysFormDao.removeEntity(form);
    }
}
