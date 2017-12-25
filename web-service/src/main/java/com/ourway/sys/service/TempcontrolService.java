package com.ourway.sys.service;

import com.ourway.sys.model.OurwaySysTempcontrol;

import java.util.List;
import java.util.Map;

/**<p>接口 TempcontrolService.java : <p>
 *<p>说明：插件控制</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 14:39
</pre>
 */
public interface TempcontrolService {
    List<OurwaySysTempcontrol> listAllByMap(Map<String,Object> params, String orderStr);

    List<OurwaySysTempcontrol> listTempControlByOwid(String templateId);
}
