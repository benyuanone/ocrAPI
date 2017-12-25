package com.ourway.sys.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.sys.model.OurwaySysFiles;
import com.ourway.sys.model.OurwaySysPage;

import java.util.List;
import java.util.Map;

/**
 * <p>接口 FilesService.java : <p>
 * <p>说明：文件</p>
 * <pre>
 * @author cc
 * @date 2017/4/12
 * </pre>
 */
public interface FilesService {

    List<OurwaySysFiles> listAllFiles(List<FilterModel> filters, String sortStr);

    List<Map<String, Object>> removeFile(List<Object> owids);

    void updateFile(List<OurwaySysFiles> files);

    void saveOrUpdate(OurwaySysFiles ourwaySysFiles);

    void saveOrUpdateAll(List<OurwaySysFiles> ourwaySysFiles);

    void saveOrUpdate(String owid,String oldOwid);

    List<OurwaySysFiles> listAllFilesByOwid(String owid);

    PageInfo<OurwaySysFiles> listAllFilesByPages(List<FilterModel> filters, String sortStr, int pageSize, int pageNo);

}
