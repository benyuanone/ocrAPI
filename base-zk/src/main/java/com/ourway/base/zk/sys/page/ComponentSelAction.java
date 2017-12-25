package com.ourway.base.zk.sys.page;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.component.*;
import com.ourway.base.zk.utils.data.DicUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Listitem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>方法 ListPageTemplateAction : <p>
 * <p>说明:模板页面</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/8 22:51
 * </pre>
 */
public class ComponentSelAction extends BaseWindow {
    protected Log info = LogFactory.getLog(ComponentSelAction.class);

    private Map<String, Object> layOutComponent;
    private Map<String, Map<String, Object>> pageComponents;

    private Map<String, Object> existMap = new HashMap<String, Object>();

    private String _totalCols;
    private String rowNum;
    private int oldCols = 0;
    private boolean flag = false;
    private int componentIndex;

    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        if (null != event.getArg().get("ppt")) {
            ppt = (Map<String, Object>) event.getArg().get("ppt");
            oldCols = Integer.parseInt(ppt.get("compCols").toString());
            flag = true;
        }
        if (null != event.getArg().get("ctrls")) {
            pageComponents = (Map<String, Map<String, Object>>) event.getArg().get("ctrls");
        }
        if (null != event.getArg().get("layoutComponent")) {
            layOutComponent = (Map<String, Object>) event.getArg().get("layoutComponent");
            if (null != layOutComponent.get("dataList")) {
                List<Map<String, Object>> datas = (List<Map<String, Object>>) layOutComponent.get("dataList");
                for (Map<String, Object> data : datas) {
                    existMap.put(data.get("pageRefOwid").toString(), data);
                }
            }
        }
        if (null != event.getArg().get("totalCols")) {
            _totalCols = event.getArg().get("totalCols").toString();
        }
        if (null != event.getArg().get("rowNum")) {
            rowNum = (new Integer(event.getArg().get("rowNum").toString()) + 1) + "";
        }
        if (null != event.getArg().get("componentIndex")) {
            componentIndex = (new Integer(event.getArg().get("componentIndex").toString()) + 1);
        }
        initPageInit();
        init();
        BaseButton saveBtn = (BaseButton) getFellowIfAny("saveBtn");
        BaseButton closeBtn = (BaseButton) getFellowIfAny("closeBtn");
        BaseButton removeBtn = (BaseButton) getFellowIfAny("removeBtn");
        saveBtn.addEventListener(Events.ON_CLICK, this);
        closeBtn.addEventListener(Events.ON_CLICK, this);
        removeBtn.addEventListener(Events.ON_CLICK, this);
        if (null != ppt)
            bind2Page();
    }

    //初始化
    private void init() {
        if (null == pageComponents)
            return;
        Set<String> keys = pageComponents.keySet();
        BaseListbox layoutRefOwid = (BaseListbox) getFellowIfAny("componentSelGrid_pageRefOwid");
        layoutRefOwid.getChildren().clear();
        Listitem item = new Listitem("---请选择---", "");
        if (null == ppt.get("pageRefOwid"))
            item.setSelected(true);
        item.setParent(layoutRefOwid);
        for (String key : keys) {
            item = new Listitem(pageComponents.get(key).get("kjName").toString() + ":" + key, key);
            if (null != ppt.get("pageRefOwid") && ppt.get("pageRefOwid").toString().equals(item.getValue().toString()))
                item.setSelected(true);
            else {
                if (null != existMap && null != existMap.get(item.getValue().toString()))
                    continue;
            }
            item.setParent(layoutRefOwid);
        }
        BaseLabel componentSelGrid_compStartRow = (BaseLabel) getFellowIfAny("componentSelGrid_compStartRow");
        componentSelGrid_compStartRow.setValue(rowNum);
        BaseIntbox componentSelGrid_compCols = (BaseIntbox) getFellowIfAny("componentSelGrid_compCols");
        componentSelGrid_compCols.setValue(new Integer(_totalCols));
    }


    private void initPageInit(){
        List<Map<String,Object>> datas = DicUtil.listDic(0);
        BaseCombobox pageInit = (BaseCombobox)getFellowIfAny("componentSelGrid_evnetFormula");
        pageInit.getChildren().clear();
        if(null!=datas)
            for(Map<String,Object> data:datas){
                Comboitem ci = new Comboitem(data.get("dicVal3").toString());
                ci.setDescription(com.ourway.base.zk.utils.TextUtils.isEmpty(data.get("dicVal2"))?"":data.get("dicVal2").toString());
                ci.setParent(pageInit);
            }
    }

    private void saveAll() {
        ppt = bindAll2Ppt(true);
        //如果是修改，则不该序号
        if(TextUtils.isEmpty(ppt.get("compOrder")))
            ppt.put("compOrder",componentIndex);
        int _cols = Integer.parseInt(ppt.get("compCols").toString());
        if (!flag) {
            if (_cols == 0 && (null == ppt.get("compHbox") || (Integer) ppt.get("compHbox") <= 0)) {
                throw new WrongValueException(getFellowIfAny("componentSelGrid_compCols"), "只有合并单元格才能设置列为0");
            }
            if (null != ppt.get("compLable"))
                _cols = _cols + 1;
            if (new Integer(_totalCols) - _cols < 0) {
                throw new WrongValueException(getFellowIfAny("componentSelGrid_compCols"), "列数必须小于等于" + _totalCols);
            }
        } else {
            if (_cols > Integer.parseInt(_totalCols+oldCols)) {
                throw new WrongValueException(getFellowIfAny("componentSelGrid_compCols"), "列数必须小于等于" + _cols);
            }
        }
        if(TextUtils.isEmpty(ppt.get("pageRefOwid"))){
            throw new WrongValueException(getFellowIfAny("componentSelGrid_pageRefOwid"),"必须选择控件");
        }
        isClosePage = true;
        detach();
    }

    @Override
    public void onEvent(Event event) throws Exception {
        Component comp = event.getTarget();
        if (comp instanceof BaseButton) {
            if (comp.getId().equals("saveBtn")) {
                saveAll();

            }
            if (comp.getId().equals("closeBtn")) {
                //关闭
                detach();
            }
            if (comp.getId().equals("removeBtn")) {
                ppt = bindAll2Ppt(false);
                setDelete(true);
                isClosePage = true;
                detach();
            }
        }
    }


}
