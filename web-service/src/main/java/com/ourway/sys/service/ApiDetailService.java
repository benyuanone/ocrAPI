package com.ourway.sys.service;

import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.model.OurwaySysApiDetail;

import java.util.List;

/**<p>接口 ApiDetailService.java : <p>
 *<p>说明：接口详细类</p>
 *<pre>
 *@author cc
 *@date 14:35 2017/4/12
</pre>
 */
public interface ApiDetailService {

    /*
    *<p>功能描述：removeApiDetail  删除apidetail 记录</p >
    *<ul>
    *<li>@param [hqlStatement]</li>
    *<li>@return void</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/4/24 0024 下午 8:02</li>
    *</ul>
    */
    void removeApiDetail(HqlStatement hqlStatement);



    /*
    *<p>功能描述：saveOrUpdateApiDetail</p >
    *<ul>
    *<li>@param [ourwaySysApiDetail]</li>
    *<li>@return void</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/4/25 0025 上午 9:52</li>
    *</ul>
    */
    void saveOrUpdateApiDetail(OurwaySysApiDetail ourwaySysApiDetail);

    /**
     *<p>功能描述:根据owid 查询数据</p >
     *<ul>
     *<li>@param [owid]</li>
     *<li>@return java.util.List<com.ourway.sys.model.OurwaySysApiDetail></li>
     *<li>@throws </li>
     *<li>@author xuby</li>
     *<li>@date 2017/7/11 0011 下午 5:11</li>
     *</ul>
    */
    List<OurwaySysApiDetail> listAllApiDetailByOwid(String owid,byte type);
}
