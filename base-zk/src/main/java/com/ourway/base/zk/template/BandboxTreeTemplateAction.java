package com.ourway.base.zk.template;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.bandbox.TreeBandbox;
import com.ourway.base.zk.component.BaseBandbox;
import com.ourway.base.zk.component.BaseBandboxWindow;
import com.ourway.base.zk.component.BaseGrid;
import com.ourway.base.zk.component.BaseTree;
import com.ourway.base.zk.models.*;
import com.ourway.base.zk.service.TreeListinerSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.ComponentUtils;
import com.ourway.base.zk.utils.TreeUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import com.ourway.base.zk.utils.data.PageDataUtil;
import net.sf.ehcache.search.attribute.KeyObjectAttributeExtractor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.*;

import java.util.ArrayList;
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
public class BandboxTreeTemplateAction extends BaseBandboxWindow {
    protected Log info = LogFactory.getLog(BandboxTreeTemplateAction.class);
    private BaseTree tree;
    private Treeitem rootItem;
    private Treecell rootCell;
    private Menupopup menupopup;
    private Treeitem selItem; //选中的节点
    private Treechildren treeChild;
    private Map<String, PageControlVO> pageControlVOMap = new HashMap<String, PageControlVO>();
    PageLayoutVO bandboxData = null;
    PageLayoutVO treeData = null;

    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        tree = (BaseTree) getFellowIfAny("tree");
        rootItem = (Treeitem) getFellowIfAny("rootItem");
        rootItem.setValue(null);
        treeChild = (Treechildren) getFellowIfAny("treeChild");
        rootCell = (Treecell) getFellowIfAny("rootCell");
        tree.setWidth("95%");
        if(null!=bandboxData){
            if(!TextUtils.isEmpty(bandboxData.getControlHeight())){
                tree.setHeight(((int)bandboxData.getControlHeight()-20)+"px");
            }
        }
        initTree();
        loadTreeData();
        reloadButton();
    }

    @Override
    public PageLayoutVO getBandboxProperty(String pageCa) {
        bandboxData = PageDataUtil.deteailControl(pageCa, "bandboxDiv");

        return bandboxData;
    }

    private void initTree() {
        treeData= PageDataUtil.deteailControl(getPageCA(), "tree");
        if (!TextUtils.isEmpty(treeData.getControlLabel())) {
            rootCell.setLabel(I18nUtil.getLabelContent(treeData.getControlLabel()));
        }
        if (null == treeData.getLayOutComponents() || null == treeData.getLayOutComponents().get("1")) {
            AlterDialog.alert("未配置树相关的配置，请完善");
            return;
        }
        List<PageControlVO> objsData = treeData.getLayOutComponents().get("1");
        for (PageControlVO pgvo : objsData) {
            pageControlVOMap.put(pgvo.getKjAttribute(), pgvo);
        }
        if (null != pageControlVOMap.get("dynamicTree") && !TextUtils.isEmpty(pageControlVOMap.get("dynamicTree").getLayoutComponent().getEvnetFormula())) {
            tree.setAttribute("dynamicClick", true);
        }
        tree.setAttribute("onDbClickTree", true);
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


    @Override
    public void showOrHide(boolean flag) {
    }


    @Override
    public List<Map<String, Object>> filterByKey(String key) {
        return null;
    }

    @Override
    public void onEvent(Event event) throws Exception {
        super.onEvent(event);
        if (event.getName().equalsIgnoreCase(Events.ON_CLICK)&&event.getTarget() instanceof Treeitem) {
            selItem = (Treeitem) event.getTarget();
        }
        if (event.getName().equalsIgnoreCase(Events.ON_CLICK) && event.getTarget() instanceof Treechildren) {
            selItem = (Treeitem) event.getTarget().getParent();
//            AlterDialog.alert(selItem.getId());
            PageControlVO vo = pageControlVOMap.get("dynamicTree");
            TreeUtils.treeAction(this, tree, selItem, vo);
        }
        if (event.getName().equalsIgnoreCase(Events.ON_DOUBLE_CLICK)) {
            selItem = (Treeitem) event.getTarget();
//            BaseBandbox treeBandbox = null;
            BaseBandbox treeBandbox = (BaseBandbox)getParent().getParent();
            if(null!=selItem.getValue()){
                TreeVO treeVO = selItem.getValue();
                Map<String,Object> map = new HashMap<String,Object>();
                BeanUtil.obj2Map(treeVO,map);
                List< Map<String,Object>> objs = new ArrayList< Map<String,Object>>(1);
                objs.add(map);
                treeBandbox.setSelVals(objs);
                treeBandbox.close();
            }

        }
    }

    @Override
    public List<Map<String, Object>> filterByModel(FilterModel model) {
        if(TextUtils.isEmpty(treeData.getControlDetailInt()))
            return null;
        List<FilterModel> models = new ArrayList<FilterModel>();
        models.add(model);
        ResponseMessage mess = JsonPostUtils.executeAPI(models, treeData.getControlDetailInt());
        if(!TextUtils.isEmpty(mess)&&!TextUtils.isEmpty(mess.getBean())){
            if(mess.getBean() instanceof ArrayList)
                return (List<Map<String,Object>>)mess.getBean();
            else{
                Map<String,Object> _obs = (Map<String,Object>)mess.getBean();
                List<Map<String,Object>> _result = new ArrayList<Map<String,Object>>(1);
                _result.add(_obs);
                return _result;
            }
        }
        return null;
    }

    @Override
    public List<Row> doGetGridRow() {
        return null;
    }
}
