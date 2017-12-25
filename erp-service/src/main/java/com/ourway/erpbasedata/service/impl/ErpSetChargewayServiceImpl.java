package com.ourway.erpbasedata.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.TextUtils;
import com.ourway.erpbasedata.dao.ErpSetChargewayDao;
import com.ourway.erpbasedata.dao.ErpSetChargewayListDao;
import com.ourway.erpbasedata.model.ErpSetChargeway;
import com.ourway.erpbasedata.model.ErpSetChargewayList;
import com.ourway.erpbasedata.service.ErpSetChargewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>接口 ErpTankServiceImpl.java : <p>
 * <p>说明：TODO</p>
 * <pre>
 * @author zhangxinyi
 * @date 2017-06-01 13:27
 * </pre>
 */
@Service("erpSetChargewayService")
public class ErpSetChargewayServiceImpl implements ErpSetChargewayService {

    @Autowired
    ErpSetChargewayDao erpSetChargewayDao;

    @Autowired
    ErpSetChargewayListDao erpSetChargewayListDao;

    @Override
    public Boolean doCheckUniqueUrl(ErpSetChargeway erpSetChargeway) {
        return erpSetChargewayDao.doCheckUniqueUrl(erpSetChargeway);
    }


    @Override
    public PageInfo<ErpSetChargeway> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        return erpSetChargewayDao.listHqlForPage(filters, pageNo, pageSize, sortStr);
    }


    @Override
    public ErpSetChargeway saveOrUpdateErpSetChargeway(ErpSetChargeway erpSetChargeway, List<ErpSetChargewayList> erpSetChargewayLists) {
        erpSetChargewayDao.saveOrUpdate(erpSetChargeway);
        if (null != erpSetChargewayLists && erpSetChargewayLists.size() > 0)
            for (ErpSetChargewayList sct : erpSetChargewayLists) {
                if (null == sct.getUpdateFlag()) {
                    sct.setUpdateFlag(0);
                }
                switch (sct.getUpdateFlag()) {
                    //0 新增
                    case 0:
                        //什么也不做
                        break;
                    //修改
                    case 1:
                        sct.setErpsetchargewayRefOwid(erpSetChargeway.getOwid());
                        // 判断 owid
                        if (TextUtils.isEmpty(sct.getOwid())) {
                            //为空 新增
                            erpSetChargewayListDao.save(sct);
                        } else {
                            erpSetChargewayListDao.update(sct);
                        }
                        break;
                    //删除
                    case 2:
                        erpSetChargewayDao.removeByIds(sct.getOwid());
                        break;
                }
            }
        return erpSetChargeway;
    }

    @Override
    public List<Map<String, Object>> removeErpSetChargewayByIds(List<String> list) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();

        for (String owid : list) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            //先根据 owid 查询 记录
            params.put("owid", owid);
            ErpSetChargeway oapi = erpSetChargewayDao.getOneByParams(params, null);
            if (null != oapi) {
                //先删子表信息
//                erpTankListDao.removeErpTankList(owid);
                erpSetChargewayDao.removeEntity(oapi);
                objs.add(params);
            }
        }
        return objs;
    }

    @Override
    public List<ErpSetChargeway> listErpSetChargewayList(Map<String, Object> dataMap, String sortStr) {
        return null;
    }

    @Override
    public ErpSetChargeway detailErpSetChargeway(String owid) {
        ErpSetChargeway oapi = erpSetChargewayDao.getOneById(owid);
        return oapi;
    }

}
