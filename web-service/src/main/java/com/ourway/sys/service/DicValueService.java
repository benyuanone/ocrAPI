package com.ourway.sys.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.sys.model.OurwaySysDic;
import com.ourway.sys.model.OurwaySysDicValue;

import java.util.List;

/**<p>接口 DicValueService.java : <p>
 *<p>说明：字典表</p>
 *<pre>
 *@author cc
 *@date 14:36 2017/4/12
</pre>
 */
public interface DicValueService {
    PageInfo<OurwaySysDicValue> listEventTypeForPage(Integer owid, List<FilterModel> filters, Integer pageNo, Integer pageSize);

    OurwaySysDicValue detailEvent(String owid);

    void saveOrUpdate(OurwaySysDicValue dicValue);

    OurwaySysDicValue detailValueByid(String owid);

    List<OurwaySysDicValue> listDicValuesByRefOwid(OurwaySysDic dic);
}
