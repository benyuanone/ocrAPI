package com.ourway.base.zk.template;

import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.*;
import com.ourway.base.zk.models.GridFilterVO;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.TreeDataUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Listitem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>方法 ListPageTemplateAction : <p>
 * <p>说明:模板页面</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/8 22:51
 * </pre>
 */
public class FilterGridAction extends BaseWindow {
    protected Log info = LogFactory.getLog(FilterGridAction.class);
    BaseButton confirm;
    BaseButton close;
    BaseListbox key1 = null;
    BaseListbox key2 = null;
    BaseListbox rel1 = null;
    BaseListbox rel2 = null;
    BaseTextbox val1 = null;
    BaseTextbox val2 = null;
    List<GridFilterVO> gridFilterVOS;
    Map<String, PageControlVO> controlVOMap = new HashMap<String, PageControlVO>();

    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        Map<String, Object> params = (Map<String, Object>) event.getArg();
        if (null == params)
            detach();
        List<PageControlVO> voList = (List<PageControlVO>) params.get("cols");
        initKeyList(voList);
        rel1 = (BaseListbox) getFellowIfAny("rel1");
        rel2 = (BaseListbox) getFellowIfAny("rel2");
        val1 = (BaseTextbox) getFellowIfAny("val1");
        val2 = (BaseTextbox) getFellowIfAny("val2");
        confirm = (BaseButton) getFellowIfAny("confirm");
        close = (BaseButton) getFellowIfAny("close");
        confirm.setLabel(I18nUtil.getLabelContent(ERCode.GRID_FILTER_SEARCH_BUTTON_TITLE));
        close.setLabel(I18nUtil.getLabelContent(ERCode.GRID_FILTER_CANCER_BUTTON_TITLE));

        confirm.addEventListener(Events.ON_CLICK, this);
        close.addEventListener(Events.ON_CLICK, this);
        reloadButton();
    }

    private void initKeyList(List<PageControlVO> voList) {
        String attribute = "";
        key1 = (BaseListbox) getFellowIfAny("key1");
        key2 = (BaseListbox) getFellowIfAny("key2");
        key1.getChildren().clear();
        key2.getChildren().clear();
        Listitem listitem = new Listitem("---", "");
        listitem.setSelected(true);
        listitem.setParent(key1);
        for (PageControlVO pageControlVO : voList) {
            attribute = pageControlVO.getKjAttribute();
            listitem = new Listitem(I18nUtil.getLabelContent(pageControlVO.getKjLabelid()), attribute);
            listitem.setParent(key1);
            controlVOMap.put(pageControlVO.getKjAttribute(), pageControlVO);
        }


        listitem = new Listitem("---", "");
        listitem.setSelected(true);
        listitem.setParent(key2);
        for (PageControlVO pageControlVO : voList) {
            attribute = pageControlVO.getKjAttribute();
            listitem = new Listitem(I18nUtil.getLabelContent(pageControlVO.getKjLabelid()), attribute);
            listitem.setParent(key2);
        }
    }

    public List<GridFilterVO> getGridFilterVOS() {
        return gridFilterVOS;
    }

    public void setGridFilterVOS(List<GridFilterVO> gridFilterVOS) {
        this.gridFilterVOS = gridFilterVOS;
    }

    void filterDataList() {
        gridFilterVOS = new ArrayList<GridFilterVO>();
        String _key = "";
        String attribute = "";
        if (!TextUtils.isEmpty(key1.getSelectedItem().getValue())) {
            _key = key1.getSelectedItem().getValue();
            PageControlVO pageControlVO = controlVOMap.get(_key);
            if (!TextUtils.isEmpty(pageControlVO.getKjAttributeDisplay()))
                attribute = pageControlVO.getKjAttributeDisplay();
            else
                attribute = pageControlVO.getKjAttribute();
            GridFilterVO vo = GridFilterVO.instance(attribute, rel1.getSelectedItem().getValue().toString(), val1.getText(),"");
            gridFilterVOS.add(vo);
        }
        if (!TextUtils.isEmpty(key2.getSelectedItem().getValue())) {
            _key = key2.getSelectedItem().getValue();
            PageControlVO pageControlVO = controlVOMap.get(_key);
            if (!TextUtils.isEmpty(pageControlVO.getKjAttributeDisplay()))
                attribute = pageControlVO.getKjAttributeDisplay();
            else
                attribute = pageControlVO.getKjAttribute();
            GridFilterVO vo = GridFilterVO.instance(attribute, rel2.getSelectedItem().getValue().toString(), val2.getText(), "");
            gridFilterVOS.add(vo);
        }
        setClosePage(true);
        detach();
    }

    @Override
    public void onEvent(Event event) throws Exception {
        if (event.getTarget().getId().equalsIgnoreCase("close")) {
            detach();
        }
        if (event.getTarget().getId().equalsIgnoreCase("confirm")) {
            filterDataList();
        }
    }

}
