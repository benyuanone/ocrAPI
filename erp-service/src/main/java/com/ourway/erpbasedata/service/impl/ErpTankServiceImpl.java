package com.ourway.erpbasedata.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.erpbasedata.dao.ErpGoodsDao;
import com.ourway.erpbasedata.dao.ErpTankDao;
import com.ourway.erpbasedata.dao.ErpTankListDao;
import com.ourway.erpbasedata.model.ErpGoods;
import com.ourway.erpbasedata.model.ErpTank;
import com.ourway.erpbasedata.model.ErpTankList;
import com.ourway.erpbasedata.service.ErpTankService;
import com.ourway.sys.model.OurwaySysApi;
import com.ourway.sys.model.OurwaySysApiDetail;
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
@Service("erpTankService")
public class ErpTankServiceImpl implements ErpTankService {

    @Autowired
    ErpTankDao erpTankDao;

    @Autowired
    ErpTankListDao erpTankListDao;

    @Override
    public Boolean doCheckUniqueUrl(ErpTank erpTank) {
        return erpTankDao.doCheckUniqueUrl(erpTank);
    }


    @Override
    public PageInfo<ErpTank> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        return erpTankDao.listHqlForPage(filters, pageNo, pageSize, sortStr);
    }

    @Override
    public List<ErpTank> listTankList(Map<String, Object> dataMap, String sortStr) {
        return erpTankDao.listAllByParam(dataMap,sortStr);
    }

    @Override
    public ErpTank saveOrUpdateErpTank(ErpTank erpTank, List<ErpTankList> erpTankListList) {
        erpTankDao.saveOrUpdate(erpTank);
        if (null != erpTankListList && erpTankListList.size() > 0)
            for (ErpTankList erpTankLists : erpTankListList) {
                if (null == erpTankLists.getUpdateFlag()) {
                    erpTankLists.setUpdateFlag(0);
                }
                switch (erpTankLists.getUpdateFlag()) {
                    //0 新增
                    case 0:
                        //什么也不做
                        break;
                    //修改
                    case 1:
                        erpTankLists.setErptankRefOwid(erpTank.getOwid());
                        //获取里面的商品主见
                        erpTankLists.setErpgoodsRefOwid(erpTankLists.getErpGoods().getOwid());
                        // 判断 owid
                        if (TextUtils.isEmpty(erpTankLists.getOwid())) {
                            //为空 新增
                            erpTankListDao.save(erpTankLists);
                        } else {
                            erpTankListDao.update(erpTankLists);
                        }
                        break;
                    //删除
                    case 2:
                        erpTankListDao.removeByIds(erpTankLists.getOwid());
                        break;
                }
            }
        return erpTank;
    }

    @Override
    public List<Map<String, Object>> removeErpTankByIds(List<String> list) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();

        for (String owid : list) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            //先根据 owid 查询 记录
            params.put("owid", owid);
            ErpTank oapi = erpTankDao.getOneByParams(params, null);
            if (null != oapi) {
                //先删子表信息
                erpTankListDao.removeErpTankList(owid);
                erpTankDao.removeEntity(oapi);
                objs.add(params);
            }
        }
        return objs;
    }

    @Override
    public ErpTank detailErpTank(String owid) {
        ErpTank oapi = erpTankDao.getOneById(owid);
        return oapi;
    }

    @Override
    public List<Object> listErpTankByLibraryArea(List<FilterModel> filters) {
        return erpTankDao.listErpTankByLibraryArea(filters);
    }
}
