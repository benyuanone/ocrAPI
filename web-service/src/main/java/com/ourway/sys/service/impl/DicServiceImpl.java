package com.ourway.sys.service.impl;


import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.BaseTree;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.sys.dao.SysDicDao;
import com.ourway.sys.dao.SysDicValueDao;
import com.ourway.sys.model.OurwaySysDic;
import com.ourway.sys.model.OurwaySysDicValue;
import com.ourway.sys.service.DicService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
@Service("dicService")
public class DicServiceImpl implements DicService {
    @Autowired
    SysDicDao sysDicDao;
    @Autowired
    SysDicValueDao sysDicValueDao;

    @Override
    public void saveOrUpdate(OurwaySysDic dic) {
        sysDicDao.saveOrUpdate(dic);
    }


    @Override
    public PageInfo<OurwaySysDic> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize) {
        return sysDicDao.listAllDicByPages(filters, pageNo, pageSize);
    }

    @Override
    public List<Map<String, Object>> removeententDicByIds(List<String> owids) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();
        List<OurwaySysDic> dicList = new ArrayList<>();
        for (int i = 0, g = owids.size(); i < g; i++) {
            dicList.add(sysDicDao.getOneByHql("from OurwaySysDic where owid = ?", Integer.parseInt(owids.get(i))));

        }
        for (OurwaySysDic dic : dicList) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            sysDicDao.removeEntity(dic);
            params.put("owid", dic.getOwid());
            objs.add(params);
        }
        return objs;
    }

    @Override
    public List<OurwaySysDic> listDicByStatement(HqlStatement hql) {
        List<OurwaySysDic> dicList = sysDicDao.listAllByHql(hql.getHql(), hql.getParams());
        Map<String, Object> _params = new HashMap<String, Object>();
        for (OurwaySysDic dic : dicList) {
            _params.put("dicRefOwid", dic.getOwid());
            dic.setDicValueList(sysDicValueDao.listAllByHql("from OurwaySysDicValue", _params));
        }
        return dicList;
    }

    @Override
    public List<OurwaySysDic> listAllTreeDic(int type) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("type", type);
        return sysDicDao.listAllByParam(params, "owid");
    }

    @Override
    public OurwaySysDic getSingleDicByType(int type) {
        return sysDicDao.getSingleDicByType(type);
    }

    @Override
    public List<Map<String, Object>> listDicByType(Integer type, String orderBy) {
        return sysDicDao.listAllDataByType(type, orderBy);
    }

    @Override
    public List<Map<String, Object>> listLanguageDicByType(Integer type, String code, String language, String orderBy) {
        List<Map<String, Object>> dicLanguageList = sysDicDao.listAllLanguageDataByType(type, language, orderBy);
        for (Map<String, Object> dicLanguage : dicLanguageList) {
            //新增可以返回单code对应数据的方法
            if (!TextUtils.isEmpty(dicLanguage.get("code")) && code.equals(dicLanguage.get("code").toString())) {
                dicLanguageList.clear();
                dicLanguageList.add(dicLanguage);
                break;
            }
        }
        return dicLanguageList;
    }

    @Override
    public void saveOrUpdateAll(List<OurwaySysDicValue> values, Integer type) {
        if (null != values && values.size() > 0)
            for (OurwaySysDicValue dicValue : values) {
                if (null == dicValue.getUpdateFlag())
                    continue;
                switch (dicValue.getUpdateFlag()) {
                    case 1://新增或者修改
                        if (TextUtils.isEmpty(dicValue.getOwid())) {
                            //新增
                            OurwaySysDic dic = new OurwaySysDic();
                            dic.setType(type);
                            sysDicDao.save(dic);
                            dicValue.setDicRefOwid(dic.getOwid());
                            sysDicValueDao.save(dicValue);
                        } else {
                            //修改
                            Map<String, Object> params = new HashMap<String, Object>(1);
                            params.put("dicRefOwid", dicValue.getOwid());
                            OurwaySysDicValue dicv = sysDicValueDao.getOneByParams(params, "");
                            BeanUtils.copyProperties(dicValue, dicv, new String[]{"owid", "dicRefOwid"});
                            sysDicValueDao.update(dicv);
                        }
                        break;
                    case 2:
                        Map<String, Object> params = new HashMap<String, Object>(1);
                        params.put("dicRefOwid", dicValue.getOwid());
                        OurwaySysDicValue dicv = sysDicValueDao.getOneByParams(params, "");
                        params.clear();
                        params.put("owid", dicValue.getOwid());
                        OurwaySysDic dic = sysDicDao.getOneByParams(params, "");
                        sysDicValueDao.removeEntity(dicv);
                        sysDicDao.removeEntity(dic);
                        break;

                }
            }
    }

    @Override
    public Object[] getSingleDicByType(Integer type, Map<String, Object> params) {
        return sysDicDao.getSingleDicByType(type, params);
    }


    @Override
    public OurwaySysDic saveOrUpdateTree(BaseTree tree, Integer type) {
        OurwaySysDic dic = new OurwaySysDic();
        if (!TextUtils.isEmpty(tree.getOwid()) && tree.getOwid().intValue() != 0) {
            dic = sysDicDao.getOneById(tree.getOwid());
        }
        BeanUtil.copy2Bean(tree, dic, new String[]{"fid-fid", "path-path", "name-name", "px-px"});
        dic.setType(type);
        sysDicDao.saveOrUpdate(dic);
        return dic;
    }

    @Override
    public Boolean removeTree(BaseTree tree, Integer type) {
        OurwaySysDic dic = sysDicDao.getOneById(tree.getOwid());
        List<OurwaySysDic> dics = sysDicDao.listAllOwidByPath(dic.getPath());
        List<Integer> owids = new ArrayList<Integer>(dics.size());
        for (OurwaySysDic ourwaySysDic : dics) {
            owids.add(ourwaySysDic.getOwid());
        }
        //在加上自己
        owids.add(dic.getOwid());
        sysDicValueDao.removeByRefOwid(owids.toArray());
        sysDicDao.removeByIds(owids.toArray());
        return true;
    }

    @Override
    public PageInfo<Object[]> listAllDicForPage(List<FilterModel> models, Integer type, int pageNo, int pageSize, String sortStr) {
        return sysDicDao.listAllDicForPage(models, type, pageNo, pageSize, sortStr);
    }

    @Override
    public PageInfo<Object[]> listAllLikeDicForPage(String key, Integer type, int pageNo, int pageSize, String sortStr) {
        return sysDicDao.listAllLikeDicForPage(key, type, pageNo, pageSize, sortStr);
    }

    @Override
    public PageInfo<Object[]> listBusinessDic(List<FilterModel> models, int pageNo, int pageSize) {
        return sysDicDao.listBusinessDic(models, pageNo, pageSize);
    }

    @Override
    public OurwaySysDic detailBusinessDic(Map<String, Object> dataMap) {
        return sysDicDao.detailBusinessDic(dataMap);
    }

    @Override
    public List<Map<String, Object>> removeItems(List<Integer> owids) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();
        for (Integer owid : owids) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            params.put("owid", owid);
            OurwaySysDic ourwaySysDic = sysDicDao.getOneByParams(params, "");
            if (null != ourwaySysDic) {
                params = new HashMap<String, Object>(1);
                params.put("dicRefOwid", owid);
                sysDicValueDao.removeByParams(params);
                params = new HashMap<String, Object>(1);
                params.put("owid", owid);
                sysDicDao.removeEntity(ourwaySysDic);
                objs.add(params);
            }
        }
        return objs;
    }

    @Override
    public void saveOrUpdateDic(OurwaySysDic ourwaySysDic) {
        sysDicDao.saveOrUpdate(ourwaySysDic);
//        if (null != ourwaySysDic.getDataList() && ourwaySysDic.getDataList().size() > 0)
//            for (OurwaySysDicValue dicValue : ourwaySysDic.getDataList()) {
//                if (null == dicValue.getUpdateFlag())
//                    dicValue.setUpdateFlag(0);
//                switch (dicValue.getUpdateFlag()) {
//                    case 0:
//                        break;
//                    case 1:
//                        dicValue.setDicRefOwid(ourwaySysDic.getOwid());
//                        if (TextUtils.isEmpty(dicValue.getOwid()))
//                            sysDicValueDao.save(dicValue);
//                        else
//                            sysDicValueDao.update(dicValue);
//                        break;
//                    case 2:
//                        sysDicValueDao.removeByIds(OurwaySysDicValue.class, dicValue.getOwid());
//                        break;
//                }
//            }
    }

    @Override
    public boolean doUniqueCheckType(OurwaySysDic ourwaySysDic) {
        return sysDicDao.doUniqueCheckType(ourwaySysDic);
    }

    @Override
    public OurwaySysDic listOneDicByOwid(String owid) {
        Integer _owid = new Integer(owid);
        return sysDicDao.getOneById(_owid);
    }

    @Override
    public OurwaySysDic saveOrUpdateObject(OurwaySysDic object, List<OurwaySysDicValue> objAttrList) {
        sysDicDao.saveOrUpdate(object);
        if (null != objAttrList && objAttrList.size() > 0) {
            for (OurwaySysDicValue attribute : objAttrList) {
                switch (attribute.getUpdateFlag()) {
                    //0 新增
                    case 0:
                        //什么也不做
                        break;
                    //修改
                    case 1:
                        attribute.setDicRefOwid(object.getOwid());

                        // 判断 owid
                        if (TextUtils.isEmpty(attribute.getOwid())) {
                            //为空 新增
                            sysDicValueDao.save(attribute);
                        } else {
                            sysDicValueDao.update(attribute);
                        }
                        break;
                    //删除
                    case 2:
                        sysDicValueDao.removeByIds(attribute.getOwid());
                        break;
                }
            }
        }
//        object.setDataList(objAttrList);
        return object;
    }

    @Override
    public List<Map<String, Object>> listLanguageDicByDicList(List<String> dicList, Integer type, String language, String orderBy) {
        return sysDicDao.listLanguageDicByDicList(dicList, type, language, orderBy);
    }

    @Override
    public String doGenerateSQL(String tables, String filePath) {
        sysDicDao.doGenerateSQL(tables, filePath);
        return "";
    }
}
