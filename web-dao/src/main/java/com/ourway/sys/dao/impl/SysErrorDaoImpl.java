package com.ourway.sys.dao.impl;


import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.sys.dao.SysErrorDao;
import com.ourway.sys.model.OurwaySysErrorcode;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**<p>接口 SysErrorDaoImpl.java : <p>
 *<p>说明：错误编号</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:42
</pre>
 */
@Repository("errorDao")
public class SysErrorDaoImpl extends BaseServiceImpl<OurwaySysErrorcode> implements SysErrorDao {
    @Override
    public Boolean doCheckUniqueCode(OurwaySysErrorcode errorcode) {

        String hql = "";
        Map<String,Object> _params = new HashMap<String,Object>();
        _params.put("errorCode",errorcode.getErrorCode());
        if(!ValidateUtils.isEmpty(errorcode.getOwid())){
            hql = "from OurwaySysErrorcode where errorCode=:errorCode and owid<>:owid";
            _params.put("owid",errorcode.getOwid());

        }else{
            hql = "from OurwaySysErrorcode where errorCode=:errorCode";
        }

        List<OurwaySysErrorcode> result =  listAllByHql(hql,_params);
        if(null!=result&&result.size()>0){
            return false;
        }
        return true;
    }


    @Override
    public PageInfo<OurwaySysErrorcode> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize) {
        HqlStatement hql = new HqlStatement(OurwaySysErrorcode.class,filters);
        //Map<String,Object> map = hql.getParams();
        PageInfo<OurwaySysErrorcode> pageInfo = listHqlForPage(hql.getHql(),hql.getCountHql(),hql.getParams(),pageNo,pageSize);

        return pageInfo;
    }
}
