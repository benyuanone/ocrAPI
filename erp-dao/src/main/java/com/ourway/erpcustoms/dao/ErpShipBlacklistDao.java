package com.ourway.erpcustoms.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.erpcustoms.model.ErpShipBlacklist;

import java.util.List;
import java.util.Map;

/*
*<p>接口 ErpShipBlacklistDao : <p>
*<p>说明：船舶黑名单dao接口</p>
*<pre>
*@author zhou_xtian
*@date 2017-09-08 14:40
</pre>
*/
public interface ErpShipBlacklistDao extends BaseService<ErpShipBlacklist>{

    PageInfo<ErpShipBlacklist> listErpShipBlacklist(List<FilterModel> filterModels, int pageNo, int pageSize, String sortStr);

    Map<String,Object> detailErpShipBlacklist(Map<String, Object> params);

    List<Map<String,Object>> historyErpShipBlacklist(Map<String, Object> params);

    void saveErpShipBlacklist(ErpShipBlacklist erpShipBlacklist);
}
