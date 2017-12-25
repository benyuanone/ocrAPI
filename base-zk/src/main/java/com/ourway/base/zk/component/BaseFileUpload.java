package com.ourway.base.zk.component;

import com.ourway.base.utils.DateUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.service.ComponentSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.data.FileDataUtil;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Span;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>方法: 时间段查询 </p>
 * <ul>
 * <li> @param null TODO</li>
 * <li>@return   </li>
 * <li>@author JackZhou </li>
 * <li>@date 2017/5/31 23:22  </li>
 * </ul>
 */
public class BaseFileUpload extends Div implements ComponentSer<String> {
    private String property;
    private PageControlVO pgvo;
    private String result;
    private BaseButton db1;
    private BaseTextbox textbox;
    private BaseWindow win;
    Map<String, Object> filePpt;

    public Map<String, Object> getFilePpt() {
        return filePpt;
    }

    public void onCreate() {
        this.setClass("input-group");
        textbox = new BaseTextbox();
        textbox.setDisabled(true);
        textbox.setClass("form-control input-sm ");
        textbox.setParent(this);
        Span span = new Span();
        span.setClass("input-group-btn");
        span.setParent(this);
        db1 = new BaseButton();
        db1.setClass("btn-default input-sm z-fileUpload-button ");
        db1.setParent(span);
        db1.setUpload("true,maxsize=-1,multiple=true");
        db1.addEventListener(Events.ON_UPLOAD, new FileUpload());
        db1.setLabel(I18nUtil.getLabelContent(ERCode.FILE_CENTER_UPLOAD));
    }

    public void setCustClass(String custClass) {
        if (!TextUtils.isEmpty(custClass)) {
            textbox.setClass("form-control input-sm " + custClass);
        }
    }
    @Override
    public PageControlVO getPgvo() {
        return pgvo;
    }

    public void setPgvo(PageControlVO pgvo) {
        this.pgvo = pgvo;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public void init(Map<String, Object> pageObj, String property) {

    }

    @Override
    public String getPageValue() {
        return result;
    }

    @Override
    public void setPageValue(Object obj) {

    }

    @Override
    public void init(PageControlVO pageControlVO, BaseWindow win) {
        this.win = win;
    }

    @Override
    public void addEventListiner(PageControlVO pageControlVO, BaseWindow win) {
    }

    @Override
    public void doEvent(Object objectMap, BaseWindow win, Event event) {
    }

    @Override
    public void reset() {
        textbox.setText("");

    }

    public void showDefault(Object obj) {
        filePpt = new HashMap<String, Object>();
        if (!TextUtils.isEmpty(obj)) {
            filePpt.put("filePath", obj.toString());
            textbox.setText(obj.toString());
            doBackEvent();
        }
    }

    //上传成功的返回
    public void doBackEvent() {
        if (!TextUtils.isEmpty(pgvo.getLayoutComponent().getEvnetFormula())) {
            String clzName = pgvo.getLayoutComponent().getEvnetFormula();
            try {
                Event event = new KeyEvent("baseFileUpload", this, 0, false, false, false);
                Object _obj = Class.forName(clzName).newInstance();
                ComponentListinerSer compAction = (ComponentListinerSer) _obj;
                compAction.doAction(win, event, pgvo);
            } catch (Exception e) {
                e.printStackTrace();
                AlterDialog.alert(clzName + "执行错误");
            }
        }

    }

    class FileUpload implements EventListener {
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
                        result = ymd + "/" + fileName;
                        textbox.setText(result);
                        doBackEvent();
                    }
                }
            }
        }
    }

    @Override
    public void setComponentDisable(boolean flag) {
        db1.setDisabled(flag);
        textbox.setDisabled(flag);
    }
}
