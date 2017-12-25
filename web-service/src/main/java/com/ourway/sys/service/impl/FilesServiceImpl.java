package com.ourway.sys.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.sys.dao.SysFilesDao;
import com.ourway.sys.model.OurwaySysFiles;
import com.ourway.sys.model.OurwaySysPage;
import com.ourway.sys.service.FilesService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>接口 FilesService.java : <p>
 * <p>说明：文件</p>
 * <pre>
 * @author cc
 * @date 2017/4/12
 * </pre>
 */
@Service("filesService")
public class FilesServiceImpl implements FilesService {
    @Autowired
    SysFilesDao sysFilesDao;

    @Override
    public List<OurwaySysFiles> listAllFiles(List<FilterModel> filters, String sortStr) {
        return sysFilesDao.listAllFiles(filters, sortStr);
    }

    @Override
    public List<Map<String, Object>> removeFile(List<Object> owids) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();
        for (Object owid : owids) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            params.put("owid", owid.toString());
            OurwaySysFiles files = sysFilesDao.getOneByParams(params, "");
            if (null != files) {
                objs.add(params);
                sysFilesDao.removeEntity(files);
            }
        }
        return objs;
    }

    @Override
    public void saveOrUpdate(OurwaySysFiles ourwaySysFiles) {
        sysFilesDao.saveOrUpdate(ourwaySysFiles);
    }

    @Override
    public void saveOrUpdateAll(List<OurwaySysFiles> ourwaySysFiles) {
        sysFilesDao.saveOrUpdate((Collection)ourwaySysFiles);

    }

    @Override
    public void updateFile(List<OurwaySysFiles> files) {
        sysFilesDao.saveOrUpdate((Collection)files);
    }

    @Override
    public void saveOrUpdate(String owid, String oldOwid) {
        sysFilesDao.saveOrUpdate(owid,oldOwid);
    }

    @Override
    public List<OurwaySysFiles> listAllFilesByOwid(String owid) {
        Map<String,Object> map = new HashMap<String,Object>(1);
        map.put("fileClassId",owid);
        return sysFilesDao.listAllByParam(map,"");
    }

    @Override
    public PageInfo<OurwaySysFiles> listAllFilesByPages(List<FilterModel> filters, String sortStr, int pageSize, int pageNo) {
        return sysFilesDao.listAllFilesByPages(filters,sortStr,pageSize,pageNo);
    }
}
