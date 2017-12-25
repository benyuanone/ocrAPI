package com.ourway.base.zk.service;

import com.ourway.base.zk.component.BaseWindow;

/**
 * <p>接口 ComponenInitSer.java : <p>
 * <p>说明：通用的初始化接口，用于定义空间的初始化</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/20 0:28
 * </pre>
 */
public interface ComponenInitSer<T> {

    /**
     * <p>方法:initComponent 传入当前窗口的实例，进行初始化 </p>
     * <ul>
     * <li> @param window 当前控件所在的窗口</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/20 0:29  </li>
     * </ul>
     */
    void initComponent(BaseWindow window);

}
