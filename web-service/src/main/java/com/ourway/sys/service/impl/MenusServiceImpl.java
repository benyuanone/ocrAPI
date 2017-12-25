package com.ourway.sys.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.TextUtils;
import com.ourway.sys.dao.SysMenusDao;
import com.ourway.sys.dao.SysPageDao;
import com.ourway.sys.model.OurwaySysEmploys;
import com.ourway.sys.model.OurwaySysMenus;
import com.ourway.sys.service.MenusService;
import com.ourway.sys.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>接口 MenusService.java : <p>
 * <p>说明：菜单</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 14:37
 * </pre>
 */
@Service("menuService")
public class MenusServiceImpl implements MenusService {
    @Autowired
    SysMenusDao sysMenusDao;
    @Autowired
    PageService pageService;

    @Override
    public void saveOrUpdate(OurwaySysMenus menu) {
        sysMenusDao.saveOrUpdate(menu);
    }

    @Override
    public PageInfo<OurwaySysMenus> listHqlForPage(List<FilterModel> models, int pageNo, int pageSize) {
        return sysMenusDao.listHqlForPage(models, pageNo, pageSize);
    }

    @Override
    public List<OurwaySysMenus> listRoleByStatement(List<FilterModel> models) {
        PageInfo<OurwaySysMenus> pageInfo = sysMenusDao.listHqlForPage(models, 1, 0);
        if (null != pageInfo)
            return pageInfo.getRecords();
        return null;
    }

    @Override
    public List<OurwaySysMenus> listEmployMenus(OurwaySysEmploys employs) {
        Map<String, String> pageMap = new HashMap<String, String>();
        List<OurwaySysMenus> menus = new ArrayList<OurwaySysMenus>();
        if ("root".equals(employs.getEmpId()))
            menus = sysMenusDao.listRootMenus();
        else {
            //根据权限获取相应的菜单
            menus = sysMenusDao.listEmpMenus(employs.getOwid());
        }
//        防止页面配置的时候，如果配置模板编号了，菜单的链接也要更改
//        for (OurwaySysMenus menu : menus) {
//            if (!TextUtils.isEmpty(pageMap.get(menu.getPageId()))) {
//                menu.setLink(pageMap.get(menu.getPageId()));
//            } else {
//                pageMap.put(menu.getPageId(), pageService.detailPageByPageId(menu.getPageId()));
//                menu.setLink(pageService.detailPageByPageId(menu.getPageId()));
//            }
//        }

        return menus;
    }

    @Override
    public void removeMenu(Map<String, Object> params) {
        sysMenusDao.removeMenu(params);
    }

    @Override
    public void saveOrUpdateAll(List<OurwaySysMenus> menuss) {
        sysMenusDao.saveOrUpdate((Collection) menuss);
    }

    @Override
    public List<OurwaySysMenus> listMenusByPath(String path) {
        return sysMenusDao.listMenusByPath(path);
    }

    @Override
    public List<OurwaySysMenus> listAllMenu() {
        return sysMenusDao.listAllMenu();
    }

    @Override
    public void update(List<OurwaySysMenus> menus) {
        List<OurwaySysMenus> subMenus = null;
        List<OurwaySysMenus> updateMenus = null;
        for (OurwaySysMenus ourwaySysMenus : menus) {
            //1.get the all subMenus
            OurwaySysMenus _menu = sysMenusDao.getOneById(ourwaySysMenus.getOwid());
            //如果路径不一致，说明该国路径了，需要更新其下面的子节点
            if (!_menu.getPath().equalsIgnoreCase(ourwaySysMenus.getPath())) {
                subMenus = sysMenusDao.listSubMenus(ourwaySysMenus);
                if (null != subMenus && subMenus.size() > 0) {
                    updateMenus = new ArrayList<OurwaySysMenus>();
                    for (OurwaySysMenus subMenu : subMenus) {
                        subMenu.setPath(ourwaySysMenus.getPath() + "/" + subMenu.getPath().split("/" + ourwaySysMenus.getOwid() + "/")[1]);
                        updateMenus.add(subMenu);
                    }
                    sysMenusDao.saveOrUpdate(updateMenus);
                }
            }
            _menu.setFid(ourwaySysMenus.getFid());
            _menu.setPx(ourwaySysMenus.getPx());
            _menu.setPath(ourwaySysMenus.getPath());
            _menu.setCc(ourwaySysMenus.getCc());
            sysMenusDao.update(_menu);
        }
    }

    @Override
    public OurwaySysMenus listOneByOwid(int owid) {
        return sysMenusDao.getOneById(owid);
    }

    @Override
    public List<Map<String, Object>> listMenusByPageCA(String pageCa) {
        return sysMenusDao.listMenusByPageCA(pageCa);
    }
}
