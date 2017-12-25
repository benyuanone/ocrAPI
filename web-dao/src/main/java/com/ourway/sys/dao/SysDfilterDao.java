package com.ourway.sys.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysDfilter;

import java.util.List;

/**<p>接口 SysDfilterDao.java : <p>
 *<p>说明：</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:42
</pre>
 */
public interface SysDfilterDao extends BaseService<OurwaySysDfilter> {
    /*
    *<p>功能描述：listDfilterForPage  分页显示数据过滤表信息</p >
    *<ul>
    *<li>@param [flist]</li>
    *<li>@return com.ourway.base.model.ResponseMessage</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/6/2 0002 下午 1:52</li>
    *</ul>
    */
    PageInfo<OurwaySysDfilter> listDfilterForPage(List<FilterModel> flist, Integer pageNo, Integer pageSize,String sortStr);

    void removeFilterByUserOwid(String owid);

    boolean doCheckSameUserApi(OurwaySysDfilter ourwaySysDfilter);
}
