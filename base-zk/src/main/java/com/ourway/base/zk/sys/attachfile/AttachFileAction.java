package com.ourway.base.zk.sys.attachfile;

import com.ourway.base.utils.*;
import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.component.BaseButton;
import com.ourway.base.zk.component.BaseListbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.utils.*;
import com.ourway.base.zk.utils.data.FileDataUtil;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.event.*;
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
public class AttachFileAction extends BaseWindow {
    protected Log info = LogFactory.getLog(AttachFileAction.class);
    private BaseListbox datas;
    private ListBoxUtils listBoxUtils;

    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        parentEvent = false;
        listBoxUtils = new ListBoxUtils();
        if (null != event.getArg())
            ppt = (Map<String, Object>) event.getArg();
        else {
            AlterDialog.alert("No params");
            detach();
        }
        this.setTitle(I18nUtil.getLabelContent(ERCode.FILE_CENTER_TITLE));
        BaseButton closeBtn = (BaseButton) getFellowIfAny("closeBtn");
        closeBtn.setLabel(I18nUtil.getLabelContent(ERCode.FILE_CENTER_CLOSE));
        BaseButton saveBtn = (BaseButton) getFellowIfAny("saveBtn");
        saveBtn.setLabel(I18nUtil.getLabelContent(ERCode.FILE_CENTER_SAVE));
        BaseButton uploadBtn = (BaseButton) getFellowIfAny("uploadBtn");
        uploadBtn.setLabel(I18nUtil.getLabelContent(ERCode.FILE_CENTER_UPLOAD));
        BaseButton downBtn = (BaseButton) getFellowIfAny("downBtn");
        downBtn.setLabel(I18nUtil.getLabelContent(ERCode.FILE_CENTER_DOWN));
        BaseButton refreshBtn = (BaseButton) getFellowIfAny("refreshBtn");
        refreshBtn.setLabel(I18nUtil.getLabelContent(ERCode.FILE_CENTER_REFRESH));
        BaseButton removeBtn = (BaseButton) getFellowIfAny("removeBtn");
        removeBtn.setLabel(I18nUtil.getLabelContent(ERCode.FILE_CENTER_REMOVE));
        datas = (BaseListbox) getFellowIfAny("datas");
        Dropupload fileDropUpload = (Dropupload) getFellowIfAny("fileDropUpload");
        fileDropUpload.setContent(I18nUtil.getLabelContent(ERCode.FILE_CENTER_DRAP_TIP));
        closeBtn.addEventListener(Events.ON_CLICK, this);
        uploadBtn.addEventListener(Events.ON_UPLOAD, this);
        downBtn.addEventListener(Events.ON_CLICK, this);
        refreshBtn.addEventListener(Events.ON_CLICK, this);
        removeBtn.addEventListener(Events.ON_CLICK, this);
        fileDropUpload.addEventListener(Events.ON_UPLOAD, this);
        saveBtn.addEventListener(Events.ON_CLICK, this);
        init();
        initData();
        Desktop desktop = super.getDesktop();
        Configuration conf = desktop.getWebApp().getConfiguration();
        conf.setUploadCharset("GBK");
    }

    private void init() {
        datas.setCheckmark(true);
        datas.setMultiple(true);
        datas.setSizedByContent(true);
        Listhead head = ListBoxUtils.doGetListHead(datas);
        ListBoxUtils.doCreateListHeader(ERCode.FILE_CENTER_FILE_NAME, "fileName", head, 0);
        ListBoxUtils.doCreateListHeader(ERCode.FILE_CENTER_FILE_LABEL, "fileLabel", head, 1);
        ListBoxUtils.doCreateListHeader(ERCode.FILE_CENTER_FILE_SIZE, "fileSize", head, 0);
        ListBoxUtils.doCreateListHeader(ERCode.FILE_CENTER_FILE_MD5, "fileMd5", head, 0);
        ListBoxUtils.doCreateListHeader(ERCode.FILE_CENTER_FILE_PATH, "filePath", head, 0);
        ListBoxUtils.doCreateListHeader(ERCode.FILE_CENTER_FILE_EXTION, "fileExtion", head, 0);
//        ListBoxUtils.doCreateListHeader(ERCode.FILE_CENTER_FILE_RANDON,"fileRandon",head);
        ListBoxUtils.doCreateListHeader(ERCode.FILE_CENTER_MEMO, "memo", head, 1);
        ListBoxUtils.doCreateListHeader(ERCode.FILE_CENTER_FILE_DOWN, "fileDown", head, 0);
        ListBoxUtils.doCreateListHeader(ERCode.FILE_CENTER_CREATETIME, "createtimeStr", head, 0);
    }

    private void initData() {
        datas.getItems().clear();
        List<Map<String, Object>> lbdatas = FileDataUtil.listFiles(ppt.get("ppt").toString(),ppt.get("fileCode").toString());
        if (null != lbdatas)
            listBoxUtils.displayData(lbdatas, datas);
    }

    public Integer getFilesNum(){
        return datas.getItems().size();
    }

    public void addNewFile(Map<String, Object> fd) {
        List<Map<String, Object>> lbdatas = new ArrayList<Map<String, Object>>(1);
        lbdatas.add(fd);
        listBoxUtils.displayData(lbdatas, datas);
    }

    void fileDown() {
        Set<Listitem> items = datas.getSelectedItems();
        if (items.size() > 1) {
            AlterDialog.alert(I18nUtil.getLabelContent(ERCode.FILE_CENTER_NOFILE));
            return;
        }
        for (Listitem item : items) {
            Map<String, Object> data = item.getValue();
            FileInputStream inputStream;
            try {
                File dosfile = new File(ZKConstants.SYSTEM_FILE_PATH + data.get("filePath"));
                if (dosfile.exists()) {
                    inputStream = new FileInputStream(dosfile);
                    Filedownload.save(inputStream, data.get("fileRandon").toString(), data.get("fileLabel").toString());
                }
            } catch (FileNotFoundException e) {

                e.printStackTrace();
            }
        }
    }

    void remove() {
        if (!AlterDialog.corfirm(ERCode.FILE_CENTER_DELETE_TIPS))
            return;
        Set<Listitem> items = datas.getSelectedItems();
        if (items.size() <= 0) {
            AlterDialog.alert(I18nUtil.getLabelContent(ERCode.FILE_CENTER_NOFILE));
            return;
        }
        List<String> owids = new ArrayList<String>();
        for (Listitem listitem : items) {
            Map<String, Object> data = (Map<String, Object>) listitem.getValue();
            owids.add(data.get("owid").toString());
        }
        FileDataUtil.removeFiles(owids);
        initData();
    }

    void save() {
        List<Listitem> items = datas.getItems();
        if (items.size() <= 0) {
            return;
        }
        List<Map<String,Object>> updateDatas = new ArrayList<Map<String,Object>>();
        for(Listitem item:items){
           Map<String,Object> data = (Map<String,Object>)item.getValue();
           if(null!=data.get("updateFlag")){
               //获取当前item中的每个值。
               ListBoxUtils.getEditValue(data,item);
               updateDatas.add(data);
           }
        }
        FileDataUtil.updateFiles(updateDatas);
        initData();
        AlterDialog.alert(ERCode.FILE_CENTER_UPDATE_TIPS);
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
                    Map<String, Object> filePpt = new HashMap<String, Object>();
                    filePpt.put("fileName", fileName);
                    filePpt.put("fileLabel", media.getName());
                    filePpt.put("fileExtion", media.getFormat());
                    filePpt.put("fileRandon", media.getContentType());
                    filePpt.put("fileClassId", ppt.get("ppt").toString());
                    filePpt.put("fileClass", ppt.get("fileCode").toString());
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
                        java.io.InputStream ins = new java.io.ByteArrayInputStream(media.getStringData().getBytes());
                        java.io.OutputStream out = new java.io.FileOutputStream(file);
                        byte[] buf = new byte[1024 * 10 * 100];
                        int len;
                        while ((len = ins.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }
                        out.close();
                        ins.close();

//                        Reader s = media.getReaderData();
//                        Files.copy(file, s, null);
//                        Files.close(s);
                    }
                    filePpt.put("fileSize", file.length());
                    filePpt.put("fileMd5", com.ourway.base.utils.TextUtils.getFileMD5String(file));
                    //调用存储接口
                    String owid = FileDataUtil.saveFiles(filePpt);
                    if (null == owid) {
                        AlterDialog.alert("上传失败");
                    } else {
                        filePpt.put("owid", owid);
                        addNewFile(filePpt);
                    }

                }
            }
        } else {
            if (event.getTarget().getId().equals("closeBtn")) {
                detach();
            }
            if (event.getTarget().getId().equals("downBtn")) {
                fileDown();
            }
            if (event.getTarget().getId().equals("refreshBtn")) {
                initData();
            }
            if (event.getTarget().getId().equals("removeBtn")) {
                remove();
            }
            if (event.getTarget().getId().equals("saveBtn")) {
                save();
            }
        }
    }


}
