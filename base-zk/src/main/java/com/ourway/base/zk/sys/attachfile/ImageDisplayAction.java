package com.ourway.base.zk.sys.attachfile;

import com.ourway.base.utils.DateUtil;
import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.component.BaseButton;
import com.ourway.base.zk.component.BaseListbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.ListBoxUtils;
import com.ourway.base.zk.utils.data.FileDataUtil;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.Configuration;
import org.zkoss.zkmax.zul.Dropupload;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Image;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listitem;

import java.io.*;
import java.util.*;

/**
 * <p>方法 ListPageTemplateAction : <p>
 * <p>说明:模板页面</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/8 22:51
 * </pre>
 */
public class ImageDisplayAction extends BaseWindow {
    protected Log info = LogFactory.getLog(ImageDisplayAction.class);

    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        if (null != event.getArg()) {
            ppt = (Map<String, Object>) event.getArg();
            Image image = (Image) getFellowIfAny("image");
            image.setSrc(ppt.get("filePath").toString());
        } else {
            AlterDialog.alert("未传入参数");
            detach();
        }
        this.setTitle("图片详情");
        init();
    }

    private void init() {


    }


    void fileDown() {
//
//            FileInputStream inputStream;
//            try {
//                File dosfile = new File(ZKConstants.SYSTEM_FILE_PATH + data.get("filePath"));
//                if (dosfile.exists()) {
//                    inputStream = new FileInputStream(dosfile);
//                    Filedownload.save(inputStream, data.get("fileRandon").toString(), data.get("fileLabel").toString());
//                }
//            } catch (FileNotFoundException e) {
//
//                e.printStackTrace();
//            }
//        }
    }


    @Override
    public void onEvent(Event event) throws Exception {

    }


}
