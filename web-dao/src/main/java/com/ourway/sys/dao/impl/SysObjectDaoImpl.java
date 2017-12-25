package com.ourway.sys.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.BaseTree;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.sys.dao.SysObjectDao;
import com.ourway.sys.model.OurwaySysDicValue;
import com.ourway.sys.model.OurwaySysObject;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 SysObjectDaoImpl.java : <p>
 * <p>说明：对象</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:43
 * </pre>
 */

@Repository("objectDao")
public class SysObjectDaoImpl extends BaseServiceImpl<OurwaySysObject> implements SysObjectDao {
    @Override
    public PageInfo<OurwaySysObject> listObjectHqlForPage(List<FilterModel> filter, int pageNo, int pageSize) {
        HqlStatement hql = new HqlStatement("from OurwaySysObject ", filter);
        return listHqlForPage(hql.getHql(), hql.getCountHql(), hql.getParams(), pageNo, pageSize);
    }

    @Override
    public List<OurwaySysObject> listObjectByClassName(String className) {
        String hql = "from OurwaySysObject order by className";
        if (!TextUtils.isEmpty(className)) {
            hql = " from OurwaySysObject where className like :className order by className";

            Map<String, Object> params = new HashMap<String, Object>(1);
            params.put("className", className);
            return listAllByHql(hql, params);
        } else {
            return listAllByHql(hql, null);
        }
    }

    @Override
    public List<BaseTree> listAllTreeType() {
        List<BaseTree> baseTreeList = new ArrayList<BaseTree>();
        String hql = "select distinct classPre from OurwaySysObject ";
        List<Object> objs = listObjAllByHql(hql, null);
        Map<String, String> classMap = getClassTypeMap();
        int index = 0;
        if (null != objs && objs.size() > 0)
            for (Object dicObj : objs) {
                if (TextUtils.isEmpty(dicObj))
                    continue;
                BaseTree bt = new BaseTree();
                bt.setOwid(index);
                bt.setFid(-1);
                if (null != classMap.get(dicObj.toString()))
                    bt.setName(classMap.get(dicObj.toString()));
                else
                    bt.setName(dicObj.toString());
                bt.setPath(dicObj.toString());
                baseTreeList.add(bt);
                index++;
            }
        return baseTreeList;
    }

    private Map<String, String> getClassTypeMap() {
        String hql;
        Map<String, String> classMap = new HashMap<String, String>();
        hql = " from OurwaySysDic a,OurwaySysDicValue b where a.owid=b.dicRefOwid and a.type=15";
        List<Object[]> dicObjs = listObjsAllByHql(hql, null);
        for (Object[] dicObj : dicObjs) {
            OurwaySysDicValue dicValue = (OurwaySysDicValue) dicObj[1];
            classMap.put(dicValue.getDicVal1(), dicValue.getDicVal2());
        }
        return classMap;
    }
}
