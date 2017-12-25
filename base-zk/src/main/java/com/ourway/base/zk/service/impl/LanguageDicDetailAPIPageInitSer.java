package com.ourway.base.zk.service.impl;

import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.TextUtils;
import com.ourway.base.zk.utils.data.DicUtil;
import org.zkoss.zul.Listitem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*<p>方法 LanguageDicDetailAPIPageInitSer : <p>
*<p>说明:多语言字典管理专用初始化类</p>
*<pre>
*@author zhou_xtian
*@date 2017-10-18 16:36
</pre>
*/
public class LanguageDicDetailAPIPageInitSer extends APIPageInitWithTreeSer {

    @Override
    public void initPage(BaseWindow window, Map args, PageVO pageVO) {
        super.initPage(window, args, pageVO);
        //多语言字典详情新增fid下拉框处理
        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
        Map<String, Object> data = new HashMap<String, Object>();
        //添加下拉框基础值
        data.put("owid", -1);
        data.put("code", -1);
        data.put("dicVal1", "顶层");
        datas.add(data);
        //添加type对应字典下拉框值
        int type = -1;
        if (!TextUtils.isEmpty(window.getPpt().get("type"))) {
            type = Integer.parseInt(window.getPpt().get("type").toString());
        } else if (!TextUtils.isEmpty(window.getPpt().get("tree"))) {
            type = Integer.parseInt(((Map) (window.getPpt().get("tree"))).get("path").toString());
        }
        datas.addAll(DicUtil.listLanguageDic(type, ""));
        for (Map<String, Object> _data : datas) {
            String label = _data.get("dicVal1").toString() + "(" + _data.get("code").toString() + ")";
            String value = _data.get("owid").toString();
            Listitem item = new Listitem(label, value);
            item.setParent(window.getFellowIfAny("mainTableGrid_fid"));
        }
        //查看初始化的时候，是否有页面标题传入，如果标题是变量名，则取ppt中的值
        PageUtils.changeWindowTitle(window, args);
    }
}
