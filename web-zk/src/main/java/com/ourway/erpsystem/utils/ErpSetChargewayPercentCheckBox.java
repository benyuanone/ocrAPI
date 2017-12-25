package com.ourway.erpsystem.utils;

import com.ourway.base.zk.component.BaseCheckbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.ComponentListinerSer;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.*;
import org.zkoss.zul.Panel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/***
*<p>方法: 点击percent的checkbox进行隐藏细表功能 </p>
*<ul>
 *<li> @param null TODO</li>
*<li>@return   </li>
*<li>@author zhangxinyi </li>
*<li>@date 2017-10-09 16:08  </li>
*</ul>
*/
public class ErpSetChargewayPercentCheckBox implements ComponentListinerSer {

    @Override
    public void doAction(BaseWindow window, Event event, PageControlVO pgvo) {

        Object windowParams = pgvo.getLayoutComponent().getEventDataContent();
        List<Object> paramList = new ArrayList<Object>();
        String url = "";
        Panel dataListGroupboxCaption = (Panel)window.getFellowIfAny("dataListGroupboxCaption");
        //通过checkBox，控制panel的显示
        try {

//            Div dataList = (Div) window.getFellowIfAny("dataList");
            BaseCheckbox cb = (BaseCheckbox)window.getFellowIfAny("mainTableGrid_chargeSplit");
                if(cb.isChecked()){
                    dataListGroupboxCaption.setVisible(true);
                }else{
                    dataListGroupboxCaption.setVisible(false);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
