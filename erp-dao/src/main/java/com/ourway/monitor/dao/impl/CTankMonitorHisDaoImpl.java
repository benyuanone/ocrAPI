package com.ourway.monitor.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.monitor.dao.CTankMonitorHisDao;
import com.ourway.monitor.model.CTankMonitorHis;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**<p>接口 SysApiDetailDaoImpl.java : <p>
 *<p>说明：接口详情</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:41
</pre>
 */
@Repository("cTankMonitorHisDao")
public class CTankMonitorHisDaoImpl extends BaseServiceImpl<CTankMonitorHis> implements CTankMonitorHisDao {


    @Override
    public PageInfo<CTankMonitorHis> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        HqlStatement hqlStatement = new HqlStatement(CTankMonitorHis.class, filters, sortStr);
        return listHqlForPage(hqlStatement.getHql(), hqlStatement.getCountHql(), hqlStatement.getParams(), pageNo, pageSize);
    }

    @Override
    public List<CTankMonitorHis> listTankMonitorHisByParam(Map<String, Object> map) {
        String hql = "";
        Map<String, Object> params = null;
//        map.put("tankId","TK0001");
//        map.put("beginTime","2017-11-08 11:27:37");
//        map.put("endTime","2017-11-09 11:27:37");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date dateOfBegin = sf.parse(map.get("beginTime").toString());
            Date dateOfEndTime = sf.parse(map.get("endTime").toString());
            if (TextUtils.isEmpty(map)) {
                hql = "from CTankMonitorHis  order by tankId";
            } else {
//                hql = "from CTankMonitorHis where tankId = :tankId and beginTime <> :beginTime";
                hql = "from CTankMonitorHis where tankId = :tankId and beginTime  between  :beginTime and :endTime ";
                params = new HashMap<String, Object>(3);
                params.put("tankId",  map.get("tankId"));
                params.put("beginTime", dateOfBegin);
                params.put("endTime",dateOfEndTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listAllByHql(hql, params);
    }
}
