package com.ourway.sys.dao;

import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysQuickmenu;

/**<p>接口 SysQuickMenuDao.java : <p>
 *<p>说明：</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:43
</pre>
 */
public interface SysQuickMenuDao extends BaseService<OurwaySysQuickmenu> {

    void removeByUserOwid(String owid);
}
