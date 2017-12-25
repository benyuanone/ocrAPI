package com.ourway.base.zk.service;

import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;

/**<p>接口 ComponentInitSer.java : <p>
*<p>说明：控件初始化方法</p>
*<pre>
*@author JackZhou
*@date 2017/5/30 11:16
</pre>
*/
public interface ComponentInitSer {

    /**
    *<p>方法:doAction 传入当前window和zk事件的event进行事件处理 </p>
    *<ul>
     *<li> @param window 当前的window</li>
     *<li> @param event 事件</li>
     *<li> @param event 控件参数配置</li>
    *<li>@return void  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/5/23 9:23  </li>
    *</ul>
    */
    void doAction(BaseWindow window,Component component, PageControlVO pageControlVO);

}
