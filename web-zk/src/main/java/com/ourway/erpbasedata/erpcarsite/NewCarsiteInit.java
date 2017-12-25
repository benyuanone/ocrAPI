package com.ourway.erpbasedata.erpcarsite;

import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.service.ComponentInitSer;
import com.ourway.base.zk.service.impl.APIPageInitSer;
import com.ourway.base.zk.service.impl.APIPageInitWithTreeSer;
import com.ourway.base.zk.utils.DataBinder;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.syszk.utils.TextUtils;
import org.zkoss.zk.ui.Component;

import java.util.Map;

/*<p>方法 NewCarsiteInit : <p>
*<p>说明:新增装车台初始化</p>
*<pre>
*@author zhou_xtian
*@date 2017-11-07 17:01
</pre>
*/
public class NewCarsiteInit extends APIPageInitWithTreeSer {

    @Override
    public void initPage(BaseWindow window, Map args, PageVO pageVO) {
        super.initPage(window, args, pageVO);
        if (!TextUtils.isEmpty(window.getPpt()) && !TextUtils.isEmpty(window.getPpt().get("tree"))) {
            //左树选择数据后新增，新增的页面中自动填入carsiteId
            Component component = window.getFellowIfAny("mainTableGrid_carsiteId");
            DataBinder.binderToPage("carsiteId", ((Map) (window.getPpt().get("tree"))).get("path"), component);
        }//查看初始化的时候，是否有页面标题传入，如果标题是变量名，则取ppt中的值
        PageUtils.changeWindowTitle(window, args);
    }
}
