package com.ourway.base.zk.component;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentFileSer;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.service.ComponentSer;
import com.ourway.base.zk.sys.attachfile.ImportFileAction;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.SingleFileUploadAction;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zul.Button;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Menuitem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/4/18.
 */
public class BaseMenuitem extends Menuitem implements ComponentSer<String> {
    private String property;
    private Integer cols;//所占据的列
    private Map<String, Object> externObject;//扩展属性
    private PageControlVO pgvo;//当前控件的属性对象

    public Map<String, Object> getExternObject() {
        return externObject;
    }

    public void setExternObject(Map<String, Object> externObject) {
        this.externObject = externObject;
    }

    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
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

    }

    @Override
    public String getPageValue() {
        return "";
    }

    @Override
    public void setPageValue(Object obj) {

    }

    @Override
    public void addEventListiner(PageControlVO pageControlVO, BaseWindow win) {
        PageUtils.doAddEvent(this, win, pageControlVO);
    }


    @Override
    public void doEvent(Object objectMap, BaseWindow win, Event event) {
        if (event.getTarget() instanceof BaseMenuitem) {
            BaseMenuitem menuitem = (BaseMenuitem) event.getTarget();
            if (null == menuitem.getAttribute("param"))
                return;
            Map<String, Object> param = (Map<String, Object>) menuitem.getAttribute("param");
            if ("1".equalsIgnoreCase(param.get("type").toString())) {
                fileDown(param);
            }
            if ("2".equalsIgnoreCase(param.get("type").toString())) {
                //打开上传界面
                try {
                    ImportFileAction winEdit = (ImportFileAction) Executions.createComponents("/template/importFile.do", null, param);
                    winEdit.setClosable(true);
                    winEdit.doModal();
                    if (winEdit.isClosePage) {
                        doBackEvent(param.get("event").toString(), winEdit.getFilePpt(), win, param.get("gridId").toString());
                    }
                } catch (Exception e) {

                }
            }
            if ("3".equalsIgnoreCase(param.get("type").toString())) {
                //执行自定义事件
                try {
                    Class claz = Class.forName(param.get("event").toString());
                    ComponentListinerSer componentListinerSer = (ComponentListinerSer) claz.newInstance();
                    PageControlVO pgvo = new PageControlVO();
                    if (!TextUtils.isEmpty(param.get("data")))
                        pgvo.setKjAttribute(param.get("data").toString());
                    componentListinerSer.doAction(win, event, pgvo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }

    //上传成功的返回
    public void doBackEvent(String clzName, Map<String, Object> filePpt, BaseWindow win, String dataList) {
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

    void fileDown(Map<String, Object> fileppt) {
        FileInputStream inputStream;
        try {
            File dosfile = new File(ZKConstants.SYSTEM_FILE_PATH + fileppt.get("link"));
            if (dosfile.exists()) {
                inputStream = new FileInputStream(dosfile);
                Filedownload.save(inputStream, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "importTemplate");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reset() {

    }

    @Override
    public void setComponentDisable(boolean flag) {
        setDisabled(flag);
    }
}
