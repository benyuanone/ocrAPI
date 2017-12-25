package com.ourway.sys.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.dao.SysFilesDao;
import com.ourway.sys.model.OurwaySysFiles;
import com.ourway.sys.model.OurwaySysPage;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 SysFilesDaoImpl.java : <p>
 * <p>说明：文件</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:42
 * </pre>
 */
@Repository("sysFilesDao")
public class SysFilesDaoImpl extends BaseServiceImpl<OurwaySysFiles> implements SysFilesDao {

    @Override
    public List<OurwaySysFiles> listAllFiles(List<FilterModel> filters, String sortStr) {
        String hql = " from OurwaySysFiles  ";
        HqlStatement hqlStatement = new HqlStatement(hql, filters, sortStr);
        return listAllByHql(hqlStatement.getHql(), hqlStatement.getParams());
    }

    @Override
    public void saveOrUpdate(String owid, String oldOwid) {
        String hql = "update OurwaySysFiles set fileClassId=:fileClassId where fileClassId=:fileClassIdOld";
        Map<String,Object> params = new HashMap<String,Object>(2);
        params.put("fileClassId",owid);
        params.put("fileClassIdOld",oldOwid);
        updateBulk(hql,params);
    }

    @Override
    public PageInfo<OurwaySysFiles> listAllFilesByPages(List<FilterModel> filters, String sortStr, int pageSize, int pageNo) {
        HqlStatement hqlStatement = new HqlStatement("from OurwaySysFiles",filters,sortStr);

        return listHqlForPage(hqlStatement.getHql(),hqlStatement.getCountHql(),hqlStatement.getParams(),pageNo,pageSize);
    }
}
