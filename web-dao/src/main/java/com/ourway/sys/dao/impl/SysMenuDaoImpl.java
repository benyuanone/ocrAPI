package com.ourway.sys.dao.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.dao.SysMenusDao;
import com.ourway.sys.model.OurwaySysMenus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 SysMenusDaoImpl.java : <p>
 * <p>说明：主页</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 15:43
 * </pre>
 */
@Repository("sysMenusDao")
public class SysMenuDaoImpl extends BaseServiceImpl<OurwaySysMenus> implements SysMenusDao {

    @Override
    public List<OurwaySysMenus> listEmpMenus(String employId) {
        Map<String,Boolean> repeatFlag = new HashMap<String,Boolean>();
        String hql = "from OurwaySysPrivsuser a,OurwaySysPrivsallocate b,OurwaySysMenus d where ";
        hql += " a.userRefOwid=:userRefOwid and a.roleRefOwid=b.roleRefOwid and b.menuRefOwid=d.owid ";
        hql += " order by d.fid,d.cc,d.px";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userRefOwid", employId);
        Map<String, OurwaySysMenus> menus = new HashMap<String, OurwaySysMenus>();
        List<Object[]> datas = listObjsAllByHql(hql, params);
        //利用map的屬性，去掉重複的
        List<OurwaySysMenus> menuList = new ArrayList<OurwaySysMenus>();
        for (Object[] data : datas) {
            OurwaySysMenus menu = (OurwaySysMenus) data[2];
            if(null==repeatFlag.get(menu.getOwid().toString())){
                menuList.add(menu);
                repeatFlag.put(menu.getOwid().toString(),true);
            }
        }
        return menuList;
    }

    @Override
    public PageInfo<OurwaySysMenus> listHqlForPage(List<FilterModel> models, int pageNo, int pageSize) {
        HqlStatement hqlStatement = new HqlStatement(OurwaySysMenus.class, models, " px ");
        return listHqlForPage(hqlStatement.getHql(), hqlStatement.getCountHql(), hqlStatement.getParams(), pageNo, pageSize);
    }

    @Override
    public void removeMenu(Map<String, Object> params) {
        String hql = "";
        HqlStatement hqlStatement = new HqlStatement(OurwaySysMenus.class, params);
        List<OurwaySysMenus> menus = listAllByHql(hqlStatement.getHql(), hqlStatement.getParams());
        for (OurwaySysMenus menu : menus) {
            hql = "delete from OurwaySysMenus where path like '" + menu.getPath() + "/%' ";
            updateBulk(hql);
            removeEntity(menu);
        }
    }

    @Override
    public List<OurwaySysMenus> listMenusByPath(String path) {
        String hql = " from OurwaySysMenus where path=? or path like ?";
        List<OurwaySysMenus> menus = listAllByParams(hql, path, path + "/%");
        return menus;
    }

    @Override
    public List<OurwaySysMenus> listSubMenus(OurwaySysMenus menus) {
        String hql = "from OurwaySysMenus where path like ?";
        List<OurwaySysMenus> subMenus = listAllByParams(hql, "%/" + menus.getOwid() + "/%");
        return subMenus;
    }

    @Override
    public List<OurwaySysMenus> listAllMenu() {
        String hql = " from OurwaySysMenus ";
        return listAllByHql(hql, null);
    }

    @Override
    public void updateMenuLink(String pageId, String path) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("pageId", pageId);
        List<OurwaySysMenus> menus = listAllByParam(params, "");
        if (null == menus || menus.size() <= 0)
            return;
        for (OurwaySysMenus menu : menus) {
            menu.setLink(path);
            update(menu);
        }
    }

    @Override
    public List<OurwaySysMenus> listRootMenus() {
        String hql = "from OurwaySysMenus  order by px ";
        return listAllByHql(hql, null);
    }

    @Override
    public List<Map<String, Object>> listMenusByPageCA(String pageCa) {
        String hql = "from OurwaySysMenus where (type=1 or type=3) and pageCa like '" + pageCa + "=%'  order by px ";
        List<OurwaySysMenus> menuss = listAllByHql(hql, null);
        if (null != menuss && menuss.size() > 0) {
            List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(menuss.size());
            for (OurwaySysMenus menu : menuss) {
                Map<String, Object> map = new HashMap<String, Object>(2);
                map.put("type", menu.getType());
                map.put("attribute", menu.getPageCa().replaceAll(pageCa + "=", ""));
                result.add(map);
            }
            return result;
        }
        return null;
    }
}
