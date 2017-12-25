package com.ourway.erpcustoms.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.SessionUtils;
import com.ourway.erpbasedata.model.ErpShip;
import com.ourway.erpcustoms.dao.ErpShipBlacklistDao;
import com.ourway.erpcustoms.model.ErpShipBlacklist;
import com.ourway.sys.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/*
*<p>方法 ErpShipBlacklistDaoImpl : <p>
*<p>说明:船舶黑名单dao实现类</p>
*<pre>
*@author zhou_xtian
*@date 2017-09-08 14:39
</pre>
*/
@Repository("erpShipBlacklistDao")
public class ErpShipBlacklistDaoImpl extends BaseServiceImpl<ErpShipBlacklist> implements ErpShipBlacklistDao{

    @Autowired
    private DicService dicService;

    @Override
    public PageInfo<ErpShipBlacklist> listErpShipBlacklist(List<FilterModel> filterModels, int pageNo, int pageSize, String sortStr) {
        for (FilterModel filterModel:filterModels) {
            filterModel.setKey("eb."+filterModel.getKey());
        }
        String hql = " from ErpShipBlacklist eb,ErpShip es where eb.erpshipRefOwid=es.owid and eb.createtime in " +
                "(select max(createtime) FROM ErpShipBlacklist group by erpshipRefOwid) ";
        HqlStatement hqlStatement = new HqlStatement(hql,filterModels,sortStr);

        PageInfo<Object[]> pageInfo = listObjectHqlForPage(hqlStatement.getHql(),hqlStatement.getCountHql(),hqlStatement.getParams(),pageNo,pageSize);
        PageInfo<ErpShipBlacklist> pageInfo2 = listHqlForPage(hqlStatement.getHql(),hqlStatement.getCountHql(),hqlStatement.getParams(),pageNo,pageSize);
        List<Object[]> erpShipBlacklistList = pageInfo.getRecords();

        //统计船舶黑名单次数
        String countSql = "SELECT erpshipRefOwid,count(*) as blacklistCount FROM ErpShipBlacklist " +
                "GROUP BY erpshipRefOwid";
        List<Object[]> countList = listObjsAllByHql(countSql,null);
        List<ErpShipBlacklist> _erpShipBlacklistList = new ArrayList<ErpShipBlacklist>();
        for(Object[] data:erpShipBlacklistList){
            ErpShipBlacklist erpShipBlacklist = (ErpShipBlacklist)data[0];
            ErpShip erpShip = (ErpShip)data[1];
            erpShipBlacklist.setErpShip(erpShip);
            for (Object[] objects:countList) {
                if (erpShipBlacklist.getErpshipRefOwid().equals(objects[0])) {
                    erpShipBlacklist.setBlacklistCount(Integer.parseInt(String.valueOf(objects[1])));
                    break;
                }
            }
            _erpShipBlacklistList.add(erpShipBlacklist);
        }
        pageInfo2.setRecords(_erpShipBlacklistList);
        return pageInfo2;
    }

    @Override
    public Map<String,Object> detailErpShipBlacklist(Map<String, Object> params) {
        String hql = " from ErpShipBlacklist eb,ErpShip es where eb.erpshipRefOwid=es.owid and eb.createtime in " +
                "(select max(createtime) FROM ErpShipBlacklist group by erpshipRefOwid)";
        HqlStatement hqlStatement = new HqlStatement(hql,params);

        PageInfo<Object[]> pageInfo = listObjectHqlForPage(hqlStatement.getHql(),hqlStatement.getCountHql(),hqlStatement.getParams(),1,9999);
        List<Object[]> erpShipBlacklistList = pageInfo.getRecords();

        Map<String,Object> map = new HashMap<String,Object>();
        if (erpShipBlacklistList.size() > 0) {
            ErpShipBlacklist erpShipBlacklist = (ErpShipBlacklist)erpShipBlacklistList.get(0)[0];
            BeanUtil.obj2Map(erpShipBlacklist,map);
            ErpShip erpShip = (ErpShip)erpShipBlacklistList.get(0)[1];
            map.put("erpShip",erpShip);
            //从字典表中取出type、code、language对应字典值
            Integer type = 1008;
            String code = map.get("state").toString();
            String language = SessionUtils.getCurrLanguage();
            List<Map<String,Object>> dicLanguageValueList = dicService.listLanguageDicByType(type,code,language,"");
            if (dicLanguageValueList.size() > 0) {
                map.put("stateStr",dicLanguageValueList.get(0).get("dicVal1").toString());
            }
        }
        return map;
    }

    @Override
    public List<Map<String,Object>> historyErpShipBlacklist(Map<String, Object> params) {
        String hql = " from ErpShipBlacklist where erpshipRefOwid in " +
                "(select erpshipRefOwid from ErpShipBlacklist where owid='"+params.get("owid").toString()+"')" +
                "order by createtime";

        List<ErpShipBlacklist> _erpShipBlacklistList = listAllByHql(hql,params);

        List<Map<String,Object>> erpShipBlacklistMap = new ArrayList<Map<String,Object>>();
        for (ErpShipBlacklist erpShipBlacklist:_erpShipBlacklistList) {
            if (_erpShipBlacklistList.size() == 1 && erpShipBlacklist.getState() == ErpShipBlacklist.ERPSHIP_BLACKLIST_USED_STATE) {
                //只有一条数据并且状态未有效时
                return null;
            }

            setNewMapAndAdd(erpShipBlacklistMap,ErpShipBlacklist.ERPSHIP_BLACKLIST_USED_STATE
                    ,erpShipBlacklist.getCreator(),erpShipBlacklist.getCreatorName(),erpShipBlacklist.getCreatetime()
                    ,erpShipBlacklist.getEvent()+"。"+erpShipBlacklist.getDeal(),erpShipBlacklist.getRemark());
            if (erpShipBlacklist.getState() == ErpShipBlacklist.ERPSHIP_BLACKLIST_REVOKE_STATE) {
                setNewMapAndAdd(erpShipBlacklistMap,ErpShipBlacklist.ERPSHIP_BLACKLIST_REVOKE_STATE
                        ,erpShipBlacklist.getUpdator(),erpShipBlacklist.getUpdatorName(),erpShipBlacklist.getLasupdate()
                        ,erpShipBlacklist.getRevokeReason(),null);
            }
        }
        return erpShipBlacklistMap;
    }

    //历史信息set代码重用
    private void setNewMapAndAdd (List<Map<String,Object>> mapList, int state, String creator, String creatorName
            , Date createTime, String deal, String remark) {
        Map<String,Object> _map = new HashMap<String,Object>();
        _map.put("dealState",state);
        _map.put("dealer",creatorName);
        _map.put("dealTime",createTime);
        _map.put("dealStr",deal);
        _map.put("remark",remark);
        mapList.add(_map);
    }

    @Override
    public void saveErpShipBlacklist(ErpShipBlacklist erpShipBlacklist) {

    }
}
