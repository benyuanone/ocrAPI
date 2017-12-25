package com.ourway.sys.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.sys.model.OurwaySysLayout;
import com.ourway.sys.model.OurwaySysPage;
import com.ourway.sys.model.OurwaySysPageComponent;

import java.util.List;
import java.util.Map;

/**
 * <p>接口 PageService.java : <p>
 * <p>说明：</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 14:37
 * </pre>
 */
public interface PageService {

    /**
     * <p>方法:doCheckUniquePageCA 判断页面的pageca是否唯一 </p>
     * <ul>
     * <li> @param ourwaySysPage 对象</li>
     * <li>@return java.lang.Boolean  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/4/25 22:19  </li>
     * </ul>
     */
    Boolean doCheckUniquePageCA(OurwaySysPage ourwaySysPage);

    void saveOrUpdate(OurwaySysPage ourwaySysPage);

    OurwaySysPage queryOneByPageCA(String pageCA);

    /**
     * <p>方法:queryPage 传入参数，获取当个页面 </p>
     * <ul>
     * <li> @param params 参数</li>
     * <li>@return com.ourway.sys.model.OurwaySysPage  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/22 12:58  </li>
     * </ul>
     */
    OurwaySysPage queryPage(Map<String, Object> params, String sortStr);

    PageInfo<OurwaySysPage> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);

    List<OurwaySysPage> listAllPages(List<FilterModel> filters, String sortStr);

    /**
     * <p>方法:saveAll 保存整个配置页面的配置信息 </p>
     * <ul>
     * <li> @param ourwaySysPage TODO</li>
     * <li> @param ourwaySysPageComponentList TODO</li>
     * <li> @param ourwaySysLayoutList TODO</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/19 9:13  </li>
     * </ul>
     */
    void savePageDetail(OurwaySysPage ourwaySysPage, List<OurwaySysPageComponent> ourwaySysPageComponentList, List<OurwaySysLayout> ourwaySysLayoutList);

    /**
     * <p>方法:saveAsPage 另存为 </p>
     * <ul>
     * <li> @param owid TODO</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/7/28 17:53  </li>
     * </ul>
     */
    OurwaySysPage saveAsPage(String owid);

    List<Map<String, Object>> removePage(List<String> owids);

    /**
     * <p>方法:detailPageByPageId 获取指定pageid的页面链接 </p>
     * <ul>
     * <li> @param pageId 页面owid</li>
     * <li>@return java.lang.String  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/6/11 15:57  </li>
     * </ul>
     */
    String detailPageByPageId(String pageId);

    /**
     * <p> writeContent.java : <p>
     * <p>说明：写入文件</p>
     * <pre>
     * @author cc
     * @date 2017/7/7 9:29
     * </pre>
     */
    String writeContent(String owid);


}
