package com.ourway.base.zk.service;

import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treeitem;

/**<p>接口 ComponentListinerSer.java : <p>
*<p>说明：统一的事件处理方法</p>
*<pre>
*@author JackZhou
*@date 2017/5/23 9:22
</pre>
*/
public interface TreeListinerSer {

    /**
    *<p>方法:doAction 传入当前window和zk事件的event进行事件处理 </p>
    *<ul>
     *<li> @param window 当前的window</li>
     *<li> @param event 事件</li>
    *<li>@return void  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/5/23 9:23  </li>
    *</ul>
    */
    void doAction(BaseWindow window, Event event, BaseTree tree, Treeitem treeitem, PageControlVO pgvo);

}
