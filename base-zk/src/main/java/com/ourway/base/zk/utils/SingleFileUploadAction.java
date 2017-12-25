package com.ourway.base.zk.utils;

import com.ourway.base.utils.DateUtil;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.service.ComponentFileSer;
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/31.
 */
public class SingleFileUploadAction implements EventListener {
    private String clzName;
    private BaseWindow win;
    private String dataList;
    private Map<String, Object> filePpt;

    public SingleFileUploadAction(String clzName, BaseWindow win, String dataList) {
        this.clzName = clzName;
        this.win = win;
        this.dataList = dataList;
        filePpt = new HashMap<String, Object>();
    }

    //上传成功的返回
    public void doBackEvent() {
        if (!com.ourway.base.utils.TextUtils.isEmpty(clzName)) {
            try {
                Object _obj = Class.forName(clzName).newInstance();
                ComponentFileSer compAction = (ComponentFileSer) _obj;
                compAction.doAction(win, filePpt, dataList);
            } catch (Exception e) {
                e.printStackTrace();
                AlterDialog.alert(clzName + "执行错误");
            }
        }

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
                    String fileName1 = System.currentTimeMillis() + "";
                    fileName = fileName1 + "." + media.getFormat();
                    folder = ZKConstants.SYSTEM_FILE_PATH + ymd + File.separator;
                    filePpt = new HashMap<String, Object>();
                    filePpt.put("fileName", fileName1);
                    filePpt.put("filePath", ymd + "/" + fileName);
                    filePpt.put("fileLabel", media.getName());
                    filePpt.put("fileExtion", media.getFormat());
                    filePpt.put("fileRandon", media.getContentType());
                    filePpt.put("fileDown", 0);
                    File _folder = new File(folder);
                    if (!_folder.exists())
                        _folder.mkdirs();
                    File file = new File(folder + fileName);
                    if (media.isBinary()) {
                        Files.copy(file, media.getStreamData());
                        Files.close(media.getStreamData());
                    } else {
                        java.io.InputStream ins = new java.io.ByteArrayInputStream(media.getStringData().getBytes());
                        java.io.OutputStream out = new java.io.FileOutputStream(file);
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
                    doBackEvent();
                }
            }
        }
    }
}
