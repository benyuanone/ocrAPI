package com.ourway.base.zk.sys.page;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.component.BaseBandbox;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zk.ui.ext.Scopes;
import org.zkoss.zul.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ComponentBandBox extends BaseBandbox {
    private static final long serialVersionUID = -7917484960028594428L;
    private Listbox lb = new Listbox();// 列表
    private Bandpopup pup = new Bandpopup();
    private List<Object> lbs = new ArrayList<Object>();

    private EventListener _onCheckBack;// 选择返回事件
    private static ComponentBandBox _instants;

    @Override
    public void onCreate() {
        lb.setMultiple(true);
        this.addEventListener(Events.ON_OPEN, new OpenCusEvent(this));
        _instants = this;
        initFilterCon();
        initList();
        initHead();
        if (!TextUtils.isEmpty(this.getValue())) {
            // 如果有初始值，则显示初始值
            showDefault(this.getValue());
        } else
            this.setText("请选择");
    }

    @Override
    public void showDefault(Object obj) {

    }

    public void setCheckBack(final String evl) {
        if (TextUtils.isEmpty(evl)) {
            this._onCheckBack = null;
            return;
        }
        this._onCheckBack = new EventListener() {
            @Override
            public void onEvent(Event event) {
                _instants.close();
                getPage().interpret("java", evl, Scopes.getCurrent(getPage())); //
                // 调用java方法
            }
        };
    }

    /**
     * <p>
     * 初始化列表
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: CustomerBandBox.java,v 0.1 2012-3-12 上午08:53:13 Jack Exp $
     */
    public void initList() {
    }

    // 显示初始数据
    public void showDefault(String id) {
//        this.setObjValue(id);
//        BaseService ser = (BaseService) BeanFactory.findBean("baseSer");
//        String hql = " from AppBizAcFwxm  order by xmmc ";
//        Object obj = ser.queryOneByHql(hql);
//        if (null != obj) {
//            AppBizAcFwxm emp = (AppBizAcFwxm) obj;
//            this.setObjValue(emp.getId() + "");
//            this.setValue(emp.getXmmc());
//            this.setText(emp.getXmmc());
//            this.setObj(emp);
//        }
    }

    public Listbox getLb() {
        return lb;
    }

    public void setLb(Listbox lb) {
        this.lb = lb;
    }

    /**
     * <p>
     * 初始化过滤条件
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: DeviceInStorageBandBox.java,v 0.1 2012-3-7 下午01:55:28 Jack
     * Exp $
     */
    public void initFilterCon() {
        pup.setParent(this);
        pup.setWidth("500px");
    }

    /**
     * <p>
     * 处理化列表头
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: DeviceInStorageBandBox.java,v 0.1 2012-3-7 下午01:55:12 Jack
     * Exp $
     */
    public void initHead() {
        // 这里加个按钮，点击选中所有
//
        lb.getItems().clear();
        lb.setStyle("height:400");
        lb.setMold("paging");
        lb.setPageSize(15);
        lb.setParent(pup);
        lb.setWidth("99%");
        lb.setSizedByContent(false);
        lb.setMultiple(true);
        lb.setCheckmark(true);

        Listhead head = lb.getListhead();
        if (null == head)
            head = new Listhead();
        head.setParent(lb);
        Listheader h1 = new Listheader("服务项目");
        h1.setParent(head);
    }

    // 点击查询的时候载入数据

    /**
     * <p>
     * 加载数据
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: DeviceInStorageBandBox.java,v 0.1 2012-3-7 下午01:55:04 Jack
     * Exp $
     */
    public void loadData(String hql) {
//
    }

    @Override
    public List<Map<String,Object>> getSelVals() {
        return selVals;
    }

    public class ingueryClickEvent implements EventListener {
        private ComponentBandBox tbox;

        public ingueryClickEvent(ComponentBandBox tbox) {
            this.tbox = tbox;
        }

        @Override
        public void onEvent(Event event) throws Exception {
            String _hql = "    ";

            // _hql += " order by name desc ";
            this.tbox.loadData(_hql);
        }
    }

    // 打开bandbox搜索框时候触发的事件
    private class OpenCusEvent implements EventListener {
        private ComponentBandBox tbox;

        public OpenCusEvent(ComponentBandBox tbox) {
            this.tbox = tbox;
        }

        @Override
        public void onEvent(Event event) throws Exception {
            OpenEvent oe = (OpenEvent) event;
            if (oe.isOpen()) {
                if (lb.getItems().size() <= 0) {
                    String hql = "  ";
                    tbox.loadData(hql);
                }
            }
        }
    }

    public void clear() {
        List<Listitem> items = lb.getItems();
        if (null != items && items.size() > 0)
            for (Listitem item : items) {
                item.setSelected(false);
            }
        setValue("请选择");
    }

    // 双击选中
    private class ItemClickEvent implements EventListener {
        private ComponentBandBox tbox;

        public ItemClickEvent(ComponentBandBox tbox) {
            this.tbox = tbox;
        }

        @Override
        public void onEvent(Event event) throws Exception {
//            Listitem item = (Listitem) event.getTarget();
//            String _bk = getText();
//            _bk = _bk.replaceAll("请选择", "");
//            if (item.isSelected()) {
//                setValue(_bk + "," + ((AppBizAcFwxm) item.getValue()).getXmmc());
//            } else {
//                _bk = _bk.replaceAll("," + ((AppBizAcFwxm) item.getValue()).getXmmc(), "");
//                setValue(_bk);
//            }


        }
    }

}
