package com.ourway.sys.service;


import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.BaseTree;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.model.OurwaySysDepat;
import com.ourway.sys.model.OurwaySysEmploys;
import com.ourway.sys.model.OurwaySysMenus;

import java.util.List;
import java.util.Map;

/**
 * <p>接口 DepatService.java : <p>
 * <p>说明：部门信息</p>
 * <pre>
 * @author cc
 * @date 14:36 2017/4/12
 * </pre>
 */
public interface DepatService {
    boolean doCheckUniqueLabel(OurwaySysDepat depat);

    void saveOrUpdateLanguage(OurwaySysDepat depat);

    PageInfo<OurwaySysDepat> listHqlForPage(List<FilterModel> models, int pageNo, int pageSize);

    void removeententDicByIds(HqlStatement hql);

    List<OurwaySysDepat> listDicByStatement(HqlStatement hql);


    void saveOrUpdate(OurwaySysDepat menu);

    void removeDepart(Map<String, Object> map);


    /**
     * <p>功能描述:getDept</p >
     * <ul>
     * <li>@param [ourwaySysEmploys]</li>
     * <li>@return com.ourway.sys.model.OurwaySysDepat</li>
     * <li>@throws </li>
     * <li>@author xuby</li>
     * <li>@date 2017/6/22 0022 下午 1:04</li>
     * </ul>
     */
    OurwaySysDepat getDept(OurwaySysEmploys ourwaySysEmploys);

    /**
     * <p>功能描述: 根据code 查询下级部门</p >
     * <ul>
     * <li>@param [depCode]</li>
     * <li>@return java.util.List<com.ourway.sys.model.OurwaySysDepat></li>
     * <li>@throws </li>
     * <li>@author xuby</li>
     * <li>@date 2017/6/21 0021 上午 10:48</li>
     * </ul>
     */
    List<OurwaySysDepat> getDeptList(Integer depCode);

    /**
     * <p>功能描述:根据主键查询部门信息</p >
     * <ul>
     * <li>@param [depCode]</li>
     * <li>@return com.ourway.sys.model.OurwaySysDepat</li>
     * <li>@throws </li>
     * <li>@author xuby</li>
     * <li>@date 2017/6/21 0021 下午 3:58</li>
     * </ul>
     */
    OurwaySysDepat getDeptById(Integer depCode);

    /**
     * <p>功能描述:根据主键 和疫苗名称查询部门信息</p >
     * <ul>
     * <li>@param [depCode, vcName]</li>
     * <li>@return java.util.List<com.ourway.sys.model.OurwaySysDepat></li>
     * <li>@throws </li>
     * <li>@author xuby</li>
     * <li>@date 2017/6/21 0021 下午 5:20</li>
     * </ul>
     */
    List<OurwaySysDepat> listAllDepat(Integer depCode, String vcName, String nextDepName);


    /**
     * <p>功能描述:查询所有部门</p >
     * <ul>
     * <li>@param []</li>
     * <li>@return java.util.List<com.ourway.sys.model.OurwaySysDepat></li>
     * <li>@throws </li>
     * <li>@author xuby</li>
     * <li>@date 2017/6/22 0022 下午 3:30</li>
     * </ul>
     */
    List<OurwaySysDepat> listDepat();

    /**
     * <p>功能描述:后台获得下级部门list</p >
     * <ul>
     * <li>@param []</li>
     * <li>@return java.util.List<java.util.Map<java.lang.String,java.lang.Object>></li>
     * <li>@throws </li>
     * <li>@author xuby</li>
     * <li>@date 2017/7/15 0015 下午 8:47</li>
     * </ul>
     */
    List<Map<String, Object>> listAllNextDepat();


    List<BaseTree> listTree(List<FilterModel> models);

    //    更新节点移动的业务类方法
    void update(List<OurwaySysDepat> depats);
}
