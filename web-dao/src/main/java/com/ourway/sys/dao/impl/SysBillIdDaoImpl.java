package com.ourway.sys.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.DateUtil;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.sys.dao.SysBillIdDao;
import com.ourway.sys.model.OurwaySysBillid;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * <p>接口 SysApiDaoImpl.java : <p>
 * <p>说明：API接口</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:41
 * </pre>
 */
@Repository("sysBillIdDao")
public class SysBillIdDaoImpl extends BaseServiceImpl<OurwaySysBillid> implements SysBillIdDao {
    @Override
    public void saveNewIds(String key,String pre, String ymd, int times,int len) {
        String _ymd = "";
        String hql = "";
        int maxid = 0;
        if (!TextUtils.isEmpty(ymd))
            _ymd = DateUtil.getDateStr(ymd);
        hql = "  from OurwaySysBillid where billType=:billType and billYmd=:billYmd order by billXh desc";
        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("billType", key);
        map.put("billYmd", pre+_ymd);
        OurwaySysBillid bill = getOneByHql(hql, map);
        if (null != bill && null != bill.getBillXh())
            maxid = bill.getBillXh();
        List<OurwaySysBillid> svObjs = new ArrayList<OurwaySysBillid>(times);
        for (int index = 1; index < (times - 1); index++) {
            OurwaySysBillid billid = new OurwaySysBillid();
            billid.setBillType(key);
            billid.setBillYmd(pre+_ymd);
            billid.setBillXh(maxid
                    + index);
            billid.setBillXhlabel(pre+_ymd+getLabel(billid.getBillXh(),len));
            billid.setBillStatus((byte) 0);
            svObjs.add(billid);
        }
        saveOrUpdate((Collection)svObjs);
    }

    private String getLabel(int xh,int len){
        String _xh = xh+"";
        int loops = 0;
        if(_xh.length()<len)
            loops = len-_xh.length();
        for (int i = 0; i <loops ; i++) {
            _xh = "0"+_xh;
        }
        return _xh;
    }

    @Override
    public List<OurwaySysBillid> listBillIds(String key,String pre, String ymd, int times) {
        String _ymd = "";
        if (!TextUtils.isEmpty(ymd))
            _ymd = DateUtil.getDateStr(ymd);
        Map<String,Object> params = new HashMap<String,Object>(3);
        params.put("billType",key);
        params.put("billYmd",pre+_ymd);
        params.put("billStatus",(byte)0);
        HqlStatement hqlStatement = new HqlStatement(OurwaySysBillid.class,params," billXh ");
        PageInfo<OurwaySysBillid> bills = listHqlForPage(hqlStatement.getHql(),hqlStatement.getCountHql(),params,1,times);
        return bills.getRecords();
    }
}
