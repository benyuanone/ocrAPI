package com.ourway.sys.service;

import com.ourway.sys.model.OurwaySysPrivsuser;

import java.util.List;

/**<p>接口 PrivsuserService.java : <p>
 *<p>说明：权限分配</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 14:38
</pre>
 */
public interface PrivsuserService {
    boolean saveUserPrivs(String roleId, List<String> selEmps);

    List<OurwaySysPrivsuser> listByUserOwid(String owid);

}
