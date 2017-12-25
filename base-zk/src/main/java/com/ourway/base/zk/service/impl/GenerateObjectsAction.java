package com.ourway.base.zk.service.impl;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.ClassTools;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.sys.page.PackageClassSelect;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;

import java.beans.PropertyDescriptor;
import java.util.*;

/**
 * <p>方法: 生成指定的类及属性列表 </p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2017/7/22 14:40  </li>
 * </ul>
 */
public class GenerateObjectsAction implements ComponentListinerSer {
    //[{"apiUrl":"","gridId":""}]
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        List<Object> paramList = new ArrayList<Object>();
        String packageName = "";
        String preClassName = "";
        try {
            List<Map<String, Object>> paramsList = JsonUtil.jsonStr2List(windowParams.toString());
            Map<String, Object> _params = paramsList.get(0);
            PackageClassSelect winEdit = (PackageClassSelect)Executions.createComponents("/sys/page/inputClassPre.do", null, null);
            winEdit.setClosable(true);
            winEdit.doModal();
            if(winEdit.isClosePage()){
                packageName = winEdit.getClassName();
                preClassName = winEdit.getPreClassName();
                _params.put("package",packageName);
                _params.put("preClassName",preClassName);
            }else{
                return;
            }
            if (TextUtils.isEmpty(_params.get("apiUrl"))) {
                AlterDialog.alert("请定义调用接口");
                return;
            }
            if (TextUtils.isEmpty(_params.get("gridId"))) {
                AlterDialog.alert("请定义刷新的Grid");
                return;
            }
            BaseGrid grid = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());
            if (null == grid) {
                AlterDialog.alert("刷新对象不存在");
                return;
            }

            //调用接口
            Map<String, Object> apiData = new HashMap<String, Object>(1);
            apiData.put("packageName", _params.get("package").toString());
            apiData.put("preClassName", _params.get("preClassName").toString());
            ResponseMessage message = JsonPostUtils.executeAPI(apiData, _params.get("apiUrl").toString());
            if (null == message) {
                AlterDialog.alert(I18nUtil.getLabelContent(ERCode.ACTION_FAIL));
                return;
            }
            if (message.getBackCode() != 0) {
                AlterDialog.alert(message.getErrorMess());
                return;
            } else {
                grid.filter(null);
                grid.display();
                AlterDialog.alert(I18nUtil.getLabelContent(ERCode.ACTION_SUCESS));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
