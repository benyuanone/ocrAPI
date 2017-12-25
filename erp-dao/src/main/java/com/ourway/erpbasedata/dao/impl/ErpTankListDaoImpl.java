package com.ourway.erpbasedata.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.erpbasedata.dao.ErpTankListDao;
import com.ourway.erpbasedata.model.ErpTank;
import com.ourway.erpbasedata.model.ErpTankList;
import com.ourway.sys.dao.SysApiDetailDao;
import com.ourway.sys.model.OurwaySysApi;
import com.ourway.sys.model.OurwaySysApiDetail;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**<p>接口 SysApiDetailDaoImpl.java : <p>
 *<p>说明：接口详情</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:41
</pre>
 */
@Repository("erpTankListDao")
public class ErpTankListDaoImpl extends BaseServiceImpl<ErpTankList> implements ErpTankListDao{

    @Override
    public void removeErpTankList(String owid) {
         /* 根据apiowid 查询 从表信息*/
        Map<String,Object> _params = new HashMap<String,Object>();
        _params.put("erptankRefOwid",owid);
        removeByParams(_params);
    }


    @Override
    public List<ErpTankList> listAllByErpTank(ErpTank oapi) {

        Map<String,Object> _params = new HashMap<String,Object>();
        _params.put("erptankRefOwid",oapi.getOwid());

        HqlStatement hql = new HqlStatement(ErpTankList.class,_params);

        return listAllByHql(hql.getHql(),hql.getParams());
    }

    @Override
    public PageInfo<ErpTankList> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        HqlStatement hqlStatement = new HqlStatement(ErpTankList.class,filters,sortStr);
        return listHqlForPage(hqlStatement.getHql(),hqlStatement.getCountHql(),hqlStatement.getParams(),pageNo,pageSize);
    }
}
