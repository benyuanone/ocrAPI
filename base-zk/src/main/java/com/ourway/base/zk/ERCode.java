package com.ourway.base.zk;

import org.zkoss.zul.Bandbox;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>方法 ERCode : <p>
 * <p>说明系统内置出错提示</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/2 15:35
 * </pre>
 */
public class ERCode {
    /* 通用提示 */
    /*操作成功提示*/
    public static String OPERATE_SUCCESS = "public.sys.common.operateSucess";
    /*操作失败提示*/
    public static String OPERATE_FAILED = "public.sys.common.operateFailed";

    /*菜单操作*/
    /*必选相关联的page页面*/
    public static String MENU_PAGE_ID_SEL = "public.sys.menu.pageIdRequired";
    public static String MENU_ITEM_NOSEL = "public.sys.menu.noSelItem";
    public static String MENU_COPY_NOCOPY = "public.sys.menu.noCopy";
    /*pageCa不存在出错提示*/
    public static String INDEX_PAGE = "首页";
    public static String CA_NO = "pageCa不存在";
    public static String LABEL_KEY_NO = "多语言不存在";
    public static final String NO_OWID = "不存在主键";
    public static final String NO_API = "未设置API";
    public static final String NO_GRID = "未设置GRID";
    public static final String NO_SUCCESS = "未设置成功提示";
    public static final String NO_FAIL = "未设置失败提示";
    public static final String MUL_SELECT = "只能选择一条";
    /*排序的多语言标签*/
    public static final String GROUP = "group";
    public static final String SELECTALL = "selectAll";
    public static final String NOSELECTALL = "noselectAll";
    /*排序的多语言标签*/
    public static final String UNGROUP = "ungroup";
    /*升序*/
    public static final String ASCENDING = "Ascending";
    /*降序*/
    public static final String DESCENDING = "Descending";
    /*参数不存在*/
    public static final String NO_PARAM = "参数传入错误";
    public static final String GRID_NOSEL = "未选中值";
    /*下拉框，bandbox默认请选择*/
    public static final String LISTBOX_DEFAULT = "请选择";
    public static final String BANDBOX_DEFAULT = "请输入关键字";
    /*grid刷新成功*/
    public static final String GRID_REFRESH_SUCCESS = "刷新成功";
    /*文件处理中心*/
    public static final String FILE_CENTER_CLOSE = "CLOSE";
    public static final String FILE_CENTER_SAVE = "SAVE";
    public static final String FILE_CENTER_UPLOAD = "上传";
    public static final String FILE_CENTER_DOWN = "DOWN";
    public static final String FILE_CENTER_REFRESH = "REFRESH";
    public static final String FILE_CENTER_REMOVE = "DELETE";
    public static final String FILE_CENTER_TITLE = "FILECENTER";
    public static final String FILE_CENTER_DRAP_TIP = "PUTFILEHERE";
    public static final String FILE_CENTER_FILE_NAME = "FILE_NAME";
    public static final String FILE_CENTER_FILE_LABEL = "FILE_LABEL";
    public static final String FILE_CENTER_FILE_SIZE = "FILE_SIZE";
    public static final String FILE_CENTER_FILE_MD5 = "FILE_MD5";
    public static final String FILE_CENTER_FILE_PATH = "FILE_PATH";
    public static final String FILE_CENTER_FILE_EXTION = "FILE_EXTION";
    public static final String FILE_CENTER_FILE_RANDON = "FILE_RANDON";
    public static final String FILE_CENTER_FILE_DOWN = "FILE_DOWN";
    public static final String FILE_CENTER_MEMO = "MEMO";
    public static final String FILE_CENTER_CREATETIME = "CREATETIME";
    public static final String FILE_CENTER_NOFILE = "请选择一个文件进行下载";
    public static final String FILE_CENTER_DELETE_TIPS = "请确定需要删除选中的文件";
    public static final String FILE_CENTER_UPDATE_TIPS = "更新成功";
    public static final String FILE_CENTER_TEXT = "个附件";
    public static final String FILE_IMPORT_TITLE = "导入数据";
    public static final String FILE_NOT_IMAGE = "请上传图片文件";


    /*树节点详细名称*/
    public static final String TREE_ALLNODE_OPEN = "全部展开";
    public static final String TREE_ALLNODE_CLOSE = "全部关闭";
    public static final String TREE_NODE_CLOSE = "关闭本节点";
    public static final String TREE_NODE_OPEN = "展开本节点";
    public static final String TREE_NODE_OPERATE = "节点操作";
    public static final String TREE_NODE_LABEL = "节点名称";
    public static final String TREE_SAVE_BUTTON = "保存";
    public static final String TREE_CLOSE_BUTTON = "关闭";
    public static final String TREE_SAVE_ERROR = "操作失败";
    public static final String TREE_SAVE_NOT = "不能增加节点";
    public static final String TREE_NO_OPERATE = "不能进行操作";
    public static final String TREE_ADD_SUB = "新增子节点";
    public static final String TREE_ADD_SAME = "新增同级节点";
    public static final String TREE_UPDATE_NODE = "修改节点";
    public static final String TREE_REMOVE_NODE = "删除节点";
    public static final String TREE_REMOVE_CONFIRM = "确定要删除节点及其下属节点";
    /*新增子菜单*/
    public static final String TREE_NEW_SUBITEM = "public.sys.tree.newSubNode";
    /*新增同级菜单*/
    public static final String TREE_NEW_SAMELEVELITEM = "public.sys.tree.newSameLevelNode";
    /*删除菜单*/
    public static final String TREE_REMOVE_ITEM = "public.sys.tree.removeNode";
    /*当前节点的子节点*/
    public static final String TREE_MOVE_SUBITEM = "public.sys.tree.moveSubNode";
    /*当前节点前*/
    public static final String TREE_MOVE_ABOVEITEM = "public.sys.tree.moveAboveNode";
    /*当前节点后*/
    public static final String TREE_MOVE_BELOWITEM = "public.sys.tree.moveBlowNode";
    /* tree 刪除节点 */
    public static final String TREE_REMOVE_NODETIP = "public.sys.tree.removeSelNode";

    public static final String FLOW_OBJ_SEL = "请选择业务类";
    public static final String FLOW_QUERY_LABEL = "查询";
    public static final String FLOW_SUB_CLASSNAME = "类名";
    public static final String FLOW_SUB_CLASSDESC = "类说明";

    public static final String ACTION_SUCESS = "执行成功";
    public static final String ACTION_FAIL = "执行失败";

    public static final String INDEX_WAIT_TASK = "项待办事项";
    public static final String INDEX_TASK_CHECK = "任务审批";

    public static final String GRID_FILTER_WINDOW_TITLE = "表格过滤";
    public static final String GRID_FILTER_SEARCH_BUTTON_TITLE = "过滤";
    public static final String GRID_FILTER_CANCER_BUTTON_TITLE = "关闭";

    public static final String COMBOBOX_NO_VALUE = "没有选中值";
    public static final String TREE_NO_VALUE = "没有选中左树节点";


    /*消息的默认标题*/
    public static final String MESSAGE_DEFAULT_TITLE = "一般通知";
    /*表格前面的序号*/
    public static final String GRID_INDEXNO = "序号";
    public static final String GRID_OPERATER = "操作人";
    public static final String GRID_OPERATERTIME = "操作时间";
    public static final String GRID_OPERATERTYPE = "操作类型";
    public static final String GRID_OPERATE_ADD = "新增";
    public static final String GRID_OPERATE_UPDATE = "修改";
    public static final String GRID_OPERATE_DELTE = "删除";
    public static final String GRID_PRIMARY_TABLE = "主表";

    public static final String JASPER_NOT_EXIST = "jasper报表不存在";
    public static final String JASPER_DEFAULT_TITLE = "报表";
    /*当前界面处理修改状态中，确定需要关闭*/
    public static final String TAB_CLOSE_IP = "public.sys.window.colosConfirm";
    /*bandbox*/
    /*bandbox默认提示*/
    public static final String BANDBOX_DEFAULT_TXT = "public.sys.common.bandbox.default";


}
