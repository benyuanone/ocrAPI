package com.ourway.sys.service;

import com.ourway.sys.model.OurwaySysPrivsallocate;

import java.util.List;
import java.util.Map;

/**<p>接口 PrivsallocateService.java : <p>
 *<p>说明：</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 14:38
</pre>
 */
public interface PrivsallocateService {
    List<OurwaySysPrivsallocate> getSelMenus(Map<String, Object> dataMap);

    boolean saveAllocatePrivs(String roleId, List<String> selEmps);

    List<OurwaySysPrivsallocate> listMenuAllocateByRoleOwid(String owid);
}
