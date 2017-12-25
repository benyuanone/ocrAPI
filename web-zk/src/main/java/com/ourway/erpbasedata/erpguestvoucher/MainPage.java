package com.ourway.erpbasedata.erpguestvoucher;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.component.*;
import com.ourway.base.zk.models.*;
import com.ourway.base.zk.service.TreeListinerSer;
import com.ourway.base.zk.utils.*;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.PageDataUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*<p>方法 MainPage : <p>
*<p>说明:模板定义文件</p>
*<pre>
*@author zhou_xtian
*@date 2017-11-20 14:20
</pre>
*/
public class MainPage extends BaseWindow {
    protected Log info = LogFactory.getLog(MainPage.class);
    private Borderlayout borderlayout;
    private West westLayout;
    private BaseTree tree;
    private Treeitem rootItem;
    private Treecell rootCell;
    private Treeitem selItem; //选中的节点
    private Treechildren treeChild;
    private Div buttonGrid;
    private BaseTextbox searchTxt;//查询条件

    private Map<String, PageControlVO> pageControlVOMap = new HashMap<String, PageControlVO>();
    /*临时变量，用来存储控件组合的时候问题*/

    private static final String GET_FILES_URL="erpGuestApi/showAllFiles";
    private static final String BIG_IMAGE_STYLE="height:350px;vertical-align: middle;";
    private static final String BIG_FILE_STYLE="height:100px;vertical-align: middle;";
    private static final String SMALL_IMAGE_STYLE_BLUR="height:80px;";
    private static final String SMALL_IMAGE_STYLE_FOCUS="height:100px;border:solid 3px red";


    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        borderlayout = (Borderlayout) getFellowIfAny("bdLayout");
        westLayout = (West) getFellowIfAny("westLayout");
        tree = (BaseTree) getFellowIfAny("tree");
        borderlayout.setHeight((ZKSessionUtils.getScreenHeight() - 140) + "px");
        tree.setHeight((ZKSessionUtils.getScreenHeight() - 180) + "px");
        rootItem = (Treeitem) getFellowIfAny("rootItem");
        rootItem.setValue(null);
        treeChild = (Treechildren) getFellowIfAny("treeChild");
        rootCell = (Treecell) getFellowIfAny("rootCell");
        searchTxt = (BaseTextbox) getFellowIfAny("searchTxt");
        searchTxt.addEventListener(Events.ON_OK, this);

        buttonGrid = (Div) getFellowIfAny("buttonGrid");
        initTree();
        loadTreeData();//加载树
        initButtonGrid();
        this.addEventListener(Events.ON_CTRL_KEY,this);
    }

    private void initTree() {
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "tree");
        if (!TextUtils.isEmpty(data.getControlParentId()))
            tree.setProperty(data.getControlParentId());
        if (!TextUtils.isEmpty(data.getControlPath()))
            tree.setValProperty(data.getControlPath());
        if (!TextUtils.isEmpty(data.getControlClass()))
            tree.setFilterType(data.getControlClass());
        if (!TextUtils.isEmpty(data.getControlLabel())) {
            westLayout.setTitle(I18nUtil.getLabelContent(data.getControlLabel()));
            rootCell.setLabel(I18nUtil.getLabelContent(data.getControlLabel()));
        }
        if (null == data.getLayOutComponents() || null == data.getLayOutComponents().get("1")) {
            AlterDialog.alert("未配置树相关的配置，请完善");
            return;
        }
        List<PageControlVO> objsData = data.getLayOutComponents().get("1");
        for (PageControlVO pgvo : objsData) {
            pageControlVOMap.put(pgvo.getKjAttribute(), pgvo);
        }

        //判断tree所包含的事件
        if (null != pageControlVOMap.get("onClickTree") && !TextUtils.isEmpty(pageControlVOMap.get("onClickTree").getLayoutComponent().getEvnetFormula())) {
            tree.setAttribute("onClickTree", true);
        }
    }

    @Override
    public void loadTreeData() {
        if (null == pageControlVOMap.get("loadTree"))
            return;
        PageControlVO vo = pageControlVOMap.get("loadTree");
        if (TextUtils.isEmpty(vo.getLayoutComponent().getEvnetFormula()))
            return;
        try {
            Object _obj = Class.forName(vo.getLayoutComponent().getEvnetFormula()).newInstance();
            TreeListinerSer compAction = (TreeListinerSer) _obj;
            compAction.doAction(this, null, tree, rootItem, vo);
        } catch (Exception e) {
            e.printStackTrace();
            AlterDialog.alert(vo.getLayoutComponent().getEvnetFormula() + "执行错误");
        }
    }

    //初始化按钮区域
    private void initButtonGrid() {
        //此功能是页面中的功能按钮，包括新增，删除，拷贝等操作
        PageLayoutVO data = PageDataUtil.deteailControl(getPageCA(), "buttonGrid");
        boolean showFlag = null == data || (null != data.getControlIsshow() && data.getControlIsshow() == 1);
        if (showFlag) {
            buttonGrid.setVisible(false);
            return;
        }

        buttonGrid.getChildren().clear();//清除下面的控件
        //2.获取下面的子控件
        if (null != data.getLayOutComponents() && data.getLayOutComponents().size() > 0) {
            //按照顺序，添加rowgetLayOutComponents
            for (int index = 1; index <= data.getLayOutComponents().size(); index++) {
                List<PageControlVO> subComps = data.getLayOutComponents().get(index + "");
                Div div = ComponentUtils.doCreateRowDiv(ZKConstants.DIV_ROW_DEFAULT_CSS);
                div.setId("buttonGrid_" + index);
                div.setParent(buttonGrid);
                getTemplateUtils().doButtonRowComponents(subComps, div, data, this);
            }
        }
    }

    @Override
    public void onEvent(Event event) throws Exception {
        super.onEvent(event);
        if (event.getName().equalsIgnoreCase(Events.ON_CLICK) && event.getTarget() instanceof Treecell) {
            selItem = (Treeitem) event.getTarget().getParent().getParent();
            PageControlVO vo = pageControlVOMap.get("onClickTree");
            //点击树的owid
            String owid = ((TreeVO)selItem.getValue()).getPath();
            //设置owid
            Map<String,Object> ppt = getPpt();
            ppt.put("owid",owid);
            setPpt(ppt);
            //清除历史数据
            Div fileList = (Div) getFellowIfAny("fileList");
            if (fileList.getChildren().size()>0 && fileList.getChildren().get(0) instanceof Span) {
                ((Span)fileList.getChildren().get(0)).getChildren().clear();
            }
            fileList.getChildren().clear();
            //获取所有文件
            List<Map<String,Object>> result = getFiles(owid);
            Image bigImage = (Image) getFellowIfAny("bigImage");
            bigImage.setVisible(false);
            if (result != null && result.size() > 0) {
                bigImage.setVisible(true);;
                setFileLabel(bigImage,result.get(0));

                Span span = new Span();
                span.setStyle("width:318px;margin:0 auto;"+SMALL_IMAGE_STYLE_BLUR);
/*                BaseButton leftButton = new BaseButton();
                leftButton.setLabel("上一张");
                leftButton.setStyle("float:left;height:80px;");
                span.appendChild(leftButton);*/
                boolean isFirstImage = true;
                for (Map<String,Object> _map:result) {
                    Image image = new Image();
                    setImageByApi(image,SMALL_IMAGE_STYLE_BLUR,_map);
                    if (isFirstImage) {
                        //第一张图片默认选中
                        image.setStyle(SMALL_IMAGE_STYLE_FOCUS);
                        isFirstImage = false;
                    }
                    image.addEventListener(Events.ON_CLICK,this);
                    span.appendChild(image);
                }
/*                BaseButton rightButton = new BaseButton();
                rightButton.setLabel("下一张");
                rightButton.setStyle("float:right;height:80px;");
                span.appendChild(rightButton);*/
                fileList.appendChild(span);
            }

            TreeUtils.treeAction(this, tree, selItem, vo);
        }
        if (event.getTarget().getId().equalsIgnoreCase("searchTxt") && event.getName().equalsIgnoreCase(Events.ON_OK)) {
            //对节点进行过滤
            TreeUtils.filterTree(tree, searchTxt.getText());
        }
        if(event.getTarget() instanceof Image){
            //图片点击
            //清除格式
            Div fileList = (Div) getFellowIfAny("fileList");
            for (Component component:((Span)fileList.getChildren().get(0)).getChildren()) {
                if (component instanceof Image) {
                    Image _image = (Image) component;
                    _image.setStyle(SMALL_IMAGE_STYLE_BLUR);
                }
            }
            //新增格式及图片
            Image selImage = (Image) event.getTarget();
            selImage.setStyle(SMALL_IMAGE_STYLE_FOCUS);
            Image bigImage = (Image) getFellowIfAny("bigImage");
            bigImage.setSrc(selImage.getSrc());
            bigImage.setContext(selImage.getContext());
            setFileLabel(bigImage,JsonUtil.jsonToMap(selImage.getContext()));
        }
    }

    private List<Map<String,Object>> getFiles(String owid) {
        //获取所有文件
        Map<String,Object> requestMap= new HashMap<String,Object>();
        requestMap.put("owid",owid);
        PublicData publicData = new PublicData();
        publicData.setMethod(GET_FILES_URL);
        publicData.setData(JsonUtil.toJson(requestMap));
        try {
            ResponseMessage mess = JsonUtil.getResponseMsg(HttpUtils.doPost(publicData,"UTF-8",false));
            if (null != mess && mess.getBackCode() == 0 &&!TextUtils.isEmpty(mess.getBean())) {
                return (List<Map<String,Object>>) mess.getBean();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    //根据api设置图片格式、路径、编号等其他属性
    private void setImageByApi(Image image,String style,Map<String,Object> fileParams) {
        image.setSrc(ZKConstants.SYSTEM_FILE_URL+fileParams.get("showImage").toString());
        image.setStyle(style);
        Map<String,Object> imageAttribute = new HashMap<String,Object>();
        //所有业务数据放在image的context里
        imageAttribute.put("owid",fileParams.get("owid"));
        imageAttribute.put("showImage",fileParams.get("showImage"));
        imageAttribute.put("filePath",fileParams.get("filePath"));
        imageAttribute.put("fileLabel",fileParams.get("fileLabel"));
        imageAttribute.put("ifImage",fileParams.get("ifImage"));
        image.setContext(JsonUtil.toJson(imageAttribute));
    }

    private void setFileLabel(Image image,Map<String,Object> params) {
        String style = BIG_IMAGE_STYLE;
        Div fileLabelDiv = (Div)getFellowIfAny("fileLabel");
        if (!TextUtils.isEmpty(image.getContext())) {
            fileLabelDiv.setContext(JsonUtil.jsonToMap(image.getContext()).get("fileLabel").toString());
        } else {
            fileLabelDiv.setContext(params.get("fileLabel").toString());
        }
        //image.getParent().getChildren().clear();
        //如果本身存储的就是图片
        if (!(boolean)params.get("ifImage")) {
            style = BIG_FILE_STYLE;
            image.getParent().appendChild(fileLabelDiv);
        }
        setImageByApi(image,style,params);
    }
}
