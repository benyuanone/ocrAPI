package com.ourway.base.zk.sys.attachfile;

import com.ourway.base.utils.DateUtil;
import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.component.BaseButton;
import com.ourway.base.zk.component.BaseListbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.service.ComponentFileSer;
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
public class ImportFileAction extends BaseWindow {
    protected Log info = LogFactory.getLog(ImportFileAction.class);
    private String clzName;
    private BaseWindow win;
    private String dataList;
    private Map<String, Object> filePpt;

    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        if (null != event.getArg())
            ppt = (Map<String, Object>) event.getArg();
        else {
            AlterDialog.alert("No params");
            detach();
        }
        this.setTitle(I18nUtil.getLabelContent(ERCode.FILE_CENTER_TITLE));
        BaseButton closeBtn = (BaseButton) getFellowIfAny("closeBtn");
        closeBtn.setLabel(I18nUtil.getLabelContent(ERCode.FILE_CENTER_CLOSE));
        BaseButton uploadBtn = (BaseButton) getFellowIfAny("uploadBtn");
        uploadBtn.setLabel(I18nUtil.getLabelContent(ERCode.FILE_CENTER_UPLOAD));
        Dropupload fileDropUpload = (Dropupload) getFellowIfAny("fileDropUpload");
        fileDropUpload.setContent(I18nUtil.getLabelContent(ERCode.FILE_CENTER_DRAP_TIP));
        closeBtn.addEventListener(Events.ON_CLICK, this);
        uploadBtn.addEventListener(Events.ON_UPLOAD, this);
        fileDropUpload.addEventListener(Events.ON_UPLOAD, this);
        Desktop desktop = super.getDesktop();
        Configuration conf = desktop.getWebApp().getConfiguration();
        conf.setUploadCharset("GBK");
    }


    public Map<String, Object> getFilePpt() {
        return filePpt;
    }

    @Override
    public void onEvent(Event event) throws Exception {
        if (event instanceof UploadEvent) {
            String ymd = "";
            String folder = "";
            String fileName = "";
            UploadEvent uploadEvent = (UploadEvent) event;
            if (null != uploadEvent.getMedias()) {
                for (Media media : uploadEvent.getMedias()) {
                    ymd = DateUtil.getDateStr("yyyyMMdd");
                    fileName = System.currentTimeMillis() + "";
                    folder = ZKConstants.SYSTEM_FILE_PATH + ymd + File.separator;
                    filePpt = new HashMap<String, Object>();
                    filePpt.put("fileName", fileName);
                    filePpt.put("fileLabel", media.getName());
                    filePpt.put("fileExtion", media.getFormat());
                    filePpt.put("fileRandon", media.getContentType());
                    filePpt.put("filePath", ymd + "/" + fileName);
                    filePpt.put("fileDown", 0);
                    File _folder = new File(folder);
                    if (!_folder.exists())
                        _folder.mkdirs();
                    File file = new File(folder + fileName);
                    if (media.isBinary()) {
                        Files.copy(file, media.getStreamData());
                        Files.close(media.getStreamData());
                    } else {
                        InputStream ins = new ByteArrayInputStream(media.getStringData().getBytes());
                        OutputStream out = new FileOutputStream(file);
                        byte[] buf = new byte[1024 * 10 * 100];
                        int len;
                        while ((len = ins.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        out.close();
                        ins.close();
                    }
                    filePpt.put("fileSize", file.length());
                    filePpt.put("fileMd5", com.ourway.base.utils.TextUtils.getFileMD5String(file));
                    //调用存储接口
                    setClosePage(true);
                    detach();
                }
            }
        } else {
            if (event.getTarget().getId().equals("closeBtn")) {
                detach();
            }


        }
    }


}
