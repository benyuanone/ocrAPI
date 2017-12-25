package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseMultipFileUpload;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.main.MainAction;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import org.apache.commons.collections.map.HashedMap;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>方法: 新增弹出新界面的方法 </p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2017/5/24 22:31  </li>
 * </ul>
 */
public class AttachFileAction implements ComponentListinerSer {

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        try {
            //判断是否有owid，如果没有，则创建一个临时的“tmp”+owid
            String fileExtId = "";
            Map<String, Object> ppt = window.getPpt();
            if (!TextUtils.isEmpty(ppt.get("owid")))
                fileExtId = ppt.get("owid").toString();
            else if (!TextUtils.isEmpty(ppt.get("fileExtId"))) {
                fileExtId = ppt.get("fileExtId").toString();
            } else {
                fileExtId = "tmp" + TextUtils.getUUID();
                window.getPpt().put("fileExtId", fileExtId);//作为处理时候的其它id
            }
            List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
            Map<String, Object> _params = paramsList.get(0);
            if (TextUtils.isEmpty(_params.get("fileCode"))) {
                AlterDialog.alert("请定义文件类别");
                return;
            }
            _params.put("ppt", fileExtId);

            com.ourway.base.zk.sys.attachfile.AttachFileAction winEdit = (com.ourway.base.zk.sys.attachfile.AttachFileAction) Executions.createComponents("/sys/attachfile/attachFile.do", null, _params);
            if (winEdit instanceof BaseWindow) {
                if (!TextUtils.isEmpty(_params.get("windowCss")))
                    winEdit.setStyle(_params.get("windowCss").toString());
                if (!TextUtils.isEmpty(_params.get("title")))
                    winEdit.setTitle(_params.get("title").toString());
                winEdit.setClosable(true);
                winEdit.setMinimizable(true);
                winEdit.setMinimized(true);
                winEdit.setParent(window);
                winEdit.doModal();
//                if (winEdit.isClosePage()) {
                    try {
                        BaseMultipFileUpload baseMultipFileUpload = (BaseMultipFileUpload) event.getTarget().getParent().getParent();
                        baseMultipFileUpload.setFileNum(winEdit.getFilesNum() + I18nUtil.getLabelContent(ERCode.FILE_CENTER_TEXT));
                    } catch (Exception e) {

                    }
                    winEdit.detach();
//                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
