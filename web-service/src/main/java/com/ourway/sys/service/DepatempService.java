package com.ourway.sys.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.model.OurwaySysDepatemp;

import java.util.List;
import java.util.Map;

/**
 * <p>接口 DepatempService.java : <p>
 * <p>说明：用户部门</p>
 * <pre>
 * @author cc
 * @date 14:36 2017/4/12
 * </pre>
 */
public interface DepatempService {
    boolean doCheckUniqueLabel(OurwaySysDepatemp depatemp);

    void saveOrUpdateDepemp(OurwaySysDepatemp depatemp);


    void removeByIds(HqlStatement hql);

    PageInfo<OurwaySysDepatemp> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sort);

    Object detailOneEmp(String owid);


    List<Map<String, Object>> removeItems(List<String> owids);

    /***
     * <p>功能描述：listAllDepatByUserId 员工细表查询</p>
     * <ul>
     * <li>@param [owid]</li>
     * <li>@return java.util.List<com.ourway.sys.model.OurwaySysDepatemp></li>
     * <li>@throws </li>
     * <li>@author CC</li>
     * <li>@date 2017/7/10 16:14</li>
     * </ul>
     */
    List<OurwaySysDepatemp> listAllDepatByUserId(String owid);
}
