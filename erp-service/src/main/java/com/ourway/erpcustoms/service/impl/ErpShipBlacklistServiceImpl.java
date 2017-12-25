package com.ourway.erpcustoms.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.BeanUtil;
import com.ourway.erpbasedata.dao.ErpShipDao;
import com.ourway.erpbasedata.model.ErpShip;
import com.ourway.erpcustoms.dao.ErpShipBlacklistDao;
import com.ourway.erpcustoms.service.ErpShipBlacklistService;
import com.ourway.erpcustoms.model.ErpShipBlacklist;
import com.ourway.sys.dao.SysEmploysDao;
import com.ourway.sys.model.OurwaySysEmploys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*<p>方法 ErpShipBlacklistServiceImpl : <p>
*<p>说明:</p>
*<pre>
*@author zhou_xtian
*@date 2017-09-08 14:08
</pre>
*/
@Service("erpShipBlacklistService")
public class ErpShipBlacklistServiceImpl implements ErpShipBlacklistService{
    @Autowired
    private ErpShipBlacklistDao erpShipBlacklistDao;
    @Autowired
    private SysEmploysDao sysEmploysDao;
    @Autowired
    private ErpShipDao erpShipDao;

    @Override
    public PageInfo<ErpShipBlacklist> listErpShipBlacklist(List<FilterModel> filterModels, int pageNo, int pageSize, String sortStr) {
        PageInfo<ErpShipBlacklist> pageInfo = erpShipBlacklistDao.listErpShipBlacklist(filterModels,pageNo,pageSize,sortStr);
        List<ErpShipBlacklist> erpShipBlacklistList = pageInfo.getRecords();
        for (ErpShipBlacklist erpShipBlacklist:erpShipBlacklistList) {
            if (erpShipBlacklist.getState() == ErpShipBlacklist.ERPSHIP_BLACKLIST_USED_STATE) {
                //如果是有效状态，则将修改人和修改时间置为null（页面上对应撤销人、撤销时间）
                erpShipBlacklist.setUpdator(null);
                erpShipBlacklist.setUpdatorName(null);
                erpShipBlacklist.setLasupdate(null);
            }
        }
        pageInfo.setRecords(erpShipBlacklistList);
        return pageInfo;
    }

    @Override
    public Map<String,Object> detailErpShipBlacklist(Map<String, Object> params) {
        return erpShipBlacklistDao.detailErpShipBlacklist(params);
    }

    @Override
    public List<Map<String,Object>> historyErpShipBlacklist(Map<String, Object> params) {
        return erpShipBlacklistDao.historyErpShipBlacklist(params);
    }

    @Override
    public void saveErpShipBlacklist(ErpShipBlacklist erpShipBlacklist) {
        erpShipBlacklistDao.saveOrUpdate(erpShipBlacklist);
        //将ship表state设为0：启用 1：禁用
        Map<String,Object> _map = new HashMap<String,Object>();
        _map.put("owid",erpShipBlacklist.getErpshipRefOwid());
        setErpShipState(_map,erpShipBlacklist.getState()==ErpShipBlacklist.ERPSHIP_BLACKLIST_USED_STATE
                ?ErpShipBlacklist.ERPSHIP_BLACKLIST_REVOKE_STATE:ErpShipBlacklist.ERPSHIP_BLACKLIST_USED_STATE);
    }

    @Override
    public void removeErpShipBlacklist(Map<String,Object> erpShipBlacklistMap) {
        ErpShipBlacklist erpShipBlacklist = BeanUtil.map2Obj(erpShipBlacklistMap,ErpShipBlacklist.class);
        if (null != erpShipBlacklist) {
            erpShipBlacklistDao.removeEntity(erpShipBlacklist);
            //将ship表state设为0：启用 1：禁用
            Map<String,Object> _map = new HashMap<String,Object>();
            _map.put("eb.erpshipRefOwid",erpShipBlacklist.getErpshipRefOwid());
            Map<String,Object> erpShipBlacklistMap2 = erpShipBlacklistDao.detailErpShipBlacklist(_map);

            Map<String,Object> _map2 = new HashMap<String,Object>();
            _map2.put("owid",erpShipBlacklist.getErpshipRefOwid());
            int state = ErpShipBlacklist.ERPSHIP_BLACKLIST_USED_STATE;
            if (erpShipBlacklistMap2.isEmpty()) {
                state = (Integer) erpShipBlacklistMap2.get("state");
            }
            setErpShipState(_map2,state);
        }
    }

    //设置船舶资料表state和船舶黑名单表对应
    private void setErpShipState(Map<String,Object> map,int state) {
        ErpShip erpShip = erpShipDao.getOneByParams(map,"");
        erpShip.setState(state==ErpShipBlacklist.ERPSHIP_BLACKLIST_USED_STATE
                ?state:ErpShipBlacklist.ERPSHIP_BLACKLIST_REVOKE_STATE);
        erpShipDao.saveOrUpdate(erpShip);
    }

    @Override
    public PageInfo<OurwaySysEmploys> listSysEmploys(List<FilterModel> filterModels, int pageNo, int pageSize, String sortStr) {
        return sysEmploysDao.listHqlForPage(filterModels,pageNo,pageSize,sortStr);
    }
}
