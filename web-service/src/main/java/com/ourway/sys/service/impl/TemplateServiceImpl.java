package com.ourway.sys.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.sys.dao.SysDicDao;
import com.ourway.sys.dao.SysPageDao;
import com.ourway.sys.dao.SysTempcontrolDao;
import com.ourway.sys.dao.SysTemplateDao;
import com.ourway.sys.model.OurwaySysPage;
import com.ourway.sys.model.OurwaySysTempcontrol;
import com.ourway.sys.model.OurwaySysTemplate;
import com.ourway.sys.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>接口 TemplateService.java : <p>
 * <p>说明：页面模板</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 14:39
 * </pre>
 */
@Service("templateService")
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    SysTemplateDao sysTemplateDao;
    @Autowired
    SysTempcontrolDao sysTempcontrolDao;
    @Autowired
    SysPageDao sysPageDao;
    @Autowired
    SysDicDao sysDicDao;

    @Override
    public List<OurwaySysTemplate> listAllTemplate(HqlStatement hqlStatement) {
        return sysTemplateDao.listAllByHql(hqlStatement.getHql(), hqlStatement.getParams());
    }

    @Override
    public OurwaySysTemplate detailTemplate(String templateId) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("owid", templateId);
        HqlStatement hql = new HqlStatement(OurwaySysTemplate.class, params);
        OurwaySysTemplate template = sysTemplateDao.getOneByHql(hql.getHql(), hql.getParams());
        if (null == template)
            return null;
        //获取
        List<OurwaySysTempcontrol> controls = sysTempcontrolDao.listAllByTemplateId(templateId);
        template.setDataList(controls);
        return template;
    }

    @Override
    public PageInfo<OurwaySysTemplate> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        return sysTemplateDao.listHqlForPage(filters, pageNo, pageSize, sortStr);
    }

    @Override
    public List<Map<String, Object>> removeItems(List<String> owids) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();
        for (String owid : owids) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            params.put("pageTemplate", owid);
            OurwaySysPage ourwaySysPage = sysPageDao.getOneByParams( params, "");
            if (null != ourwaySysPage) {
                params = new HashMap<String, Object>(1);
                params.put("tempRefOwid", owid);
                sysTempcontrolDao.removeByParams( params);
                params = new HashMap<String, Object>(1);
                params.put("owid", owid);
                sysTemplateDao.removeByParams(params);
                objs.add(params);
            }
            sysPageDao.removeEntity(ourwaySysPage);
        }
        return objs;
    }

    @Override
    public OurwaySysTemplate saveOrUpdate(OurwaySysTemplate template, List<OurwaySysTempcontrol> tempcontrolList) {
        sysTemplateDao.saveOrUpdate(template);
        String owids = "";
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("tempRefOwid", template.getOwid());
        if (null != tempcontrolList && tempcontrolList.size() > 0)
            for (OurwaySysTempcontrol ourwaySysTempcontrol : tempcontrolList) {
                if (null == ourwaySysTempcontrol.getUpdateFlag())
                    ourwaySysTempcontrol.setUpdateFlag(0);
                switch (ourwaySysTempcontrol.getUpdateFlag()) {
                    case 0:
//                    ourwaySysTempcontrol.setTempRefOwid(template.getOwid());
//                    sysTempcontrolDao.save(ourwaySysTempcontrol);
//                    template.getDataList().add(ourwaySysTempcontrol);
                        break;
                    case 1:
                        ourwaySysTempcontrol.setTempRefOwid(template.getOwid());
                        if (TextUtils.isEmpty(ourwaySysTempcontrol.getOwid()))
                            sysTempcontrolDao.save(ourwaySysTempcontrol);
                        else
                            sysTempcontrolDao.update(ourwaySysTempcontrol);
                        template.getDataList().add(ourwaySysTempcontrol);
                        break;
                    case 2:
                        sysTempcontrolDao.removeByIds(ourwaySysTempcontrol.getOwid());
                        break;
                }
            }

        return template;
    }

    private OurwaySysTempcontrol isInList(List<OurwaySysTempcontrol> controls, String owid) {
        for (OurwaySysTempcontrol control : controls) {
            if (control.getOwid().equals(owid))
                return control;
        }
        return null;
    }

}
