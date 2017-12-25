package com.ourway.sys.dao;

import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysBillid;
import com.ourway.sys.model.OurwaySysTemplate;

import java.util.List;

public interface SysBillIdDao extends BaseService<OurwaySysBillid> {

    /**
    *<p>方法:saveNewIds 根据关键字和日期产生序号 </p>
    *<ul>
     *<li> @param key 关键字</li>
     *<li> @param ymd 日期</li>
     *<li> @param times 条数</li>
    *<li>@return java.util.List<com.ourway.sys.model.OurwaySysBillid>  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/6/1 15:40  </li>
    *</ul>
    */
    void saveNewIds(String key,String pre, String ymd,int times,int len);

    List<OurwaySysBillid> listBillIds(String key,String pre,String ymd,int times);
}
