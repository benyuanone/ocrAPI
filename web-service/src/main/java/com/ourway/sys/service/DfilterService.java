package com.ourway.sys.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.sys.model.OurwaySysDfilter;

import java.util.List;
import java.util.Map;

/**
 * <p>接口 DfilterService.java : <p>
 * <p>说明：数据过滤</p>
 * <pre>
 * @author cc
 * @date 14:36 2017/4/12
 * </pre>
 */
public interface DfilterService {

    /*
    *<p>功能描述：listDfilterForPage  分页显示数据过滤信息</p >
    *<ul>
    *<li>@param [flist]</li>
    *<li>@return com.ourway.base.model.ResponseMessage</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/6/2 0002 下午 1:47</li>
    *</ul>
    */
    PageInfo<OurwaySysDfilter> listDfilterForPage(List<FilterModel> flist, Integer pageNo, Integer pageSize,String sortStr);

    /*
    *<p>功能描述：removeItems 根据owid删除 </p >
    *<ul>
    *<li>@param [owids]</li>
    *<li>@return java.util.List<java.util.Map<java.lang.String,java.lang.Object>></li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/6/3 0003 下午 3:50</li>
    *</ul>
    */
    List<Map<String, Object>> removeItems(List<String> owids);

    /*
    *<p>功能描述：detailOneDfilter 根据owid查询记录</p >
    *<ul>
    *<li>@param [owid]</li>
    *<li>@return com.ourway.sys.model.OurwaySysDfilter</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/6/4 0004 下午 11:05</li>
    *</ul>
    */
    OurwaySysDfilter detailOneDfilter(String owid);

    /*
    *<p>功能描述：saveOrUpdate  保存数据</p >
    *<ul>
    *<li>@param [ourwaySysDfilter]</li>
    *<li>@return void</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/6/12 0012 上午 6:47</li>
    *</ul>
    */
    void saveOrUpdate(OurwaySysDfilter ourwaySysDfilter);

    List<OurwaySysDfilter> listByUserOwid(String owid);

    //判断是否有重复的api
    boolean doCheckSameUserApi(OurwaySysDfilter ourwaySysDfilter);

    //组织过滤条件，获取当前用户的过滤条件
    String doComposeFilter(String pageCa,int HqlType);
}
