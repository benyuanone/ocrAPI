package com.ourway.sys.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.BaseTree;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.model.OurwaySysDic;
import com.ourway.sys.model.OurwaySysDicValue;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 DicService.java : <p>
 * <p>说明：通用字典表</p>
 * <pre>
 * @author cc
 * @date 14:36 2017/4/12
 * </pre>
 */
public interface DicService {

    void saveOrUpdate(OurwaySysDic dic);

    /**
     * <p>方法:saveOrUpdateAll 保存所有及其子表 </p>
     * <ul>
     * <li> @param dic TODO</li>
     * <li> @param values TODO</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/30 15:33  </li>
     * </ul>
     */
    void saveOrUpdateAll(List<OurwaySysDicValue> values, Integer type);


    PageInfo<OurwaySysDic> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize);

    List<Map<String, Object>> removeententDicByIds(List<String> owids);

    List<OurwaySysDic> listDicByStatement(HqlStatement hql);

    List<OurwaySysDic> listAllTreeDic(int type);

    /**
     * <p>方法:listDicByType 根据类型获取指定类别的字典数据 </p>
     * <ul>
     * <li> @param type 字典表类型</li>
     * <li>@return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/30 14:55  </li>
     * </ul>
     */
    List<Map<String, Object>> listDicByType(Integer type, String orderBy);

    /**
     * <p>方法:listLanguageDicByType 获取多语言字典 </p>
     * <ul>
     * <li> @param type TODO</li>
     * <li> @param orderBy TODO</li>
     * <li>@return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/9/17 21:40  </li>
     * </ul>
     */
    List<Map<String, Object>> listLanguageDicByType(Integer type, String code, String language, String orderBy);


    OurwaySysDic getSingleDicByType(int type);


    Object[] getSingleDicByType(Integer type, Map<String, Object> params);

    OurwaySysDic saveOrUpdateTree(BaseTree tree, Integer type);

    Boolean removeTree(BaseTree tree, Integer type);

    PageInfo<Object[]> listAllDicForPage(List<FilterModel> models, Integer type, int pageNo, int pageSize, String sortStr);

    PageInfo<Object[]> listAllLikeDicForPage(String key, Integer type, int pageNo, int pageSize, String sortStr);

    /*<p>方法: 说明:列出所有业务字典（分页） </p>
    *<ul>
     *<li> @param models 过滤条件</li>
     *<li> @param pageNo 起始页</li>
     *<li> @param pageSize 当前页显示数量</li>
    *<li>@return 业务字典分页对象 </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-08-15 9:41  </li>
    *</ul>
    */
    PageInfo<Object[]> listBusinessDic(List<FilterModel> models, int pageNo, int pageSize);

    /*<p>方法: 根据owid查询业务字典详情 </p>
    *<ul>
     *<li> @param dataMap 过滤条件（owid）</li>
    *<li>@return  业务字典详情 </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-08-15 9:45  </li>
    *</ul>
    */
    OurwaySysDic detailBusinessDic(Map<String, Object> dataMap);

    /*<p>方法: 删除指定的owid对应的dicValue信息 </p>
    *<ul>
     *<li> @param owids 需要删除的owid组</li>
    *<li>@return 被删除的owid主表和细表信息  </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-08-15 17:05  </li>
    *</ul>
    */
    List<Map<String, Object>> removeItems(List<Integer> owids);

    /*<p>方法: 业务字典保存 </p>
    *<ul>
     *<li> @param ourwaySysDic 前台传入的字典对象</li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-08-15 9:48  </li>
    *</ul>
    */
    void saveOrUpdateDic(OurwaySysDic ourwaySysDic);

    /*<p>方法: 根据传入的条件判断当前业务字典是否唯一 </p>
    *<ul>
     *<li> @param ourwaySysDic 前台传入的条件</li>
    *<li>@return 是否唯一  </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-08-15 9:49  </li>
    *</ul>
    */
    boolean doUniqueCheckType(OurwaySysDic ourwaySysDic);


    OurwaySysDic listOneDicByOwid(String owid);


    OurwaySysDic saveOrUpdateObject(OurwaySysDic object, List<OurwaySysDicValue> objAttrList);

    List<Map<String, Object>> listLanguageDicByDicList(List<String> dicList, Integer type, String language, String orderBy);

    String doGenerateSQL(String tables,String filePath);
}
