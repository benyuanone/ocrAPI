package com.ourway.sys.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysDepat;
import com.ourway.sys.model.OurwaySysMenus;

import java.util.List;
import java.util.Map;

/**
 * <p>接口 SysDepatDao.java : <p>
 * <p>说明：</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:42
 * </pre>
 */
public interface SysDepatDao extends BaseService<OurwaySysDepat> {
    boolean doCheckUniqueLabelKey(OurwaySysDepat depat);

    PageInfo<OurwaySysDepat> listHqlForPage(List<FilterModel> models, int pageNo, int pageSize);

    void removeDepart(Map<String, Object> map);


    /**
     * <p>功能描述:根据code查询部门</p >
     * <ul>
     * <li>@param [owid]</li>
     * <li>@return com.ourway.sys.model.OurwaySysDepat</li>
     * <li>@throws </li>
     * <li>@author xuby</li>
     * <li>@date 2017/6/21 0021 上午 10:44</li>
     * </ul>
     */
    OurwaySysDepat getDept(Integer owid);

    /**
     * <p>功能描述:根据code查询下级所有部门</p >
     * <ul>
     * <li>@param [depCode]</li>
     * <li>@return java.util.List<com.ourway.sys.model.OurwaySysDepat></li>
     * <li>@throws </li>
     * <li>@author xuby</li>
     * <li>@date 2017/6/21 0021 上午 10:44</li>
     * </ul>
     */
    List<OurwaySysDepat> getDeptList(Integer depCode);

    /**
     * <p>功能描述:查询下级部门</p >
     * <ul>
     * <li>@param [depCode, vcName]</li>
     * <li>@return java.util.List<com.ourway.sys.model.OurwaySysDepat></li>
     * <li>@throws </li>
     * <li>@author xuby</li>
     * <li>@date 2017/6/21 0021 下午 4:42</li>
     * </ul>
     */
    List<OurwaySysDepat> listAllDepat(Integer depCode, String vcName, String nextDepName);

    /**
     * <p>功能描述:下级部门</p >
     * <ul>
     * <li>@param [depCode, depName, depPath]</li>
     * <li>@return java.util.List<com.ourway.sys.model.OurwaySysDepat></li>
     * <li>@throws </li>
     * <li>@author xuby</li>
     * <li>@date 2017/7/15 0015 下午 8:24</li>
     * </ul>
     */
    List<OurwaySysDepat> listAllNextDepat(Integer depCode);

    List<OurwaySysDepat> listDeptByFilters(List<FilterModel> models);

    List<OurwaySysDepat> listSubDepats(OurwaySysDepat menus);
}
