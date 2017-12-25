package com.ourway.sys.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.sys.model.OurwaySysEmploys;
import com.ourway.sys.model.OurwaySysMenus;

import java.util.List;
import java.util.Map;

/**
 * <p>接口 MenusService.java : <p>
 * <p>说明：菜单</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 14:37
 * </pre>
 */
public interface MenusService {
    void saveOrUpdate(OurwaySysMenus menu);

    void removeMenu(Map<String, Object> params);

    PageInfo<OurwaySysMenus> listHqlForPage(List<FilterModel> models, int pageNo, int pageSize);

    List<OurwaySysMenus> listRoleByStatement(List<FilterModel> models);

    /**
     * <p>方法:listEmployMenus 根据员工来获取菜单 </p>
     * <ul>
     * <li> @param employs 指定的员工</li>
     * <li>@return java.util.List<com.ourway.sys.model.OurwaySysMenus>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/6 12:04  </li>
     * </ul>
     */
    List<OurwaySysMenus> listEmployMenus(OurwaySysEmploys employs);

    void saveOrUpdateAll(List<OurwaySysMenus> menuss);

    List<OurwaySysMenus> listMenusByPath(String path);

    List<OurwaySysMenus> listAllMenu();

    /*用来更新节点，更新的时候，把相应的子节点也做更新*/
    void update(List<OurwaySysMenus> menus);

    OurwaySysMenus listOneByOwid(int owid);
    //根据pageca获取相关的menu，包括控件和按钮
    List<Map<String,Object>> listMenusByPageCA(String pageCa);
}
