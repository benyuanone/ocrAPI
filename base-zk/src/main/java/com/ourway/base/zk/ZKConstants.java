package com.ourway.base.zk;

import org.zkoss.zul.Bandbox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/27.
 */
public class ZKConstants {
    public static String SYSTEM_MESS = ""; // 系统提示信息
    public static String SYSTEM_MESS_ICON = ""; // 系统提示logo
    public static String DELETE_CONFIRM_MESS = "确定删除这些记录,删除后数据不可恢复!";//删除确认信息
    public static List<Bandbox> SYSTEM_BANDBOX = new ArrayList<Bandbox>();//页面绑定bandbox信息
    public static String checkclass = "";
    public final static String PAGECA_NAME = "caname"; //页面权限
    public final static int PAGE_SIZE = 20;
    /*几行查询条件后隐藏*/
    public final static int QUERY_ROW_NUM = 2;
    /*zk用户的session名称*/
    public final static String ZKUSER_SESSION = "zkUser";
    public final static String ZKHEIGHT_SESSION = "zkScreenHeight";
    public final static String ZKWIDTH_SESSION = "zkScreenWidth";
    /*表格中的默认序号*/
    public final static String GRID_INDEXNO = "序号";
    /*同一个控件的label显示sytle样式*/
    public final static String DIV_COMP_STYLE_LABEL = "text-align:right;padding-right:2px;height:30px;";
    public final static String DIV_COMP_STYLE_CONTROL = "text-align:left;padding-left:2px;";
    /*必填Label颜色*/
    public final static String DIV_COMP_REQUIRED_LABEL = "color:red";
    /*输入框的样式*/
    public final static String DIV_COMP_COMPONENT_CSS = "form-control input-sm";
    public final static String DIV_COMP_DATEBOX_CSS = " ";
    /*默认的按钮样式*/
    public final static String DIV_BUTTON_DEFAULT_CSS = "btn-primary";
    /*表单中单行的样式*/
    public final static String DIV_ROW_DEFAULT_CSS = "row form-horizontal";
    /*默认的分组*/
    public final static String DIV_BUTTON_GROUP_DEFAULT_CSS = "btn-group ";
    /*两个连续的按钮分组相隔样式*/
    public final static String DIV_GROUP_SEPERATE_STYLE = "padding-left:3px; ";
    /*默认panel显示的样式*/
    public final static String PANEL_DEFAULT_CSS = "panel-primary";
    /*默认grid中单元格的样式*/
    public final static String GRID_INLINE_DEFAULT_TEXTBOX_CSS = "height:100%;width:100%;border:0px;background-color: rgba(0,0,0,0);";
    public final static String GRID_INLINE_EDIT_CSS = "";
    /*单元格编辑状态*/
    public final static String GRID_INLINE_EDITING_CSS = "warning";
    public final static String GRID_INLINE_EDITING_SUCESS_CSS = "sucess";
    /*grid内部编辑的样式名称*/
    public final static String GRID_INLINE_EDITING_COMPONENT_CSS = "inlineEdit";
    public final static String GRID_INLINE_EDITING_DATEBOX_CSS = "inlineEdit_Datebox";
    /*grid中表头最短宽度*/
    public final static int GRID_MIN_HEADER_WIDTH = 60;


    /*系统文件存储路径*/
    public  static String SYSTEM_FILE_PATH = "E:\\workspace\\devlop2017\\owerp\\out\\artifacts\\web_zk_Web_exploded\\files\\";
    /*报表中的图片，logo图片*/
    public  static String REPORT_IMG_PATH = "D:\\\\img_logo.png";
    /*系统文件存储路径*/
    public static String SQL_PATH = "E:\\workspace\\devlop2017\\owerp\\out\\artifacts\\web_zk_Web_exploded\\files\\";
    /*文件访问路径*/
    public static String SYSTEM_FILE_URL = "http://localhost:8080/webZk/files/";
    /*创建window时候，减去的宽度*/
    public static int SYSTEM_WINDOW_WIDTH = 218;
    public static int SYSTEM_WINDOW_CUSTMAXWIDTH = 60;

    /*label标签的的提示样式名称*/
    public final static String LABEL_POPUP_CSS = "labelPopup";
    public final static String LABEL_POPUP_IMG_CSS = "labelImgCss";

}
