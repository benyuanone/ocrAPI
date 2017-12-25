package com.ourway.sys.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysFiles;

import java.util.List;

/**
 * <p>接口 SysFilesDao.java : <p>
 * <p>说明：文件</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:42
 * </pre>
 */
public interface SysFilesDao extends BaseService<OurwaySysFiles> {
    public List<OurwaySysFiles> listAllFiles(List<FilterModel> filters, String sortStr);

    void saveOrUpdate(String owid,String oldOwid);

    PageInfo<OurwaySysFiles> listAllFilesByPages(List<FilterModel> filters, String sortStr, int pageSize, int pageNo);
}
