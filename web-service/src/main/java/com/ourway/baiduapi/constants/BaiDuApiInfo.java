package com.ourway.baiduapi.constants;

/**
 * Created by D.chen.g on 2017/12/25.
 * 百度orc应用的基本信息，没有的话自己去申请
 */
public class BaiDuApiInfo {
    //设置APPID/AK/SK（不同的应用不一样的）
    public static  String APP_ID = "";
    public static  String API_KEY = "";
    public static  String SECRET_KEY = "";
    public final static String PIC_PNG="PNG";
    public final static String PIC_GIF="GIF";
    public final static String PIC_JPG="JPG";
    public final static String SPILT_POINT=".";
    public final static int BIGEST_SIZE=4096;//最大尺寸
    public final static int SMALLEST_SIZE=15;//最小尺寸
    public final static int FAILED_CODE=0;//最小尺寸
    public final static String TOOLITTLE_HEIGHT="图片高度太小，请大于15px";
    public final static String TOOMUCH_HEIGHT="图片高度太大，请小于4096px";
    public final static String TOOLITTLE_WIDTH="图片宽度太小，请大于15px";
    public final static String TOOMUCH_WIDTH="图片宽度太大，请小于4096px";
    public final static String SUCCESS_RESULT="ok";
    public final static String NODATA_RESULT="NO DATA RESPONE";
    public final static String DEAL_EXCEPTION="识别异常";
    public final static String NOT_PICTURE="不是图片文件";
    public final static String NO_FILES="图片不存在";
    public static  String TOKEN = "24.eb2c1d824a4fd13b4fbda27c84ca5b02.2592000.1517731617.282335-10586457";
}
