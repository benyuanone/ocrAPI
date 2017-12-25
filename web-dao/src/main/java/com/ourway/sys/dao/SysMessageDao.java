package com.ourway.sys.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysMessage;

import java.util.List;

/**<p>接口 SysMessageDao.java : <p>
 *<p>说明：消息类</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:43
</pre>
 */
public interface SysMessageDao extends BaseService<OurwaySysMessage> {

    List<OurwaySysMessage> listAllMessageByEmpId(String empId);

    PageInfo<OurwaySysMessage> listMessagePageByEmpId(List<FilterModel> filterModelList, Integer pageNo, Integer pageSize,String sortStr);
}
