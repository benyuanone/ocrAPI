package com.ourway.sys.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.dao.SysTemplateDao;
import com.ourway.sys.model.OurwaySysPage;
import com.ourway.sys.model.OurwaySysTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>接口 SysTemplateDaoImpl.java : <p>
 * <p>说明：模板类</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:44
 * </pre>
 */
@Repository("sysTemplateDao")
public class SysTemplateDaoImpl extends BaseServiceImpl<OurwaySysTemplate> implements SysTemplateDao {

    @Override
    public PageInfo<OurwaySysTemplate> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        HqlStatement hqlStatement = new HqlStatement(OurwaySysTemplate.class, filters, sortStr);
        return listHqlForPage(hqlStatement.getHql(), hqlStatement.getCountHql(), hqlStatement.getParams(), pageNo, pageSize);

    }
}
