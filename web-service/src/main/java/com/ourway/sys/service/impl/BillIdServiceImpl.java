package com.ourway.sys.service.impl;


import com.ourway.base.utils.CacheUtil;
import com.ourway.base.utils.DateUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.sys.dao.SysBillIdDao;
import com.ourway.sys.model.OurwaySysBillid;
import com.ourway.sys.service.BillIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>接口 ApiDetailService.java : <p>
 * <p>说明：接口详细类</p>
 * <pre>
 * @author cc
 * @date 14:35 2017/4/12
 * </pre>
 */
@Service("billIdService")
public class BillIdServiceImpl implements BillIdService {
    public static final String BILLID = "sys.billId.";

    @Autowired
    SysBillIdDao sysBillIdDao;


    @Override
    public void updateListId2Redis(String key, String pre, String ymd, int times) {
        if(!TextUtils.isEmpty(ymd))
            ymd = DateUtil.getDateStr(ymd);
        List<OurwaySysBillid> ids = sysBillIdDao.listBillIds(key, pre, ymd, times);
        List<OurwaySysBillid> menIds = CacheUtil.getVals(BILLID + key +pre+ ymd, OurwaySysBillid.class);
        if (null == menIds)
            menIds = new ArrayList<OurwaySysBillid>();
        for (OurwaySysBillid id : ids) {
            menIds.add(id);
            id.setBillStatus((byte) 1);//表示客户已经领用
        }
        sysBillIdDao.saveOrUpdate((Collection) ids);
        CacheUtil.setVal(BILLID + key + pre + ymd, menIds);
    }

    @Override
    public void saveNewIds(String key, String pre, String ymd, int times, int len) {
        sysBillIdDao.saveNewIds(key,pre, ymd, times, len);
    }
}
