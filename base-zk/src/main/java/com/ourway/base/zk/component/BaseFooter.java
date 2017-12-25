package com.ourway.base.zk.component;

import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentSer;
import com.ourway.base.zk.utils.PageUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Footer;
import org.zkoss.zul.Timebox;

import java.util.Map;

/**
 * Created by Administrator on 2017/4/18.
 */
public class BaseFooter extends Footer {
    private String property;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public void fillValue(Map<String,Object> data){
        if(null==data||null==data.get(property))
            setLabel("  ");
        else{
            if(null!=data.get(property)){
                setLabel(data.get(property).toString());
            }else
                setLabel("  ");
        }
    }

}
