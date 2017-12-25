package com.ourway.erpbasedata.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.erpbasedata.dao.ErpSetChargewayListDao;
import com.ourway.erpbasedata.model.ErpSetChargeway;
import com.ourway.erpbasedata.model.ErpSetChargewayList;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
*<p>方法: TODO </p>
*<ul>
 *<li> @param null TODO</li>
*<li>@return   </li>
*<li>@author zhangxinyi </li>
*<li>@date 2017-09-28 16:57  </li>
*</ul>
*/
@Repository("erpSetChargewayListDao")
public class ErpSetChargewayListDaoImpl extends BaseServiceImpl<ErpSetChargewayList> implements ErpSetChargewayListDao{

    @Override
    public void removeErpSetChargewayList(String owid) {
         /* 根据apiowid 查询 从表信息*/
        Map<String,Object> _params = new HashMap<String,Object>();
        _params.put("erpsetchargewayRefOwid",owid);
        removeByParams(_params);
    }


    @Override
    public List<ErpSetChargewayList> listAllByErpSetChargewayList(ErpSetChargeway oapi) {

        Map<String,Object> _params = new HashMap<String,Object>();
        _params.put("erpsetchargewayRefOwid",oapi.getOwid());

        HqlStatement hql = new HqlStatement(ErpSetChargewayList.class,_params);

        return listAllByHql(hql.getHql(),hql.getParams());
    }

    @Override
    public PageInfo<ErpSetChargewayList> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        HqlStatement hqlStatement = new HqlStatement(ErpSetChargewayList.class,filters,sortStr);
        return listHqlForPage(hqlStatement.getHql(),hqlStatement.getCountHql(),hqlStatement.getParams(),pageNo,pageSize);
    }
}
