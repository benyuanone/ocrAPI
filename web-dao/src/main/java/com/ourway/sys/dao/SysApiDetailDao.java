package com.ourway.sys.dao;

import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysApi;
import com.ourway.sys.model.OurwaySysApiDetail;

import java.util.List;

/**<p>接口 SysApiDetailDao.java : <p>
 *<p>说明：</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:41
</pre>
 */
public interface SysApiDetailDao extends BaseService<OurwaySysApiDetail> {
    /*
    *<p>功能描述：removeApiDetail  根据关联的owid 查询 然后在删除</p >
    *<ul>
    *<li>@param [owid]</li>
    *<li>@return void</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/5/31 0031 上午 11:01</li>
    *</ul>
    */
    void removeApiDetail(String owid);

    /**
    *<p>功能描述：listAllByAPi  根据主表的owid 查询子表的信息</p >
    *<ul>
    *<li>@param [oapi]</li>
    *<li>@return java.util.List<com.ourway.sys.model.OurwaySysApiDetail></li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/5/31 0031 上午 11:12</li>
    *</ul>
    */
    List<OurwaySysApiDetail> listAllByAPi(OurwaySysApi oapi);
}
