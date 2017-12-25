package com.ourway.sys.service.impl;


import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.dao.SysDicValueDao;
import com.ourway.sys.model.OurwaySysDic;
import com.ourway.sys.model.OurwaySysDicValue;
import com.ourway.sys.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**<p>接口 DicValueService.java : <p>
 *<p>说明：字典表</p>
 *<pre>
 *@author cc
 *@date 14:36 2017/4/12
</pre>
 */
@Service("dicValue")
public class DicValueServiceImpl implements DicValueService {

    @Autowired
    SysDicValueDao sysDicValueDao;

    @Override
    public PageInfo<OurwaySysDicValue> listEventTypeForPage(Integer owid, List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        return sysDicValueDao.listEventTypeForPage(owid,filters,pageNo,pageSize);
    }

    @Override
    public OurwaySysDicValue detailEvent(String owid) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("owid", owid);
        HqlStatement hql = new HqlStatement(OurwaySysDicValue.class, params);
        OurwaySysDicValue sysDicValue = sysDicValueDao.getOneByHql(hql.getHql(), hql.getParams());
        if (null == sysDicValue)
            return null;
        return sysDicValue;
    }

    @Override
    public void saveOrUpdate(OurwaySysDicValue dicValue) {
        sysDicValueDao.saveOrUpdate(dicValue);
    }

    @Override
    public OurwaySysDicValue detailValueByid(String owid) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("dicRefOwid", owid);
        return sysDicValueDao.getOneByParams(params,"");
    }

    @Override
    public List<OurwaySysDicValue> listDicValuesByRefOwid(OurwaySysDic dic) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("dicRefOwid",dic.getOwid());
        return sysDicValueDao.listAllByParam(params," indexno ");
    }
}
