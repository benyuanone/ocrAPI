package com.ourway.erpbasedata.erpguestvoucher;

import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.service.TreeListinerSer;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Treeitem;

/*<p>方法 TreeClickAction : <p>
*<p>说明:客户样张管理左树点击（已在MainPage中实现，此处只保留空文件）</p>
*<pre>
*@author zhou_xtian
*@date 2017-11-09 10:03
</pre>
*/
public class TreeClickAction implements TreeListinerSer {
    @Override
    public void doAction(BaseWindow window, Event event, BaseTree tree, Treeitem treeitem, PageControlVO pgvo) {
    }
}
