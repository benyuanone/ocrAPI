package com.ourway.sys.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.OurwaySysLog;
import com.ourway.base.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p>接口 SysLogDao.java : <p>
 * <p>说明：日志</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:43
 * </pre>
 */
public interface SysLogDao extends BaseService<OurwaySysLog> {

    /**
     * <p>功能描述：countObject 获取业务类对象数量</p>
     * <ul>
     * <li>@param []</li>
     * <li>@return int</li>
     * <li>@throws </li>
     * <li>@author jackson</li>
     * <li>@date 17-5-29 下午1:51</li>
     * </ul>
     */
    int countObject(String owid);

    PageInfo<OurwaySysLog> listHqlForPage(List<FilterModel> models, int pageNo, int pageSize, String sortStr);

    void removeAllLogs();

    List<String> listAllDistinctClassName(String owid);

    Map<String, List<OurwaySysLog>> listRefLogsByOwid(String owid);

    Map<String, List<OurwaySysLog>> listRefLogsByOwid(String owid, String label);
}
