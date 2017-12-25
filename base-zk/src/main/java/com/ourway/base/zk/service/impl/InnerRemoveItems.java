package com.ourway.base.zk.service.impl;

import com.ourway.base.utils.JsonUtil;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.GridUtils;
import net.sf.json.JSONArray;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>方法 RemoveInlineItems : <p>
 * <p>说明:删除子表grid中的选中数据</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/30 13:45
 * </pre>
 */
public class InnerRemoveItems implements ComponentListinerSer {

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object params = pgvo.getLayoutComponent().getEventDataContent();
        List<Object> removeCs = new ArrayList<Object>();
        if (null != params) {
            JSONArray jsonArray = JSONArray.fromObject(params.toString());
            removeCs = JsonUtil.jsonToList(jsonArray);
            if (!AlterDialog.corfirm("确定需要删除选中的行"))
                return;
            for (Object object : removeCs) {
                Map<String, Object> map = (Map<String, Object>) object;
                BaseGrid baseGrid = (BaseGrid) window.getFellowIfAny(map.get("gridId").toString());
                List<Row> selectRows = baseGrid.getSelectRows();
                for (Row selectRow : selectRows) {
                    Map<String, Object> data = (Map<String, Object>) selectRow.getValue();
                    data.put(GridUtils.SUBEDIT, 2);//表示要删除
                    selectRow.setVisible(false);
                }

            }
        }
    }
}
