package com.ourway.sys.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.BaseTree;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.sys.dao.SysLogDao;
import com.ourway.sys.dao.SysLogSettingDao;
import com.ourway.sys.dao.SysObjectAttributeDao;
import com.ourway.sys.dao.SysObjectDao;
import com.ourway.sys.model.*;
import com.ourway.sys.service.ObjectService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 ObjectService.java : <p>
 * <p>说明：系统业务类</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 14:37
 * </pre>
 */
@Service("objectSer")
public class ObjectServiceImpl implements ObjectService {

    @Autowired
    private SysObjectDao objectDao;
    @Autowired
    private SysObjectAttributeDao objectAttDao;
    @Autowired
    private SysLogDao sysLogDao;
    @Autowired
    private SysLogSettingDao logSetDao;

    @Override
    public OurwaySysObject saveOrUpdateObject(OurwaySysObject object, List<OurwaySysObjectAttribute> objAttrList) {
        objectDao.saveOrUpdate(object);
        if (null != objAttrList && objAttrList.size() > 0) {
            for (OurwaySysObjectAttribute attribute : objAttrList) {
                switch (attribute.getUpdateFlag()) {
                    //0 新增
                    case 0:
                        //什么也不做
                        break;
                    //修改
                    case 1:
                        attribute.setObjectRefOwid(object.getOwid());

                        // 判断 owid
                        if (TextUtils.isEmpty(attribute.getOwid())) {
                            //为空 新增
                            objectAttDao.save(attribute);
                        } else {
                            objectAttDao.update(attribute);
                        }
                        break;
                    //删除
                    case 2:
                        objectAttDao.removeByIds(attribute.getOwid());
                        break;
                }
            }
        }
//        object.setDataList(objAttrList);
        return object;
    }

    @Override
    public void saveOrUpdateAllObject(List<OurwaySysObject> objs) {
        List<OurwaySysObject> _objs = new ArrayList<OurwaySysObject>();
        for (OurwaySysObject obj : objs) {
            _objs = objectDao.listObjectByClassName(obj.getClassName());
            if (null != _objs && _objs.size() > 0)
                continue;
            objectDao.saveOrUpdate(obj);
            for (OurwaySysObjectAttribute attr : obj.getOurwaySysObjectAttributeList()) {
                attr.setObjectRefOwid(obj.getOwid());
                objectAttDao.saveOrUpdate(attr);
            }
        }
    }

    @Override
    public List<Map<String, Object>> removeObjects(List<String> owids) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();
        for (String owid : owids) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            //判断该业务类是否被业务日志类使用
            if (sysLogDao.countObject(owid) >= 1) {
               continue;
            }
            params.put("owid", owid);
            objectDao.removeByParams(params);
            objs.add(params);
            //删除对象属性
            params = new HashedMap();
            params.put("objectRefOwid", owid);
            objectAttDao.removeByParams(params);
        }
        return objs;
    }

    @Override
    public PageInfo<OurwaySysObject> listHqlForPage(List<FilterModel> filter, int pageNo, int pageSize) {
        return objectDao.listObjectHqlForPage(filter, pageNo, pageSize);
    }

    @Override
    public OurwaySysObject detailObject(String objId) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("owid", objId);
        return objectDao.getOneByParams(params, "");
    }


    @Override
    public List<OurwaySysObject> listAllObjectByClassName(String className) {
        return objectDao.listObjectByClassName(className);
    }

    @Override
    public List<OurwaySysObjectAttribute> listAllAttributesByOwid(String owid) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("objectRefOwid", owid);
        return objectAttDao.listAllByParam(params, " attributeName ");
    }

    @Override
    public List<OurwaySysObjectAttribute> listAllAttributesByClassName(String className) {
        List<OurwaySysObject> objs = listAllObjectByClassName(className);
        if(null==objs||objs.size()<=0) {
            return null;
        }
        OurwaySysObject obj = objs.get(0);
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("objectRefOwid", obj.getOwid());
        return objectAttDao.listAllByParam(params, " attributeName ");
    }

    @Override
    public List<BaseTree> listAllTreeType() {
        return objectDao.listAllTreeType();
    }
}
