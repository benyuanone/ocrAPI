package com.ourway.sys.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.DateUtil;
import com.ourway.sys.dao.*;
import com.ourway.sys.model.*;
import com.ourway.sys.service.PageService;
import com.ourway.sys.utils.WriteUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * <p>接口 PageService.java : <p>
 * <p>说明：</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 14:37
 * </pre>
 */
@Service("pageService")
public class PageServiceImpl implements PageService {
    @Autowired
    SysPageDao sysPageDao;
    @Autowired
    SysPageCompDao sysPageCompDao;
    @Autowired
    SysLayoutDao sysLayoutDao;
    @Autowired
    SysLayoutCompDao sysLayoutCompDao;
    @Autowired
    SysTemplateDao templateDao;
    @Autowired
    SysMenusDao sysMenusDao;


    @Override
    public Boolean doCheckUniquePageCA(OurwaySysPage ourwaySysPage) {
        return sysPageDao.doCheckUniquePageCA(ourwaySysPage);
    }

    @Override
    public void saveOrUpdate(OurwaySysPage ourwaySysPage) {
        sysPageDao.saveOrUpdate(ourwaySysPage);
    }

    @Override
    public OurwaySysPage queryOneByPageCA(String pageCA) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pageCa", pageCA);
        return sysPageDao.getOneByParams(params, "");
    }


    @Override
    public PageInfo<OurwaySysPage> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        PageInfo<OurwaySysPage> pageInfo = sysPageDao.listHqlForPage(filters, pageNo, pageSize, sortStr);
        //处理如果有模板页面，显示想要的模板页面
        List<OurwaySysTemplate> templateList = templateDao.listAll();
        Map<String, OurwaySysTemplate> templateMap = new HashMap<String, OurwaySysTemplate>(templateList.size());
        for (OurwaySysTemplate template : templateList) {
            templateMap.put(template.getOwid(), template);
        }
        for (OurwaySysPage ourwaySysPage : pageInfo.getRecords()) {
            if (null != templateMap.get(ourwaySysPage.getPageTemplate()))
                ourwaySysPage.setPageTemplateLabel(templateMap.get(ourwaySysPage.getPageTemplate()).getTemplateName());
        }
        return pageInfo;
    }

    @Override
    public void savePageDetail(OurwaySysPage ourwaySysPage, List<OurwaySysPageComponent> ourwaySysPageComponentList, List<OurwaySysLayout> ourwaySysLayoutList) {
        sysPageDao.saveOrUpdate(ourwaySysPage);
        updateMenuLink(ourwaySysPage);
        //先做删除
        sysPageCompDao.removeComponents(ourwaySysPage);
        sysLayoutDao.removeLayoutByPage(ourwaySysPage);
        if (null != ourwaySysPageComponentList && ourwaySysPageComponentList.size() > 0)
            for (OurwaySysPageComponent pageComponent : ourwaySysPageComponentList) {
                pageComponent.setPageRefOwid(ourwaySysPage.getOwid());
                sysPageCompDao.save(pageComponent);
            }
        if (null != ourwaySysLayoutList && ourwaySysLayoutList.size() > 0)
            for (OurwaySysLayout ourwaySysLayout : ourwaySysLayoutList) {
                ourwaySysLayout.setPageRefOwid(ourwaySysPage.getOwid());
                sysLayoutDao.save(ourwaySysLayout);
                if (null != ourwaySysLayout.getComponents() && ourwaySysLayout.getComponents().size() > 0) {
                    for (OurwaySysLayoutComponent ourwaySysLayoutComponent : ourwaySysLayout.getComponents()) {
                        if (null != ourwaySysLayoutComponent.getCompOrder() && ourwaySysLayoutComponent.getCompOrder() == -1) {
                            //表示不再控件列表中的
                            ourwaySysLayoutComponent.setLayoutRefOwid(ourwaySysLayout.getOwid());
                            sysLayoutCompDao.save(ourwaySysLayoutComponent);
                        } else
                            for (OurwaySysPageComponent pageComponent : ourwaySysPageComponentList) {
                                System.out.println(pageComponent.getKjAttribute() + "=" + ourwaySysLayoutComponent.getPageRefOwid());
                                if (pageComponent.getKjAttribute().trim().equalsIgnoreCase(ourwaySysLayoutComponent.getCompId().trim())) {
                                    ourwaySysLayoutComponent.setPageRefOwid(pageComponent.getOwid());
//                                ourwaySysLayoutComponent.setCompId(pageComponent.getKjAttribute());
                                    ourwaySysLayoutComponent.setLayoutRefOwid(ourwaySysLayout.getOwid());
                                    sysLayoutCompDao.save(ourwaySysLayoutComponent);
                                    break;
                                }
                            }
                    }
                }
            }
    }

    @Override
    public OurwaySysPage saveAsPage(String owid) {
        OurwaySysPage ourwaySysPage = sysPageDao.getOneById(owid);
        OurwaySysPage newPage = new OurwaySysPage();
        BeanUtils.copyProperties(ourwaySysPage, newPage, "owid");
        newPage.setPageCa(newPage.getPageCa()+"-copy");
        sysPageDao.save(newPage);
        Map<String, OurwaySysPageComponent> componentMap = new HashMap<String, OurwaySysPageComponent>();
        //获取pagecomponent
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("pageRefOwid", ourwaySysPage.getOwid());
        List<OurwaySysPageComponent> pageComponents = sysPageCompDao.listAllByParam(params, "");
        for (OurwaySysPageComponent pageComponent : pageComponents) {
            OurwaySysPageComponent newPageCOmponent = new OurwaySysPageComponent();
            BeanUtils.copyProperties(pageComponent, newPageCOmponent, "owid");
            newPageCOmponent.setPageRefOwid(newPage.getOwid());
            sysPageCompDao.save(newPageCOmponent);
            componentMap.put(newPageCOmponent.getKjAttribute(), newPageCOmponent);
        }

        List<OurwaySysLayoutComponent> layoutComponents;
        List<OurwaySysLayout> layouts = sysLayoutDao.listAllByParam(params, "");
        for (OurwaySysLayout layout : layouts) {
            params.clear();
            params.put("layoutRefOwid", layout.getOwid());
            OurwaySysLayout newLaout = new OurwaySysLayout();
            BeanUtils.copyProperties(layout, newLaout, "owid");
            newLaout.setPageRefOwid(newPage.getOwid());
            sysLayoutDao.save(newLaout);
            layoutComponents = sysLayoutCompDao.listAllByParam(params, "");
            for (OurwaySysLayoutComponent ourwaySysLayoutComponent : layoutComponents) {
                OurwaySysLayoutComponent newLayoutComponent = new OurwaySysLayoutComponent();
                BeanUtils.copyProperties(ourwaySysLayoutComponent, newLayoutComponent, "owid");
                newLayoutComponent.setLayoutRefOwid(newLaout.getOwid());
                OurwaySysPageComponent pageComponent = componentMap.get(newLayoutComponent.getCompId());
                if (null == pageComponent)
                    continue;
                newLayoutComponent.setPageRefOwid(pageComponent.getOwid());
                sysLayoutCompDao.save(newLayoutComponent);
            }
        }
        return newPage;
    }

    private void updateMenuLink(OurwaySysPage page) {
        if (null != page.getPageCustomer() && page.getPageCustomer() == 1)
            sysMenusDao.updateMenuLink(page.getOwid(), page.getPageCa());
        else {
            OurwaySysTemplate template = templateDao.getOneById(page.getPageTemplate());
            if (null != template)
                sysMenusDao.updateMenuLink(page.getOwid(), template.getTemplatePath());
        }
    }


    @Override
    public OurwaySysPage queryPage(Map<String, Object> params, String sortStr) {

        return sysPageDao.getOneByParams(params, sortStr);
    }

    @Override
    public List<Map<String, Object>> removePage(List<String> owids) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();
        for (String owid : owids) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            params.put("owid", owid);
            OurwaySysPage ourwaySysPage = sysPageDao.getOneByParams(params, "");
            if (null != ourwaySysPage) {
                objs.add(params);
                sysPageCompDao.removeComponents(ourwaySysPage);
                sysLayoutDao.removeLayoutByPage(ourwaySysPage);
                sysPageDao.removeEntity(ourwaySysPage);
            }
        }
        return objs;
    }

    @Override
    public String detailPageByPageId(String pageId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("owid", pageId);
        OurwaySysPage page = sysPageDao.getOneByParams(params, "");
        if (null == page)
            return "";
        if (null != page.getPageCustomer() && page.getPageCustomer() == 1)
            return page.getPageCa();
        params.put("owid", page.getPageTemplate());
        OurwaySysTemplate template = templateDao.getOneByParams(params, "");
        if (null == template)
            return "";
        return template.getTemplatePath();
    }

    @Override
    public String writeContent(String owid) {
        String timeStamp = DateUtil.getTimeStamp();//时间戳 用作生成文件名使用
        String fileName = timeStamp + ".txt";
        Map params = new HashMap();
        params.put("owid", owid);
        List<Map<String, Object>> pageList = sysPageDao.queryAllBySql("select * from ourway_sys_page where owid=? ", owid);
        if (pageList == null || pageList.size() < 1)
            return null;
        //先执行删除语句
        WriteUtils.writeContent("DELETE FROM ourway_sys_page WHERE OWID = '" + owid + "'; \r\n", fileName, true);//先删除
        WriteUtils.writeContent("DELETE FROM ourway_sys_page_component WHERE PAGE_REF_OWID = '" + owid + "'; \r\n", fileName, true);//先删除
        WriteUtils.writeContent("DELETE FROM ourway_sys_layout WHERE PAGE_REF_OWID = '" + owid + "'; \r\n", fileName, true);//先删除
        //pageSQL
        String pageContent = map2Sql(pageList.get(0), "ourway_sys_page");
        WriteUtils.writeContent(pageContent.toString(), fileName, true);
        //pageCompSQL
        String pageCompSQL = "SELECT * FROM ourway_sys_page_component WHERE PAGE_REF_OWID=? ";
        List<Map<String, Object>> pageCompList = sysPageCompDao.queryAllBySql(pageCompSQL, owid);
        if (pageCompList != null) {
            for (Map temp_pageComp : pageCompList) {
                String pageCompContent = map2Sql(temp_pageComp, "ourway_sys_page_component");
                WriteUtils.writeContent(pageCompContent, fileName, true);
            }
        }
        //layoutSQL
        String layoutSQL = "SELECT * FROM ourway_sys_layout WHERE PAGE_REF_OWID=? ";
        List<Map<String, Object>> layoutList = sysLayoutDao.queryAllBySql(layoutSQL, owid);
        if (layoutList != null) {
            for (Map temp_layout : layoutList) {
                String layoutContent = map2Sql(temp_layout, "ourway_sys_layout");
                WriteUtils.writeContent(layoutContent, fileName, true);
                //layoutComp
                String layoutCompSQL = "SELECT * FROM ourway_sys_layout_component WHERE LAYOUT_REF_OWID=? ";
                List<Map<String, Object>> layoutCompList = sysLayoutCompDao.queryAllBySql(layoutCompSQL, temp_layout.get("OWID"));
                WriteUtils.writeContent("DELETE FROM ourway_sys_layout_component WHERE LAYOUT_REF_OWID = '" + temp_layout.get("OWID") + "'; \r\n", fileName, true);
                for (Map temp_layoutComp : layoutCompList) {
                    String layoutCompContent = map2Sql(temp_layoutComp, "ourway_sys_layout_component");
                    WriteUtils.writeContent(layoutCompContent, fileName, true);
                }
            }
        }
        return fileName;
    }

    @Override
    public List<OurwaySysPage> listAllPages(List<FilterModel> filters, String sortStr) {
        return sysPageDao.listAllPages(filters, sortStr);
    }


    public static String map2Sql(Map map, String model) {
        Set keySet = map.keySet();
        StringBuffer content = new StringBuffer("INSERT INTO " + model + "(");
        for (Object keyName : keySet) {
            content.append(keyName).append(",");
        }
        content.deleteCharAt(content.length() - 1).append(")VALUES( ");
        for (Object keyName : keySet) {
            if (map.get(keyName) == null || map.get(keyName).equals("")) {
                content.append("null").append(map.get(keyName)).append(",");
            } else if (map.get(keyName).getClass().getSimpleName().equals("String")||map.get(keyName).getClass().getSimpleName().equals("Date")||map.get(keyName).getClass().getSimpleName().equals("Timestamp")) {
                content.append("'").append(map.get(keyName)).append("',");
            } else {
                content.append(map.get(keyName)).append(",");
            }
        }
        content.deleteCharAt(content.length() - 1).append(");\r\n");
//        System.out.println(model + ":" + content);
        return content.toString();
    }

}
