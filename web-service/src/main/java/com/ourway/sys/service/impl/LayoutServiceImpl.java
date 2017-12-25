package com.ourway.sys.service.impl;

import com.ourway.sys.dao.SysLayoutDao;
import com.ourway.sys.dao.SysPageCompDao;
import com.ourway.sys.dao.SysPageDao;
import com.ourway.sys.model.OurwaySysLayout;
import com.ourway.sys.model.OurwaySysPage;
import com.ourway.sys.model.OurwaySysPageComponent;
import com.ourway.sys.service.LayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 LayoutService.java : <p>
 * <p>说明：</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 14:37
 * </pre>
 */
@Service("layoutService")
public class LayoutServiceImpl implements LayoutService {
    @Autowired
    SysLayoutDao sysLayoutDao;
    @Autowired
    SysPageCompDao sysPageCompDao;
    @Autowired
    SysPageDao sysPageDao;

    @Override
    public OurwaySysLayout queryOneByPageCaAndControlId(String pageCa, String controlId) {
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("pageCa", pageCa);
        OurwaySysPage page = sysPageDao.getOneByParams(map, "");
//        "from OurwaySysPage where pageCa=?",pageCa);
        if (null == page)
            return null;
        map = new HashMap<String, Object>(2);
        map.put("pageRefOwid", page.getOwid());
        map.put("controlId", controlId);
        OurwaySysLayout layout = sysLayoutDao.getOneByParams(map, "");
        if (null == layout)
            return null;
        List<OurwaySysPageComponent> componentList = sysPageCompDao.listComponentByControl(layout);
        layout.setLayOutComponents(doHandle(componentList));
        return layout;
    }

    @Override
    public List<OurwaySysLayout> queryAllTabPanelLayouts(String pageCa) {
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("pageCa", pageCa);
        OurwaySysPage page = sysPageDao.getOneByParams(map, "");
//        "from OurwaySysPage where pageCa=?",pageCa);
        if (null == page)
            return null;
        map = new HashMap<String, Object>(2);
        map.put("pageRefOwid", page.getOwid());
        map.put("controlType", -1);
        List<OurwaySysLayout> layouts = sysLayoutDao.listAllByParam(map, "");
        return layouts;
    }

    @Override
    public List<OurwaySysLayout> queryAllDataListLayouts(String pageCa, String startComp) {
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("pageCa", pageCa);
        OurwaySysPage page = sysPageDao.getOneByParams(map, "");
//        "from OurwaySysPage where pageCa=?",pageCa);
        if (null == page)
            return null;
        List<OurwaySysLayout> layouts = sysLayoutDao.listAllDataListByCompid(page, startComp);
        for(OurwaySysLayout layout:layouts){
            List<OurwaySysPageComponent> componentList = sysPageCompDao.listComponentByControl(layout);
            layout.setLayOutComponents(doHandle(componentList));
        }

        return layouts;
    }

    //工具类，处理layout和component的关系。
    private Map<Integer, List<OurwaySysPageComponent>> doHandle(List<OurwaySysPageComponent> componentList) {
        Map<Integer, List<OurwaySysPageComponent>> compMap = new HashMap<Integer, List<OurwaySysPageComponent>>();

        for (OurwaySysPageComponent comp : componentList) {
            List<OurwaySysPageComponent> pageComponents = new ArrayList<OurwaySysPageComponent>();
            if (null == comp.getLayoutComponent().getCompStartRow())
                comp.getLayoutComponent().setCompStartRow((byte) 1);
            if (null != compMap.get(comp.getLayoutComponent().getCompStartRow().intValue())) {
                pageComponents = compMap.get(comp.getLayoutComponent().getCompStartRow().intValue());
            }
            pageComponents.add(comp);
            compMap.put(comp.getLayoutComponent().getCompStartRow().intValue(), pageComponents);
        }
        return compMap;
    }


    @Override
    public List<OurwaySysLayout> listAllByPage(OurwaySysPage page) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("pageRefOwid", page.getOwid());
        return sysLayoutDao.listAllByParam(params, "controlId");
    }
}
