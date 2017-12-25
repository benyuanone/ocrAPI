package com.ourway.sys.dao.impl;

import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.dao.SysApiDetailDao;
import com.ourway.sys.model.OurwaySysApi;
import com.ourway.sys.model.OurwaySysApiDetail;
import org.springframework.stereotype.Repository;

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
@Repository("apiDetailDao")
public class SysApiDetailDaoImpl extends BaseServiceImpl<OurwaySysApiDetail> implements SysApiDetailDao{

    @Override
    public void removeApiDetail(String owid) {
         /* 根据apiowid 查询 从表信息*/
        Map<String,Object> _params = new HashMap<String,Object>();
        _params.put("apiRefOwid",owid);
        String hql = " from OurwaySysApiDetail where apiRefOwid =:apiRefOwid";
        List<OurwaySysApiDetail> list = listAllByHql(hql,_params);
        /*非空 从数据库中删除查询到的从表信息*/
        if((null != list)&&(list.size() > 0)){
            for(OurwaySysApiDetail ourwaySysApiDetail : list){
                removeEntity(ourwaySysApiDetail);
            }
        }
    }


    @Override
    public List<OurwaySysApiDetail> listAllByAPi(OurwaySysApi oapi) {

        Map<String,Object> _params = new HashMap<String,Object>();
        _params.put("apiRefOwid",oapi.getOwid());

        HqlStatement hql = new HqlStatement(OurwaySysApiDetail.class,_params);

        return listAllByHql(hql.getHql(),hql.getParams());
    }
}
