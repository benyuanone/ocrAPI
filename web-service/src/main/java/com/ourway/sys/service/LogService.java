package com.ourway.sys.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.OurwaySysLog;
import com.ourway.sys.model.OurwaySysObjectAttribute;

import java.util.List;
import java.util.Map;

/**
 * <p>接口 LogService.java : <p>
 * <p>说明：日志</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 14:37
 * </pre>
 */
public interface LogService {

    /**
     * <p>功能描述：saveOrUpdateLog 保存或者更新业务类</p>
     * <ul>
     * <li>@param [object]</li>
     * <li>@return void</li>
     * <li>@throws </li>
     * <li>@author jackson</li>
     * <li>@date 17-4-27 下午1:59</li>
     * </ul>
     */
    void saveOrUpdateLog(OurwaySysLog object);


    /**
     * <p>功能描述：listHqlForPage 分页获取对象数据</p>
     * <ul>
     * <li>@param [hql, pageNo, pageSize]</li>
     * <li>@return com.ourway.base.dataobject.PageInfo<com.ourway.sys.model.OurwaySysObject></li>
     * <li>@throws </li>
     * <li>@author jackson</li>
     * <li>@date 17-4-27 下午3:25</li>
     * </ul>
     */
    PageInfo<OurwaySysLog> listHqlForPage(List<FilterModel> models, int pageNo, int pageSize, String sortStr);

    List<Map<String, Object>> removeObjects(List<String> owids);

    void removeAll();

    List<OurwaySysObjectAttribute> listLogAttrByClassName(String className);

    Map<String,List<OurwaySysObjectAttribute>> listRefLogAttributeByOwid(String owid);

    List<OurwaySysLog> listLogsByOwid(String owid);

    /**
    *<p>方法:listLogsByOwidAndLabel 根据标签和主键id返回相关的子表数据 </p>
    *<ul>
     *<li> @param owid TODO</li>
     *<li> @param label TODO</li>
    *<li>@return java.util.List<com.ourway.base.model.OurwaySysLog>  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/12/23 12:30  </li>
    *</ul>
    */
    Map<String, List<OurwaySysLog>> listLogsByOwidAndLabel(String owid,String label);

    Map<String,List<OurwaySysLog>> listRefLogsByOwid(String owid);


}