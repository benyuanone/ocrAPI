package com.ourway.erpbasedata.erpgoods;

import com.ourway.base.zk.component.BaseBandbox;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseListbox;
import com.ourway.base.zk.component.BaseTextbox;
import com.ourway.base.zk.utils.ComponentUtils;
import com.ourway.base.zk.utils.data.DicUtil;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.erpsystem.ERPTipConstants;
import com.ourway.syszk.utils.TextUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.OpenEvent;
import org.zkoss.zul.*;

import java.util.*;


public class ErpGoodsPropertyBandbox extends BaseBandbox {
    private static final long serialVersionUID = -7917484960028594428L;
    private BaseListbox lb = new BaseListbox();// 列表
    private Bandpopup pup = new Bandpopup();
    private String selLabel = "";
    //用于窗体打开时默认值情况下的勾选列表判断使用
    private List<String> defalultSels =  new ArrayList<String>();;
    private List<Map<String, Object>> datas;

    private int dicCode=1043;

    /**
    *<p>方法:创建事件 TODO </p>
    *<ul>
     *<li> @param  TODO</li>
    *<li>@return void  </li>
    *<li>@author CuiLiang </li>
    *<li>@date 2017-11-14 14:58  </li>
    *</ul>
    */
    @Override
    public void onCreate() {
        //初始化显示
        initBandBox();
        //初始化数据
        if (!TextUtils.isEmpty(this.getValue())) {
            showDefault(this.getValue());
        } else
            this.setText(I18nUtil.getLabelContent(ERPTipConstants.PLEASE_SEL));
    }

    /**
     *<p>方法:初始化bandbox显示控制 TODO </p>
     *<ul>
     *<li> @param  TODO</li>
     *<li>@return void  </li>
     *<li>@author CuiLiang </li>
     *<li>@date 2017-11-14 15:01  </li>
     *</ul>
     */
    public void initBandBox() {
        initPup();
        initList();

        //设置本输入框只读
        setReadonly(true);
        //绑定事件
        this.addEventListener(Events.ON_OPEN, new OpenCusEvent());
    }
    /**
    *<p>方法:窗体打开时设置初始化值 TODO </p>
    *<ul>
     *<li> @param obj TODO</li>
    *<li>@return void  </li>
    *<li>@author CuiLiang </li>
    *<li>@date 2017-11-14 14:55  </li>
    *</ul>
    */
    @Override
    public void showDefault(Object obj) {
        if (TextUtils.isEmpty(obj))
            return;
        String vals = obj.toString();
        if (vals.indexOf(",") > 0) {
            if (null == selVals)
                setSelVals(new ArrayList<Map<String,Object>>());
            String[] _vals = vals.split("\\,");
            defalultSels =  java.util.Arrays.asList(_vals);
        }
        initLoadData();
    }

    /**
    *<p>方法:初始化弹出框布局 TODO </p>
    *<ul>
     *<li> @param  TODO</li>
    *<li>@return void  </li>
    *<li>@author CuiLiang </li>
    *<li>@date 2017-11-14 14:56  </li>
    *</ul>
    */
    public void initPup() {
        pup.setParent(this);
        pup.setWidth("200px");
        BaseGrid grid = new BaseGrid();
        grid.setWidth("99%");
        grid.setParent(pup);
        Rows rows = new Rows();
        rows.setParent(grid);
        Row row = new Row();
        row.setParent(rows);
        row.setAlign("left");
    }

    /**
    *<p>方法:初始化下拉框内容 TODO </p>
    *<ul>
     *<li> @param  TODO</li>
    *<li>@return void  </li>
    *<li>@author CuiLiang </li>
    *<li>@date 2017-11-14 15:06  </li>
    *</ul>
    */
    public void initList() {
        lb.setMultiple(true);
        lb.setCheckmark(true);
        lb.getItems().clear();
        lb.setStyle("height:400");
        lb.setMold("paging");
        lb.setPageSize(15);
        lb.setParent(pup);
        lb.setWidth("99%");
        lb.setSizedByContent(false);
    }

    /**
    *<p>方法:加载数据 TODO </p>
    *<ul>
     *<li> @param  TODO</li>
    *<li>@return void  </li>
    *<li>@author CuiLiang </li>
    *<li>@date 2017-11-14 15:13  </li>
    *</ul>
    */
    public void initLoadData() {
        lb.getItems().clear();
        datas = DicUtil.listDic(dicCode, "b.dicVal1");
        initDisplayList(datas);
    }
    /**
     *<p>方法: 初始化时设置bandbox的选中及显示 </p>
     *<ul>
     *<li> @param null TODO</li>
     *<li>@return   </li>
     *<li>@author CuiLiang </li>
     *<li>@date 2017-11-14 14:53  </li>
     *</ul>
     */
    private void initDisplayList(List<Map<String, Object>> datas) {
        if (null == datas || datas.size() <= 0)
            return;
        for (Map<String, Object> cst : datas) {
            Listitem item = new Listitem();
            item.setValue(cst);
            item.setParent(lb);
            item.addEventListener(Events.ON_CLICK, new OpenCusEvent());
            Listcell cell = new Listcell();
            cell.setParent(item);
            cell.setLabel(I18nUtil.getLabelContent(cst.get("dicVal1").toString()));
            //判断是否选中
            if (null != defalultSels && defalultSels.contains(cst.get("owid").toString())) {
                item.setSelected(true);
                selLabel += cell.getLabel()+",";
                if (!TextUtils.isEmpty(cst.get("code")) && cst.get("code").toString().equals("1")) {
                    setComState(false);
                }
            }
        }
        if (!TextUtils.isEmpty(selLabel)){
            selLabel =selLabel.substring(0,selLabel.length()-1);
            setText(selLabel);
        }
    }

    /**
    *<p>方法:setComState 根据值设置界面其他控件状态 </p>
    *<ul>
     *<li> @param disabled TODO</li>
    *<li>@return void  </li>
    *<li>@author CuiLiang </li>
    *<li>@date 2017-11-14 14:53  </li>
    *</ul>
    */
    private void setComState(boolean disabled){
        Component comp = ComponentUtils.getWindow(this).getFellowIfAny("mainTableGrid_dangerLevel");
        if (null != comp && comp instanceof BaseListbox) {
            BaseListbox lb = (BaseListbox) comp;
            if(!disabled)
                lb.setSelectedIndex(0);
            lb.setDisabled(disabled);
        }
        comp = ComponentUtils.getWindow(this).getFellowIfAny("mainTableGrid_unno");
        if (null != comp && comp instanceof BaseTextbox) {
            BaseTextbox lb = (BaseTextbox) comp;
            lb.setDisabled(disabled);
        }
        comp = ComponentUtils.getWindow(this).getFellowIfAny("mainTableGrid_cnno");
        if (null != comp && comp instanceof BaseTextbox) {
            BaseTextbox lb = (BaseTextbox) comp;
            lb.setDisabled(disabled);
        }

    }

    /**
    *<p>方法:打开bandbox搜索框时候触发的事件 </p>
    *<ul>
    *<li>@return   </li>
    *<li>@author CuiLiang </li>
    *<li>@date 2017-11-14 13:58  </li>
    *</ul>
    */
    private class OpenCusEvent implements EventListener {
        @Override
        public void onEvent(Event event) throws Exception {
            if (event instanceof OpenEvent) {
                OpenEvent oe = (OpenEvent) event;
                if (null == lb.getItems() || lb.getItems().size() <= 0)
                    initLoadData();
            }
            if (event.getTarget() instanceof Listitem) {
                checkSelValue();
            }
        }
    }

    /**
    *<p>方法:点击勾选后获取值、设置显示值 TODO </p>
    *<ul>
     *<li> @param  TODO</li>
    *<li>@return void  </li>
    *<li>@author CuiLiang </li>
    *<li>@date 2017-11-14 15:43  </li>
    *</ul>
    */
    void checkSelValue() {
        Set<Listitem> listitems = lb.getSelectedItems();
        boolean flag = false;
        selLabel = "";
        for (Listitem listitem : listitems) {
            if (null != listitem.getValue()) {
                Map<String, Object> data = (Map<String, Object>) listitem.getValue();
                if (!TextUtils.isEmpty(data.get("code")) && data.get("code").toString().equals("1")) {
                    flag = true;
                }
                selLabel = selLabel + data.get("dicVal1").toString() + ",";
            }
        }
        if (selLabel!="")
            selLabel =selLabel.substring(0,selLabel.length()-1);
        setText(selLabel);
        setComState(!flag);
    }
    
    /**
    *<p>方法:保存时获取勾选的值 TODO </p>
    *<ul>
     *<li> @param  TODO</li>
    *<li>@return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>  </li>
    *<li>@author CuiLiang </li>
    *<li>@date 2017-11-14 15:15  </li>
    *</ul>
    */
    @Override
    public List<Map<String,Object>> getSelVals() {
        if (null == selVals)
            selVals = new ArrayList<Map<String,Object>>();
        Set<Listitem> set = lb.getSelectedItems();
        if (null != set && set.size() > 0) {
            selVals.clear();
            for (Listitem listitem : set) {
                Map<String, Object> data = (Map<String, Object>) listitem.getValue();
                    selVals.add(data);
            }
        }
        return selVals;
    }
}
