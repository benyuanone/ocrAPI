package com.ourway.base.zk.template;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.comparator.CompareIndexOrder;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageLayoutVO;
import com.ourway.base.zk.models.PageVO;
import com.ourway.base.zk.service.PageInitSer;
import com.ourway.base.zk.service.impl.APIPageInitSer;
import com.ourway.base.zk.utils.*;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.PageDataUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>方法 ListPageTemplateAction : <p>
 * <p>说明:模板页面</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/8 22:51
 * </pre>
 */
public class DetailOneTableTemplateAction extends BaseWindow {
    protected Log info = LogFactory.getLog(DetailOneTableTemplateAction.class);

    /*操作按钮区域*/
    private Div operateGrid;
    /*主表填写区域*/
    private Div mainTableGrid;
    private Map<String, Component> hboxMap = new HashMap<String, Component>();
    PageVO pageVO = null;
    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        if (null != event.getArg() && event.getArg().size() > 0)
            ppt = (Map<String, Object>) event.getArg();
        operateGrid = (Div) getFellowIfAny("operateGrid");
        mainTableGrid = (Div) getFellowIfAny("mainTableGrid");
        PageDataUtil.detailPageByCa(getPageCA());
        initOperateGrid();
        initMainTableGrid();
        initPage(event);
        reloadButton();
        afterInitPage(event);
    }
    private void afterInitPage(CreateEvent event) {
        if (null != pageVO && !TextUtils.isEmpty(pageVO.getPageInitAfter())) {
            try {
                PageInitSer ser = (PageInitSer) Class.forName(pageVO.getPageInitAfter()).newInstance();
                ser.initPage(this, event.getArg(), pageVO);
                //初始化后绑定到页面中
            } catch (Exception e) {
                AlterDialog.alert("请确认配置信息是否正确");
            }
        }
    }
    private void initPage(CreateEvent event) {
        PageVO pageVO = PageDataUtil.detailPageByCa(getPageCA());
        if (null != pageVO && !TextUtils.isEmpty(pageVO.getPageInit())) {
            try {
                PageInitSer ser = (PageInitSer) Class.forName(pageVO.getPageInit()).newInstance();
                ser.initPage(this, event.getArg(), pageVO);
                if (getWindowType() == 2)
                    bind2Page();
            } catch (Exception e) {
                e.printStackTrace();
                AlterDialog.alert("实现类不存在，请确认配置信息是否正确");
            }
        }

    }


    //只有一行
    private void initOperateGrid() {
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "operateGrid");
        boolean showFlag = null == data || (null != data.getControlIsshow() && data.getControlIsshow() == 1);
        if (showFlag) {
            operateGrid.setVisible(false);
            return;
        }
        operateGrid.getChildren().clear();//清除下面的控件

        //2.获取下面的子控件
        if (null != data.getLayOutComponents() && data.getLayOutComponents().size() > 0) {
            //按照顺序，添加rowgetLayOutComponents
            for (int index = 1; index <= data.getLayOutComponents().size(); index++) {
                List<PageControlVO> subComps = data.getLayOutComponents().get(index + "");
                Div div = ComponentUtils.doCreateRowDiv(ZKConstants.DIV_ROW_DEFAULT_CSS);
                div.setId("operateGrid_" + index);
                div.setParent(operateGrid);
                getTemplateUtils().doButtonRowComponents(subComps, div, data, this);
            }
        }


    }


    //    主表区域
    private void initMainTableGrid() {
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "mainTableGrid");
        boolean showFlag = null == data || (null != data.getControlIsshow() && data.getControlIsshow() == 1);
        if (showFlag) {
            mainTableGrid.setVisible(false);
            return;
        }
        mainTableGrid.getChildren().clear();//清除下面的控件
        if (null != data.getLayOutComponents() && data.getLayOutComponents().size() > 0) {
            List<String> setList = TemplateUtils.doGenOrderForSet(data.getLayOutComponents());
            for (String s : setList) {
                List<PageControlVO> subComps = data.getLayOutComponents().get(s);
                if (null == subComps || subComps.size() <= 0)
                    continue;
                Div div = ComponentUtils.doCreateRowDiv(ZKConstants.DIV_ROW_DEFAULT_CSS + " form-group");
                div.setId("mainTableGrid_" + s);
                div.setParent(mainTableGrid);
                getTemplateUtils().doRowComponents(subComps, div, data, this);
            }
        }
    }


    @Override
    public void onEvent(Event event) throws Exception {
        super.onEvent(event);
        System.out.println(event.getTarget().getClass().getName());
    }

    @Override
    public void reloadPpt(boolean flag) {
        PageVO pageVO = PageDataUtil.detailPageByCa(getPageCA());
        if (null != pageVO && !TextUtils.isEmpty(pageVO.getPageInit())) {
            try {
                PageInitSer ser = (PageInitSer) Class.forName(pageVO.getPageInit()).newInstance();
                ser.initPage(this, parentArgs, pageVO);
                if (flag)
                    bind2Page();
            } catch (Exception e) {

            }
        }
    }
}
