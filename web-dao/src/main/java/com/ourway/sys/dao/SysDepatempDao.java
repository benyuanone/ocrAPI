package com.ourway.sys.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysDepatemp;
import com.ourway.sys.model.OurwaySysEmploys;

import java.util.List;

/**
 * <p>接口 SysDepatempDao.java : <p>
 * <p>说明：</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:42
 * </pre>
 */
public interface SysDepatempDao extends BaseService<OurwaySysDepatemp> {
    boolean doCheckUniqueLabelKey(OurwaySysDepatemp depatemp);

    PageInfo<OurwaySysDepatemp> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);

    List<OurwaySysDepatemp> listDepatEmp(String userRefOwId);

    /**
     * <p>接口 SysDepatempDao.java : <p>
     * <p>说明：查询从表信息</p>
     * <pre>
     * @author cc
     * @date 2017/5/31 14:28
     * </pre>
     */
    List<OurwaySysDepatemp> listAllByObjectId(String owid);


    /*
    *<p>功能描述:根据用户owid 查询</p >
    *<ul>
    *<li>@param [owid]</li>
    *<li>@return com.ourway.sys.model.OurwaySysDepatemp</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/6/30 0030 下午 6:47</li>
    *</ul>
    */
    OurwaySysDepatemp getOneDepatemp(String owid);

    /***
     *<p>功能描述：listDepatemp</p>
     *<ul>
     *<li>@param [owid]</li>
     *<li>@return java.util.List<com.ourway.sys.model.OurwaySysDepatemp></li>
     *<li>@throws </li>
     *<li>@author CC</li>
     *<li>@date 2017/7/11 19:27</li>
     *</ul>
     */
    List<OurwaySysDepatemp> listDepatemp(String owid);

    void removeByUserOwid(String owid);

    OurwaySysDepatemp listDefaultDepart(OurwaySysEmploys employs);
}
