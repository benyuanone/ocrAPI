package com.ourway.erpbasedata.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.erpbasedata.dao.ErpPipeDao;
import com.ourway.erpbasedata.model.ErpPipe;
import com.ourway.sys.model.OurwaySysDic;
import com.ourway.sys.model.OurwaySysDicValue;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kevin on 2017-09-11.
 */
@Repository("erpPipeDao")
public class ErpPipeDaoImpl extends BaseServiceImpl<ErpPipe> implements ErpPipeDao{

    @Override
    public boolean doCheckUnique(ErpPipe erpPipe){
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = " from ErpPipe where pipeId=:pipeId ";
        params.put("pipeId", erpPipe.getPipeId());
        if (!TextUtils.isEmpty(erpPipe.getOwid())) {
            hql += " and owid<>:owid";
            params.put("owid", erpPipe.getOwid());
        }
        ErpPipe temp = getOneByHql(hql, params);
        if (null == temp)
            return true;
        else
            return false;
    }
    @Override
    public List<ErpPipe> listHqlForPage(Map<String,Object> params){
        HqlStatement hqlStatement = new HqlStatement(ErpPipe.class,params);
        return listAllByHql(hqlStatement.getHql(),params);
    }

    @Override
    public PageInfo<ErpPipe> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        HqlStatement hqlStatement = new HqlStatement(ErpPipe.class, filters, sortStr);
        PageInfo<ErpPipe> pageInfo = listHqlForPage(hqlStatement.getHql(), hqlStatement.getCountHql(), hqlStatement.getParams(), pageNo, pageSize);
        for(ErpPipe pp:pageInfo.getRecords()){
            Map<String ,Object> _map = new HashMap<>();
//            公升/米=3.14*（规格前-规格后*2）²/4/1000
//            容量(m³)=长度(m)*公升/米/1000
            double pro = pp.getStandard1().doubleValue();
            double after = pp.getStandard2().doubleValue();
            double capacity = 3.14*(Math.pow((pro-after*2),2))/4/1000;
            double literPM = pp.getLength().doubleValue()*capacity/1000;
//            _map.put("capacity",String.valueOf(capacity));
//            _map.put("literPerM",String.valueOf(literPM));
            pp.setCapacity(capacity);
            pp.setLiterPerM(literPM);
        }
        return pageInfo;
    }

    public List<Map<String, Object>> listAllDataByType(Integer type,String orderBy) {
        String hql = " from OurwaySysDic a,OurwaySysDicValue b where a.owid=b.dicRefOwid and a.type=:type and b.language='chn'";
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("type", type);
        if(!TextUtils.isEmpty(orderBy))
            hql +=" order by "+orderBy;
        List<Object[]> objs = listObjsAllByHql(hql, params);
        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>(objs.size());
        for (Object[] objects : objs) {
            OurwaySysDic dic = (OurwaySysDic) objects[0];
            OurwaySysDicValue val = (OurwaySysDicValue) objects[1];
            Map<String, Object> dataMap = new HashMap<String, Object>();
            BeanUtil.obj2Map(val, dataMap);
            BeanUtil.obj2Map(dic, dataMap);
            datas.add(dataMap);
        }
        return datas;
    }
}
