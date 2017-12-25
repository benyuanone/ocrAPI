package com.ourway.sys.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.BaseService;
import com.ourway.sys.model.OurwaySysMenus;

import java.util.List;
import java.util.Map;

/**
 * <p>接口 SysMenusDao.java : <p>
 * <p>说明：主页</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:43
 * </pre>
 */
public interface SysMenusDao extends BaseService<OurwaySysMenus> {


    /**
     * <p>方法:listEmpMenus 根据员工获取对应的菜单 </p>
     * <ul>
     * <li> @param employs 员工</li>
     * <li>@return java.util.List<com.ourway.sys.model.OurwaySysMenus>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/6 12:12  </li>
     * </ul>
     */
    List<OurwaySysMenus> listEmpMenus(String employId);

    List<OurwaySysMenus> listRootMenus();


    PageInfo<OurwaySysMenus> listHqlForPage(List<FilterModel> models, int pageNo, int pageSize);

    void removeMenu(Map<String, Object> params);

    List<OurwaySysMenus> listMenusByPath(String path);

    List<OurwaySysMenus> listSubMenus(OurwaySysMenus menus);

    List<OurwaySysMenus> listAllMenu();

    /**
     * <p>方法:updateMenuLink 当页面模板更新的时候，更新菜单中的链接 </p>
     * <ul>
     * <li> @param pageId 页面id</li>
     * <li> @param path 链接</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/6/11 23:56  </li>
     * </ul>
     */
    void updateMenuLink(String pageId, String path);

    List<Map<String, Object>> listMenusByPageCA(String pageCa);
}
