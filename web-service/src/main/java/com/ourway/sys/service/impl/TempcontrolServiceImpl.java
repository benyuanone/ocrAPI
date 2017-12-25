package com.ourway.sys.service.impl;

import com.ourway.sys.dao.SysTempcontrolDao;
import com.ourway.sys.model.OurwaySysTempcontrol;
import com.ourway.sys.service.TempcontrolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**<p>接口 TempcontrolService.java : <p>
 *<p>说明：插件控制</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 14:39
</pre>
 */
@Service("tempcontrolService")
public class TempcontrolServiceImpl implements TempcontrolService {
    @Autowired
    SysTempcontrolDao sysTempcontrolDao;
    @Override
    public List<OurwaySysTempcontrol> listAllByMap(Map<String, Object> params, String orderStr) {
        return listAllByMap(params,orderStr);
    }

    @Override
    public List<OurwaySysTempcontrol> listTempControlByOwid(String templateId) {
        return sysTempcontrolDao.listAllByTemplateId(templateId);
    }
}
