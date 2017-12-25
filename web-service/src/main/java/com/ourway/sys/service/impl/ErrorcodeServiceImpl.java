package com.ourway.sys.service.impl;


import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.dao.SysErrorDao;
import com.ourway.sys.model.OurwaySysErrorcode;
import com.ourway.sys.service.ErrorcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**<p>接口 ErrorcodeService.java : <p>
 *<p>说明：错误编码</p>
 *<pre>
 *@author cc
 *@date 2017/4/12
</pre>
 */
@Service("errorCode")
public class ErrorcodeServiceImpl implements ErrorcodeService {
    @Autowired
    private SysErrorDao sysErrorDao;

    @Override
    public Boolean doCheckUniqueCode(OurwaySysErrorcode errorCode){
        return sysErrorDao.doCheckUniqueCode(errorCode);

    }

    @Override
    public void saveOrUpdateErrorCode(OurwaySysErrorcode errorCode) {
        if(null == errorCode.getTemplateVisible()){
            errorCode.setTemplateVisible((byte)0);
        }
        sysErrorDao.saveOrUpdate(errorCode);
    }

    @Override
    public PageInfo<OurwaySysErrorcode> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize) {

        PageInfo<OurwaySysErrorcode> pageInfo = sysErrorDao.listHqlForPage(filters,pageNo,pageSize);

        if(null != pageInfo.getRecords() && pageInfo.getRecords().size()>0) {

            List<OurwaySysErrorcode> list = pageInfo.getRecords();
            for (OurwaySysErrorcode errorcode : list) {

                String label = (errorcode.getTemplateVisible() == 1) ? "有效" : "失效";
                errorcode.setVisibleLabel(label);

                /* 类别名称 应该是字典表中的存在dicVal1 的值*/
               /* if(!TextUtils.isEmpty(errorcode.getErrorType())){
                    OurwaySysDicValue dicValue = DicUtils.get
                }*/
            }
        }
        return pageInfo;
    }


    @Override
    public List<Map<String, Object>> removeItems(List<String> owids) {

        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(String owid : owids){
            Map<String,Object> param = new HashMap<>(1);
            param.put("owid",owid);
            sysErrorDao.removeByParams(param);
            list.add(param);
        }
        return list;
    }

    @Override
    public OurwaySysErrorcode detailOneErrorCode(String owid) {
        Map<String,Object> param = new HashMap<String,Object>(1);
        param.put("owid",owid);
        HqlStatement hql = new HqlStatement(OurwaySysErrorcode.class,param);

         return sysErrorDao.getOneByHql(hql.getHql(),hql.getParams());
    }
}
