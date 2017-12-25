package com.ourway.base.zk;

/**
 * <p>方法 BaseConstants : <p>
 * <p>说明:Base端公共常量</p>
 * <pre>
 * @author JackZhou
 * @date 2017/4/17 21:28
 * </pre>
 */
public class BaseConstants {
    //borderlayout的高度，当前窗口高度减去
    public static final int BORDER_LAYOUT_HEIGHT = 134;
    public static final int BORDER_LAYOUT_HEIGHT_TREE=175;
    public static final String INTERCEPT_SUFFIX = ".do";
    public static final String UTF8 = "UTF-8";
    public static final String GBK = "gbk";
    public static final String ERROR_OK = "success";
    public static final String ERROR_FAIL = "接口调用失败";
    /*当前应用的appkey*/
    public static String APP_KEY = "webzk001";
    /*当前应用的远程访问地址*/
    public static String URL_HOST = "http://127.0.0.1:8080/webApi/";
//    public static String URL_HOST = "http://iteeio.zust.edu.cn/ourway/";
    /*当前应用的远程访问地址*/
    public static String APP_SECTRECT = "1234567890";

    public static String WEBSOCKET_URL = "localhost:8080/webApi/echo.do";
    public static String WEBSOCKET_SOCKET_URL = "localhost:8080/webApi/sockjs.do";

}
