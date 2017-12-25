package com.ourway.sys.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.BaseTree;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.model.OurwaySysApi;
import com.ourway.sys.model.OurwaySysApiDetail;

import java.util.List;
import java.util.Map;

/**<p>接口 ApiService.java : <p>
 *<p>说明：接口类</p>
 *<pre>
 *@author cc
 *@date 14:35 2017/4/12
</pre>
 */
public interface ApiService {

    /*
    *<p>功能描述：doCheckUniqueUrl 检查url唯一性/p >
    *<ul>
    *<li>@param [ourwaySysApi]</li>
    *<li>@return java.lang.Boolean</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/4/24 0024 下午 1:50</li>
    *</ul>
    */
    Boolean doCheckUniqueUrl(OurwaySysApi ourwaySysApi);


    /*
    *<p>功能描述：savOrUpdateApi 保存或者修改</p >
    *<ul>
    *<li>@param [ourwaySysApi]</li>
    *<li>@return void</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/4/24 0024 下午 1:53</li>
    *</ul>
    */
    OurwaySysApi  saveOrUpdateApi(OurwaySysApi ourwaySysApi, List<OurwaySysApiDetail> inList,List<OurwaySysApiDetail> outList);


    /*
    *<p>功能描述：removeApiByIds 根据owid 删除</p >
    *<ul>
    *<li>@param [hqlStatement]</li>
    *<li>@return void</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/4/24 0024 下午 3:04</li>
    *</ul>
    */
    List<Map<String,Object>> removeApiByIds(List<String> list);


    /*
    *<p>功能描述：listApiByStatement  </p >
    *<ul>
    *<li>@param [hqlStatement]</li>
    *<li>@return java.util.List<com.ourway.sys.model.OurwaySysApi></li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/4/24 0024 下午 4:43</li>
    *</ul>
    */
    List<OurwaySysApi> listApiByStatement(HqlStatement hqlStatement);


    /*
    *<p>功能描述：listApiForPage  分页显示 api</p >
    *<ul>
    *<li>@param [hqlStatement]</li>
    *<li>@return com.ourway.boss.base.dataobject.PageInfo<com.ourway.sys.model.OurwaySysApi></li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/4/24 0024 下午 5:26</li>
    *</ul>
    */
    PageInfo<OurwaySysApi> listApiForPage(List<FilterModel> filterModelList, Integer pageNo, Integer pageSize);

    /*
    *<p>功能描述：detailApi  根据owid查询 </p >
    *<ul>
    *<li>@param [owid]</li>
    *<li>@return java.lang.Object</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/5/31 0031 上午 11:00</li>
    *</ul>
    */
    OurwaySysApi detailApi(String owid);


    List<BaseTree> listApiTree(List<FilterModel> models);
}
