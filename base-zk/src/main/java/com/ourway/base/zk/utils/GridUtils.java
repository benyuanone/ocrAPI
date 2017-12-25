package com.ourway.base.zk.utils;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.component.*;
import com.ourway.base.zk.models.*;
import com.ourway.base.zk.service.ComponentSer;
import com.ourway.base.zk.service.GridRowDbclickListinerSer;
import com.ourway.base.zk.service.RenderUtils;
import com.ourway.base.zk.template.FilterGridAction;
import com.ourway.base.zk.template.ImageDisplayAction;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.PageDataUtil;
import net.sf.json.JSONNull;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.Serializable;
import java.util.*;

/**
 * <p>方法 PageUtils : <p>
 * <p>说明:页面处理相关方法</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/3 20:20
 * </pre>
 */
public class GridUtils implements Serializable {
    /*当前行的更改标识*/
    public static final String SUBEDIT = "updateFlag";
    public static final String DEFAULTLABEL = "defaultLabel";//判断是否需要绑定新值
    /*判断每行值是否更改的一句*/
    public static final String ROWINDEXSTR = "_rowIndexStr";
    /*每行的序号*/
    public static final String ROWINDEXNO = "indexno";

    /**
     * <p>方法:setGridProperty 设置表格的属性 </p>
     * <ul>
     * <li> @param data 传入的pageLayout的属性</li>
     * <li>@return   </li>
     * <li>@author JackZhou </li>
     * <li>@date    </li>
     * </ul>
     */
    public static void setGridProperty(BaseGrid grid, PageLayoutVO data) {
        if (!TextUtils.isEmpty(data.getControlCss()))
            grid.setClass(data.getControlClass());
        if (null != data.getControlHeight() && 0 != data.getControlHeight().intValue()) {
            if (!TextUtils.isEmpty(grid.getStyle())) {
                grid.setStyle(grid.getStyle() + "height:" + data.getControlHeight() + "px;");
            } else
                grid.setStyle("height:" + data.getControlHeight() + "px;");
        }
        if (null != data.getControlWidth() && 0 != data.getControlWidth().intValue())
            grid.setWidth(data.getControlWidth() + "px");
        //设置随内容变化而变化
        if (null != data.getControlSizebycontent() && 1 == data.getControlSizebycontent()) {
            grid.setSizedByContent(true);
            grid.setSpan(true);
        }


    }

    //设置数据区的高度
    public static void setDataGridProperty(BaseGrid grid, PageLayoutVO data) {
        if (null != data.getControlHeight() && 0 != data.getControlHeight().intValue())
            grid.setHeight((ZKSessionUtils.getScreenHeight() - data.getControlHeight()) + "px");
        else {
            grid.setHeight((ZKSessionUtils.getScreenHeight() - 100) + "px");
        }
    }


    /**
     * <p>方法:setGridHeader 传入数据，设置Grid的表头 </p>
     * <ul>
     * <li> @param grid TODO</li>
     * <li> @param data TODO</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/15 8:54  </li>
     * </ul>
     */
    public void setGridHeader(BaseGrid grid, PageLayoutVO data) {
        if (null == data)
            return;

        int _colWidth = 0;
        String colWidth = "";
        Menupopup menupopup = new Menupopup();
        menupopup.setParent(grid.getParent());
        menupopup.setId(grid.getId() + "_editPop");
        createCommonMenuItem(menupopup, grid);
        //清空grid中的表頭信息
        grid.getChildren().clear();
        Columns cls = new Columns();
        cls.setMenupopup(grid.getId() + "_editPop");
        cls.setSizable(true);
        cls.setParent(grid);
        cls.setColumnshide(true);

        doAddKeyListinerInGrid(grid, cls);
        //如果存在detail了，则表格多出一个列
        if (null != grid.getLayoutVO() && !TextUtils.isEmpty(grid.getLayoutVO().getControlDetailInt())) {
            Column col = new Column();
            col.setAlign("center");
            col.setParent(cls);
            col.setWidth("40px");
            col.setContext("");
        }
        if (null != data.getControlCheck()) {
            if (1 == data.getControlCheck()) {
                //checkbox
                Column col = new Column();
                col.setAlign("center");
                col.setParent(cls);
                col.setWidth("30px");
                col.setContext("");
            }
            if (2 == data.getControlCheck()) {
                Column col = new Column();
                col.setAlign("center");
                col.setWidth("30px");
                col.setParent(cls);
            }
        }
        //每个grid都有一个序号
        Column col = new Column();
        col.setAlign("center");
        col.setWidth("40px");
        col.setLabel(I18nUtil.getLabelContent(ZKConstants.GRID_INDEXNO));
        col.setParent(cls);
        setIndexColSort(col);

        grid.setIndexCol(true);
        //查看是否有表头的定义
        //to-do: 查看多表头
        if (null != data.getLayOutComponents() && data.getLayOutComponents().size() > 0) {
            for (int index = 1; index <= data.getLayOutComponents().size(); index++) {
                List<PageControlVO> subComps = data.getLayOutComponents().get(index + "");
                _colWidth = doCalHeaderWidth(subComps.size());
                if (_colWidth > 0)
                    colWidth = _colWidth + "px";
//                else {
//                    colWidth = (100 / subComps.size()) + "%";
//                }
                Collections.sort(subComps, new PageControlVOCompare());
                //如果是最后一个row，表示是定义表头的label标签和对应的属性
                if (index == data.getLayOutComponents().size()) {
                    //初始化表头和排序方式
                    grid.setControlVOS(new ArrayList<PageControlVO>(subComps.size()));

                    for (final PageControlVO subComp : subComps) {
                        grid.getControlVOS().add(subComp);
                        col = createColumn(subComp, grid);
                        col.setParent(cls);
                        if (null != subComp.getLayoutComponent() && null != subComp.getLayoutComponent().getCompLength()) {
                            col.setWidth(subComp.getLayoutComponent().getCompLength() + "px");
                            grid.setSizedByContent(true);//自定义宽度了，就必须定义随内容扩大而拉大
                            grid.setSpan(true);
                        } else {
                            if (_colWidth > 0)
                                col.setWidth(colWidth + "px");
                        }
                        try {
                            col.setSort("auto");
                            col.setSortAscending(new Comparator<Object>() {
                                @Override
                                public int compare(Object o1, Object o2) {
                                    return doSort((Row) o2, (Row) o1, subComp);
                                }
                            });
                            col.setSortDescending(new Comparator<Object>() {
                                @Override
                                public int compare(Object o1, Object o2) {
                                    return doSort((Row) o1, (Row) o2, subComp);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (!TextUtils.isEmpty(subComp.getLayoutComponent().getEventBackContent()))
                            col.setTooltiptext(I18nUtil.getLabelContent(subComp.getLayoutComponent().getEventBackContent()));
                        createMenuItem(subComp, menupopup, grid);
                    }
                }
            }
        }
    }

    private int getColIndex(Column col) {
        Columns cls = (Columns) col.getParent();
        List<Column> columns = cls.getChildren();
        int index = 0;
        for (Column column : columns) {
            if (column.getLabel().equalsIgnoreCase(col.getLabel())) {
                return index;
            }
            index++;
        }
        return 0;
    }

    //设置序号列的排序
    private void setIndexColSort(Column col) {
        try {
            final int colIndex = getColIndex(col);
            col.setSort("auto");
            col.setSortAscending(new Comparator<Object>() {
                @Override
                public int compare(Object o1, Object o2) {
                    return doSort((Row) o2, (Row) o1, colIndex);
                }
            });
            col.setSortDescending(new Comparator<Object>() {
                @Override
                public int compare(Object o1, Object o2) {
                    return doSort((Row) o1, (Row) o2, colIndex);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*定义grid的表头宽度，若该宽度满足基本条件，
    则用百分比表示，不满足则用数量表述*/
    private int doCalHeaderWidth(int cols) {
        int totalWidth = ZKSessionUtils.getScreenWidth();
        int avgWidth = (totalWidth - 300) / cols;
        if (avgWidth < ZKConstants.GRID_MIN_HEADER_WIDTH)
            return ZKConstants.GRID_MIN_HEADER_WIDTH;
        else
            return 0;
    }

    //添加鼠标右键的过滤事件，只有在标题栏上右键打开
    private void doAddKeyListinerInGrid(BaseGrid grid, Columns cls) {
        cls.addEventListener(Events.ON_RIGHT_CLICK, new ItemClickEvent(grid));
    }

    /**
     * <p>方法:setInnerGridHeader 创建从表grid，不分页，可以有排序 </p>
     * <ul>
     * <li> @param grid TODO</li>
     * <li> @param data TODO</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/26 14:23  </li>
     * </ul>
     */
    public void setInnerGridHeader(BaseGrid grid, PageLayoutVO data) {
        if (null == data)
            return;
        //清空grid中的表頭信息
        grid.getChildren().clear();
        Columns cls = grid.getColumns();
        if (null == cls) {
            cls = new Columns();
            cls.setSizable(true);
            cls.setParent(grid);
        }
        doAddKeyListinerInGrid(grid, cls);
        if (null != data.getControlCheck()) {
            if (1 == data.getControlCheck()) {
                //checkbox
                createColumn(cls, "center", "30px", "");
            }
            if (2 == data.getControlCheck()) {
                createColumn(cls, "center", "30px", "");
            }
        }

        int _colWidth = 0;
        String colWidth = "";
        //查看是否有表头的定义
        //to-do: 查看多表头
        if (null != data.getLayOutComponents() && data.getLayOutComponents().size() > 0) {
            for (int index = 1; index <= data.getLayOutComponents().size(); index++) {
                List<PageControlVO> subComps = data.getLayOutComponents().get(index + "");
                _colWidth = doCalHeaderWidth(subComps.size());
                if (_colWidth > 0)
                    colWidth = _colWidth + "px";
                else {
                    colWidth = (100 / subComps.size()) + "%";
                }
                Collections.sort(subComps, new PageControlVOCompare());
                //如果是最后一个row，表示是定义表头的label标签和对应的属性
                if (index == data.getLayOutComponents().size()) {
                    //初始化表头和排序方式
                    grid.setControlVOS(new ArrayList<PageControlVO>(subComps.size()));
                    for (PageControlVO subComp : subComps) {
                        grid.getControlVOS().add(subComp);
                        Column col = createColumn(subComp, grid);
                        col.setWidth(colWidth);
                        col.setAttribute("comp", subComp);
                        col.setParent(cls);
                    }
                }
            }
        }
    }

    private void createCommonMenuItem(Menupopup menupopup, BaseGrid grid) {
        Menuitem item = new Menuitem();
        item.setLabel(I18nUtil.getLabelContent(ERCode.SELECTALL));
        item.setImage("~./zul/img/grid/menu-group.png");
        item.setParent(menupopup);
        item.setId(grid.getId() + "_selectAll");
        item.setAttribute("colProperty", ERCode.SELECTALL);
        item.addEventListener(Events.ON_CLICK, new ItemClickEvent(grid));

        item = new Menuitem();
        item.setLabel(I18nUtil.getLabelContent(ERCode.NOSELECTALL));
        item.setImage("~./zul/img/grid/menu-group.png");
        item.setParent(menupopup);
        item.setId(grid.getId() + "_noselectAll");
        item.setAttribute("colProperty", ERCode.NOSELECTALL);
        item.addEventListener(Events.ON_CLICK, new ItemClickEvent(grid));

//        item = new Menuitem();
//        item.setLabel(I18nUtil.getLabelContent(ERCode.ASCENDING));
//        item.setImage("~./zul/img/grid/menu-arrowup.png");
//        item.setParent(menupopup);
//        item.setCheckmark(true);
//        item.setId(grid.getId() + "_ascending");
//        item.setAttribute("colProperty", ERCode.ASCENDING);
//        item.addEventListener(Events.ON_CLICK, new ItemClickEvent(grid));
//        item = new Menuitem();
//        item.setLabel(I18nUtil.getLabelContent(ERCode.DESCENDING));
//        item.setImage("~./zul/img/grid/menu-arrowdown.png");
//        item.setParent(menupopup);
//        item.setId(grid.getId() + "_descending");
//        item.setCheckmark(true);
//        item.setAttribute("colProperty", ERCode.DESCENDING);
//        item.addEventListener(Events.ON_CHECK, new ItemClickEvent(grid));
        Menuseparator menuseparator = new Menuseparator();
        menuseparator.setParent(menupopup);
    }

    //生成每个列的菜单
    private void createMenuItem(PageControlVO subComp, Menupopup menupopup, BaseGrid grid) {
        boolean flag = null == subComp || null == subComp.getLayoutComponent();
        if (flag)
            return;
        if (null != subComp.getLayoutComponent().getCompGroup() && subComp.getLayoutComponent().getCompGroup() == 1) {
            Component comp = menupopup.getFellowIfAny(grid.getId() + "_group");
            comp.setVisible(false);
        }
//        if (null != subComp.getLayoutComponent().getCompIsorder() && 1 == subComp.getLayoutComponent().getCompIsorder()) {
//            Component comp = menupopup.getFellowIfAny(grid.getId() + "_ascending");
//            comp.setVisible(true);
//            comp = menupopup.getFellowIfAny(grid.getId() + "_descending");
//            comp.setVisible(true);
//        }
        Menuitem item = new Menuitem();
        item.setLabel(I18nUtil.getLabelContent(subComp.getKjLabelid()));
        item.setParent(menupopup);
        item.setAttribute("colProperty", subComp.getKjAttribute());
        item.setCheckmark(true);
        item.addEventListener(Events.ON_CLICK, new ItemClickEvent(grid));
    }

    private Column createColumn(final PageControlVO subComp, BaseGrid grid) {
        Column col = new Column();
        col.setAlign("center");
        //把整个对象加入进去，以便后续可以进行操作。
        col.setAttribute("subComp", subComp);
        if (!TextUtils.isEmpty(subComp.getKjAttributeDisplay()))
            col.setAttribute("key", subComp.getKjAttributeDisplay());
        else
            col.setAttribute("key", subComp.getKjAttribute());
        //处理css
        if (!TextUtils.isEmpty(subComp.getLayoutComponent().getCompCss())) {
            col.setStyle(subComp.getLayoutComponent().getCompCss());
        }
        //处理颜色
        if (!TextUtils.isEmpty(subComp.getLayoutComponent().getCompColor())) {
            if (TextUtils.isEmpty(col.getStyle()))
                col.setStyle("background-color:" + subComp.getLayoutComponent().getCompColor());
            else
                col.setStyle(col.getStyle() + ";background-color:" + subComp.getLayoutComponent().getCompColor());
        }

        col.setLabel(I18nUtil.getLabelContent(subComp.getKjLabelid()));
        col.setId(grid.getId() + "_" + subComp.getKjAttribute() + "_column");
        try {
            col.setSort("auto");
            col.setSortAscending(new Comparator<Object>() {
                @Override
                public int compare(Object o1, Object o2) {
                    return doSort((Row) o2, (Row) o1, subComp);
                }
            });
            col.setSortDescending(new Comparator<Object>() {
                @Override
                public int compare(Object o1, Object o2) {
                    return doSort((Row) o1, (Row) o2, subComp);
                }
            });
        } catch (Exception e) {

        }
        return col;
    }

    //倒序排
    public int doSort(Row o1, Row o2, PageControlVO subComp) {
        Row row1 = o1;
        Row row2 = o2;
        String attrLabel = "";
        Map<String, Object> data1 = row1.getValue();
        Map<String, Object> data2 = row2.getValue();
        String _o1 = "";
        String _o2 = "";
        if (!TextUtils.isEmpty(subComp.getKjAttributeDisplay()))
            attrLabel = subComp.getKjAttributeDisplay();
        else
            attrLabel = subComp.getKjAttribute();
        if (TextUtils.isEmpty(doGetObjByLabel(attrLabel, data1)))
            _o1 = "0";
        else
            _o1 = doGetObjByLabel(attrLabel, data1).toString();
        if (TextUtils.isEmpty(doGetObjByLabel(attrLabel, data2)))
            _o2 = "0";
        else
            _o2 = doGetObjByLabel(attrLabel, data2).toString();
        try {
            return (int) ((Double.parseDouble(_o2) - Double.parseDouble(_o1)) * 100);
        } catch (Exception e) {
            return _o2.compareTo(_o1);
        }
    }

    public int doSort(Row o1, Row o2, final int index) {
        Row row1 = o1;
        Row row2 = o2;
        String attrLabel = "";
        Map<String, Object> data1 = row1.getValue();
        Map<String, Object> data2 = row2.getValue();
        String _o1 = "";
        String _o2 = "";
        BaseTextbox baseTextbox1 = (BaseTextbox) row1.getChildren().get(index);
        BaseTextbox baseTextbox2 = (BaseTextbox) row2.getChildren().get(index);
        if (TextUtils.isEmpty(baseTextbox1.getText()))
            _o1 = "0";
        else
            _o1 = baseTextbox1.getText();
        if (TextUtils.isEmpty(baseTextbox2.getText()))
            _o2 = "0";
        else
            _o2 = baseTextbox2.getText();
        try {
            return (int) ((Double.parseDouble(_o2) - Double.parseDouble(_o1)) * 100);
        } catch (Exception e) {
            return _o2.compareTo(_o1);
        }
    }
    //展现数据在grid中

    /**
     * <p>方法:displayData 显示grid的数据 </p>
     * <ul>
     * <li> @param grid 指定的grid</li>
     * <li> @param datas 指定数据列表</li>
     * <li> @param window T当前窗口</li>
     * <li> @param subFlag 是否是内部编辑的</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/30 13:05  </li>
     * </ul>
     */
//    public void displayData(BaseGrid grid, List<Map<String, Object>> datas, BaseWindow window, boolean subFlag)
    public void displayDataWithTextbox(BaseGrid grid, List<Map<String, Object>> datas, BaseWindow window, boolean subFlag) {
        Rows rows = null;
        Object _obj = null;
        String _css = "";
        grid.setSubFlag(subFlag);
        if (null == grid.getRows()) {
            rows = new Rows();
            rows.setParent(grid);
        } else
            rows = grid.getRows();
        rows.getChildren().clear();
        if (null == datas)
            return;
        doDisplay(grid, datas, subFlag, rows);
    }

    //追加新增
    public void displayAppendDataWithTextbox(BaseGrid grid, List<Map<String, Object>> datas, BaseWindow window, boolean subFlag) {
        Rows rows = null;
        Object _obj = null;
        String _css = "";
        grid.setSubFlag(subFlag);
        if (null == grid.getRows()) {
            rows = new Rows();
            rows.setParent(grid);
        } else
            rows = grid.getRows();
        if (null == datas)
            return;
        doDisplay(grid, datas, subFlag, rows);
    }


    //具体展示
    private void doDisplay(BaseGrid grid, List<Map<String, Object>> datas, boolean subFlag, Rows rows) {
        String _css;
        Object _obj;//循环显示数据
        int _countIndex = (grid.getPageNo() - 1) * grid.getPageSize();
        int _inCountIndex = 1;
        //处理统计
        Map<String, Object> footMap = new HashMap<String, Object>(); // 如果存在foot，则用次进行统计

        if (null == grid.getParent().getFellowIfAny(grid.getId() + "_rdGroup")) {
            Radiogroup radiogroup = new Radiogroup();
            radiogroup.setId(grid.getId() + "_rdGroup");
            radiogroup.setParent(grid.getParent());
        }

        for (Object dataObj : datas) {
            Map<String, Object> data = null;
            if (dataObj instanceof Map)
                data = (Map<String, Object>) dataObj;
            if (dataObj instanceof ArrayList) {
                data = convertList2Map((ArrayList) dataObj);
            }
            Row row = new Row();
            //是否数据中存在了，存在则不设置为0
            if (TextUtils.isEmpty(data.get(SUBEDIT)))
                data.put(SUBEDIT, 0);//表示未修改过
            row.setValue(data);
            row.setParent(rows);
            row.setId(TextUtils.getUUID());
            data.put(ROWINDEXNO, (row.getIndex() + 1) + "");
            data.put(ROWINDEXSTR, data.get(ROWINDEXNO).toString());
            //处理行移动
            doSetMoveRow(grid, row);
            //处理是否有detail
            if (null != grid.getLayoutVO() && !TextUtils.isEmpty(grid.getLayoutVO().getControlDetailInt())) {
                Detail detail = new Detail();
                detail.setParent(row);
                detail.setOpen(false);
                detail.addEventListener(Events.ON_OPEN, new OnCheckEvent(grid));
            }
            //给行加底色，分别是 warning,danger,success
            _css = doRowCssFromData(grid.getLayoutVO(), data);
            if (!TextUtils.isEmpty(_css)) {
                row.setClass(_css);
            }
            //处理多选框还是单选框
            if (null != grid.getLayoutVO() && null != grid.getLayoutVO().getControlCheck()) {
                if (1 == grid.getLayoutVO().getControlCheck()) {
                    BaseCheckbox ck = new BaseCheckbox();
                    ck.setParent(row);
                    ck.setClass("checkbox");
//                    row.addEventListener(Events.ON_CLICK, new OnCheckEvent(grid));
                }
                if (2 == grid.getLayoutVO().getControlCheck()) {
                    BaseRadio rd = new BaseRadio();
                    rd.setParent(row);
                    rd.setClass("checkbox");
                    rd.setRadiogroup(grid.getId() + "_rdGroup");
//                    row.addEventListener(Events.ON_CLICK, new OnCheckEvent(grid));
                }
            }

            //双击事件
            if (subFlag) {
                //内部编辑的，一定有默认的双击事件
                row.addEventListener(Events.ON_CLICK, new OnCheckEvent(grid));
            } else {
                if (null != grid.getLayoutVO() && !TextUtils.isEmpty(grid.getLayoutVO().getControlDbclickEvent())) {
                    row.addEventListener(Events.ON_DOUBLE_CLICK, new OnCheckEvent(grid));
                }
            }
            //显示每页的编号
            if (grid.isIndexCol()) {
                _countIndex = (grid.getPageNo() - 1) * grid.getPageSize() + _inCountIndex;
                BaseTextbox label = TemplateUtils.doCreateInGridTextbox();
                label.setParent(row);
                label.setValue(_countIndex + "");
                _inCountIndex++;
                grid.setMaxIndex(_countIndex);
            }
            if (null != grid.getControlVOS()) {
                int _colIndex = 0;
                for (PageControlVO pageControlVO : grid.getControlVOS()) {
                    _obj = doHandleMultipleProperty(data, pageControlVO); //处理数据中包含.的多级显示
                    displayCell(data, row, pageControlVO, _obj, _colIndex);
                    doHandleFootData(footMap, pageControlVO, _obj);//处理foot
                    _colIndex++;
                }
            }
        }
        //处理页脚
        displayFoot(footMap, grid);
    }

    //根据每行的唯一标识来进行刷新
    public void refreshRow(Map<String, Object> data, BaseGrid grid, String rowId) {
        List<Column> cls = grid.getColumns().getChildren();
        BaseTextbox label = null;
        int index = 0;
        Row row = doFindSelRow(grid, rowId);
        if (null == row)
            return;
        Object _obj;

        int _inCountIndex = 1;
        row.setValue(data);
        row.getChildren().clear();
        int _countIndex;
        if (null != grid.getLayoutVO() && null != grid.getLayoutVO().getControlCheck()) {
            if (1 == grid.getLayoutVO().getControlCheck()) {
                BaseCheckbox ck = new BaseCheckbox();
                ck.setParent(row);
                ck.setClass("checkbox");
//                    row.addEventListener(Events.ON_CLICK, new OnCheckEvent(grid));
            }
            if (2 == grid.getLayoutVO().getControlCheck()) {
                BaseRadio rd = new BaseRadio();
                rd.setParent(row);
                rd.setClass("checkbox");
                rd.setRadiogroup(grid.getId() + "_rdGroup");
//                    row.addEventListener(Events.ON_CLICK, new OnCheckEvent(grid));
            }
        }
        //处理是否有detail
        if (null != grid.getLayoutVO() && !TextUtils.isEmpty(grid.getLayoutVO().getControlDetailInt())) {
            Detail detail = new Detail();
            detail.setParent(row);
            detail.setOpen(false);
            detail.addEventListener(Events.ON_OPEN, new OnCheckEvent(grid));
        }

        //显示每页的编号
        if (grid.isIndexCol()) {
            _countIndex = (grid.getPageNo() - 1) * grid.getPageSize() + _inCountIndex;
            BaseTextbox _label = TemplateUtils.doCreateInGridTextbox();
            _label.setParent(row);
            _label.setValue(_countIndex + "");
            _inCountIndex++;
            grid.setMaxIndex(_countIndex);
        }

        if (null != grid.getControlVOS()) {
            int _colIndex = 0;
            for (PageControlVO pageControlVO : grid.getControlVOS()) {
                _obj = doHandleMultipleProperty(data, pageControlVO); //处理数据中包含.的多级显示
                displayCell(data, row, pageControlVO, _obj, _colIndex);
//                doHandleFootData(footMap, pageControlVO, _obj);//处理foot
                _colIndex++;
            }
        }
//        for (Column cl : cls) {
//            if (!TextUtils.isEmpty(cl.getAttribute("key"))) {
//                if (null != row.getChildren().get(index) && row.getChildren().get(index) instanceof BaseTextbox) {
//                    label = (BaseTextbox) row.getChildren().get(index);
//                } else {
//                    label = TemplateUtils.doCreateInGridTextbox();
//                    label.setParent(row);
//                }
//                if (null != data.get(cl.getAttribute("key").toString()))
//                    label.setValue(doRemoveNull(data.get(cl.getAttribute("key").toString()).toString()));
//                else
//                    label.setValue("");
//            }
//            index++;
//        }
    }

    //处理row，设置row为可移动
    private void doSetMoveRow(BaseGrid grid, Row row) {
        row.setDroppable("true");
        row.setDraggable("true");
        row.addEventListener(Events.ON_DROP, new ItemClickEvent(grid));
        row.addEventListener(Events.ON_DOUBLE_CLICK, new ItemClickEvent(grid));
    }

    private void doMoveRow(Row row, Event event) {
        DropEvent dropEvent = (DropEvent) event;
        if (dropEvent.getDragged() instanceof Row) {
            Row dragBtn = (Row) dropEvent.getDragged();
            Map<String, Object> sourBtn = dragBtn.getValue();
            Map<String, Object> destBtn = row.getValue();
            if (TextUtils.isEmpty(destBtn.get(ROWINDEXNO)))
                destBtn.put(ROWINDEXNO, dragBtn.getIndex() + 1);
            if (TextUtils.isEmpty(sourBtn.get(ROWINDEXNO)))
                sourBtn.put(ROWINDEXNO, row.getIndex() + 1);
            double destOrder = Double.parseDouble(destBtn.get(ROWINDEXNO).toString());
            Row preRow = null;
            if (row.getIndex() != 0)
                preRow = (Row) (((Rows) row.getParent()).getChildren().get(row.getIndex() - 1));
            if (null == preRow) {
                sourBtn.put(ROWINDEXNO, destOrder - 0.1);
            } else {
                Map<String, Object> _preData = preRow.getValue();
                if (TextUtils.isEmpty(_preData.get(ROWINDEXNO)))
                    _preData.put(ROWINDEXNO, preRow.getIndex());
                double sorder = Double.parseDouble(_preData.get(ROWINDEXNO).toString());
                double sorder1 = (sorder + destOrder) / 2;
                sourBtn.put(ROWINDEXNO, sorder1);
            }
            dragBtn.setValue(sourBtn);
            row.getParent().insertBefore(dragBtn, row);
        }
    }

    //显示grid中指定的单元格
    public void displayCell(Map<String, Object> data, Row row, PageControlVO pageControlVO, Object _obj, int index) {
//        addRowStrCharater(data,pageControlVO,row);
        String _css;
        //checkbox 定义显示
        if (pageControlVO.getKjType() == 8) {
            BaseCheckbox checkbox = new BaseCheckbox();
            checkbox.setPgvo(pageControlVO);
            checkbox.setParent(row);
            if (!TextUtils.isEmpty(_obj) && _obj.toString().equalsIgnoreCase("1"))
                checkbox.setChecked(true);
            checkbox.setDisabled(true);
            checkbox.setClass("checkbox");
            checkbox.setAttribute(DEFAULTLABEL, 1);//表示只是显示控件
            checkbox.addEventListener(Events.ON_CLICK, new OnCheckEvent(checkbox, index));
            return;
        }
        if (pageControlVO.getKjType() == 21) {
            Image image = new Image();
            image.setStyle("width:30px;height:30px;");
            if (!TextUtils.isEmpty(_obj))
                image.setSrc(_obj.toString());
            image.setParent(row);
            image.setAttribute(DEFAULTLABEL, 1);//表示只是显示控件
            image.addEventListener(Events.ON_CLICK, new ImageEvent(_obj.toString()));
            return;
        }
        BaseTextbox label = TemplateUtils.doCreateInGridTextbox();
        label.setAttribute(DEFAULTLABEL, 1);//表示只是显示控件
        if (!TextUtils.isEmpty(pageControlVO.getLayoutComponent().getCompCols()) && pageControlVO.getLayoutComponent().getCompCols() > 0)
            label.setWidth(pageControlVO.getLayoutComponent().getCompCols() + "px");
        label.setPgvo(pageControlVO);
        label.setParent(row);
        if (!TextUtils.isEmpty(pageControlVO.getKjAttributeDisplay())) {
            label.setProperty(pageControlVO.getKjAttributeDisplay());
        } else {
            label.setProperty(pageControlVO.getKjAttribute());
        }
        label.setAttribute("property", label.getProperty());
        if (null != _obj) {
            //判断整列的css
            if (!TextUtils.isEmpty(pageControlVO.getLayoutComponent().getColumCss()))
                label.setSclass(label.getSclass() + " " + pageControlVO.getLayoutComponent().getColumCss());
            //判断单元格是否有样式定义
            _css = doCellCssFromData(pageControlVO.getLayoutComponent(), data);
            if (!TextUtils.isEmpty(_css))
                label.setSclass(label.getSclass() + " " + _css);
            //判断单元格是否有个事转换定义
            if (!TextUtils.isEmpty(pageControlVO.getKjFormat())) {
                try {
                    String cls = "";
                    String params = "";
                    if (pageControlVO.getKjFormat().indexOf("?") > 0) {
                        String[] _kjformats = pageControlVO.getKjFormat().split("\\?");
                        cls = _kjformats[0];
                        params = _kjformats[1];
                    } else
                        cls = pageControlVO.getKjFormat();
                    RenderUtils renderUtils = (RenderUtils) Class.forName(cls).newInstance();
                    label.setValue(doRemoveNull(renderUtils.rendar(_obj, params, label)));
                } catch (Exception e) {
                    label.setValue("");
                }
            } else
                label.setValue(doRemoveNull(_obj));

            //对齐方式处理
            label.setStyle(label.getStyle() + doCellAlign(pageControlVO.getLayoutComponent()));
            //浮动提示
            label.setTooltiptext(label.getValue());
        } else
            label.setValue("");
        label.setRealVal(label.getValue());
        //label的点击事件，在处理修改状态的时候可以定位到该控件

        label.addEventListener(Events.ON_CLICK, new OnCheckEvent(label, index));

    }

    private void addRowStrCharater(Map<String, Object> data, PageControlVO pageControlVO, Row row) {

        if (!TextUtils.isEmpty(data.get(pageControlVO.getKjAttribute())))
            data.put(ROWINDEXSTR, data.get(ROWINDEXSTR) + data.get(pageControlVO.getKjAttribute()).toString());
    }

    //去除内容是null的显示
    private String doRemoveNull(Object obj) {
        if (TextUtils.isEmpty(obj))
            return "";
        else
            return obj.toString();
    }

    /*处理单元格的对齐方式*/
    private String doCellAlign(PageLayoutControlVO vo) {
        if (null == vo.getCompPosition())
            return "text-align:left;";
        switch (vo.getCompPosition()) {
            case 0:
                return "text-align:left;";
            case 1:
                return "text-align:center;";
            case 2:
                return "text-align:right;";
        }
        return "text-align:left;";
    }

    /*处理整行的样式*/
    private String doRowCssFromData(PageLayoutVO vo, Map<String, Object> data) {
        boolean flag = null == vo || TextUtils.isEmpty(vo.getControlRowCss()) || null == data;
        if (flag)
            return "";
        String jsonStr = vo.getControlRowCss().toString();
        List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(jsonStr);
        String formula = "";
        String css = "";
        boolean cssflag = false;
        Set<String> keys = data.keySet();
        for (Map<String, Object> objectMap : paramsList) {
            formula = objectMap.get("formula").toString();
            css = objectMap.get("css").toString();
            for (String s : keys) {
                formula = formula.replace(s, TextUtils.isEmpty(data.get(s)) ? "" : data.get(s).toString());
            }
            formula = formula.replaceAll("\\$", "'");
            cssflag = doRunFormula(formula);
//            System.out.println(formula + cssflag);
            if (cssflag)
                return css;
        }
        return "";

    }

    private String doCellCssFromData(PageLayoutControlVO vo, Map<String, Object> data) {
        boolean flag = null == vo || TextUtils.isEmpty(vo.getCompFontCss()) || null == data;
        if (flag)
            return "";
        String jsonStr = vo.getCompFontCss().toString();
        List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(jsonStr);
        String formula = "";
        String css = "";
        boolean cssflag = false;
        Set<String> keys = data.keySet();
        for (Map<String, Object> objectMap : paramsList) {
            formula = objectMap.get("formula").toString();
            css = objectMap.get("css").toString();
            for (String s : keys) {
                formula = formula.replace(s, TextUtils.isEmpty(data.get(s)) ? "" : data.get(s).toString());
            }
            formula = formula.replaceAll("\\$", "'");
            cssflag = doRunFormula(formula);
            if (cssflag)
                return css;
        }
        return "";

    }

    private boolean doRunFormula(String formula) {
        ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
        try {
            return jse.eval(formula).toString().equals("true");
        } catch (Exception t) {
            return false;
        }
    }

    private void displayFoot(Map<String, Object> footMap, BaseGrid grid) {
        Foot foot = createOrGetFoot(grid);
        List<BaseFooter> footers = foot.getChildren();
        for (BaseFooter footer : footers) {
            footer.fillValue(footMap);
        }
    }

    //处理表格的foot
    private Foot createOrGetFoot(BaseGrid grid) {
        Foot foot = grid.getFoot();
        if (null == foot) {
            //创建
            foot = new Foot();
            foot.setParent(grid);
            if (null != grid.getLayoutVO() && null != grid.getLayoutVO().getControlCheck()) {
                BaseFooter footer = new BaseFooter();
                footer.setProperty("checkbox");
                footer.setParent(foot);
            }
            if (grid.isIndexCol()) {
                BaseFooter footer = new BaseFooter();
                footer.setProperty(ROWINDEXNO);
                footer.setParent(foot);
            }
            if (null != grid && null != grid.getControlVOS() && grid.getControlVOS().size() > 0)
                for (PageControlVO pageControlVO : grid.getControlVOS()) {
                    BaseFooter footer = new BaseFooter();
                    footer.setProperty(pageControlVO.getKjAttribute());
                    footer.setParent(foot);
                }
        }
        return foot;
    }

    //根据foot的定义，展现foot数据
    private void doHandleFootData(Map<String, Object> data, PageControlVO pageControlVO, Object valueObj) {
        boolean flag = null == pageControlVO || null == pageControlVO.getLayoutComponent() || null == pageControlVO.getLayoutComponent().getCompTjlx() || pageControlVO.getLayoutComponent().getCompTjlx() == 0;
        if (flag) {
            data.put(pageControlVO.getKjAttribute(), " ");
            return;
        }
        switch (pageControlVO.getLayoutComponent().getCompTjlx()) {
            case 1:
                data.put(pageControlVO.getKjAttribute(), pageControlVO.getLayoutComponent().getCompTjwb());
                break;
            case 2://条数
                countCol(data, pageControlVO.getKjAttribute());
                break;
            case 3://求和
                sumCol(data, valueObj, pageControlVO.getKjAttribute());
                break;
            case 4://平均
                sumCol(data, valueObj, pageControlVO.getKjAttribute() + "_sum");
                countCol(data, pageControlVO.getKjAttribute() + "_count");
                if (null != data.get(pageControlVO.getKjAttribute() + "_count") && (Integer) data.get(pageControlVO.getKjAttribute() + "_count") != 0) {
                    data.put(pageControlVO.getKjAttribute(), (Double) data.get(pageControlVO.getKjAttribute() + "_sum") / (Integer) data.get(pageControlVO.getKjAttribute() + "_count"));
                } else
                    data.put(pageControlVO.getKjAttribute(), 0);
                break;
            case 5:
                if (null == data.get(pageControlVO.getKjAttribute())) {
                    if (null != valueObj) {
                        try {
                            data.put(pageControlVO.getKjAttribute(), (Double) valueObj);
                        } catch (Exception e) {
                        }
                    }
                } else {
                    if (null != valueObj) {
                        try {
                            if ((Double) valueObj > (Double) data.get(pageControlVO.getKjAttribute())) {
                                data.put(pageControlVO.getKjAttribute(), (Double) valueObj);
                            }
                        } catch (Exception e) {
                        }
                    }
                }
                break;
            case 6:
                if (null == data.get(pageControlVO.getKjAttribute())) {
                    if (null != valueObj) {
                        try {
                            data.put(pageControlVO.getKjAttribute(), (Double) valueObj);
                        } catch (Exception e) {
                        }
                    }
                } else {
                    if (null != valueObj) {
                        try {
                            if ((Double) valueObj < (Double) data.get(pageControlVO.getKjAttribute())) {
                                data.put(pageControlVO.getKjAttribute(), (Double) valueObj);
                            }
                        } catch (Exception e) {
                        }
                    }
                }
                break;
        }


    }

    private void countCol(Map<String, Object> data, String keyword) {
        if (null == data.get(keyword))
            data.put(keyword, 1);
        else {
            data.put(keyword, (Integer) data.get(keyword) + 1);
        }
    }

    //单个字段求和
    private void sumCol(Map<String, Object> data, Object valueObj, String key) {
        if (null == data.get(key))
            if (null == valueObj)
                data.put(key, 0);
            else {
                try {
                    double d = new Double(valueObj.toString());
                    data.put(key, d);
                } catch (Exception e) {

                }
            }
        else {
            if (null != valueObj) {
                try {
                    double d = new Double(valueObj.toString());
                    data.put(key, (Double) data.get(key) + d);
                } catch (Exception e) {

                }
            }
        }
    }

    //递归方式处理返回时数组类型
    private Map<String, Object> convertList2Map(ArrayList arrayList) {
        Map<String, Object> data = new HashMap<String, Object>();
        for (int index = 0; index < arrayList.size(); index++) {
            Object o = arrayList.get(index);
            if (o instanceof Map) {
                if (index == 0)
                    data = (Map<String, Object>) o;//如果是第一个，则变为整个map
                else
                    data.put("bean" + index, (Map<String, Object>) o);
            } else {
                data.put("bean" + index, convertList2Map((ArrayList) o));
            }
        }
        return data;
    }

    //处理多级别的属性，如 a.b.c,逐级解析
    public Object doHandleMultipleProperty(Map<String, Object> data, PageControlVO pageControlVO) {
        Object _obj = null;
        String labelKey = "";
        if (TextUtils.isEmpty(pageControlVO.getKjAttributeDisplay())) {
            labelKey = pageControlVO.getKjAttribute();
        } else {
            labelKey = pageControlVO.getKjAttributeDisplay();
        }
        _obj = doGetObjByLabel(labelKey, data);
        if (_obj instanceof JSONNull)
            _obj = null;
//        System.out.println("==============" + labelKey + "====" + _obj);
        return _obj;
    }

    /*从分离点路径，获取数据*/
    private Object doGetObjByLabel(String labelKey, Map<String, Object> data) {
        Object _obj = null;
        if (labelKey.contains(".")) {
            Map<String, Object> tmpData = data;
            String[] _labelKeys = labelKey.split("\\.");
            for (int index = 0; index < _labelKeys.length; index++) {
                if (index != _labelKeys.length - 1) {
                    if (TextUtils.isEmpty(tmpData.get(_labelKeys[index])))
                        return null;
                    tmpData = (Map<String, Object>) tmpData.get(_labelKeys[index]);
                    if (TextUtils.isEmpty(tmpData))
                        return null;
                } else
                    _obj = tmpData.get(_labelKeys[index]);
            }
        } else
            _obj = data.get(labelKey);
        return _obj;
    }

    //自动根据表格中是否有行，插入到最前面
    public void addNewRow(Map<String, Object> data, BaseGrid grid, BaseWindow window) {
        doaddNewRow(data, grid, window, true);
    }

    public void addNewLastRow(Map<String, Object> data, BaseGrid grid, BaseWindow window) {
        doaddNewRow(data, grid, window, false);
    }

    /**
     * <p>方法:addNewRow 自动添加一行 </p>
     * <ul>
     * <li> @param data 单行数据</li>
     * <li> @param grid 指定的grid</li>
     * <li> @param window 指定的window</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/25 13:58  </li>
     * </ul>
     */
    public void doaddNewRow(Map<String, Object> data, BaseGrid grid, BaseWindow window, boolean preFlag) {
        Rows rows = grid.getHeaderRows();
        Row row = new Row();
        row.setValue(data);
        row.setId(TextUtils.getUUID());
        int _index = 0;
        if (null != grid.getLayoutVO() && null != grid.getLayoutVO().getControlCheck()) {
            if (1 == grid.getLayoutVO().getControlCheck()) {
                BaseCheckbox checkbox = PageUtils.createCheckBox(row, "width:30px");
                checkbox.setClass("checkbox");
                _index = 1;
            }
            if (2 == grid.getLayoutVO().getControlCheck()) {
                PageUtils.createRadio(row, "width:30px");
                _index = 1;
            }
        }
        //处理是否有detail
        if (null != grid.getLayoutVO() && !com.ourway.base.utils.TextUtils.isEmpty(grid.getLayoutVO().getControlDetailInt())) {
            Detail detail = new Detail();
            detail.setParent(row);
            detail.setOpen(false);
        }
//        row.addEventListener(Events.ON_CLICK, new OnCheckEvent(grid));
        //双击事件

//        if (null != grid.getLayoutVO() && null != grid.getLayoutVO().getControlIsdb() && grid.getLayoutVO().getControlIsdb() == 1) {
//            row.addEventListener(Events.ON_DOUBLE_CLICK, new OnCheckEvent(grid));
//        }
        if (null != grid.getLayoutVO() && !TextUtils.isEmpty(grid.getLayoutVO().getControlDbclickEvent())) {
            row.addEventListener(Events.ON_DOUBLE_CLICK, new OnCheckEvent(grid));
        } else if (null != grid.getLayoutVO() && null != grid.getLayoutVO().getControlIsdb() && grid.getLayoutVO().getControlIsdb() == 2)
            row.addEventListener(Events.ON_DOUBLE_CLICK, window);
        //如果有序号，则获取当页最大序号
        if (grid.isIndexCol()) {
            BaseTextbox label = TemplateUtils.doCreateInGridTextbox();
            label.setParent(row);
            if (null == grid.getMaxIndex())
                grid.setMaxIndex(1);
            else
                grid.setMaxIndex(grid.getMaxIndex() + 1);
            label.setText(grid.getMaxIndex() + "");
            _index++;
        }
        List<Column> cls = grid.getColumns().getChildren();
        for (; _index < cls.size(); _index++) {
            Column cl = cls.get(_index);
            PageControlVO _vo = (PageControlVO) cl.getAttribute("subComp");
            if (null != _vo)
                displayCell(data, row, _vo, data.get(cl.getAttribute("key").toString()), _index);
        }
        if (preFlag) {
            if (null != rows.getFirstChild())
                rows.insertBefore(row, rows.getFirstChild());
            else
                row.setParent(rows);
        } else
            row.setParent(rows);
    }

    //新增或者修改表格行内操作
    public void addNewEditRow(Map<String, Object> data, BaseGrid grid, BaseWindow window) {
        //如果页面是查看状态，则不能修改
        if (window.getWindowType() == 2)
            return;
        Rows rows = grid.getHeaderRows();
        Row row = PageUtils.createRow(grid, "");
        //新增的时候，添加数据
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("owid", null);
        dataMap.put("updateFlag", 1);
        Set<String> set = data.keySet();
        for (String s : set) {
            dataMap.put(s, data.get(s));
        }
        row.setValue(dataMap);
        doInlineEditRow(row, grid, window, dataMap);
//        row.setParent(rows);
        //做插入row
        doInsertNewRow(grid, row, rows);


    }

    private void doInsertNewRow(BaseGrid grid, Row newRow, Rows rows) {
        List<Row> selRows = grid.getSelectRows();
        Map<String, Object> selRowMap = null;
        double newSort = 1;
        if (null == selRows || selRows.size() <= 0) {
            newRow.setParent(rows);
            newSort = newRow.getIndex();
        } else {
            Row selRow = selRows.get(0);
            selRowMap = selRow.getValue();
            double _selSort = Double.parseDouble(selRowMap.get(ROWINDEXNO).toString());
            double _preSort = 0;
            if (selRow.getIndex() > 1) {
                Row _preRow = (Row) rows.getChildren().get(selRow.getIndex() - 1);
                selRowMap = _preRow.getValue();
                _preSort = Double.parseDouble(selRowMap.get(ROWINDEXNO).toString());
            }
            newSort = (_selSort + _preSort) / 2;
            rows.insertBefore(newRow, selRow);
        }
        selRowMap = newRow.getValue();
        selRowMap.put(ROWINDEXNO, newSort);
        selRowMap.put(ROWINDEXSTR, selRowMap.get(ROWINDEXNO).toString());
        newRow.setValue(selRowMap);
    }

    /*返回刷新列表*/
    public void refreshRow(Map<String, Object> data, BaseGrid grid, Row row) {
        List<Column> cls = grid.getColumns().getChildren();
        BaseTextbox label = null;
        int index = 0;
        int _inCountIndex = 1;
        row.setValue(data);
        row.getChildren().clear();
        Object _obj;
        int _countIndex;
        if (null != grid.getLayoutVO() && null != grid.getLayoutVO().getControlCheck()) {
            if (1 == grid.getLayoutVO().getControlCheck()) {
                BaseCheckbox ck = new BaseCheckbox();
                ck.setParent(row);
                ck.setClass("checkbox");
//                    row.addEventListener(Events.ON_CLICK, new OnCheckEvent(grid));
            }
            if (2 == grid.getLayoutVO().getControlCheck()) {
                BaseRadio rd = new BaseRadio();
                rd.setParent(row);
                rd.setClass("checkbox");
                rd.setRadiogroup(grid.getId() + "_rdGroup");
//                    row.addEventListener(Events.ON_CLICK, new OnCheckEvent(grid));
            }
        }
        //处理是否有detail
        if (null != grid.getLayoutVO() && !TextUtils.isEmpty(grid.getLayoutVO().getControlDetailInt())) {
            Detail detail = new Detail();
            detail.setParent(row);
            detail.setOpen(false);
            detail.addEventListener(Events.ON_OPEN, new OnCheckEvent(grid));
        }

        //显示每页的编号
        if (grid.isIndexCol()) {
            _countIndex = (grid.getPageNo() - 1) * grid.getPageSize() + _inCountIndex;
            BaseTextbox _label = TemplateUtils.doCreateInGridTextbox();
            _label.setParent(row);
            _label.setValue(_countIndex + "");
            _inCountIndex++;
            grid.setMaxIndex(_countIndex);
        }
        if (null != grid.getControlVOS()) {
            int _colIndex = 0;
            for (PageControlVO pageControlVO : grid.getControlVOS()) {
                _obj = doHandleMultipleProperty(data, pageControlVO); //处理数据中包含.的多级显示
                displayCell(data, row, pageControlVO, _obj, _colIndex);
//                doHandleFootData(footMap, pageControlVO, _obj);//处理foot
                _colIndex++;
            }
        }

//        for (Column cl : cls) {
//            if (!TextUtils.isEmpty(cl.getAttribute("key"))) {
//                if (null != row.getChildren().get(index) && row.getChildren().get(index) instanceof BaseTextbox) {
//                    label = (BaseTextbox) row.getChildren().get(index);
//                } else {
//                    label = TemplateUtils.doCreateInGridTextbox();
//                    label.setParent(row);
//                }
//                if (null != data.get(cl.getAttribute("key").toString()))
//                    label.setValue(doRemoveNull(data.get(cl.getAttribute("key").toString()).toString()));
//                else
//                    label.setValue("");
//            }
//            index++;
//        }
    }

    //删除选中的行
    public void removeRow(BaseGrid grid, String rowId) {
        Row row = (Row) grid.getFellowIfAny(rowId);
        if (null != row) {
            grid.getRows().removeChild(row);
        }
    }


    public Row doFindSelRow(BaseGrid grid, String rowId) {
        List<Row> _rows = grid.getRows().getChildren();
        for (Row row : _rows) {
            if (row.getId().equalsIgnoreCase(rowId))
                return row;
        }
        return null;
    }


    /**
     * <p>方法:getAllDatas 用于内部编辑的时候，获取内部编辑过的数据 </p>
     * <ul>
     * <li> @param grid TODO</li>
     * <li>@return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/30 14:01  </li>
     * </ul>
     */
    public List<Map<String, Object>> getAllDatas(BaseGrid grid, boolean all) {
        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
        boolean flag = false;//用于判断是否填写了数据
        boolean _increaceFlag = false;//是否全部取数据还是增量
        Rows rows = grid.getHeaderRows();
        if (null == grid.getColumns())
            return null;
        List<Column> cls = grid.getColumns().getChildren();
        List<Row> dataRows = rows.getChildren();
        int _index = 0;
        if (null != grid.getLayoutVO() && null != grid.getLayoutVO().getControlCheck()) {
            if (1 == grid.getLayoutVO().getControlCheck()) {
                _index = 1;
            }
            if (2 == grid.getLayoutVO().getControlCheck()) {
                _index = 1;
            }
        }
        if (null != grid.getLayoutVO() && !TextUtils.isEmpty(grid.getLayoutVO().getControlWay()) && grid.getLayoutVO().getControlWay() == 1)
            _increaceFlag = true;
        int index = _index;
        for (Row dataRow : dataRows) {
            Map<String, Object> _dataMap = (Map<String, Object>) dataRow.getValue();
            //加入这一行的RowId，便于操作完毕后对正行进行刷新
            _dataMap.put("rowId",dataRow.getId());
            if (null == _dataMap.get(GridUtils.SUBEDIT)) {
                _dataMap.put(SUBEDIT, 0);
                _dataMap.put("_increaseFlag", _increaceFlag);
                datas.add(_dataMap);
            } else {
                switch (Integer.parseInt(_dataMap.get(SUBEDIT).toString())) {
                    case 0://表示什么都不做，但是还是要传递到后台，可能涉及到数据的判断
                        _dataMap.put(SUBEDIT, 0);
                        _dataMap.put("_increaseFlag", _increaceFlag);
                        datas.add(_dataMap);
                        break;
                    case 1://表示新增修改
                        flag = bindToRowMap(grid, cls, index, dataRow, _dataMap);
                        index = _index;
                        _dataMap.put("_increaseFlag", true);
                        if (flag)
                            datas.add(_dataMap);
                        break;
                    case 2://表示删除
                        _dataMap.put(SUBEDIT, 2);
                        _dataMap.put("_increaseFlag", true);
                        datas.add(_dataMap);
                        break;
                    case 3://表示不经过编辑，直接从其他程序嵌入的
                        _dataMap.put(SUBEDIT, 1);
                        _dataMap.put("_increaseFlag", true);
                        datas.add(_dataMap);
                        break;
                }
            }
        }
        //对datas里面的内容进行重新排序，indexNo
        Collections.sort(datas, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                double d1 = Double.parseDouble(o1.get(ROWINDEXNO).toString());
                double d2 = Double.parseDouble(o2.get(ROWINDEXNO).toString());
                return (int) ((d1 - d2) * 1000);
            }
        });
        //重新组织data中的顺序
        int _rowIndex = 1;
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> dataRow : datas) {
            //处理增量更新还是全部返回。默认是增量
            if ((boolean) dataRow.get("_increaseFlag"))
                result.add(dataRow);
//            dataRow.put(ROWINDEXNO, _rowIndex);
//            if (null != dataRow.get(SUBEDIT) && (Integer.parseInt(dataRow.get(SUBEDIT).toString())) != 2) {
//                if (!dataRow.get(ROWINDEXNO).toString().equalsIgnoreCase(dataRow.get(ROWINDEXSTR).toString()))
//                    dataRow.put(SUBEDIT, 1);
//            }
//            _rowIndex++;
//            if (all) {
//                if (null != dataRow.get(SUBEDIT) && (Integer.parseInt(dataRow.get(SUBEDIT).toString())) > 0)
//                    result.add(dataRow);
//            } else

        }
        return result;
    }

    //    绑定表格中的一行数据，从控件中把值绑定到dataMap中,返回true表示有数据填，返回false表示没有数据
    private boolean bindToRowMap(BaseGrid grid, List<Column> cls, int index, Row dataRow, Map<String, Object> _dataMap) {
        Component component = null;
        PageControlVO subComp = null;
        Column cl = null;
        Object _obj = null;
        boolean flag = false;//用于判断是否每个选项输入的都为空
        for (; index < cls.size(); index++) {
            cl = cls.get(index);
            subComp = (PageControlVO) cl.getAttribute("comp");
            component = dataRow.getChildren().get(index);
            //默认的就不需要继续取显示框中取值。
            if (null != component.getAttribute(DEFAULTLABEL) && component.getAttribute(DEFAULTLABEL).toString().equalsIgnoreCase("1"))
                continue;
            _obj = DataBinder.component2Obj(component, true, getWindowFromGrid(grid));
            if (!TextUtils.isEmpty(_obj))
                flag = true;
            doMuiltProperty(_dataMap, subComp.getKjBindKey().replaceAll(grid.getId() + ".", ""), _obj);
//                        doMuiltProperty(_dataMap, subComp.getKjAttribute().replaceAll(grid.getId() + ".", ""), _obj);
        }
        return flag;
    }


    //打开过滤窗口
    private void doFilterWindow(BaseGrid grid) {
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("cols", grid.getControlVOS());
        try {
            FilterGridAction winEdit = (FilterGridAction) Executions.createComponents("/template/filterGrid.do", null, params);
            winEdit.setStyle("width:420px;height:180px;");
            winEdit.setClosable(true);
//            winEdit.setPosition("center,top");
            winEdit.setTitle(I18nUtil.getLabelContent(ERCode.GRID_FILTER_WINDOW_TITLE));
            winEdit.doModal();
            if (winEdit.isClosePage()) {
                List<GridFilterVO> vos = winEdit.getGridFilterVOS();
                if (null == vos || vos.size() <= 0)
                    return;
                //对grid进行过滤
                gridFilter(grid, vos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //对grid进行过滤
    private void gridFilter(BaseGrid grid, List<GridFilterVO> gridFilterVOS) {
        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();//元数据
        if (null == grid.getResult() || grid.getResult().size() <= 0) {
            return;
        } else
            datas = grid.getResult();

        Object _data = null;
        boolean flag = false;
        int flag1 = 100;
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();//比较的结果

        for (Map<String, Object> data : datas) {
            flag1 = 100;
            for (GridFilterVO vo : gridFilterVOS) {
                _data = doGetObjByLabel(vo.getKey(), data);
                flag = doFilter(data, vo);
                if (!flag)
                    flag1--;
            }
            if (flag1 == 100)
                result.add(data);
        }
        //展示过滤结果
        displayDataWithTextbox(grid, result, grid.getWindow(grid), grid.isSubFlag());
    }

    private boolean doFilter(Map<String, Object> data, GridFilterVO vo) {
        Object _data = null;
        _data = doGetObjByLabel(vo.getKey(), data);
        if (TextUtils.isEmpty(_data))
            _data = "";
        switch (Integer.parseInt(vo.getRel())) {
            case 0://=
                return _data.toString().equalsIgnoreCase(vo.getVal());
            case 1://>=
                try {
                    double _d = Double.parseDouble(_data.toString());
                    if (TextUtils.isEmpty(vo.getVal()))
                        return true;
                    else
                        return _d >= Double.parseDouble(vo.getVal());
                } catch (Exception e) {
                    return _data.toString().compareTo(vo.getVal()) >= 0;
                }
            case 2:
                try {
                    double _d = Double.parseDouble(_data.toString());
                    if (TextUtils.isEmpty(vo.getVal()))
                        return true;
                    else
                        return _d > Double.parseDouble(vo.getVal());
                } catch (Exception e) {
                    return _data.toString().compareTo(vo.getVal()) > 0;
                }
            case 3:
                try {
                    double _d = Double.parseDouble(_data.toString());
                    if (TextUtils.isEmpty(vo.getVal()))
                        return true;
                    else
                        return _d <= Double.parseDouble(vo.getVal());
                } catch (Exception e) {
                    return _data.toString().compareTo(vo.getVal()) <= 0;
                }
            case 4:
                try {
                    double _d = Double.parseDouble(_data.toString());
                    if (TextUtils.isEmpty(vo.getVal()))
                        return true;
                    else
                        return _d < Double.parseDouble(vo.getVal());
                } catch (Exception e) {
                    return _data.toString().compareTo(vo.getVal()) < 0;
                }
            case 5://like
                if (TextUtils.isEmpty(vo.getVal()))
                    return true;
                else
                    return _data.toString().contains(vo.getVal());

        }
        return false;
    }

    //处理checkbox全选或全取消事件
    private void doCheckBoxClick(Event event, BaseGrid grid) {
        BaseCheckbox ck = (BaseCheckbox) event.getTarget();
        if (null == grid)
            return;
        Rows rows = grid.getRows();
        if (null == rows)
            return;
        List<Row> _row = rows.getChildren();
        if (null != _row && _row.size() > 0) {
            for (Row row : _row) {
                BaseCheckbox ck1 = null;
                if (row.getChildren().get(0) instanceof BaseCheckbox)
                    ck1 = (BaseCheckbox) row.getChildren().get(0);
                if (null == ck1)
                    if (row.getChildren().get(1) instanceof BaseCheckbox)
                        ck1 = (BaseCheckbox) row.getChildren().get(1);
                if (ck.isChecked())
                    ck1.setChecked(true);
                else
                    ck1.setChecked(false);
            }
        }
    }

    //选中第一列的checkbox或者radiobox
    private void doClickRow(Row row, BaseGrid grid) {
        //unselOtherRow(row);
        Component comp = row.getChildren().get(0);
        if (comp instanceof BaseCheckbox) {
            BaseCheckbox ck = (BaseCheckbox) comp;
            if (ck.isChecked())
                ck.setChecked(false);
            else {
                ck.setChecked(true);
                grid.setDbRow(row);
                if (null != row.getValue())
                    grid.setDbData((Map<String, Object>) row.getValue());
            }
        } else if (comp instanceof BaseRadio) {
            BaseRadio ck = (BaseRadio) comp;
            if (ck.isChecked())
                ck.setChecked(false);
            else {
                ck.setChecked(true);
                grid.setDbRow(row);
                if (null != row.getValue())
                    grid.setDbData((Map<String, Object>) row.getValue());
            }
        } else {
            comp = row.getChildren().get(1);
            if (comp instanceof BaseCheckbox) {
                BaseCheckbox ck = (BaseCheckbox) comp;
                if (ck.isChecked())
                    ck.setChecked(false);
                else {
                    ck.setChecked(true);
                    grid.setDbRow(row);
                    if (null != row.getValue())
                        grid.setDbData((Map<String, Object>) row.getValue());
                }
            } else if (comp instanceof BaseRadio) {
                BaseRadio ck = (BaseRadio) comp;
                if (ck.isChecked())
                    ck.setChecked(false);
                else {
                    ck.setChecked(true);
                    grid.setDbRow(row);
                    if (null != row.getValue())
                        grid.setDbData((Map<String, Object>) row.getValue());
                }
            }
        }
    }

    //取消其他的选择
    private void unselOtherRow(Row row) {
        Rows rows = (Rows) row.getParent();
        List<Row> allrow = rows.getChildren();
        for (Row row1 : allrow) {
            Component comp = row1.getChildren().get(0);
            if (comp instanceof BaseCheckbox) {
                BaseCheckbox ck = (BaseCheckbox) comp;
                ck.setChecked(false);
            } else if (comp instanceof BaseRadio) {
                BaseRadio ck = (BaseRadio) comp;
                ck.setChecked(false);
            } else {
                comp = row.getChildren().get(1);
                if (comp instanceof BaseCheckbox) {
                    BaseCheckbox ck = (BaseCheckbox) comp;
                    ck.setChecked(false);
                } else if (comp instanceof BaseRadio) {
                    BaseRadio ck = (BaseRadio) comp;
                    ck.setChecked(false);
                }
            }
        }
    }

    //点击表格中的具体项目，进入修改状态
    private void inlineEditFormComponent(Component component, int index) {
        //
        BaseGrid grid = getBaseGridFromComponent(component);
        Row row = getCloseRowFromComponent(component);
        BaseWindow win = getWindowFromGrid(grid);
        doClickRow(row, grid);
        //如果不是修改状态，表示不能修改
        if (win.getWindowType() != 3 && win.getWindowType() != 1)
            return;
        if (TextUtils.isEmpty(grid.getLayoutVO().getControlDbclickEvent())) {
            //进入内部编辑
            doInlineEditRow(row, grid, grid.getWindow(grid));
        }
        //focus
        if (row.getChildren().size() > (index + 1)) {
            Component _comp = row.getChildren().get(index + 1);
            if (_comp instanceof ComponentSer) {
                ComponentSer componentSer = (ComponentSer) _comp;
                componentSer.focus();
            }
        }
    }

    //row的双击事件
    private void doDbClickRow(Row row, BaseGrid grid) {
        Component comp = row.getChildren().get(0);
        grid.setDbRow(row);
        if (null != row.getValue())
            grid.setDbData((Map<String, Object>) row.getValue());
        String url = "";//双击路径
        String dburl = "";

        //没有双击事件的话，就进入当行修改。
        if (TextUtils.isEmpty(grid.getLayoutVO().getControlDbclickEvent())) {
            //进入内部编辑
            doInlineEditRow(row, grid, grid.getWindow(grid));
        } else {
            try {
                String clzName = grid.getLayoutVO().getControlDbclickEvent();
                Object _obj = Class.forName(clzName).newInstance();
                GridRowDbclickListinerSer compAction = (GridRowDbclickListinerSer) _obj;
                compAction.doAction(ComponentUtils.getWindow(grid), grid, row);
            } catch (Exception e) {
                AlterDialog.alert("grid的双击事件配置错误");
                return;
            }
        }
    }

    /*内部编辑的时候，双击row进入编辑状态*/
    private void doInlineEditRow(Row row, BaseGrid grid, BaseWindow window) {
        //如果不进入修改页面，则无法进入编辑试图。
        if (window.getWindowType() == 2)
            return;
        Map<String, Object> dataMap = (Map<String, Object>) row.getValue();
        if (null != row.getAttribute("rowEdit") && row.getAttribute("rowEdit").toString().equalsIgnoreCase("1")) {
            return;
        }
        //如果这一行中有readOnly的标识，不可更改
        if(!TextUtils.isEmpty(dataMap.get("readOnly"))&&dataMap.get("readOnly").toString().equalsIgnoreCase("true"))
            return;
        dataMap.put(SUBEDIT, 1);//把标识更改为需要进行编辑的。
        row.setValue(dataMap);
        row.setAttribute("rowEdit", 1);
        row.getChildren().clear();//清空
        //变更颜色
        row.setSclass(ZKConstants.GRID_INLINE_EDITING_CSS);
        doInlineEditRow(row, grid, window, dataMap);
    }

    private void doInlineEditRow(Row row, BaseGrid grid, BaseWindow window, Map<String, Object> dataMap) {
        int _index = 0;
        Object _obj = null;
        if (null != grid.getLayoutVO() && null != grid.getLayoutVO().getControlCheck()) {
            if (1 == grid.getLayoutVO().getControlCheck()) {
                BaseCheckbox checkbox = PageUtils.createCheckBox(row, "width:30px");
                checkbox.setClass("checkbox");
                _index = 1;
            }
            if (2 == grid.getLayoutVO().getControlCheck()) {
                BaseRadio radio = PageUtils.createRadio(row, "width:30px");
                radio.setClass("checkbox");
                _index = 1;
            }
        }
//        row.addEventListener(Events.ON_CLICK, new OnCheckEvent(grid));
        List<Map<String, Object>> khLists = new ArrayList<Map<String, Object>>();//控件和属性值列表
        List<Column> cls = grid.getColumns().getChildren();
        for (; _index < cls.size(); _index++) {
            Column cl = cls.get(_index);
            //创建单个控件
            if (null == cl.getAttribute("comp"))
                continue;
            PageControlVO subComp = (PageControlVO) cl.getAttribute("comp");
            Component kjComp = window.getTemplateUtils().doInlineEditComponent(subComp, "", window);
            kjComp.setAttribute("property", subComp.getKjAttribute());
            kjComp.setParent(row);
            //判断是否只读
            if (null != subComp.getLayoutComponent().getCompEditable() && subComp.getLayoutComponent().getCompEditable() == 1)
                PageUtils.setInplace(kjComp, true, true);
            else
                PageUtils.setInplace(kjComp, true, false);
            if (!TextUtils.isEmpty(subComp.getKjAttributeDisplay()))
                _obj = doGetObjByLabel(subComp.getKjAttributeDisplay(), dataMap);
            else
                _obj = doGetObjByLabel(subComp.getKjAttribute(), dataMap);
            Map<String, Object> valMap = new HashMap<String, Object>(2);
            valMap.put("comp", kjComp);
            valMap.put("val", _obj);
            khLists.add(valMap);

        }
        for (Map<String, Object> cl : khLists) {
            Component comp = (Component) cl.get("comp");
            Object _valObj = cl.get("val");
            if (comp instanceof BaseBandbox) {
                _valObj = dataMap.get(comp.getAttribute("property").toString());
            }
            PageUtils.setDefaultValue(comp, _valObj);
        }

    }

    //从url的？后面的参数获取
    public static Map<String, Object> getParamsFromUrl(String url) {
        if (url.indexOf("?") <= 0)
            return null;
        Map<String, Object> map = new HashMap<String, Object>();
        String param = url.split("\\?")[1];
        String[] _params = param.split("\\&");
        for (String s : _params) {
            map.put(s.split("\\=")[0], s.split("\\=")[1]);
        }
        return map;
    }

    private String getUrlMoveParam(String url) {
        if (url.indexOf("?") <= 0)
            return url;
        return url.split("\\?")[0];

    }

    /*用递归方法处理数据绑定*/
    private Object doMuiltProperty(Map<String, Object> dataMap, String attribute, Object obj) {
        if (TextUtils.isEmpty(attribute))
            return dataMap;
        if (attribute.indexOf(".") > 0) {
            String[] _tmps = attribute.split("\\.");
            Map<String, Object> _objMap = new HashMap<String, Object>();
            if (null != dataMap.get(_tmps[0]))
                _objMap = (Map<String, Object>) dataMap.get(_tmps[0]);
            else
                dataMap.put(_tmps[0], _objMap);
            doMuiltProperty(_objMap, attribute.replaceAll(_tmps[0] + ".", ""), obj);
        } else
            dataMap.put(attribute, obj);
        return dataMap;
    }

    //用户点击详情列表
    private void doDetailClick(Event event, BaseGrid grid) {
        String pageCa = grid.getLayoutVO().getControlDetailInt();
        String url = "";
        Detail detail = (Detail) event.getTarget();
        if (!detail.isOpen())
            return;
        Row row = (Row) detail.getParent();
        PageVO vo = PageDataUtil.detailPageByCa(pageCa);
        if (null == vo) {
            AlterDialog.alert("请在模板中定义指定ca的页面");
            return;
        }
        if (vo.getPageCustomer() == 0)
            url = vo.getPageTemplatePath();
        else
            url = vo.getPageCa();
        Map<String, Object> params = new HashMap<String, Object>();
        if (null != row.getValue()) {
            params.put("ppt", (Map<String, Object>) row.getValue());
        }
        params.put("pageType", 2);
        params.put("pageCa", pageCa);
        Component winEdit = Executions.createComponents(url, null, params);
        if (winEdit instanceof BaseWindow) {
            BaseWindow _win = (BaseWindow) winEdit;
            detail.getChildren().clear();// qingchu
            Div _div = new Div();
            _div.setParent(detail);
            _div.appendChild(_win);
        }

    }

    public class OnCheckEvent implements EventListener {
        private BaseGrid grid;
        private Component component;
        private int index;

        public OnCheckEvent(BaseGrid grid) {
            this.grid = grid;
        }

        public OnCheckEvent(Component component, int index) {
            this.component = component;
            this.index = index;
        }

        @Override
        public void onEvent(Event event) throws Exception {
            if (event.getTarget() instanceof BaseTextbox || event.getTarget() instanceof BaseCheckbox) {
                //如果是点击单个项目，则进行判断，是否可以编辑
                inlineEditFormComponent(this.component, this.index);
            }
            if (event.getTarget() instanceof BaseCheckbox) {
                doCheckBoxClick(event, grid);
            }
            //如果是单行的打开详情页面
            if (event.getTarget() instanceof Detail) {
                doDetailClick(event, grid);
            }
            if (event.getTarget() instanceof Row) {
                Row row = (Row) event.getTarget();
                if (Events.ON_CLICK.equals(event.getName())) {
                    doClickRow(row, grid);
//                    doDbClickRow(row, grid);

                }
                if (event.getName().equals(Events.ON_DOUBLE_CLICK)) {
                    //双击事件
                    doDbClickRow(row, grid);
                }
            }
            if (event.getTarget() instanceof BasePaging) {
//                AlterDialog.alert("========");
                BasePaging bp = (BasePaging) event.getTarget();
                int indexNo = bp.getActivePage();
                indexNo = indexNo + 1;
                grid.onPaging(indexNo);
            }
        }
    }

    //实例化当前类
    public static GridUtils instance() {
        GridUtils gridUtils = new GridUtils();
        return gridUtils;
    }

    public Column createColumn(Columns cls, String align, String width, String context) {
        Column col = new Column();
        col.setAlign(align);
        col.setParent(cls);
        col.setWidth(width);
        col.setContext(context);
        return col;
    }

    public void addPagingEvent(BaseGrid grid, BasePaging paging) {
        paging.addEventListener("onPaging", new OnCheckEvent(grid));
    }


    //显示大图
    class ImageEvent implements EventListener {
        private String imagePath;

        public ImageEvent(String imagePath) {
            this.imagePath = imagePath;
        }

        @Override
        public void onEvent(Event event) throws Exception {
            try {
                Map<String, Object> params = new HashMap<String, Object>(1);
                params.put("imagePath", imagePath);
                ImageDisplayAction winEdit = (ImageDisplayAction) Executions.createComponents("/template/bigImage.do", null, params);
                winEdit.setStyle("width:400px;height:400px;");
                winEdit.doHighlighted();
                winEdit.setClosable(true);
            } catch (Exception e) {

            }
        }
    }

    class ItemClickEvent implements EventListener {
        private BaseGrid grid;

        public ItemClickEvent(BaseGrid grid) {
            this.grid = grid;
        }

        @Override
        public void onEvent(Event event) throws Exception {
            //表格中的菜单处理
            if (event.getTarget() instanceof Menuitem) {
                Menuitem item = (Menuitem) event.getTarget();
                if ((grid.getId() + "_group").equals(item.getId())) {

                } else if ((grid.getId() + "_ascending").equals(item.getId())) {

                } else if ((grid.getId() + "_descending").equals(item.getId())) {

                } else if ((grid.getId() + "_selectAll").equals(item.getId())) {
                    Rows rows = grid.getRows();
                    if (null == rows)
                        return;
                    List<Row> _row = rows.getChildren();
                    if (null != _row && _row.size() > 0) {
                        for (Row row : _row) {
                            BaseCheckbox ck1 = null;
                            if (row.getChildren().get(0) instanceof BaseCheckbox)
                                ck1 = (BaseCheckbox) row.getChildren().get(0);
                            if (null == ck1)
                                if (row.getChildren().get(1) instanceof BaseCheckbox)
                                    ck1 = (BaseCheckbox) row.getChildren().get(1);
                            ck1.setChecked(true);
                        }
                    }
                } else if ((grid.getId() + "_noselectAll").equals(item.getId())) {
                    Rows rows = grid.getRows();
                    if (null == rows)
                        return;
                    List<Row> _row = rows.getChildren();
                    if (null != _row && _row.size() > 0) {
                        for (Row row : _row) {
                            BaseCheckbox ck1 = null;
                            if (row.getChildren().get(0) instanceof BaseCheckbox)
                                ck1 = (BaseCheckbox) row.getChildren().get(0);
                            if (null == ck1)
                                if (row.getChildren().get(1) instanceof BaseCheckbox)
                                    ck1 = (BaseCheckbox) row.getChildren().get(1);
                            ck1.setChecked(false);
                        }
                    }
                } else {
                    if (item.isCheckmark()) {
                        item.setCheckmark(false);
                        //不显示列
                        grid.getFellowIfAny(grid.getId() + "_" + item.getAttribute("colProperty") + "_column").setVisible(false);

                    } else {
                        item.setCheckmark(true);
                        grid.getFellowIfAny(grid.getId() + "_" + item.getAttribute("colProperty") + "_column").setVisible(true);
                    }
                }
            }
            if (event instanceof MouseEvent && event.getTarget() instanceof Columns) {
                doFilterWindow(grid);
            }
            if (event.getName().equals(Events.ON_DROP) && event.getTarget() instanceof Row) {
                //控件移动处理
                Row baseButton = (Row) event.getTarget();
                doMoveRow(baseButton, event);
            }

        }
    }

    //获取当前控件的最新window
    public BaseWindow getWindowFromGrid(Component grid) {
        if (grid.getParent() instanceof BaseWindow)
            return (BaseWindow) grid.getParent();
        else
            return getWindowFromGrid(grid.getParent());
    }

    public BaseGrid getBaseGridFromComponent(Component component) {
        if (component.getParent() instanceof BaseGrid)
            return (BaseGrid) component.getParent();
        else
            return getBaseGridFromComponent(component.getParent());
    }

    public Row getCloseRowFromComponent(Component component) {
        if (component.getParent() instanceof Row)
            return (Row) component.getParent();
        else
            return getCloseRowFromComponent(component.getParent());
    }

    public static void main(String[] args) {

        ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
        String strs = "'004'=='004'";
        try {
            System.out.println(jse.eval(strs));
        } catch (Exception t) {
            t.printStackTrace();
        }
    }
}
