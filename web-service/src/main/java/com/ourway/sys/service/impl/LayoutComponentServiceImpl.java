package com.ourway.sys.service.impl;

import com.ourway.sys.dao.SysLayoutCompDao;
import com.ourway.sys.model.OurwaySysLayout;
import com.ourway.sys.model.OurwaySysLayoutComponent;
import com.ourway.sys.service.LayoutComponentService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>接口 LayoutComponentService.java : <p>
 * <p>说明：</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 14:37
 * </pre>
 */
@Service("layoutComponent")
public class LayoutComponentServiceImpl implements LayoutComponentService {
    @Autowired
    SysLayoutCompDao sysLayoutCompDao;

    @Override
    public List<OurwaySysLayoutComponent> listAllByParams(Map<String, Object> params, String sortStr) {
        return sysLayoutCompDao.listAllByParam(params,sortStr);
    }

    @Override
    public List<OurwaySysLayoutComponent> listAllByParams(OurwaySysLayout layout) {
        Map<String, Object> params = new HashMap<String,Object>(1);
        params.put("layoutRefOwid",layout.getOwid());
        return sysLayoutCompDao.listAllByParam(params,"compId");
    }
}
