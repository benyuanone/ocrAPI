package com.ourway.base.zk.utils;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ZKConstants;
import org.zkoss.zk.ui.util.Clients;

/**<p>方法 AlterDIalog : <p>
*<p>说明:Zk端不同类型的弹出框</p>
*<pre>
*@author JackZhou
*@date 2017/4/24 22:04
</pre>
*/
public class AlterDialog {
    /**
    *<p>方法:alert 警告框 </p>
    *<ul>
     *<li> @param message 警告信息，跟多语言关联</li>
    *<li>@return void  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/4/24 22:05  </li>
    *</ul>
    */
    public static void alert(String message){
        Clients.showNotification(message);
//        BaseMessagebox.show(message, ZKConstants.SYSTEM_MESS, BaseMessagebox.OK, null);
    }

    public static void openAlart(String message){
        BaseMessagebox.show(message, ZKConstants.SYSTEM_MESS, 0, null);
    }

    /**
    *<p>方法:corfirm 警告确认框 </p>
    *<ul>
     *<li> @param message 警告信息</li>
    *<li>@return java.lang.Boolean  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/4/24 22:15  </li>
    *</ul>
    */
    public static Boolean corfirm(String message){
        if(TextUtils.isEmpty(message))
            message = ZKConstants.DELETE_CONFIRM_MESS;
        int result = BaseMessagebox.show(message, ZKConstants.SYSTEM_MESS,
                BaseMessagebox.YES | BaseMessagebox.NO, null);
        if (result == BaseMessagebox.YES)
            return true;
        else
            return false;
    }
}
