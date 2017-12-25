package com.ourway.syszk.login;

import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.BaseListbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.EmployVO;
import com.ourway.base.zk.utils.*;
import com.ourway.base.zk.utils.data.DicUtil;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.LoginUtil;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ClientInfoEvent;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

import java.util.List;
import java.util.Map;

/**
 * <p>方法 LoginAction : <p>
 * <p>说明:用户登录和权限处理</p>
 * <pre>
 * @author JackZhou
 * @date 2017/4/23 14:02
 * </pre>
 */
public class LoginAction extends BaseWindow {


    /**
    *<p>方法:check 用户登录校验 </p>
    *<ul>
     *<li> @param  TODO</li>
    *<li>@return void  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/5/3 22:44  </li>
    *</ul>
    */
    public void check() {

        bindAll2Ppt(true);
        EmployVO _vo =ZKSessionUtils.getZkUser();
        if(null==_vo){
            //进行登录判断
            _vo = LoginUtil.login(ppt);
            if(null==_vo){
                AlterDialog.alert("用户名或者密码错误，登录不成功");
                return;
            }else{
                System.out.println(_vo.getSessionId());
                ZKSessionUtils.setZkUser(_vo);
                //调用成功
                Executions.sendRedirect("/applications/index.do?"+System.currentTimeMillis());
            }
        }else{
            //已经登录过，不需要重复调用接口
            Executions.sendRedirect("/applications/index.do?"+System.currentTimeMillis());
        }
    }

    //设置界面的宽度和高度
    public void onClientInfo(ClientInfoEvent evt) {
        ZKSessionUtils.setScreenHeight(evt.getDesktopHeight());
//        ZKSessionUtils.setScreenHeight(evt.getScreenHeight());
        ZKSessionUtils.setScreenWidth(evt.getScreenWidth());
    }

    private void initLanguage(){
        List<Map<String, Object>> datas = DicUtil.listDic(9,"b.dicVal1");
        BaseListbox language = (BaseListbox)getFellowIfAny("loginWin_language");
        language.getChildren().clear();
        int index = 0;
        for(Map<String,Object> data:datas){
            Listitem item = new Listitem();
            item.setLabel(data.get("dicVal2").toString());
            item.setValue(data.get("dicVal1").toString());
            if(index ==0 )
                item.setSelected(true);
            index ++;
            item.setParent(language);
        }
    }

    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        initLanguage();
    }

}
