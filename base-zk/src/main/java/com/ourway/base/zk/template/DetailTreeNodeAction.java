package com.ourway.base.zk.template;

import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.BaseButton;
import com.ourway.base.zk.component.BaseLabel;
import com.ourway.base.zk.component.BaseTextbox;
import com.ourway.base.zk.component.BaseWindow;
import com.ourway.base.zk.models.TreeVO;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.TreeDataUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;

import java.util.Map;

/**
 * <p>方法 ListPageTemplateAction : <p>
 * <p>说明:模板页面</p>
 * <pre>
 * @author JackZhou
 * @date 2017/5/8 22:51
 * </pre>
 */
public class DetailTreeNodeAction extends BaseWindow {
    protected Log info = LogFactory.getLog(DetailTreeNodeAction.class);

    private String url;//操作接口
    private String type;//类型
    private BaseTextbox nodeName;

    private TreeVO bean;

    public TreeVO getBean() {
        return bean;
    }

    public void setBean(TreeVO bean) {
        this.bean = bean;
    }

    public void onCreate(CreateEvent event) {
        super.onCreate(event);
        bean = new TreeVO();
        nodeName = (BaseTextbox) getFellowIfAny("treeDetail_nodeName");
        Map<String, Object> _params = (Map<String, Object>) event.getArg();
        url = _params.get("url").toString();
        type = _params.get("type").toString();
        bean.setFid(Integer.parseInt(_params.get("fid").toString()));
        bean.setPath(_params.get("path").toString());
        bean.setPx(Double.parseDouble(_params.get("px").toString()));
        bean.setCc(Integer.parseInt(_params.get("cc").toString()));

        if (null != event.getArg() && null != event.getArg().get("selItem")) {
            TreeVO _bean = (TreeVO) event.getArg().get("selItem");
            //只有更新节点的时候
            if (type.equalsIgnoreCase("3")) {
                nodeName.setText(_bean.getName());
                bean.setName(_bean.getName());
                bean.setOwid(_bean.getOwid());
            }

        }
        BaseButton saveBtn = (BaseButton) getFellowIfAny("saveBtn");
        BaseButton closeBtn = (BaseButton) getFellowIfAny("closeBtn");
        saveBtn.setLabel(I18nUtil.getLabelContent(ERCode.TREE_SAVE_BUTTON));
        closeBtn.setLabel(I18nUtil.getLabelContent(ERCode.TREE_CLOSE_BUTTON));
        saveBtn.addEventListener(Events.ON_CLICK, this);
        closeBtn.addEventListener(Events.ON_CLICK, this);
        BaseLabel treeDetail_nodeLabel = (BaseLabel) getFellowIfAny("treeDetail_nodeLabel");
        treeDetail_nodeLabel.setValue(I18nUtil.getLabelContent(ERCode.TREE_NODE_LABEL));
        reloadButton();
    }

    void save() {
        bean.setName(nodeName.getText());
        bean = TreeDataUtil.updateTreeNode(bean, url);
        if (null == bean) {
            AlterDialog.alert(I18nUtil.getLabelContent(ERCode.TREE_SAVE_ERROR));
        } else {
            //更新节点
            setBean(bean);
            setClosePage(true);
            detach();
        }

    }

    @Override
    public void onEvent(Event event) throws Exception {
        if (event.getTarget().getId().equalsIgnoreCase("saveBtn")) {
            save();
        }
        if (event.getTarget().getId().equalsIgnoreCase("closeBtn")) {
            detach();
        }
    }

}
