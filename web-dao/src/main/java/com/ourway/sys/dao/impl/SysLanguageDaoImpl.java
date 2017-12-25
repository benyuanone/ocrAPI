package com.ourway.sys.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.sys.dao.SysLanguageDao;
import com.ourway.sys.model.OurwaySysLanguage;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**<p>方法 SysLanguageDaoImpl : <p>
*<p>说明:多语言数据库实现类</p>
*<pre>
*@author JackZhou
*@date 2017/4/12 13:31
</pre>
*/
@Repository("sysLanguageDao")
public class SysLanguageDaoImpl extends BaseServiceImpl<OurwaySysLanguage> implements SysLanguageDao {

    @Override
    public Boolean doCheckUniqueLabelKey(OurwaySysLanguage language) {
        String hql = " from OurwaySysLanguage where labelKey=:labelKey";
        if(!ValidateUtils.isEmpty(language.getOwid()))
            hql = " from OurwaySysLanguage where labelKey=:labelKey and owid<>:owid";
        Map<String,Object> _params = new HashMap<String,Object>();
        _params.put("labelKey",language.getLabelKey());
        _params.put("owid",language.getOwid());
        List<OurwaySysLanguage> result =  listAllByHql(hql,_params);
        if(null!=result&&result.size()>0)
            return false;
        return true;
    }

    @Override
    public PageInfo<OurwaySysLanguage> listLanguageForPage(List<FilterModel> models,Integer pageNo, Integer pageSize) {
        HqlStatement hql = new HqlStatement(OurwaySysLanguage.class,models," labelKey ");
        return listHqlForPage(hql.getHql(),hql.getCountHql(),hql.getParams(),pageNo,pageSize);
    }
}
