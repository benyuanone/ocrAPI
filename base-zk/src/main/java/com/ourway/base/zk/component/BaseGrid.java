package com.ourway.base.zk.component;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.models.*;
import com.ourway.base.zk.service.ComponentSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.GridUtils;
import com.ourway.base.zk.utils.TemplateUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Column;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/18.
 */
public class BaseGrid extends Grid implements ComponentSer<String> {
    private String property;
    private PageLayoutVO layoutVO;//当前grid的配置
    private BasePaging basePaging;//当前grid的分页组件
    private List<FilterModel> queryModels;
    private Integer pageNo;//当前的页面数
    private Integer maxIndex;//当前页面最大序号
    /* 0 表示展示所有的数据 >0 表示展示指定的大小行数*/
    private Integer pageSize = 0;//当前的pageSize
    PageInfoVO pageInfoVO;//当前grid的数据对象
    private boolean isIndexCol;//列表中是否有序号
    private Row dbRow;//双击或者单击所选中的行
    private Map<String, Object> dbData;//双击或者单击所选中行的数据
    private String filterUrl = "";//查询用的链接
    private boolean subFlag;//grid的属性，表示列表还是单行双击修改

    public boolean isSubFlag() {
        return subFlag;
    }

    public void setSubFlag(boolean subFlag) {
        this.subFlag = subFlag;
    }

    public Integer getMaxIndex() {
        return maxIndex;
    }

    public void setMaxIndex(Integer maxIndex) {
        this.maxIndex = maxIndex;
    }

    public Row getDbRow() {
        return dbRow;
    }

    public void setDbRow(Row dbRow) {
        this.dbRow = dbRow;
    }

    public Map<String, Object> getDbData() {
        return dbData;
    }

    public void setDbData(Map<String, Object> dbData) {
        this.dbData = dbData;
    }

    public PageLayoutVO getLayoutVO() {
        return layoutVO;
    }

    public boolean isIndexCol() {
        return isIndexCol;
    }

    public void setIndexCol(boolean indexCol) {
        isIndexCol = indexCol;
    }

    public void setPageInfoVO(PageInfoVO pageInfoVO) {
        this.pageInfoVO = pageInfoVO;
    }

    public void setLayoutVO(PageLayoutVO layoutVO) {
        this.layoutVO = layoutVO;
        //
        if (null != layoutVO && null != layoutVO.getControlSplitpage() && layoutVO.getControlSplitpage() == 1) {
            //需要分頁
            pageSize = layoutVO.getControlPagesize();
            pageNo = 1;
            initPaging();
        } else {
            //不需要分頁
            pageSize = 0;
            pageNo = 1;
        }
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    private List<PageControlVO> controlVOS;//表示内容的其它扩展属性

    public List<PageControlVO> getControlVOS() {
        return controlVOS;
    }

    public void setControlVOS(List<PageControlVO> controlVOS) {
        this.controlVOS = controlVOS;
    }


    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }


    @Override
    public void init(Map<String, Object> pageObj, String property) {

    }

    @Override
    public void init(PageControlVO pageControlVO, BaseWindow win) {

    }

    @Override
    public String getPageValue() {
        return "";
    }


    @Override
    public void addEventListiner(PageControlVO pageControlVO, BaseWindow win) {

    }

    @Override
    public void doEvent(Object objectMap, BaseWindow win, Event event) {

    }

    /**
     * <p>方法:doGridOnPage grid的分页执行函数 </p>
     * <ul>
     * <li> @param params 参数</li>
     * <li> @param jsonMethod TODO</li>
     * <li> @param pageNo TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/16 23:25  </li>
     * </ul>
     */
    public void onPaging(int pageIndex) {
        clearRows();
        ResponseMessage mess = JsonPostUtils.executeAPI(queryModels, filterUrl, pageIndex, pageSize);
        if (null != mess && mess.getBackCode() == 0) {
            pageInfoVO = BeanUtil.map2Obj((HashMap) mess.getBean(), PageInfoVO.class);
            //更新页面参数和显示数据
            updatePaging();
            display();
        }
    }

    /**
     * <p>方法:doGridFilter 查询按钮执行的事件 </p>
     * <ul>
     * <li> @param params TODO</li>
     * <li> @param jsonMethod TODO</li>
     * <li> @param pageSize TODO</li>
     * <li>@return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/16 23:27  </li>
     * </ul>
     */
    public void filter(List<FilterModel> params) {
        pageNo = 1;
        queryModels = params;
        clearRows();
        filterUrl = layoutVO.getControlInt();
        filterData(params);
    }

    public void filter(List<FilterModel> params, String url) {
        pageNo = 1;
        queryModels = params;
        clearRows();
        filterUrl = url;
        filterData(params);
    }

    public List<Map<String, Object>> exportFilter() {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        ResponseMessage mess = JsonPostUtils.executeAPI(queryModels, filterUrl, pageNo, 0);
        PageInfoVO _pageinfovo = null;
        if (null != mess && mess.getBackCode() == 0) {
            if (mess.getBean() instanceof Map)
                _pageinfovo = BeanUtil.map2Obj((HashMap) mess.getBean(), PageInfoVO.class);
            if (mess.getBean() instanceof ArrayList) {
                _pageinfovo = new PageInfoVO();
                _pageinfovo.setRecords((List<Map<String, Object>>) mess.getBean());
            }
        }
        if (null != _pageinfovo && _pageinfovo.getRecords().size() > 0)
            result = _pageinfovo.getRecords();

        return result;
    }

    private void filterData(List<FilterModel> params) {
        ResponseMessage mess = JsonPostUtils.executeAPI(params, filterUrl, pageNo, pageSize);
        if (null != mess && mess.getBackCode() == 0) {
            if (mess.getBean() instanceof Map)
                pageInfoVO = BeanUtil.map2Obj((HashMap) mess.getBean(), PageInfoVO.class);
            if (mess.getBean() instanceof ArrayList) {
                pageInfoVO = new PageInfoVO();
                pageInfoVO.setRecords((List<Map<String, Object>>) mess.getBean());
                pageInfoVO.setCurrentPage(1);
                pageInfoVO.setCurrentIndex(1);
                pageInfoVO.setTotalCount(pageInfoVO.getRecords().size());
                pageInfoVO.setTotalPage(1);
            }
            updatePaging();
        }
    }


    /**
     * <p>方法:display 展现数据到grid中 </p>
     * <ul>
     * <li> @param datas 数据</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/16 23:38  </li>
     * </ul>
     */
    public void display() {
        clearRows();
        if (null != pageInfoVO && null != pageInfoVO.getRecords())
            getWindow(this).getGridUtils().displayDataWithTextbox(this, pageInfoVO.getRecords(), getWindow(this), false);
    }

    public List<Map<String, Object>> getResult() {
        if (null != pageInfoVO && !TextUtils.isEmpty(pageInfoVO.getRecords()) && pageInfoVO.getRecords().size() > 0)
            return pageInfoVO.getRecords();
        else
            return null;
    }

    public void display(List<Map<String, Object>> datas) {
        clearRows();
//        getWindow(this).getGridUtils().displayData(this, datas, getWindow(this), false);
    }

    //    显示子表数据
    public void displaySubDatas(List<Map<String, Object>> datas) {
        clearRows();
        pageInfoVO = new PageInfoVO();
        pageInfoVO.setCurrentPage(1);
        pageInfoVO.setCurrentIndex(1);
        pageInfoVO.setTotalCount(datas.size());
        pageInfoVO.setTotalPage(1);
        pageInfoVO.setRecords(datas);
        getWindow(this).getGridUtils().displayDataWithTextbox(this, datas, getWindow(this), true);
    }


    //在表格的添加新的结果行
    public void addNewRow(Map<String, Object> data, int checkBox, boolean dbClick) {
        Rows rows = getHeaderRows();
        Row row = new Row();
//        if (!TextUtils.isEmpty(ZKConstants.GRID_NEWROW))
//            row.setStyle(ZKConstants.GRID_NEWROW);
        row.setValue(data);
        if (checkBox == 1) {
            BaseCheckbox checkbox = new BaseCheckbox();
            checkbox.setStyle("width:30px");
            checkbox.setClass("checkbox");
            checkbox.setParent(row);

        }
        if (checkBox == 2) {
            BaseRadio checkbox = new BaseRadio();
            checkbox.setStyle("width:30px");
            checkbox.setClass("checkbox");
            checkbox.setRadiogroup(rows.getParent().getId() + "_rdGroup");
            checkbox.setParent(row);
        }
        List<Column> cls = getColumns().getChildren();
        int _index = 0;
        if (checkBox != 0)
            _index = 1;
        //是否有序号，新增的时候放入本页最大的条数
        if (isIndexCol()) {
            BaseTextbox label = TemplateUtils.doCreateInGridTextbox();
            setMaxIndex(getMaxIndex() + 1);
            label.setValue(getMaxIndex() + "");
            label.setParent(row);
            _index = 2;
        }
        for (; _index < cls.size(); _index++) {
            Column cl = cls.get(_index);
            BaseTextbox label = TemplateUtils.doCreateInGridTextbox();
            label.setParent(row);
            if (!TextUtils.isEmpty(data.get(cl.getAttribute("key"))))
                label.setValue(data.get(cl.getAttribute("key").toString()).toString());
            else
                label.setValue("");
        }
        if (null != rows.getFirstChild())
            rows.insertBefore(row, rows.getFirstChild());
        else
            row.setParent(rows);
        //是否有双击
        if (dbClick)
            row.addEventListener(Events.ON_DOUBLE_CLICK, getWindow(this));

    }

    //在表格的添加新的结果行
    public void addNewRow(Map<String, Object> data) {
        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>(1);
        datas.add(data);
        getWindow(this).getGridUtils().displayAppendDataWithTextbox(this, datas, getWindow(this), true);
    }

    //添加多行到内部表格中
    public void addNewRows(List<Map<String, Object>> datas) {
        for (Map<String, Object> data : datas) {
            addNewRow(data);
        }
    }

    /**
     * <p>方法:refreshRow 刷新指定的Row </p>
     * <ul>
     * <li> @param data row的数据</li>
     * <li> @param row row</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/20 15:13  </li>
     * </ul>
     */
    public void refreshRow(Map<String, Object> data, Row row) {
        List<Column> cls = getColumns().getChildren();
        BaseTextbox label = null;
        int index = 0;
        row.setValue(data);

        for (Column cl : cls) {
            if (!TextUtils.isEmpty(cl.getAttribute("key"))) {
                if (null != row.getChildren().get(index) && row.getChildren().get(index) instanceof BaseTextbox) {
                    label = (BaseTextbox) row.getChildren().get(index);
                } else {
                    label = TemplateUtils.doCreateInGridTextbox();
                    label.setParent(row);
                }
                if (null != data.get(cl.getAttribute("key").toString()))
                    label.setValue(data.get(cl.getAttribute("key").toString()).toString());
                else
                    label.setValue("-");
            }
            index++;
        }
    }

    /*若内部编辑保存成功后执行*/
    public void refreshInlineEditRow() {
        Rows rows = getHeaderRows();
        List<Column> cls = getColumns().getChildren();
        List<Row> dataRows = rows.getChildren();
        int _index = 0;
        Object _obj = null;
        if (null != getLayoutVO() && null != getLayoutVO().getControlCheck()) {
            if (1 == getLayoutVO().getControlCheck()) {
                _index = 1;
            }
            if (2 == getLayoutVO().getControlCheck()) {
                _index = 1;
            }
        }
        int index = _index;
        for (Row dataRow : dataRows) {
            Map<String, Object> _dataMap = (Map<String, Object>) dataRow.getValue();
            if (null == _dataMap.get(GridUtils.SUBEDIT))
                continue;
            switch (Integer.parseInt(_dataMap.get(GridUtils.SUBEDIT).toString())) {
                case 0:
                    break;
                case 1://表示新增修改
                    _dataMap.put(GridUtils.SUBEDIT, 0);
                    dataRow.setValue(_dataMap);
                    dataRow.getChildren().clear();
                    dataRow.setClass(ZKConstants.GRID_INLINE_EDITING_SUCESS_CSS);
                    if (1 == getLayoutVO().getControlCheck()) {
                        BaseCheckbox ck = new BaseCheckbox();
                        ck.setClass("checkbox");
                        ck.setParent(dataRow);
                    }
                    if (2 == getLayoutVO().getControlCheck()) {
                        BaseRadio rd = new BaseRadio();
                        rd.setClass("checkbox");
                        rd.setRadiogroup(rows.getParent() + "_rdGroup");
                        rd.setParent(dataRow);
                    }
                    for (; index < cls.size(); index++) {
                        Column cl = cls.get(index);
                        PageControlVO subComp = (PageControlVO) cl.getAttribute("comp");
                        _obj = getWindow(this).getGridUtils().doHandleMultipleProperty(_dataMap, subComp);
                        if (_obj instanceof Map)
                            _obj = getWindow(this).getGridUtils().doHandleMultipleProperty((Map<String, Object>) _obj, subComp);
                        getWindow(this).getGridUtils().displayCell(_dataMap, dataRow, subComp, _obj, index);
                    }
                    index = _index;
                    break;
                case 2://表示删除
                    _dataMap.put(GridUtils.SUBEDIT, 0);
                    dataRow.setValue(_dataMap);
                    break;
            }
        }
    }


    public void removeRow(Row row) {
        getHeaderRows().removeChild(row);
    }


    public Rows getHeaderRows() {
        Rows rows = null;
        if (null != this.getRows())
            rows = this.getRows();
        else {
            rows = new Rows();
            rows.setParent(this);
        }
        return rows;
    }

    public void clearRows() {
        if (null != this.getRows())
            this.getRows().getChildren().clear();
    }

    public List<Map<String, Object>> getSelectRowsData() {
        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
        Rows rows = getHeaderRows();
        List<Row> rows1 = rows.getChildren();
        for (Row row : rows1) {
            if (row.getFirstChild() instanceof BaseCheckbox) {
                BaseCheckbox ck = (BaseCheckbox) row.getFirstChild();
                if (ck.isChecked()) {
                    Map<String,Object> data = (Map<String, Object>) row.getValue();
                    data.put("rowId",row.getId());
                    datas.add(data);
                }
            }
            if (row.getFirstChild() instanceof BaseRadio) {
                BaseRadio ck = (BaseRadio) row.getFirstChild();
                if (ck.isChecked()) {
                    Map<String,Object> data = (Map<String, Object>) row.getValue();
                    data.put("rowId",row.getId());
                    datas.add(data);
                }
            }
        }
        return datas;
    }

    public List<Row> getSelectRows() {
        List<Row> datas = new ArrayList<Row>();
        Rows rows = getHeaderRows();
        List<Row> rows1 = rows.getChildren();
        for (Row row : rows1) {
            if (row.getFirstChild() instanceof BaseCheckbox) {
                BaseCheckbox ck = (BaseCheckbox) row.getFirstChild();
                if (ck.isChecked())
                    datas.add(row);
            }
            if (row.getFirstChild() instanceof BaseRadio) {
                BaseRadio ck = (BaseRadio) row.getFirstChild();
                if (ck.isChecked())
                    datas.add(row);
            }
        }
        return datas;
    }

    public void removeItems(String api) {
        List<Map<String, Object>> objs = getSelectRowsData();
        ResponseMessage message = JsonPostUtils.executeObjAPI(objs, api);
        if (null != message && message.getBackCode() != 0) {
            AlterDialog.alert(message.getErrorMess());
            return;
        }
        if (null != message && !TextUtils.isEmpty(message.getBean())) {
            //处理那些row可以删除
            List<Row> selectRows = getSelectRows();
            List<Map<String, Object>> datas = (List<Map<String, Object>>) message.getBean();
            for (Map<String, Object> data : datas) {
                for (Row row : selectRows) {
                    try {
                        if (((Map<String, Object>) row.getValue()).get("owid").toString().equals(data.get("owid").toString())) {
                            getRows().removeChild(row);
                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //获取当前控件的window
    public BaseWindow getWindow(Component component) {
        if (component instanceof BaseWindow)
            return (BaseWindow) component;
        else
            return getWindow(component.getParent());
    }

    @Override
    public void reset() {

    }

    @Override
    public PageControlVO getPgvo() {
        return null;
    }

    /*判断是否进行分页*/
    public void initPaging() {
        if (null == layoutVO || null == layoutVO.getControlSplitpage() || layoutVO.getControlSplitpage() != 1)
            return;
        basePaging = new BasePaging();
        basePaging.setPageSize(layoutVO.getControlPagesize());
        basePaging.setMold("bs");
        basePaging.setDetailed(true);
        basePaging.setClass("pagination-sm");
        this.getParent().appendChild(basePaging);
        GridUtils.instance().addPagingEvent(this, basePaging);
    }

    public void updatePaging() {
        if (null == basePaging || null == pageInfoVO)
            return;
        basePaging.setTotalSize(pageInfoVO.getTotalCount());
        basePaging.setActivePage(pageInfoVO.getCurrentIndex() - 1);
        pageNo = pageInfoVO.getCurrentIndex();
    }

    @Override
    public void setComponentDisable(boolean flag) {

    }

    @Override
    public void setPageValue(Object obj) {

    }
}
