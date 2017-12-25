package com.ourway.sys.dao;

import com.ourway.base.model.UUidEntity;
import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysLayout;
import com.ourway.sys.model.OurwaySysPage;

import java.util.List;

/**<p>接口 SysLayoutDao.java : <p>
 *<p>说明：</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:43
</pre>
 */
public interface SysLayoutDao extends BaseService<OurwaySysLayout> {


    void  removeLayoutByPage(UUidEntity page); //OurwaySysPage

    List<OurwaySysLayout> listAllDataListByCompid(OurwaySysPage page,String compId);

}
