package com.ourway.base.zk.component;

import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.service.ComponentInitSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TreeUtils;
import net.sf.json.JSONArray;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Treeitem;

import java.util.*;


public class BaseMultipleTree extends BaseTree {
    private String property;
    private PageControlVO pgvo;//当前控件的属性对象
    private String selNodes;


    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public void setPgvo(PageControlVO pgvo) {
        this.pgvo = pgvo;
    }

    public void setSelNodes(String selNodes) {
        this.selNodes = selNodes;
    }

    public String getSelNodes() {
        StringBuilder sb = new StringBuilder();
        Set set = this.getSelectedItems();
        for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
            Treeitem it = (Treeitem) iterator.next();
            TreeVO menu = (TreeVO) it.getValue();
            sb.append(menu.getOwid() + ",");
        }
        return sb.toString();
    }

    public void setDefault(String selNodes) {
        this.selNodes = selNodes;
        if (!TextUtils.isEmpty(selNodes)) {
            List<String> _nodeList = new ArrayList<String>();
            String[] _nodes = selNodes.split("\\,");
            for (String node : _nodes) {
                _nodeList.add(node);
            }
            Collection<Treeitem> treeitems = this.getItems();
            for (Treeitem treeitem : treeitems) {
                if (null != treeitem.getValue()) {
                    TreeVO treeVO = (TreeVO) treeitem.getValue();
                    if (_nodeList.contains(treeVO.getOwid()+""))
                        treeitem.setSelected(true);
                }
            }
        }


    }


    @Override
    public void init(Map<String, Object> pageObj, String property) {

    }

    @Override
    public void init(PageControlVO pageControlVO, BaseWindow win) {
        List<String> _selNodes = new ArrayList<String>();
        if (!TextUtils.isEmpty(selNodes)) {
            String[] _nodes = selNodes.split("\\,");
            for (String node : _nodes) {
                _selNodes.add(node);
            }
        }
//判断是否需要初始化
        boolean flag = null == pageControlVO || null == pageControlVO.getLayoutComponent() || null == pageControlVO.getLayoutComponent().getCompInit() || pageControlVO.getLayoutComponent().getCompInit() == 0;
        if (flag)
            return;
        if (null == pageControlVO.getKjDatasource())
            return;
        TreeUtils treeUtils = new TreeUtils();
        switch (pageControlVO.getKjDatasource()) {
            case 0://用户输入
                break;
            case 1://默认值
                if (null != pageControlVO.getKjDefaultData()) {
                    try {
                        JSONArray jsonArray = JSONArray.fromObject(pageControlVO.getKjDefaultData().toString());
                        List<TreeVO> treeVOList = JsonUtil.jsonToList(jsonArray, TreeVO.class);
                        if (null != treeVOList && treeVOList.size() > 0) {
                            treeVOList = TreeUtils.getTrees(treeVOList);
                            treeUtils.showMultipleTree(win, null, this, treeVOList, _selNodes);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 2://调用接口

                break;
            case 3://自定义类
                try {
                    ComponentInitSer ser = (ComponentInitSer) Class.forName(pageControlVO.getKjClassImpl()).newInstance();
                    ser.doAction(win, this, pageControlVO);
                } catch (Exception e) {
                    AlterDialog.alert("控件初始化类失败");
                }
                break;
        }
    }

    @Override
    public String getPageValue() {
        return null;
    }

    @Override
    public void addEventListiner(PageControlVO pageControlVO, BaseWindow win) {

    }

    @Override
    public void doEvent(Object objectMap, BaseWindow win, Event event) {

    }

    @Override
    public void reset() {

    }

    @Override
    public PageControlVO getPgvo() {
        return pgvo;
    }

    @Override
    public void setComponentDisable(boolean flag) {
        Collection<Treeitem> treeitems = this.getItems();
        for(Treeitem treeitem:treeitems){
           treeitem.setDisabled(flag);
        }
    }
}
