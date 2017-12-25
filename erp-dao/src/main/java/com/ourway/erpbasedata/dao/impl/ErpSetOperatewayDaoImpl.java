package com.ourway.erpbasedata.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.erpbasedata.dao.ErpSetOperatewayDao;
import com.ourway.erpbasedata.model.ErpSetOperateway;
import com.ourway.erpbasedata.model.ErpTank;
import com.ourway.sys.utils.I18nUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by David on 2017-09-22.
 */
@Repository("ErpSetOperatewayDao")
public class ErpSetOperatewayDaoImpl extends BaseServiceImpl<ErpSetOperateway> implements ErpSetOperatewayDao{

    @Override
    public PageInfo<ErpSetOperateway> listErpSetOperatewayForPage(List<FilterModel> filterModels, int pageNo, int pageSize, String sortStr) {
        HqlStatement hqlStatement = new HqlStatement(ErpSetOperateway.class,filterModels,sortStr);
        return listHqlForPage(hqlStatement.getHql(),hqlStatement.getCountHql(),hqlStatement.getParams(),pageNo,pageSize);
    }

    @Override
    public ErpSetOperateway detailErpSetOperateway(Map<String,Object> dataMap) {
        HqlStatement hqlStatement = new HqlStatement(ErpSetOperateway.class,dataMap);
        return getOneByHql(hqlStatement.getHql(),dataMap);
    }

    @Override
    public String checkUnique(Map dataMap,PublicDataVO data) {
        //校验字段array
        String[] strArray = {"operatewayId","operatewayName","operatewayType"};
        for (String s:strArray) {
            String hql = " from ErpSetOperateway where "+s+"=:"+s+" ";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(s, dataMap.get(s));
            if (!TextUtils.isEmpty(dataMap.get("owid"))) {
                hql += " and owid<>:owid";
                params.put("owid", dataMap.get("owid"));
            }
            ErpSetOperateway _erpSetOperateway = getOneByHql(hql, params);
            if (null != _erpSetOperateway) {
                return I18nUtils.getLanguageContent("UI.CLN.ErpSetOperateway."+s+".01",data.getCurrLanguage());
            }
        }
        return null;
    }
}
