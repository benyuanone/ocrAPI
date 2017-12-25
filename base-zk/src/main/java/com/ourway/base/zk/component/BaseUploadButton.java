package com.ourway.base.zk.component;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.service.ComponentSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.PageUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;

import java.util.Map;

/**
 * Created by Administrator on 2017/4/18.
 */
public class BaseUploadButton extends BaseButton {
    public BaseUploadButton(){
        super();
        setUpload("true,maxsize=-1,multiple=true");
    }
}
