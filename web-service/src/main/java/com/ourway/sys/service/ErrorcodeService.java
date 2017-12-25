package com.ourway.sys.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.sys.model.OurwaySysErrorcode;

import java.util.List;
import java.util.Map;

/**<p>接口 ErrorcodeService.java : <p>
 *<p>说明：错误编码</p>
 *<pre>
 *@author cc
 *@date 2017/4/12
</pre>
 */
public interface ErrorcodeService {

    /*
    *<p>功能描述：doCheckUniqueCode  判断Code唯一性</p >
    *<ul>
    *<li>@param [errorCode]</li>
    *<li>@return Boolean</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/4/24 0024 上午 9:20</li>
    *</ul>
    */
    Boolean doCheckUniqueCode(OurwaySysErrorcode errorCode);


    /**
     *<p>功能描述：saveOrUpdateErrorCode 保存或者更新系统异常</p >
     *<ul>
     *<li>@param [errorCode]</li>
     *<li>@return void</li>
     *<li>@throws </li>
     *<li>@author xuby</li>
     *<li>@date 2017/4/19 0019 下午 6:32</li>
     *</ul>
     */
    void saveOrUpdateErrorCode(OurwaySysErrorcode errorCode);


    /**
     *<p>功能描述：listErrorCodeByPage 分页查询</p >
     *<ul>
     *<li> @param hqlStatement 分页自组装对象</li>
     *<li> @param pageNo 当前页面</li>
     *<li> @param pageSize 每页显示数</li>
     *<li>@return PageInfo<com.ourway.web.model.OurwaySysErrorcode></li>
     *<li>@throws </li>
     *<li>@author xuby</li>
     *<li>@date 2017/4/19 0019 下午 6:39</li>
     *</ul>
     */
    PageInfo<OurwaySysErrorcode> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize);



    /*
    *<p>功能描述：removeItems  根据owid 批量删除 数据</p >
    *<ul>
    *<li>@param [owids]</li>
    *<li>@return java.util.List<java.util.Map<java.lang.String,java.lang.Object>></li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/5/29 0029 下午 2:17</li>
    *</ul>
    */
    List<Map<String,Object>> removeItems(List<String> owids);

    /*
    *<p>功能描述：detailOneErrorCode</p >
    *<ul>
    *<li>@param [owid]</li>
    *<li>@return java.lang.Object</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/5/29 0029 下午 4:00</li>
    *</ul>
    */
    OurwaySysErrorcode detailOneErrorCode(String owid);
}
