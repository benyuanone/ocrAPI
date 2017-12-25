package com.ourway.base.zk.component;

import com.ourway.base.zk.models.FilterModel;
import com.ourway.base.zk.models.PageLayoutVO;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zul.Row;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/18.
 */
public abstract class BaseBandboxWindow extends BaseWindow {

    @Override
    public void onCreate(CreateEvent event) {
        super.onCreate(event);
    }

    //获取bandbox基本属性
    public abstract PageLayoutVO getBandboxProperty(String pageCa);

    /**
     * <p>方法:filterByKey 根据传入的关键字查询 </p>
     * <ul>
     * <li> @param key 关键字</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/8/8 0:18  </li>
     * </ul>
     */
    public abstract  List<Map<String,Object>>  filterByKey(String key);

    /**
    *<p>方法:filterByModel default的时候进行调用 </p>
    *<ul>
     *<li> @param model TODO</li>
    *<li>@return java.util.Map<java.lang.String,java.lang.Object>  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/8/11 14:07  </li>
    *</ul>
    */
    public abstract  List<Map<String,Object>> filterByModel(FilterModel model);

    public abstract List<Row> doGetGridRow();


}
