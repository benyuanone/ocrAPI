package com.ourway.base.zk.service.impl;

import com.ourway.base.utils.JsonUtil;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import net.sf.json.JSONArray;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Filedownload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>方法: 附件下载，每次只能下载一个附件 </p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2017/5/24 22:31  </li>
 * </ul>
 */
public class FileDownloadAction implements ComponentListinerSer {
//[{"gridId":""}]
    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {
        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        if (TextUtils.isEmpty(windowParams)) {
            AlterDialog.alert("请输入参数");
            return;
        }
        List<Map<String, Object>> paramsList = com.ourway.base.utils.JsonUtil.jsonStr2List(windowParams.toString());
        Map<String, Object> _params = paramsList.get(0);
        BaseGrid baseGrid = (BaseGrid) window.getFellowIfAny(_params.get("gridId").toString());
        List<Map<String,Object>> datas = baseGrid.getSelectRowsData();
        if(null!=datas&&datas.size()==1){
            Map<String,Object> data = datas.get(0);
            FileInputStream inputStream;
            try {
                File dosfile = new File(ZKConstants.SYSTEM_FILE_PATH + data.get("filePath"));
                if (dosfile.exists()) {
                    inputStream = new FileInputStream(dosfile);
                    Filedownload.save(inputStream, data.get("fileRandon").toString(), data.get("fileLabel").toString());
                }else{
                    AlterDialog.alert(I18nUtil.getLabelContent("public.sys.filenoexit"));
                    return;
                }
            } catch (FileNotFoundException e) {
                AlterDialog.alert(I18nUtil.getLabelContent("public.sys.filenoexit"));
                e.printStackTrace();
            }
        }else{
            AlterDialog.alert(I18nUtil.getLabelContent("public.sys.selectOneFile"));
        }
    }
}
