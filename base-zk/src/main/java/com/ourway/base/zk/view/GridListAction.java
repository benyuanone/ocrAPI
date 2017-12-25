package com.ourway.base.zk.view;

import com.ourway.base.utils.DateUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.component.PaginalCus;
import com.ourway.base.zk.utils.ZKSessionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public abstract class GridListAction extends BaseAction {
//
//    private static final long serialVersionUID = 290812051123953520L;
//
//    protected GridPaging pageList;
//    protected String hql = "";
//    protected String exportHql = "";
//    protected List<Object> searchCondition = new ArrayList<Object>();// 自动组的查询条件
//    protected boolean displayFlag = true;
//    private String pageCA = "";
//    protected Groupbox gp;// 查询条件框
//    private int minHeight = 0;// 搜索条件的高
//    private int maxHeight = 0;// 默认的搞
//    private boolean groupboxOpenFlag = false;
//    private Map<String, Object> condComps = new HashMap<String, Object>();// 搜索查询条件
//    private boolean judgAuto;// 判断是手动还是自动
//
//    @Override
//    public void afterCompose() {
//        super.afterCompose();
//        if (displayFlag) {
//            initPageList();
//        }
//
//    }
//
//    // 初始化pagelist
//    public void initPageList() {
//        pageList = (GridPaging) this.getFellowIfAny("pageList");
//        pageList.setPageSize(ZKSessionUtils.DEFAULT_PAGESIZE);// 设置每页显示几行
//        PaginalCus paginal = (PaginalCus) this
//                .getFellowIfAny("pageList_paginal");
//        pageList.setPaginalCus(paginal);
//        initPaginal(paginal);
//
////		if (null == CommonConstants.ZKConstants.GNDMAP.get(getCa().toString())) {
//        judgAuto = false;
//        initHeader(); // 初始化列表头,该方法需要被重写
//        pageList.initHeads();// 调用pageList，展现列表表头
//        this.onPaging();// 初始查询
////		} else {
////			AppSysGnd gnd = CoreConstants.GNDMAP.get(getCa().toString());
////			if (gnd.getGnlx() == 1) {
////				judgAuto = true;
////				autoHeader();
////				pageList.initHeads();// 调用pageList，展现列表表头
////				this.autonPaging();// 动态组织搜索框查询
////			} else if (gnd.getGnlx() == 2) {
////				judgAuto = false;
////				autoHeader(); // 初始化列表头,该方法需要被重写
////				pageList.initHeads();// 调用pageList，展现列表表头
////				this.onPaging();// 初始查询，自己写java
////			} else {
////				judgAuto = false;
////				initHeader(); // 初始化列表头,该方法需要被重写
////				pageList.initHeads();// 调用pageList，展现列表表头
////				this.onPaging();// 初始查询，自己写java
////			}
////			gnd = null;
////		}
//
//        pageList.initItems();// 调用pageList，展现数据
//        pageList.initPagnal();// 调用pageList，分页栏目
//        pageList.initFooter();// 调用pageList，foot信息
//    }
//
//    /**
//     * <p>
//     * 自动进行表头的初始化操作，要求是在系统中进行配置，且功能类型为 1的功能
//     * </p>
//     *
//     * @author Jack Zhou
//     * @version $Id: BaseListAction.java,v 0.1 2016-4-6 下午3:34:17 Jack Exp $
//     */
////    public void autoHeader() {
////        AppSysGnd gnd = CoreConstants.GNDMAP.get(getCa().toString());
////        Map <String, List <AppSysGndXm>> custDis = SessionUtil
////                .getPageDisplayMap();
////        // 从用户中判断是否由显示的字段，若没有，则采用默认的字段显示所有
////        List <AppSysGndXm> xms = custDis.get(getCa().toString());// 从当前用户中选择
////        if (null == xms || xms.size() <= 0) {
////            xms = CoreConstants.GNDXM_MAP.get(getCa().toString());
////        }
////        String chn[] = new String[xms.size()];
////        String eng[] = new String[xms.size()];
////        String sort[] = new String[xms.size()];
////        String colWidth[] = new String[xms.size()];
////        int index = 0;
////        for (AppSysGndXm appSysGndXm : xms) {
////            chn[index] = appSysGndXm.getName();
////            eng[index] = appSysGndXm.getCode();
////            sort[index] = appSysGndXm.getSfpx() + "";
////            if (!TextUtils.isEmpty(appSysGndXm.getExp5()))
////                colWidth[index] = appSysGndXm.getExp5();
////            else
////                colWidth[index] = "0";
////            index++;
////        }
////        pageList.setChns(chn);
////        pageList.setEngs(eng);
////        pageList.setSorts(sort);
////
////        switch (gnd.getIschecked()) {
////            case 0:
////                pageList.setIsradio(false);
////                pageList.setIschecked(false);
////                break;
////            case 1:// danxuan
////                pageList.setIschecked(false);
////                pageList.setIsradio(true);
////                break;
////            case 2:
////                pageList.setIschecked(true);
////                pageList.setIsradio(false);
////                break;
////        }
////        if (gnd.getSizedcon() == 1) {
////            pageList.setSizedByContent(true);
//////		    pageList.setSpan(true);
//////		    pageList.setHflex("min");
////        } else
////            pageList.setSizedByContent(false);
////        maxHeight = gnd.getBheight();
////        groupboxOpenFlag = false;
////        if (null != gnd.getIsopem() && gnd.getIsopem() == 0) {
////            groupboxOpenFlag = true;
////        }
////        if (TextUtils.isEmpty(gnd.getExp2()) || "0".equals(gnd.getExp2()))
////            initSearComp();// 初始化搜索组件
////        if (groupboxOpenFlag) {
////            pageList.setButtonHeight(maxHeight);
////        } else {
////            pageList.setButtonHeight(maxHeight - minHeight);
////        }
////        if (null != gnd.getIsdetail() && gnd.getIsdetail() == 1)
////            pageList.setIsOpen(true);
////        gnd = null;
////        chn = null;
////        eng = null;
////        sort = null;
////        colWidth = null;
////    }
//
//    /**
//     * @deprecated:配置自定义条件，显示和搜索
//     */
////    private void initCustSear() {
////        Listbox sys_Cols = (Listbox) getFellowIfAny("sys_Cols");// 放所有的查询条件的listbox，共用户进行自定义选择
////        Button sys_Cols_btn = (Button) getFellowIfAny("sys_Cols_btn");// 保存
////        Listbox cust_show_col = (Listbox) getFellowIfAny("sys_show_Cols");// 放所有的列表显示的listbox，共用户进行自定义选择
////        Button sys_show_btn = (Button) getFellowIfAny("sys_show_btn");// 保存
////        AppSysGnd _gnd = CoreConstants.GNDMAP.get(getCa().toString());
////        sys_show_btn.addEventListener(Events.ON_CLICK,
////                new DealClick(0, _gnd.getPageid()));
////        sys_Cols_btn.addEventListener(Events.ON_CLICK,
////                new DealClick(1, _gnd.getPageid()));
////        List <AppSysGndXm> alls = _gnd.getXmList();// 所有的查询条件
////        Map <String, List <AppSysGndXm>> custDis = SessionUtil.getPageCondMap();
////        Map <String, List <AppSysGndXm>> custShow = SessionUtil
////                .getPageDisplayMap();
////        List <AppSysGndXm> custs = custDis.get(getCa().toString()); // 用户自定义的查询条件
////        List <AppSysGndXm> cuShow = custShow.get(getCa().toString()); // 用户自定义的列表显示
////        // 初始化下拉的添加选择listbox
////        for (AppSysGndXm xm : alls) {
////            Listitem showXm = new Listitem();
////            showXm.setLabel(xm.getName());
////            showXm.setParent(cust_show_col);
////            showXm.setAttribute("ppt", xm);
////            if (null != cuShow && cuShow.size() > 0) {
////                for (AppSysGndXm _xm : cuShow) {
////                    if (xm.getId().equals(_xm.getId())) {
////                        showXm.setSelected(true);
////                        break;
////                    }
////                }
////            } else {
////                showXm.setSelected(true);// 如果用户不选，默认都是选择的
////            }
////            if (xm.getSfcx() == 1) {// 是否查询
////                Listitem item = new Listitem();
////                item.setLabel(xm.getName());
////                item.setAttribute("ppt", xm);// 将整个属性项放入listitem
////                item.setValue(xm.getId());
////                item.setParent(sys_Cols);
////                if (null != custs && custs.size() > 0) {
////                    for (AppSysGndXm _xm : custs) {
////                        if (xm.getId().equals(_xm.getId())) {
////                            item.setSelected(true);
////                            break;
////                        }
////                    }
////                } else {
////                    item.setSelected(true);
////                }
////            }
////        }
////    }
//
//    /**
//     * not used
//     *
//     * @return
//     * @deprecated:与bsh连用，查询数据
//     */
////    private Object dataDao(int type, Map para) {
////        CommonSer serv = (CommonSer) BeanFactory.findBean("appBaseSer");
////        String dataHql = (String) para.get("hql");
////        List param = (List) para.get("params");
////        switch (type) {
////            case 5:
////                return serv.queryOneByHql(dataHql, param.toArray());
////            case 1:
////                return serv.queryAllByHql(dataHql, param.toArray());
////            case 4:
////                int pageNo = (Integer) para.get("pageNo");
////                int pageSize = (Integer) para.get("pageSize");
////                return serv.queryHqlByPage(dataHql, pageNo, pageSize,
////                        param.toArray());
////            case 2:
////                return serv.queryAllBySql(dataHql);
////            case 3:
////                return serv.queryAllCount(dataHql, param.toArray());
////        }
////        return null;
////    }
//
//    /**
//     * <p>
//     * 初始化查询条件,控制是否显示
//     * </p>
//     *
//     * @author Jack Zhou
//     * @version $Id: GridListAction.java,v 0.1 2016-4-6 下午11:18:31 Jack Exp $
//     */
//    @SuppressWarnings("unchecked")
////    private void initSearComp() {
////        AppSysGnd gnd = CoreConstants.GNDMAP.get(getCa().toString());
////        Map <String, List <AppSysGndXm>> custDis = SessionUtil.getPageCondMap();
////        List <AppSysGndXm> custs = custDis.get(getCa().toString());
////        // 当自己没有查询条件的时候
////        if (null == custs || custs.size() <= 0) {
////            custs = new ArrayList <AppSysGndXm>();
////            for (AppSysGndXm appSysGndXm : CoreConstants.GNDXM_MAP.get(getCa()
////                    .toString())) {
////                if (appSysGndXm.getSfcx() == 1 || appSysGndXm.getSfcx() == 2)
////                    custs.add(appSysGndXm);
////            }
////        }
////        gp = (Groupbox) getFellowIfAny("gp");
////        int len = custs.size();
////        // if (len < 6) {// 当显示项目太少的时候自动设置宽度不让随着内容变化而变
////        // pageList.setSizedByContent(false);
////        // }
////        minHeight = ((len / 4) + 1) * 40;// 计算缩放最小值，设置每行高度40，每4个条件放在一起
////        if (gnd.getIsopem() == 1) {// groupbox控制开关
////            gp.setOpen(false);
////        }
////        if (null != gp.getCaption())
////            gp.getCaption().addEventListener(Events.ON_CLICK,
////                    new GroupBoxEvent());// groupbox的缩放事件
////        // gp.addEventListener(Events.ON_CLICK, new GroupBoxEvent());//
////        // groupbox的缩放事件
////        initCustSear();// 放所有的查询条件的listbox，共用户进行自定义选择
////        // 组织查询条件，每3个条件一行
////        Row _row = null;
////        for (AppSysGndXm _xm : custs) {
////            Div div = (Div) this.getFellowIfAny(_xm.getSxm() + "Div");
////            if (null != div)
////                div.setVisible(true);
////        }
////    }
//
//    /**
//     * <p>
//     * 重新初始化表头
//     * </p>
//     *
//     * @author Jack Zhou
//     * @version $Id: GridListAction.java,v 0.1 2016-4-6 下午11:18:16 Jack Exp $
//     */
//    public void reInitPageList() {
//        initHeader(); // 初始化列表头,该方法需要被重写
//        pageList.initHeads();// 调用pageList，展现列表表头
//        this.onPaging();// 初始查询
//        pageList.changePagnial();
//        pageList.initItems();
//        pageList.initFooter();
//    }
//
//    public void reInitPageList2() {
//        initHeader(); // 初始化列表头,该方法需要被重写
//        pageList.initHeads();// 调用pageList，展现列表表头
//    }
//
//    /**
//     * <p>
//     * 初始化分页控件
//     * </p>
//     *
//     * @author Jack Zhou
//     * @version $Id: GridListAction.java,v 0.1 Apr 12, 2011 6:09:37 PM Jack Exp
//     * $
//     */
//    @SuppressWarnings("unchecked")
//    private void initPaginal(PaginalCus paginal) {
//        List list = paginal.getChildren();
//        for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
//            Component obj = (Component) iterator.next();
//            if (obj instanceof Label) {
//                Label lb = (Label) obj;
//                if (lb.getValue().equals("10") || lb.getValue().equals("20")
//                        || lb.getValue().equals("30")
//                        || lb.getValue().equals("40")
//                        || lb.getValue().equals("50")
//                        || lb.getValue().equals("100")) {
//                    lb.addEventListener(Events.ON_CLICK, new ChangePageSize(
//                            this, new Integer(lb.getValue())));
//                }
//                if (lb.getValue().equals(
//                        ((Integer) (this.pageList.getPageSize())).toString()))
//                    lb.setStyle(lb.getStyle() + ";background-color:#FFFF33;");
//            }
//            if (obj instanceof Intbox) {
//                Intbox ib = (Intbox) obj;
//                ib.addEventListener("onOK", new FrozenCols(this));
//            }
//        }
//    }
//
//    /**
//     * <p>
//     * 调整每页显示多少条记录
//     * </p>
//     *
//     * @author Jack Zhou
//     * @version $Id: GridListAction.java,v 0.1 May 10, 2011 10:59:21 PM Jack Exp
//     *          $
//     */
//    private class ChangePageSize implements EventListener {
//        private GridListAction ga;
//        private int newSize;
//
//        public ChangePageSize(GridListAction ga, int newSize) {
//            this.ga = ga;
//            this.newSize = newSize;
//        }
//
//        public void onEvent(Event arg0) throws Exception {
//            // Listbox lb = (Listbox) arg0.getTarget();
//            // Listitem item = lb.getSelectedItem();
//            // if (null != item && null != item.getValue()) {
//            ga.pageList.setPageSize(new Integer(newSize));
////            ZKSessionUtils.setPageSize(newSize);
//            // 保存改用户的每页大小。
//            saveUserPageSize(new Integer(newSize).toString());
//            // 改底色
//            String style = "";
//            List list = arg0.getTarget().getParent().getChildren();
//            for (Object object : list) {
//                Component obj = (Component) object;
//                if (obj instanceof Label) {
//                    Label lb = (Label) obj;
//                    if (lb.getValue().equals(((Integer) (newSize)).toString()))
//                        lb.setStyle(lb.getStyle()
//                                + ";background-color:#FFFF33;");
//                    else {
//                        style = lb.getStyle();
//                        if (!TextUtils.isEmpty(style)) {
//                            style = style.replaceAll(
//                                    "background-color:#FFFF33;", "");
//                            lb.setStyle(style);
//                        }
//                    }
//                }
//            }
//            ga.loadWithNoPage();
//
//            // if (item.getValue().toString().equals("1")) {
//            // if (ga.pageList.getAllCount() > 0)
//            // ga.pageList.setPageSize(ga.pageList.getAllCount());
//            //
//            // } else
//            // ga.pageList.setPageSize(new Integer(item.getValue()
//            // .toString()));
//            // ga.loadWithNoPage();
//            // }
//
//        }
//
//    }
//
//    /**
//     * <p>
//     * 输入数据，回车，冻结几行数据
//     * </p>
//     *
//     * @author Jack Zhou
//     * @version $Id: GridListAction.java,v 0.1 May 10, 2011 10:59:46 PM Jack Exp
//     *          $
//     */
//    private class FrozenCols implements EventListener {
//        private GridListAction ga;
//
//        public FrozenCols(GridListAction ga) {
//            this.ga = ga;
//        }
//
//        @SuppressWarnings("unchecked")
//        public void onEvent(Event arg0) throws Exception {
//            Intbox tb = (Intbox) arg0.getTarget();
//            if (null != tb.getValue() && tb.getValue() <= 0) {
//                ga.showMess("冻结列数必须大于0列");
//                return;
//            }
//            if (null != tb.getValue()) {
//                Frozen fz = new Frozen();
//                List list = ga.pageList.getChildren();
//                // 若以及存在了Frozen ，则取当前存在的Frozen
//                for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
//                    Object obj = iterator.next();
//                    if (obj instanceof Frozen) {
//                        fz = (Frozen) obj;
//                        break;
//                    }
//                }
//                fz.setParent(ga.pageList);
//                fz.setColumns(tb.getValue().intValue());
//                fz.setStyle("background: #DFDED8");
//                Div _dv = new Div();
//                _dv.setParent(fz);
//                _dv.setStyle("padding: 0 10px;");
//            }
//        }
//
//    }
//
//    /**
//     * <p>
//     * 定义数据列表表头 chn: 列表中文名 sort：对应中文名的排序，0表示不排序，1 可排序 eng： 对应中文名的属性 ischeck ：
//     * 是否有复选框 String[] chn = new String[] { "编号", "姓名", "测试1" }; 列表中文名 <br/>
//     * String[] eng = new String[] { "a1", "a2", "a3" }; 对应的英文属性<br/>
//     * String[] sort = new String[] { "0", "1", "1" }; 是否可以排序 <br/>
//     * 以下固定写法 pageList.setChns(chn); <br/>
//     * pageList.setEngs(eng);<br/>
//     * pageList.setSorts(sort);<br/>
//     * pageList.setIschecked(true);<br/>
//     * </p>
//     *
//     * @author Jack Zhou
//     * @version $Id: BaseListAction.java,v 0.1 Nov 1, 2010 2:27:56 PM Jack Exp $
//     */
//    public abstract void initHeader();
//
//    /**
//     * <p>
//     * 双击某一条记录，弹出的事件。修改界面，或者其它操作界面都可以 pageList.getItemDBClickValue();
//     * //获得当前选中的对象
//     * </p>
//     *
//     * @author Jack Zhou
//     * @version $Id: BaseListAction.java,v 0.1 Nov 1, 2010 10:26:07 AM Jack Exp
//     * $
//     */
//    public abstract void updateUI();
//
//    /**
//     * <p>
//     * 分页操作，在该方法中组织查询条件和查询语句，进行分页查询 若重新查询，则需要给pagelist设置总记录条数 pageNo : 当前页面数 过程：
//     * 1： 组织查询语句 2： 获取总条数,并pageList.setAllCount(总条数); 3： 获取数据分页显示
//     * 通过pageList.getPageNo()获取当前页面，pageList.getPageSize()获得每页显示几条
//     * 4：pageList.setDatas(list) 对界面进行排序，通过 pageList.getOrderColumn()
//     * 获得当前可排序字段，如果返回null代表用户未选中排序。 有的话，需要在组装hql或者sql中加入
//     * <p>
//     * </p>
//     *
//     * @author Jack Zhou
//     * @version $Id: BaseListAction.java,v 0.1 Nov 1, 2010 12:17:46 PM Jack Exp
//     * $
//     */
//    public abstract void onPaging();
//
//    /**
//     * 处理返回结果给列表页面,在继承类中可以进行重写
//     *
//     * @param list
//     * @return
//     */
//    public List<Object> handleResult(List<Object> list) {
//        return list;
//    }
//
//    /**
//     * 默认的查询语句，子类可以对语句进行重写。
//     *
//     * @param hql
//     * @return
//     */
//    public String composeHqlForQuery(String hql) {
//        return hql;
//    }
//
//    /**
//     * @deprecated:自动组查询框的hql/sql语句
//     */
//    @SuppressWarnings({"unused", "rawtypes", "unchecked"})
////    public void autonPaging() {
////        log.info("----autonPaging----");
////        AppSysGnd gnd = CoreConstants.GNDMAP.get(getCa().toString());
////        CommonSer serv = (CommonSer) BeanFactory.findBean("appBaseSer");
////        AppSysGnd _gnd = CoreConstants.GNDMAP.get(getCa().toString());
////        List <AppSysGndXm> alls = _gnd.getXmList();// 所有的条件
////        List para = new ArrayList();
////        String hql = _gnd.getExp1() + " where 1=1 ";
////        hql = composeHqlForQuery(hql);
////        if (TextUtils.isEmpty(hql)) {
////            // 如果传入的hql为空，则不查询。
////            return;
////        }
////        for (AppSysGndXm xm : alls) {
////            if (null != xm.getSfcx() && xm.getSfcx() != 0) {
////                if (xm.getSfqj() != 1) {// 非区间
////                    if (xm.getQzfs() == 0) {
////                        switch (xm.getZdlx()) {
////                            case 1:
////                                Textbox tbox = (Textbox) this.getFellowIfAny(xm
////                                        .getSxm());
////                                if (null != tbox && tbox.getParent().isVisible()
////                                        && !TextUtils.isEmpty(tbox.getValue())) {
////                                    hql += "and " + xm.getExp4Str() + xm.getSxm()
////                                            + " like ? ";
////                                    para.add("%" + tbox.getValue() + "%");
////                                }
////                                break;
////                            case 2:
////                                Intbox ibox = (Intbox) this.getFellowIfAny(xm
////                                        .getSxm());
////                                if (null != ibox && ibox.getParent().isVisible()
////                                        && !TextUtils.isEmpty(ibox.getValue())) {
////                                    hql += "and " + xm.getExp4Str() + xm.getSxm()
////                                            + "= ? ";
////                                    para.add(ibox.getValue());
////                                }
////                                break;
////                            case 3:
////                                Intbox sbox = (Intbox) this.getFellowIfAny(xm
////                                        .getSxm());
////                                if (null != sbox && sbox.getParent().isVisible()
////                                        && !TextUtils.isEmpty(sbox.getValue())) {
////                                    hql += "and " + xm.getExp4Str() + xm.getSxm()
////                                            + "= ? ";
////                                    para.add(new Short(sbox.getValue().toString()));
////                                }
////                                break;
////                            case 4:
////                                Doublebox fbox = (Doublebox) this.getFellowIfAny(xm
////                                        .getSxm());
////                                if (null != fbox && fbox.getParent().isVisible()
////                                        && !TextUtils.isEmpty(fbox.getValue())) {
////                                    hql += "and " + xm.getExp4Str() + xm.getSxm()
////                                            + "= ? ";
////                                    para.add(new Float(fbox.getValue().toString()));
////                                }
////                                break;
////                            case 5:
////                                Doublebox dbox = (Doublebox) this.getFellowIfAny(xm
////                                        .getSxm());
////                                if (null != dbox && dbox.getParent().isVisible()
////                                        && !TextUtils.isEmpty(dbox.getValue())) {
////                                    hql += "and " + xm.getExp4Str() + xm.getSxm()
////                                            + "= ? ";
////                                    para.add(dbox.getValue());
////                                }
////                                break;
////                            case 6:
////                                Datebox dybox = (Datebox) this.getFellowIfAny(xm
////                                        .getSxm());
////                                if (null != dybox && dybox.getParent().isVisible()
////                                        && !TextUtils.isEmpty(dybox.getValue())) {
////                                    hql += "and " + xm.getExp4Str() + xm.getSxm()
////                                            + " between ? and ? ";
////                                    para.add(DateUtil.getDate(dybox.getText()
////                                            + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
////                                    para.add(DateUtil.getDate(dybox.getText()
////                                            + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
////                                }
////                                break;
////                        }
////                    } else if (xm.getQzfs() == 1) {
////                        // 下拉框
////                        Listbox lbox = (Listbox) this.getFellowIfAny(xm
////                                .getSxm());
////                        if (null != lbox
////                                && lbox.getParent().isVisible()
////                                && null != lbox.getSelectedItem()
////                                && !TextUtils.isEmpty(lbox.getSelectedItem()
////                                .getValue())) {
////                            hql += "and " + xm.getExp4Str() + xm.getSxm()
////                                    + "= ? ";
////                            switch (xm.getZdlx()) {
////                                case 1:
////                                    para.add(lbox.getSelectedItem().getValue()
////                                            .toString());
////                                    break;
////                                case 2:
////                                    para.add(new Integer(lbox.getSelectedItem()
////                                            .getValue().toString()));
////                                    break;
////                                case 3:
////                                    para.add(new Short(lbox.getSelectedItem()
////                                            .getValue().toString()));
////                                    break;
////                                case 4:
////                                    para.add(new Float(lbox.getSelectedItem()
////                                            .getValue().toString()));
////                                    break;
////                                case 5:
////                                    para.add(new Double(lbox.getSelectedItem()
////                                            .getValue().toString()));
////                                    break;
////                                case 6:
////                                    para.add(DateUtil.getDate(lbox
////                                            .getSelectedItem().getValue()
////                                            .toString(), "yyyy-MM-dd"));
////                                    break;
////                            }
////                        }
////                    } else {
////                        BaseBandbox babox = (BaseBandbox) this
////                                .getFellowIfAny(xm.getSxm());
////                        if (null != babox && babox.getParent().isVisible()
////                                && !TextUtils.isEmpty(babox.getObjValue())) {
////                            hql += "and " + xm.getExp4Str() + xm.getSxm()
////                                    + "= ? ";
////                            switch (xm.getZdlx()) {
////                                case 1:
////                                    para.add(babox.getObjValue());
////                                    break;
////                                case 2:
////                                    para.add(new Integer(babox.getObjValue()));
////                                    break;
////                                case 3:
////                                    para.add(new Short(babox.getObjValue()));
////                                    break;
////                                case 4:
////                                    para.add(new Float(babox.getObjValue()));
////                                    break;
////                                case 5:
////                                    para.add(new Double(babox.getObjValue()));
////                                    break;
////                                case 6:
////                                    para.add(DateUtil.getDate(babox.getObjValue(),
////                                            "yyyy-MM-dd"));
////                                    break;
////                            }
////                        }
////                    }
////
////                } else {
////                    // 区间
////                    if (xm.getQzfs() == 0) {
////                        switch (xm.getZdlx()) {
////                            case 1:
////                                Textbox tbox = (Textbox) this.getFellowIfAny(xm
////                                        .getZjid1());
////                                Textbox tbox2 = (Textbox) this.getFellowIfAny(xm
////                                        .getZjid2());
////                                if (null != tbox && tbox.getParent().isVisible()
////                                        && !TextUtils.isEmpty(tbox.getValue())) {
////                                    hql += " and " + xm.getExp4Str() + xm.getSxm()
////                                            + ">= ? ";
////                                    para.add(tbox.getValue());
////                                }
////                                if (null != tbox2 && tbox2.getParent().isVisible()
////                                        && !TextUtils.isEmpty(tbox2.getValue())) {
////                                    hql += " and " + xm.getExp4Str() + xm.getSxm()
////                                            + "<= ? ";
////                                    para.add(tbox2.getValue());
////                                }
////                                break;
////                            case 2:
////                                Intbox ibox = (Intbox) this.getFellowIfAny(xm
////                                        .getZjid1());
////                                Intbox ibox2 = (Intbox) this.getFellowIfAny(xm
////                                        .getZjid2());
////                                if (null != ibox && ibox.getParent().isVisible()
////                                        && !TextUtils.isEmpty(ibox.getValue())) {
////                                    hql += " and " + xm.getExp4Str() + xm.getSxm()
////                                            + ">= ? ";
////                                    para.add(ibox.getValue());
////                                }
////                                if (null != ibox2 && ibox2.getParent().isVisible()
////                                        && !TextUtils.isEmpty(ibox2.getValue())) {
////                                    hql += " and " + xm.getExp4Str() + xm.getSxm()
////                                            + "<= ? ";
////                                    para.add(ibox2.getValue());
////                                }
////                                break;
////                            case 3:
////                                Intbox sbox = (Intbox) this.getFellowIfAny(xm
////                                        .getZjid1());
////                                Intbox sbox2 = (Intbox) this.getFellowIfAny(xm
////                                        .getZjid2());
////                                if (null != sbox && sbox.getParent().isVisible()
////                                        && !TextUtils.isEmpty(sbox.getValue())) {
////                                    hql += " and " + xm.getExp4Str() + xm.getSxm()
////                                            + ">= ? ";
////                                    para.add(new Short(sbox.getValue().toString()));
////                                }
////                                if (null != sbox2 && sbox2.getParent().isVisible()
////                                        && !TextUtils.isEmpty(sbox2.getValue())) {
////                                    hql += " and " + xm.getExp4Str() + xm.getSxm()
////                                            + "<= ? ";
////                                    para.add(new Short(sbox2.getValue().toString()));
////                                }
////                                break;
////                            case 4:
////                                Doublebox fbox = (Doublebox) this.getFellowIfAny(xm
////                                        .getZjid1());
////                                Doublebox fbox2 = (Doublebox) this
////                                        .getFellowIfAny(xm.getZjid2());
////                                if (null != fbox && fbox.getParent().isVisible()
////                                        && !TextUtils.isEmpty(fbox.getValue())) {
////                                    hql += " and " + xm.getExp4Str() + xm.getSxm()
////                                            + ">= ? ";
////                                    para.add(new Float(fbox.getValue().toString()));
////                                }
////                                if (null != fbox2 && fbox2.getParent().isVisible()
////                                        && !TextUtils.isEmpty(fbox2.getValue())) {
////                                    hql += " and " + xm.getExp4Str() + xm.getSxm()
////                                            + "<= ? ";
////                                    para.add(new Float(fbox2.getValue().toString()));
////                                }
////                                break;
////                            case 5:
////                                Doublebox dbox = (Doublebox) this.getFellowIfAny(xm
////                                        .getZjid1());
////                                Doublebox dbox2 = (Doublebox) this
////                                        .getFellowIfAny(xm.getZjid2());
////                                if (null != dbox && dbox.getParent().isVisible()
////                                        && !TextUtils.isEmpty(dbox.getValue())) {
////                                    hql += " and " + xm.getExp4Str() + xm.getSxm()
////                                            + ">= ? ";
////                                    para.add(dbox.getValue());
////                                }
////                                if (null != dbox2 && dbox2.getParent().isVisible()
////                                        && !TextUtils.isEmpty(dbox2.getValue())) {
////                                    hql += " and " + xm.getExp4Str() + xm.getSxm()
////                                            + "<= ? ";
////                                    para.add(dbox2.getValue());
////                                }
////                                break;
////                            case 6:
////                                Datebox dabox = (Datebox) this.getFellowIfAny(xm
////                                        .getZjid1());
////                                Datebox dabox2 = (Datebox) this.getFellowIfAny(xm
////                                        .getZjid2());
////                                if (null != dabox && dabox.getParent().isVisible()
////                                        && !TextUtils.isEmpty(dabox.getValue())) {
////                                    hql += " and " + xm.getExp4Str() + xm.getSxm()
////                                            + ">= ? ";
////                                    para.add(DateUtil.getDate(dabox.getText()
////                                            + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
////                                }
////                                if (null != dabox2
////                                        && dabox2.getParent().isVisible()
////                                        && !TextUtils.isEmpty(dabox2.getValue())) {
////                                    hql += " and " + xm.getExp4Str() + xm.getSxm()
////                                            + "<= ? ";
////                                    para.add(DateUtil.getDate(dabox2.getText()
////                                            + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
////                                }
////                                break;
////                        }
////                    } else if (xm.getQzfs() == 1) {
////                        Listbox lbox = (Listbox) this.getFellowIfAny(xm
////                                .getZjid1());
////                        if (null != lbox
////                                && lbox.getParent().isVisible()
////                                && null != lbox.getSelectedItem()
////                                && !TextUtils.isEmpty(lbox.getSelectedItem()
////                                .getValue())) {
////                            hql += "and " + xm.getExp4Str() + xm.getSxm()
////                                    + ">= ? ";
////                            switch (xm.getZdlx()) {
////                                case 1:
////                                    para.add(lbox.getSelectedItem().getValue()
////                                            .toString());
////                                    break;
////                                case 2:
////                                    para.add(new Integer(lbox.getSelectedItem()
////                                            .getValue().toString()));
////                                    break;
////                                case 3:
////                                    para.add(new Short(lbox.getSelectedItem()
////                                            .getValue().toString()));
////                                    break;
////                                case 4:
////                                    para.add(new Float(lbox.getSelectedItem()
////                                            .getValue().toString()));
////                                    break;
////                                case 5:
////                                    para.add(new Double(lbox.getSelectedItem()
////                                            .getValue().toString()));
////                                    break;
////                                case 6:
////                                    para.add(DateUtil.getDate(lbox
////                                            .getSelectedItem().getValue()
////                                            .toString(), "yyyy-MM-dd"));
////                                    break;
////                            }
////                        }
////                        Listbox lbox2 = (Listbox) this.getFellowIfAny(xm
////                                .getZjid2());
////                        if (null != lbox2
////                                && lbox2.getParent().isVisible()
////                                && null != lbox2.getSelectedItem()
////                                && !TextUtils.isEmpty(lbox2.getSelectedItem()
////                                .getValue())) {
////                            hql += "and " + xm.getExp4Str() + xm.getSxm()
////                                    + "<= ? ";
////                            switch (xm.getZdlx()) {
////                                case 1:
////                                    para.add(lbox2.getSelectedItem().getValue()
////                                            .toString());
////                                    break;
////                                case 2:
////                                    para.add(new Integer(lbox2.getSelectedItem()
////                                            .getValue().toString()));
////                                    break;
////                                case 3:
////                                    para.add(new Short(lbox2.getSelectedItem()
////                                            .getValue().toString()));
////                                    break;
////                                case 4:
////                                    para.add(new Float(lbox2.getSelectedItem()
////                                            .getValue().toString()));
////                                    break;
////                                case 5:
////                                    para.add(new Double(lbox2.getSelectedItem()
////                                            .getValue().toString()));
////                                    break;
////                                case 6:
////                                    para.add(DateUtil.getDate(lbox2
////                                            .getSelectedItem().getValue()
////                                            .toString(), "yyyy-MM-dd"));
////                                    break;
////                            }
////                        }
////                    } else {
////                        BaseBandbox babox = (BaseBandbox) this
////                                .getFellowIfAny(xm.getSxm());
////                        BaseBandbox babox2 = (BaseBandbox) this
////                                .getFellowIfAny(xm.getSxm());
////                        if (null != babox && babox.getParent().isVisible()
////                                && !TextUtils.isEmpty(babox.getObjValue())) {
////                            hql += "and " + xm.getExp4Str() + xm.getSxm()
////                                    + ">= ? ";
////                            switch (xm.getZdlx()) {
////                                case 1:
////                                    para.add(babox.getObjValue());
////                                    break;
////                                case 2:
////                                    para.add(new Integer(babox.getObjValue()));
////                                    break;
////                                case 3:
////                                    para.add(new Short(babox.getObjValue()));
////                                    break;
////                                case 4:
////                                    para.add(new Float(babox.getObjValue()));
////                                    break;
////                                case 5:
////                                    para.add(new Double(babox.getObjValue()));
////                                    break;
////                                case 6:
////                                    para.add(DateUtil.getDate(babox.getObjValue()
////                                            + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
////                                    break;
////                            }
////                        }
////                        if (null != babox2 && babox2.getParent().isVisible()
////                                && !TextUtils.isEmpty(babox2.getObjValue())) {
////                            hql += "and " + xm.getExp4Str() + xm.getSxm()
////                                    + "<= ? ";
////                            switch (xm.getZdlx()) {
////                                case 1:
////                                    para.add(babox2.getObjValue());
////                                    break;
////                                case 2:
////                                    para.add(new Integer(babox2.getObjValue()));
////                                    break;
////                                case 3:
////                                    para.add(new Short(babox2.getObjValue()));
////                                    break;
////                                case 4:
////                                    para.add(new Float(babox2.getObjValue()));
////                                    break;
////                                case 5:
////                                    para.add(new Double(babox2.getObjValue()));
////                                    break;
////                                case 6:
////                                    para.add(DateUtil.getDate(babox2.getObjValue()
////                                            + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
////                                    break;
////                            }
////                        }
////                    }
////                }
////            }
////        }
////
////        // 组织用户的条件
////
////        String orderName = pageList.getOrderColumn();
////        if (!TextUtils.isEmpty(orderName)) {
////            hql += " order by " + orderName.replaceAll("Str", "") + " "
////                    + pageList.getOrderForward();
////        } else {
////            if (!TextUtils.isEmpty(_gnd.getExp4()))
////                hql += " order by " + _gnd.getExp4();
////        }
////        this.exportHql = hql;
//////		System.out.println(hql);
////        this.searchCondition = para;
////        int allcount = serv.queryAllCount(hql, para.toArray());
////        pageList.setAllCount(allcount);
////        int pageno = 0;
////        if (null != pageList.getPageNo())
////            pageno = pageList.getPageNo();
////        List <Object> list = serv.queryHqlByPage(hql, pageno, pageList.getPageSize(), para.toArray());
////        pageList.setDatas(handleResult(list));
////        para = null;
////    }
//
//    /**
//     * @deprecated:清楚搜索的条件值
//     */
////    public void clear() {
////        Map <String, List <AppSysGndXm>> custDis = SessionUtil.getPageCondMap();
////        List <AppSysGndXm> custs = custDis.get(getCa().toString()); // 用户自定义的查询条件
////        // 当自己没有查询条件的时候，从全局取**登陆的时候已经做过了
////        if (null == custs || custs.size() <= 0) {
////            custs = new ArrayList <AppSysGndXm>();
////            for (AppSysGndXm appSysGndXm : CoreConstants.GNDXM_MAP.get(getCa()
////                    .toString())) {
////                if (appSysGndXm.getSfcx() == 1 || appSysGndXm.getSfcx() == 2)
////                    custs.add(appSysGndXm);
////            }
////        }
////        for (AppSysGndXm xm : custs) {
////            Component comp = this.getFellowIfAny(xm.getSxm());
////            if (null != comp) {
////                String type = comp.getDefinition().getName();
////                if (type.equals("listbox")) {
////                    ((Listbox) comp).setSelectedIndex(0);
////                } else {
////                    if (xm.getQzfs() == 1) {
////                        continue;
////                    } else {
////                        if (type.equals("textbox")) {
////                            ((Textbox) comp).setValue("");
////                        } else if (type.equals("intbox")) {
////                            ((Intbox) comp).setValue(null);
////                        } else if (type.equals("doublebox")) {
////                            ((Doublebox) comp).setText("");
////                        } else if (type.equals("datebox")) {
////                            ((Datebox) comp).setValue(null);
////                        }
////
////                    }
////                }
////            }
////            if (!TextUtils.isEmpty(xm.getZjid1())) {
////                comp = this.getFellowIfAny(xm.getZjid1());
////                if (null != comp) {
////                    String type = comp.getDefinition().getName();
////                    if (type.equals("listbox")) {
////                        ((Listbox) comp).setSelectedIndex(0);
////                    } else {
////                        if (xm.getQzfs() == 1) {
////                            continue;
////                        } else {
////                            if (type.equals("textbox")) {
////                                ((Textbox) comp).setValue("");
////                            } else if (type.equals("intbox")) {
////                                ((Intbox) comp).setValue(null);
////                            } else if (type.equals("doublebox")) {
////                                ((Doublebox) comp).setText("");
////                            } else if (type.equals("datebox")) {
////                                ((Datebox) comp).setValue(null);
////                            }
////                        }
////                    }
////                }
////            }
////            if (!TextUtils.isEmpty(xm.getZjid2())) {
////                comp = this.getFellowIfAny(xm.getZjid2());
////                if (null != comp) {
////                    String type = comp.getDefinition().getName();
////                    if (type.equals("listbox")) {
////                        ((Listbox) comp).setSelectedIndex(0);
////                    } else {
////                        if (xm.getQzfs() == 1) {
////                            continue;
////                        } else {
////                            if (type.equals("textbox")) {
////                                ((Textbox) comp).setValue("");
////                            } else if (type.equals("intbox")) {
////                                ((Intbox) comp).setValue(null);
////                            } else if (type.equals("doublebox")) {
////                                ((Doublebox) comp).setText("");
////                            } else if (type.equals("datebox")) {
////                                ((Datebox) comp).setValue(null);
////                            }
////                        }
////                    }
////                }
////            }
////        }
////    }
//
//    /**
//     * <p>
//     * 打开新增界面
//     * </p>
//     *
//     * @author Jack Zhou
//     * @version $Id: BaseListAction.java,v 0.1 Nov 2, 2010 10:14:46 PM Jack Exp
//     * $
//     */
//    public abstract void addUI();
//
//    /**
//     * <p>
//     * 右键菜单
//     * </p>
//     *
//     * @author Jack Zhou
//     * @version $Id: GridListAction.java,v 0.1 2012-12-31 下午01:52:36 Jack Exp $
//     */
//    public void onRightUI() {
//    }
//
//    ;
//
//    /**
//     * <p>
//     * 打开detail界面事件
//     * </p>
//     *
//     * @author Jack Zhou
//     * @version $Id: GridListAction.java,v 0.1 2013-9-15 下午03:42:26 Jack Exp $
//     */
//    public void onOpenUI() {
//        pageList.OpenGrid(this.queryOpenDatas());
//    }
//
//    /**
//     * <p>
//     * 获得onOpen的数据时间
//     * </p>
//     *
//     * @author Jack Zhou
//     * @version $Id: GridListAction.java,v 0.1 2013-9-15 下午04:22:17 Jack Exp $
//     */
//    public Object queryOpenDatas() {
//        return null;
//    }
//
//    ;
//
//    /**
//     * <p>
//     * 获取导出xls的数据列表。数据从查询结果一致，只不过这里不分页
//     * </p>
//     *
//     * @author Jack Zhou
//     * @version $Id: BaseListAction.java,v 0.1 Dec 11, 2010 8:22:15 PM Jack Exp
//     * $
//     */
//    @SuppressWarnings("unchecked")
//    protected List getExportData() {
//        return new ArrayList();
//    }
//
//    /**
//     * <p>
//     * 导出excel数据
//     * </p>
//     *
//     * @author Jack Zhou
//     * @version $Id: BaseListAction.java,v 0.1 Dec 11, 2010 8:24:04 PM Jack Exp
//     * $
//     */
//    @SuppressWarnings({"unchecked", "deprecation"})
//    public void exportXls(String title) {
//        if (null == pageList)
//            return;
//        List datas = getExportData();
//        if (null == datas || datas.size() <= 0) {
//            this.showMess("无数据导出");
//            return;
//        }
//        ExcelUtil xls = new ExcelUtil();
//        xls.setAuxHeader(pageList.getAuxHeader());
//        xls.setHeanders(pageList.getCols());
//        xls.setBeannames(pageList.getEngs());
//        xls.setSheetTitle(title);
//        xls.setDatas(datas);
//        FileOutputStream out = null;
//        try {
//            HSSFWorkbook wb = xls.doExportXLS();
//            String path = Executions.getCurrent().getDesktop().getWebApp()
//                    .getRealPath("/");
//            File file = new File(path + title + "("
//                    + DateUtil.getDateStr("yyyy-MM-dd") + ").xls");
//            out = new FileOutputStream(file);
//            wb.write(out);
//            out.close();
//            Filedownload.save(
//                    "/" + title + "(" + DateUtil.getDateStr("yyyy-MM-dd")
//                            + ").xls", null);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (null != out)
//                    out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
//
//    public void exportXlsExt(String title, List<String[]> auxheader,
//                             String[] chg, String[] eng) {
//        if (TextUtils.isEmpty(this.exportHql)) {
//            this.showMess("Please First Inquery Then Export");
//            return;
//        }
//        List datas = getExportData();
//        ExcelUtil xls = new ExcelUtil();
//        xls.setAuxHeader(auxheader);
//        xls.setHeanders(chg);
//        xls.setBeannames(eng);
//        xls.setSheetTitle(title);
//        xls.setDatas(datas);
//        FileOutputStream out = null;
//        try {
//            HSSFWorkbook wb = xls.doExportXLS();
//            File file = new File(this.request().getRealPath("/") + title
//                    + ".xlsx");
//            out = new FileOutputStream(file);
//            wb.write(out);
//            out.close();
//            Filedownload.save(file,
//                    title + "(" + DateUtil.getDateStr("yyyy-MM-dd") + ").xlsx");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (null != out)
//                    out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
//
//    public void exportXlsExt2(String title, List<String[]> auxheader,
//                              String[] chg, String[] eng) {
//        if (TextUtils.isEmpty(this.exportHql)) {
//            this.showMess("Please First Inquery Then Export");
//            return;
//        }
//        List datas = getExportData();
//        ExcelUtil xls = new ExcelUtil();
//        xls.setAuxHeader(auxheader);
//        xls.setHeanders(chg);
//        xls.setBeannames(eng);
//        xls.setSheetTitle(title);
//        xls.setDatas(datas);
//        FileOutputStream out = null;
//        try {
//            HSSFWorkbook wb = xls.doExportXLSMutiSheet();
//            File file = new File(this.request().getRealPath("/") + title
//                    + ".xlsx");
//            out = new FileOutputStream(file);
//            wb.write(out);
//            out.close();
//            Filedownload.save(file, title + ".xlsx");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (null != out)
//                    out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
//
//    /**
//     * <p>
//     * 带当前页面数的刷新数据列表,修改，新增后可以调用该方法
//     * </p>
//     *
//     * @author Jack Zhou
//     * @version $Id: BaseListAction.java,v 0.1 Nov 2, 2010 9:03:33 PM Jack Exp $
//     */
//    public void loadWithPage() {
////        if (judgAuto)
////            this.autonPaging();
////        else
//        this.onPaging();
//        pageList.changePagnial();
//        pageList.initItems();
//        pageList.initFooter();
//    }
//
//    /**
//     * <p>
//     * 页面为0的刷新数据。删除可以调用
//     * </p>
//     *
//     * @author Jack Zhou
//     * @version $Id: BaseListAction.java,v 0.1 Nov 2, 2010 9:04:20 PM Jack Exp $
//     */
//    public void loadWithNoPage() {
//        pageList.setPageNo(0);
////        if (judgAuto)
////            this.autonPaging();
////        else
//        this.onPaging();
//        pageList.changePagnial();
//        pageList.initItems();
//        pageList.initFooter();
//    }
//
//    /**
//     * <p>
//     * 进行删除，如果没有特殊的处理，可以直接调用此删除把选中的记录进行删除 传入当前的控件对象，界面调用 remove(self)就可以了。
//     * <p>
//     * </p>
//     *
//     * @author Jack Zhou
//     * @version $Id: BaseListAction.java,v 0.1 Nov 2, 2010 8:40:53 PM Jack Exp $
//     */
//    @SuppressWarnings("unchecked")
//    public void removeUI(Component comp) {
////        if (pageList.getSelectedCount() <= 0) {
////            this.showMess("未选中需要操作的数据记录，请选择");
////            return;
////        }
////        if (this.deleteConfirm(null)) {
////            Set set = pageList.getSelectedItems();
////            BaseService bser = (BaseService) BeanFactory.findBean("baseSer");
////            for (Object object : set) {
////                bser.removeObj(object);
////            }
////            this.showMess("数据已经成功删除");
////            loadWithNoPage();
////        }
//    }
//
//    /**
//     * <p>
//     * 根据word模板，导出word文件，word模板通过生成xml制作成freemark模板
//     * </p>
//     *
//     * @author Jack Zhou
//     * @version $Id: BaseListAction.java,v 0.1 2011-9-22 下午09:48:02 Jack Exp $
//     */
//    @SuppressWarnings({"deprecation", "unchecked"})
//    public void exportDOC(String templateName, String saveName, Map datas) {
////        String dirPath = this.request().getRealPath("/")
////                + "WEB-INF\\classes\\template\\";
////        String savePath = this.request().getRealPath("/") + saveName + ".doc";
////        try {
////            DocumentHandler docHandle = new DocumentHandler(templateName,
////                    savePath);
////            docHandle.setRoot(datas);
////            docHandle.setTemplateDir(dirPath);
////            docHandle.createDoc();
////            File file = new File(savePath);
////            Filedownload.save(file, saveName + ".doc");
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//    }
//
//    /**
//     * <p>
//     * 查询条件缩放事件
//     * </p>
//     *
//     * @author Jack Zhou
//     * @version $Id: GridListAction.java,v 0.1 2016-4-6 下午10:36:00 Jack Exp $
//     */
//    private class GroupBoxEvent implements EventListener {
//        public void onEvent(Event arg0) throws Exception {
//            Groupbox pn = (Groupbox) getFellowIfAny("gp");
//            if (pn.isOpen()) {
//                pageList.setButtonHeight(maxHeight);
//            } else {
//                pageList.setButtonHeight(maxHeight - minHeight);
//            }
//        }
//    }
//
//    // 处理保存条件事件
//    private class DealClick implements EventListener {
//        private int type;
//        private String pageId;
//
//        public DealClick(int type, String pageId) {
//            this.type = type;
//            this.pageId = pageId;
//        }
//
//        public void onEvent(Event arg0) throws Exception {
////            Listbox box;
////            Popup popup;
////            CommonSer serv = (CommonSer) BeanFactory.findBean("appBaseSer");
////            UserModel user = (UserModel) SessionUtil.getUser();
////            String hql = null;
////            if (this.type == 1) {// 处理搜索条件保存
////                hql = " update AppSysCustomcon set issys=0 where appId=? and pageid=?";
////                serv.executeUpdateHql(hql, user.getId(), this.pageId);
////                box = (Listbox) getFellowIfAny("sys_Cols");
////                popup = (Popup) getFellowIfAny("cust_sel_col");
////                Set <Listitem> sitem = box.getSelectedItems();
////                for (Listitem listitem : sitem) {
////                    AppSysGndXm xm = (AppSysGndXm) listitem.getAttribute("ppt");
////                    hql = " from AppSysCustomcon where appId=? and appId2=?";
////                    AppSysCustomcon cxm = (AppSysCustomcon) serv.queryOneByHql(
////                            hql, user.getId(), xm.getId());
////                    if (null == cxm) {
////                        cxm = new AppSysCustomcon();
////                        cxm.setId(TextUtils.getUUID());
////                        cxm.setPageid(this.pageId);
////                        cxm.setAppId(user.getId());
////                        cxm.setIsshow((short) 0);
////                        cxm.setIssys((short) 1);
////                        cxm.setAppId2(xm.getId());
////                        cxm.setCreatetime(new Date());
////                        cxm.setUpdatetime(new Date());
////                    } else {
////                        cxm.setIssys((short) 1);
////                    }
////                    serv.saveOrUpdate(cxm);
////                }
////            } else {
////                box = (Listbox) getFellowIfAny("sys_show_Cols");
////                popup = (Popup) getFellowIfAny("cust_show_col");
////                hql = " update AppSysCustomcon set isshow=0 where appId=? and pageid=?";
////                serv.executeUpdateHql(hql, user.getId(), this.pageId);
////                Set <Listitem> sitem = box.getSelectedItems();
////                for (Listitem _xm : sitem) {
////                    AppSysGndXm xm = (AppSysGndXm) _xm.getAttribute("ppt");
////                    hql = " from AppSysCustomcon where appId=? and appId2=?";
////                    AppSysCustomcon cxm = (AppSysCustomcon) serv.queryOneByHql(
////                            hql, user.getId(), xm.getId());
////                    if (null == cxm) {
////                        cxm = new AppSysCustomcon();
////                        cxm.setId(TextUtils.getUUID());
////                        cxm.setPageid(this.pageId);
////                        cxm.setAppId(user.getId());
////                        cxm.setIssys((short) 0);
////                        cxm.setAppId2(xm.getId());
////                        cxm.setIsshow((short) 1);
////                        cxm.setCreatetime(new Date());
////                        cxm.setUpdatetime(new Date());
////                        cxm.setIsshow((short) 0);
////                    }
////                    cxm.setIsshow((short) 1);
////                    serv.saveOrUpdate(cxm);
////                }
////            }
////            hql = "delete from AppSysCustomcon where appId=? and pageid=? and issys=0 and isshow=0";
////            serv.executeUpdateHql(hql, user.getId(), this.pageId);
////            popup.close();
////            showMess("保存成功");
//        }
//
//    }

}
