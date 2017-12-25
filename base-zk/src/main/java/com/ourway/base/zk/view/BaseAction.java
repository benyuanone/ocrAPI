package com.ourway.base.zk.view;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.utils.DataBinder;
import com.ourway.base.zk.utils.BaseMessagebox;
import com.ourway.base.zk.utils.ZKSessionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ClientInfoEvent;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Filedownload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 所有zk的基本类，集成zk中通用的一下方法
 */
public abstract class BaseAction extends BaseWindow implements AfterCompose, Serializable {

    //页面权限
    protected String pageCA;
    protected boolean closePage = false; // 是否关闭新增或者修改成功关闭页面标志
    protected Log info = LogFactory.getLog(BaseAction.class);

    public HttpServletRequest request() {
        HttpServletRequest request = (HttpServletRequest) Executions
                .getCurrent().getNativeRequest();
        return request;
    }

    public HttpSession session() {
        return this.request().getSession();
    }

    public HttpServletResponse response() {
        HttpServletResponse response = (HttpServletResponse) Executions
                .getCurrent().getNativeResponse();
        return response;
    }

    public void showMess(String mess) {
        BaseMessagebox.show(mess, ZKConstants.SYSTEM_MESS, BaseMessagebox.OK| BaseMessagebox.CANCEL, null);
    }

    public void onCreate(CreateEvent event) {

    }

    @SuppressWarnings("unchecked")
    public void afterCompose() {
        if (null == getAttribute(ZKConstants.PAGECA_NAME))
           info.error("当前页面未包含CA元素");
        pageCA = getAttribute(ZKConstants.PAGECA_NAME).toString();
        System.out.println("the pageCA is :" + pageCA);
        if(TextUtils.isEmpty(pageCA))
            showMess("没有pageCa元素");
        //@todo
       /* if (!TextUtils.isEmpty(CoreConstants.checkClass)) {
            try {
                Class checkClass = Class.forName(CoreConstants.checkClass);
                Constructor[] cons = checkClass.getConstructors();
                for (Constructor constructor : cons) {
                    Object obj = constructor.newInstance(this);
                    Method method = checkClass.getMethod("checkPrivelege",
                            String.class);
                    method.invoke(obj, ca);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
*/
    }


    public boolean deleteConfirm(String mess) {
        if (TextUtils.isEmpty(mess))
            mess = ZKConstants.DELETE_CONFIRM_MESS;
        int result = BaseMessagebox.show(mess, ZKConstants.SYSTEM_MESS,
                BaseMessagebox.YES | BaseMessagebox.NO, null);
        if (result == BaseMessagebox.YES)
            return true;
        else
            return false;
    }

    public boolean isClosePage() {
        return closePage;
    }

    public void setClosePage(boolean closePage) {
        this.closePage = closePage;
    }

    /**
     * <p>
     * 关闭新增，修改页面
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: BaseAction.java,v 0.1 Nov 2, 2010 10:21:32 PM Jack Exp $
     */
    public void detch() {
        this.detach();
    }


       /**
     * <p>
     * 初始化用户配置
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: BaseAction.java,v 0.1 2013-4-29 上午11:10:49 Jack Exp $
     */
    @SuppressWarnings("unchecked")
    protected void initUsrConfig() {

    }

    /**
     * <p>
     * 保存系统主题
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: BaseAction.java,v 0.1 2013-4-29 下午08:43:14 Jack Exp $
     */
    protected void saveTheme(String themeName) {

    }

    /**
     * <p>
     * 初始化主题
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: BaseAction.java,v 0.1 2013-4-29 上午11:26:15 Jack Exp $
     */
    protected void initTheme() {
    }

    /**
     * <p>
     * 保存用户行数设置
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: BaseAction.java,v 0.1 2013-4-29 上午11:53:06 Jack Exp $
     */
    protected void saveUserPageSize(String pageSize) {
    }

    /**
     * 系统首个页面的像素
     */
    public void onClientInfo(ClientInfoEvent evt) {
        ZKSessionUtils.setScreenHeight(evt.getDesktopHeight());
        ZKSessionUtils.setScreenWidth(evt.getScreenWidth());
        System.out.println(evt.getScreenWidth());
    }


    /**
    *<p>方法:bindFromPage 从界面把控件的值绑定到map中 </p>
    *<ul>
     *<li> @param ppt map对象</li>
    *<li>@return void  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/4/12 22:20  </li>
    *</ul>
    */
    public void  bindFromPage(Map<String,Object> ppt){
        Set set = ppt.keySet();
        for (Object _obj : set) {
            Component comp = this.getFellowIfAny(_obj.toString());
            DataBinder.bind(_obj.toString(), "", ppt, comp,
                    DataBinder.PAGE_TO_MAP);
        }
    }
}
