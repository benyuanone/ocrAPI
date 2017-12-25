package com.ourway.sys.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysDic;

import java.util.List;
import java.util.Map;

/**
 * <p>接口 SysDicDao.java : <p>
 * <p>说明：通用字典表</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:42
 * </pre>
 */
public interface SysDicDao extends BaseService<OurwaySysDic> {

    void removeSysDic(OurwaySysDic dic);

    OurwaySysDic getSingleDicByType(int type);

    String getOneRecoredName(Integer roleType);

    List<Map<String, Object>> listAllDataByType(Integer type, String orderBy);

    List<Map<String, Object>> listAllLanguageDataByType(Integer type,String language, String orderBy);


    Object[] getSingleDicByType(Integer type, Map<String, Object> params);

    List<OurwaySysDic> listAllOwidByPath(String path);

    PageInfo<Object[]> listAllDicForPage(List<FilterModel> models, Integer type, int pageNo, int pageSize, String sortStr);

    PageInfo<Object[]> listAllLikeDicForPage(String key, Integer type, int pageNo, int pageSize, String sortStr);

    PageInfo<Object[]> listBusinessDic(List<FilterModel> models, int pageNo, int pageSize);

    OurwaySysDic detailBusinessDic(Map<String, Object> dataMap);

    boolean doUniqueCheckType(OurwaySysDic ourwaySysDic);


    PageInfo<OurwaySysDic> listAllDicByPages(List<FilterModel> filters, Integer pageNo, Integer pageSize);

    List<Map<String, Object>> listLanguageDicByDicList(List<String> dicList,Integer type,String language, String orderBy);

    void doGenerateSQL(String tables,String filePath);
}
