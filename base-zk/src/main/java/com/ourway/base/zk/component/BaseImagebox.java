package com.ourway.base.zk.component;

import com.ourway.base.utils.DateUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.service.ComponentSer;
import com.ourway.base.zk.sys.attachfile.ImageDisplayAction;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.zkoss.image.AImage;
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Vbox;

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
public class BaseImagebox extends Div implements ComponentSer<String> {
    public static String DEFAULT_IMG = "/applications/images/upload.png";
    private String property;
    private PageControlVO pgvo;//当前控件的属性对象
    private String result; //返回选中的值
    private Image img;//显示小图
    private BaseButton db1;//点击上传
    Map<String, Object> filePpt;
    private BaseWindow win;

    public void onCreate() {
        Vbox vbox = new Vbox();
        vbox.setParent(this);
        img = new Image();
        img.setSrc(DEFAULT_IMG);
        img.setSclass("base-imageUpload");
        img.setParent(vbox);
        img.addEventListener(Events.ON_CLICK, new FileUpload());
        db1 = new BaseButton();
        db1.setClass("btn-default btn-image-upload");
        db1.setParent(vbox);
        db1.setWidth("100%");
        db1.setUpload("true,maxsize=-1,multiple=true");
        db1.addEventListener(Events.ON_UPLOAD, new FileUpload());
        db1.setLabel(I18nUtil.getLabelContent(ERCode.FILE_CENTER_UPLOAD));

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
                       if(media instanceof Image || media instanceof AImage) {
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
                           filePpt.put("fileMd5", TextUtils.getFileMD5String(file));
                           result = ymd + "/" + fileName;
                           img.setSrc(ZKConstants.SYSTEM_FILE_URL + result);
                           doBackEvent();
                       }else{
                           throw new WrongValueException(event.getTarget(),I18nUtil.getLabelContent(ERCode.FILE_NOT_IMAGE));
                       }
                    }
                }
            }
            if (event.getTarget() instanceof Image) {
                Image image = (Image) event.getTarget();
                if (image.getSrc().equalsIgnoreCase(DEFAULT_IMG))
                    return;
                Map<String, Object> params = new HashMap<String, Object>(1);
                params.put("filePath", image.getSrc());
                ImageDisplayAction winEdit = (ImageDisplayAction) Executions.createComponents("/sys/attachfile/imageDisplay.do", null, params);
                if (winEdit instanceof BaseWindow) {
                    winEdit.setClosable(true);
                    winEdit.setMinimizable(true);
                    winEdit.setMaximizable(true);
                    winEdit.setParent(win);
                    winEdit.doModal();
                }
            }
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
    public void init(PageControlVO pageControlVO, BaseWindow win) {
        this.win = win;
    }

    @Override
    public String getPageValue() {
        return result;
    }

    @Override
    public void setPageValue(Object obj) {
        if (!TextUtils.isEmpty(obj)) {
            img.setSrc(ZKConstants.SYSTEM_FILE_URL + obj.toString());
            result = obj.toString();
        }
    }

    public void showDefault(Object obj) {
        filePpt = new HashMap<String, Object>();
        setPageValue(obj);
    }

    @Override
    public void addEventListiner(PageControlVO pageControlVO, BaseWindow win) {
    }

    @Override
    public void doEvent(Object objectMap, BaseWindow win, Event event) {
    }

    @Override
    public void reset() {
        img.setSrc(DEFAULT_IMG);
        result = "";
    }


    @Override
    public void setComponentDisable(boolean flag) {
        db1.setDisabled(flag);
    }

    //上传成功的返回
    public void doBackEvent() {
        if (!TextUtils.isEmpty(pgvo.getLayoutComponent().getEvnetFormula())) {
            String clzName = pgvo.getLayoutComponent().getEvnetFormula();
            try {
                Event event = new KeyEvent("baseImageBox", this, 0, false, false, false);
                Object _obj = Class.forName(clzName).newInstance();
                ComponentListinerSer compAction = (ComponentListinerSer) _obj;
                compAction.doAction(win, event, pgvo);
            } catch (Exception e) {
                e.printStackTrace();
                AlterDialog.alert(clzName + "执行错误");
            }
        }

    }
}
