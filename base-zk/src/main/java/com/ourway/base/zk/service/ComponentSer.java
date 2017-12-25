package com.ourway.base.zk.service;

import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;

import java.util.Map;

/**
 * <p>接口 ComponentSer.java : <p>
 * <p>说明：控件调用的接口</p>
 * <pre>
 * @author JackZhou
 * @date 2017/4/15 12:36
 * </pre>
 */
public interface ComponentSer<T> {

    /**
     * <p>方法:init 初始化控件 </p>
     * <ul>
     * <li> @param pageObj 页面当前对象</li>
     * <li> @param property 当前对象中的属性</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/4/17 20:56  </li>
     * </ul>
     */
    void init(Map<String, Object> pageObj, String property);

    /**
     * <p>方法:init 传入页面布局对象，进行初始化 </p>
     * <ul>
     * <li> @param pageLayoutControlVO 页面布局对象</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/10 1:15  </li>
     * </ul>
     */
    void init(PageControlVO pageControlVO, BaseWindow win);

    /**
     * <p>方法:getPageValue 返回当前控件的值 </p>
     * <ul>
     * <li>@return T  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/4/17 21:00  </li>
     * </ul>
     */
    T getPageValue();

    /**
    *<p>方法:setPageValue 给指定的控件赋值 </p>
    *<ul>
     *<li> @param obj TODO</li>
    *<li>@return void  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/12/22 0022 22:44  </li>
    *</ul>
    */
    void setPageValue(Object obj);

    /**
     * <p>方法:addEventListiner 根据条件添加事件 </p>
     * <ul>
     * <li> @param pageControlVO TODO</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/16 19:59  </li>
     * </ul>
     */
    void addEventListiner(PageControlVO pageControlVO, BaseWindow win);

    void doEvent(Object objectMap, BaseWindow win, org.zkoss.zk.ui.event.Event event);

    /**
     * <p>方法:reset 重置 </p>
     * <ul>
     * <li> @param  TODO</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/17 23:08  </li>
     * </ul>
     */
    void reset();

    //集中焦点
    void focus();

    //获取当前控件的pgvo
    public abstract PageControlVO getPgvo();
    //设置控件是否有效
    public abstract void setComponentDisable(boolean flag);
}
