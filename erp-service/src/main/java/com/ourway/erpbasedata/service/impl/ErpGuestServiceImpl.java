package com.ourway.erpbasedata.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.TextUtils;
import com.ourway.erpbasedata.dao.ErpGuestDao;
import com.ourway.erpbasedata.dao.ErpGuestDetailDao;
import com.ourway.erpbasedata.model.ErpGuest;
import com.ourway.erpbasedata.model.ErpGuestLinklist;
import com.ourway.erpbasedata.service.ErpGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
<p>方法 ErpGuestServiceImpl : <p>
*<p>说明:客户资料Service</p>
*<pre>
*@author Kevin
*@date 2017-11-10 14:20
</pre>
*/
@Service("erpGuestService")
public class ErpGuestServiceImpl implements ErpGuestService{

    @Autowired
    ErpGuestDao erpGuestDao;

    @Autowired
    ErpGuestDetailDao erpGuestDetailDao;

    @Override
    public PageInfo<ErpGuest> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        return erpGuestDao.listHqlForPage(filters, pageNo, pageSize, sortStr);
    }
    @Override
    public List<ErpGuestLinklist> listGuestDetailById(String shipId){
        return  null;
    }

    @Override
    public ErpGuest detailErpGuest(String owid){
        ErpGuest oapi = erpGuestDao.getOneById(owid);
        return oapi;
    }

    @Override
    public ErpGuest saveOrUpdateErpShip (ErpGuest erpShip, List<ErpGuestLinklist> erpShipListList){

        erpGuestDao.saveOrUpdate(erpShip);
        if (null != erpShipListList && erpShipListList.size() > 0)
            for (ErpGuestLinklist erpShipLists : erpShipListList) {
                if (null == erpShipLists.getUpdateFlag()) {
                    erpShipLists.setUpdateFlag(0);
                }
                switch (erpShipLists.getUpdateFlag()) {
                    //0 新增
                    case 0:
                        //什么也不做
                        break;
                    //修改
                    case 1:
                        erpShipLists.setErpguestRefOwid(erpShip.getOwid());
                        // 判断 owid
                        if (TextUtils.isEmpty(erpShipLists.getOwid())) {
                            //为空 新增
                            erpGuestDetailDao.save(erpShipLists);
                        } else {
                            erpGuestDetailDao.update(erpShipLists);
                        }
                        break;
                    //删除
                    case 2:
                        erpGuestDetailDao.removeByIds(erpShipLists.getOwid());
                        break;
                }
            }
        return erpShip;
    }

    @Override
    public List<Map<String, Object>> removeErpShipByIds(List<String> list) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();

        for (String owid : list) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            //先根据 owid 查询 记录
            params.put("owid", owid);
            ErpGuest oapi = erpGuestDao.getOneByParams(params, null);
            if (null != oapi) {
                //先删子表信息
//                erpGuestDetailDao.removeErpShipList(owid);
                erpGuestDetailDao.removeByIds(owid);
                erpGuestDao.removeEntity(oapi);
                objs.add(params);
            }
        }
        return objs;
    }

    @Override
    public Boolean doCheckFirstChoiceUnique(String owId){
        return erpGuestDetailDao.doCheckFirstChoiceUnique(owId);
    }
    @Override
    public List<ErpGuest> listAllGuest() {
        return erpGuestDao.listAll();
    }
}
