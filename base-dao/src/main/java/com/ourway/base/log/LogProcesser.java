package com.ourway.base.log;

/**
 * <p>接口 LogProcesser.java : <p>
 * <p>说明：日志拦截器</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/10 22:41
 * </pre>
 */
public interface LogProcesser {
    /**
     * <p>方法:receiveLog4jMsg 处理接收到的log4j消息 </p>
     * <ul>
     * <li> @param msg 消息</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 22:42  </li>
     * </ul>
     */
    void receiveLog4jMsg(String msg);
}
