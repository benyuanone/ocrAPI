package com.ourway.base.zk.component;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.models.ListboxVO;
import com.ourway.base.zk.models.PageControlVO;
import com.ourway.base.zk.models.PageLayoutControlVO;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.service.ComponentInitSer;
import com.ourway.base.zk.service.ComponentListinerSer;
import com.ourway.base.zk.service.ComponentSer;
import com.ourway.base.zk.utils.AlterDialog;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.PageUtils;
import com.ourway.base.zk.utils.data.I18nUtil;
import com.ourway.base.zk.utils.data.JsonPostUtils;
import net.sf.json.JSONArray;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/18.
 */
public class BaseListbox extends Listbox implements ComponentSer<Listitem> {
    private String property;
    private PageControlVO pgvo;//当前控件的属性对象
    @Override
    public PageControlVO getPgvo() {
        return pgvo;
    }

    public void setPgvo(PageControlVO pgvo) {
        this.pgvo = pgvo;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public void init(Map<String, Object> pageObj, String property) {

    }

    private void setEdit(PageControlVO pageControlVO){
        if(null!=pageControlVO.getLayoutComponent().getCompEditable()&&pageControlVO.getLayoutComponent().getCompEditable()==1)
            setDisabled(true);
    }

    @Override
    public void init(PageControlVO pageControlVO, BaseWindow win) {
        setEdit(pageControlVO);
        //判断是否需要初始化
        boolean flag = null == pageControlVO || null == pageControlVO.getLayoutComponent() || null == pageControlVO.getLayoutComponent().getCompInit() || pageControlVO.getLayoutComponent().getCompInit() == 0;
        if (flag)
            return;
        if (null == pageControlVO.getKjDatasource())
            return;
        switch (pageControlVO.getKjDatasource()) {
            case 0://用户输入
                break;
            case 1://默认值
                if (null != pageControlVO.getKjDefaultData()) {
                    try {
                        JSONArray jsonArray = JSONArray.fromObject(pageControlVO.getKjDefaultData().toString());
                        List<Object> objs = com.ourway.base.utils.JsonUtil.jsonToList(jsonArray);
                        if (null != objs && objs.size() > 0) {
                            for (Object obj : objs) {
                                Map<String, Object> _map = (Map<String, Object>)obj;
                                ListboxVO vo = com.ourway.base.utils.JsonUtil.map2Bean(_map, ListboxVO.class);
                                Listitem item = createNewItem(vo);
                                item.setParent(this);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 2://调用接口
                ResponseMessage mess = JsonPostUtils.executeAPI(pageControlVO.getKjInitData().toString(), pageControlVO.getKjInterface());
                if (null != mess && mess.getBackCode() == 0) {
                    List<Map<String, Object>> list = (List<Map<String, Object>>) mess.getBean();
                    for (Map<String, Object> map : list) {
                        ListboxVO vo = com.ourway.base.utils.JsonUtil.map2Bean(map, ListboxVO.class);
                        Listitem item = createNewItem(vo);
                        item.setParent(this);
                    }
                }
                break;
            case 3://自定义类
                try{
                   if(TextUtils.isEmpty(pageControlVO.getKjClassImpl())){
                       AlterDialog.alert("未定义初始化类");
                       return;
                   }
                   Object _obj = Class.forName(pageControlVO.getKjClassImpl()).newInstance();
                   ComponentInitSer initSer = (ComponentInitSer)_obj;
                   initSer.doAction(win,this,pageControlVO);
                }catch(Exception e){
                e.printStackTrace();
                    AlterDialog.alert(e.getMessage());
                }
                break;
        }
    }

    @Override
    public Listitem getPageValue() {
        return this.getSelectedItem();
    }

    @Override
    public void setPageValue(Object obj) {

    }

    //创建Listitem
    private Listitem createNewItem(ListboxVO vo) {
        Listitem item = new Listitem();
        item.setValue(vo.getValue());
        item.setLabel(I18nUtil.getLabelContent(vo.getLabel()));
        item.setSelected(vo.getSelected());
        return item;
    }

    @Override
    public void addEventListiner(PageControlVO pageControlVO, BaseWindow win) {
        PageUtils.doAddEvent(this, win, pageControlVO);
    }

    @Override
    public void doEvent(Object objectMap, BaseWindow win, Event event) {
        boolean flag = null == pgvo.getLayoutComponent() || null == pgvo.getLayoutComponent().getEventType() || pgvo.getLayoutComponent().getEventType() == 0;
        if (flag)
            return;
        //根据类别组装调用的数据
        if (!TextUtils.isEmpty(pgvo.getLayoutComponent().getEvnetFormula())) {
            String clzName = pgvo.getLayoutComponent().getEvnetFormula();
            try {
                Object _obj = Class.forName(clzName).newInstance();
                ComponentListinerSer compAction = (ComponentListinerSer) _obj;
                compAction.doAction(win, event, pgvo);
            } catch (Exception e) {
                e.printStackTrace();
                AlterDialog.alert(clzName + "执行错误");
            }
        }
    }

    @Override
    public void reset() {
        setSelectedIndex(0);
    }

    @Override
    public void setComponentDisable(boolean flag) {
        setDisabled(flag);
    }
}
