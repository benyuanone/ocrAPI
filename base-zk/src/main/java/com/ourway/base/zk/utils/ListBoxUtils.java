package com.ourway.base.zk.utils;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.component.*;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;

import java.util.*;

/**
 * <p>方法 PageUtils : <p>
 * <p>说明:页面处理相关方法</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/3 20:20
 * </pre>
 */
public class ListBoxUtils {
    /*当前行的更改标识*/
    public static final String SUBEDIT = "updateFlag";

    public static Listhead doGetListHead(BaseListbox lb) {
        if (null != lb.getListhead())
            return lb.getListhead();
        else {
            Listhead head = new Listhead();
            head.setSizable(true);
            head.setParent(lb);
            return head;
        }
    }

    public static void doCreateListHeader(String label, String property, Listhead listhead, int compType) {
        Listheader listheader = new Listheader();
        listheader.setStyle("text-align:center;");
        listheader.setLabel(I18nUtil.getLabelContent(label));
        listheader.setAttribute("property", property);
        listheader.setAttribute("compType", compType);
        listheader.setParent(listhead);
    }

    public static Listitem doCreateItem(BaseListbox listbox) {
        Listitem item = new Listitem();
        item.setParent(listbox);
        return item;
    }

    public static Listcell doCreateCell(Listitem item, Object obj) {
        Listcell cell = new Listcell();
        if (!TextUtils.isEmpty(obj))
            cell.setLabel(obj.toString());
        cell.setParent(item);
        return cell;
    }

    public static Listcell doCreateCellByTextbox(Listitem item, Object obj,String key) {
        Listcell cell = new Listcell();
        cell.setParent(item);
        cell.setAttribute("edit",1);
        cell.setAttribute("key",key);
        BaseTextbox textbox = new BaseTextbox();
        textbox.setInplace(true);
        textbox.setStyle("min-width:120px;");
        textbox.setParent(cell);
        if (!TextUtils.isEmpty(obj))
            textbox.setText(obj.toString());
        textbox.setClass(ZKConstants.GRID_INLINE_EDITING_CSS);
        return cell;
    }

    public void displayData(List<Map<String, Object>> datas, BaseListbox listbox) {
        Listhead head = listbox.getListhead();
        List<Listheader> headers = head.getChildren();
        for (Map<String, Object> data : datas) {
            Listitem item = doCreateItem(listbox);
            item.addEventListener(Events.ON_DOUBLE_CLICK, new DbclickEdit());
            item.setValue(data);
            for (Listheader listheader : headers) {
                String key = listheader.getAttribute("property").toString();
                String cp = listheader.getAttribute("compType").toString();
//                switch (Integer.parseInt(cp)) {
//                    case 0:
                doCreateCell(item, data.get(key));
//                        break;
//                    case 1:
//                        doCreateCellByTextbox(item, data.get(key));
//                        break;
//                }
            }
        }
    }

    public void editRow(BaseListbox lb, Listitem item) {
        Listhead head = lb.getListhead();
        List<Listheader> headers = head.getChildren();
        item.getChildren().clear();
        Map<String, Object> data = (Map<String, Object>) item.getValue();
        data.put(SUBEDIT, 1);
        item.setValue(data);
        for (Listheader listheader : headers) {
            String key = listheader.getAttribute("property").toString();
            String cp = listheader.getAttribute("compType").toString();
            switch (Integer.parseInt(cp)) {
                case 0:
                    doCreateCell(item, data.get(key));
                    break;
                case 1:
                    doCreateCellByTextbox(item, data.get(key),key);
                    break;
            }
        }
    }

    public static void getEditValue(Map<String,Object> data,Listitem listitem){
        BaseListbox lb = (BaseListbox)listitem.getParent();
        Listhead head = lb.getListhead();
        List<Listheader> headers = head.getChildren();
        List<Listcell> listcells = listitem.getChildren();
        for(Listcell cell :listcells){
           if(null!=cell.getAttribute("edit")){
               BaseTextbox bt = (BaseTextbox)cell.getFirstChild();
               data.put(cell.getAttribute("key").toString(),bt.getText());
           }
        }
    }

    class DbclickEdit implements EventListener {
        @Override
        public void onEvent(Event event) throws Exception {
            if (event.getTarget() instanceof Listitem) {
                Listitem item = (Listitem) event.getTarget();
                BaseListbox baseListbox = (BaseListbox) item.getParent();
                editRow(baseListbox,item);
            }
        }
    }

}
