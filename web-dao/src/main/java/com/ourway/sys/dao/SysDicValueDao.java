package com.ourway.sys.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysDicValue;

import java.util.List;

/**
 * <p>接口 SysDicValueDao.java : <p>
 * <p>说明：字典值</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:42
 * </pre>
 */
public interface SysDicValueDao extends BaseService<OurwaySysDicValue> {
    PageInfo<OurwaySysDicValue> listEventTypeForPage(Integer owid, List<FilterModel> filters, Integer pageNo, Integer pageSize);

    void removeByRefOwid(Object[] owids);

    OurwaySysDicValue listOneDic(int type,String dicVal1);
}
