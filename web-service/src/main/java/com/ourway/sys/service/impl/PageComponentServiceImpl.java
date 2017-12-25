package com.ourway.sys.service.impl;

import com.ourway.sys.dao.SysPageCompDao;
import com.ourway.sys.model.OurwaySysPage;
import com.ourway.sys.model.OurwaySysPageComponent;
import com.ourway.sys.service.PageComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>接口 PageComponentService.java : <p>
 * <p>说明：</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 14:37
 * </pre>
 */
@Service("pageComponentService")
public class PageComponentServiceImpl implements PageComponentService {
    @Autowired
    SysPageCompDao sysPageCompDao;

    @Override
    public List<OurwaySysPageComponent> listComponenetsByPage(OurwaySysPage page) {
        if (null == page)
            return null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageRefOwid", page.getOwid());
        return sysPageCompDao.listAllByParam(map," kjOrder ");
    }
}
