package com.ourway.base.zk.service;

import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageLayoutVO;
import com.ourway.base.zk.models.PageVO;

import java.util.Map;

/**
 * <p>接口 PageInitSer.java : <p>
 * <p>说明：进入页面的初始化类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/23 9:21
 * </pre>
 */
public interface InnerGridInitSer {

    /**
     * <p>方法:initPage 传入当前window和前页传入的args进行初始化 </p>
     * <ul>
     * <li> @param window 当前的window</li>
     * <li> @param args 参数</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/23 9:22  </li>
     * </ul>
     */
    void initPage(BaseWindow window, PageLayoutVO layoutVO, Map args, BaseGrid grid);

}
