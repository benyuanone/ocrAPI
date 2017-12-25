package com.ourway.syszk.utils;

import com.ourway.base.zk.ERCode;
import com.ourway.base.zk.component.BaseListbox;
import com.ourway.base.zk.utils.*;
import com.ourway.base.zk.utils.data.DicUtil;
import com.ourway.base.zk.utils.data.I18nUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Listitem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*<p>方法 DicCascadeUtil : <p>
*<p>说明:字典表转换util</p>
*<pre>
*@author zhou_xtian
*@date 2017-10-10 14:22
</pre>
*/
public class DicCascadeUtil {

    /*<p>方法: 根据前台传入的字符串设置listbox属性值 </p>
    *<ul>
     *<li> @param str 前台传值字符串</li>
    *格式示例1(多对一):1014-1015:11,12,13,14,15,16,17-1|21,22-2|23,24,25-3|26-4
    *格式示例2(一对多):1014-1017:1-11,12,13,14,15,16,17|21-21,22|22-23,24,25,26|23-23,24,25,26
     *<li> @param selectedValue 主对象的字典code值</li>
     *<li> @param component 当前listbox的component对象</li>
     *<li> @param type 0：格式示例1(多对一)  1：格式示例2(一对多)</li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-10-17 9:54  </li>
    *</ul>
    */
    public static void autoSwitchNewListbox(String str, String selectedValue,Component component,int type) {
        String[] strArray = str.split(":");
        int selectedType = Integer.parseInt(strArray[0].split("-")[0]);
        int autoSwitchType = Integer.parseInt(strArray[0].split("-")[1]);
        String switchStr = strArray[1];
        switch (type) {
            case 0 :
                setNewListboxValue(selectedType,selectedValue,autoSwitchType,switchStr,component);
                break;
            case 1:
                setNewListbox(selectedType,selectedValue,autoSwitchType,switchStr,component);
                break;
        }
    }

    /*<p>方法: 根据传入的值设置listbox的下拉选项 </p>
    *<ul>
     *<li> @param selectedType 传入主对象的字典type值</li>
     *<li> @param selVal 主对象的字典code值</li>
     *<li> @param type listbox对应的字典type值</li>
     *<li> @param paramStr 转化str</li>
     *<li> @param component 当前listbox的component对象</li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-10-17 9:00  </li>
    *</ul>
    */
    private static void setNewListbox(int selectedType, String selVal, int type, String paramStr, Component component) {
        //根据前台定义拼装最终map
        Map<String,List<String>> finaMap = new HashMap<String,List<String>>();
        List<String> list1 = Arrays.asList(paramStr.split("\\|"));
        for (String s : list1) {
            List<String> valueList = Arrays.asList(s.split("-")[1].split(","));
            finaMap.put(s.split("-")[0],valueList);
        }
        //获取所选值对应的dic参数code
        //这里改装为取节点数据；如果取不到，则取其父节点数据；若再取不到，则再父节点。。。。
        List<String> dicList = getFatherCode(finaMap,selVal,selectedType);

        //获取所选值对应的dic参数val
        List<Map<String, Object>> dicValList = DicUtil.listLanguageDicList(type,dicList);
        if (null != dicValList && dicValList.size() > 0) {
            BaseListbox listbox = (BaseListbox) component;
            listbox.getItems().clear();
            //放入“请选择”item
            Listitem item = new Listitem(I18nUtil.getLabelContent(ERCode.LISTBOX_DEFAULT),"");
            item.setParent(component);
            listbox.selectItem(item);
            //增加新的listbox选项
            for (Map<String, Object> data : dicValList) {
                item = new Listitem(data.get("dicVal1").toString(), data.get("code").toString());
                item.setParent(component);
            }
        }
    }

    /*<p>方法: 根据传入的值设置listbox的选择项 </p>
    *<ul>
     *<li> @param selectedType 传入主对象的字典type值</li>
     *<li> @param selVal 主对象的字典code值</li>
     *<li> @param type listbox对应的字典type值</li>
     *<li> @param paramStr 转化str</li>
     *<li> @param component 当前listbox的component对象</li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-10-17 8:55  </li>
    *</ul>
    */
    private static void setNewListboxValue(int selectedType, String selVal, int type, String paramStr, Component component) {
        List<String> list1 = Arrays.asList(paramStr.split("\\|"));
        for (String s : list1) {
            List<String> valueList = Arrays.asList(s.split("-")[0].split(","));
            valueList.contains(selVal);
            if (isCodeInList(valueList,selVal,selectedType)) {
                DataBinder.binderToPage("", s.split("-")[1], component);
                break;
            }
        }
    }

    //递归调用，无限层级获取父级菜单对应code并判断是否在集合中
    private static boolean isCodeInList(List<String> valueList,String selVal,int selectedType) {
        if (null != valueList) {
            if (valueList.contains(selVal)) {
                return true;
            } else {
                Map<String, Object> dic =  DicUtil.detailDicWithLanguage(selectedType,"code",selVal);
                if (!TextUtils.isEmpty(dic)) {
                    //获取父级菜单
                    Map<String, Object> fatherDic =  DicUtil.detailDicWithLanguage(selectedType,"owid",dic.get("fid"));
                    if (!TextUtils.isEmpty(fatherDic)) {
                        isCodeInList(valueList,fatherDic.get("code").toString(),selectedType);
                    }
                }
            }
        }
        return false;
    }

    //递归调用，无限层级获取父级菜单对应code并判断是否在集合中，最后根据key对应value得到listbox列表
    private static List<String> getFatherCode(Map<String,List<String>> finaMap,String selVal,int selectedType) {
        List<String> dicList = finaMap.get(selVal);
        if (null == dicList || dicList.size() <= 0) {
            Map<String, Object> dic =  DicUtil.detailDicWithLanguage(selectedType,"code",selVal);
            if (!TextUtils.isEmpty(dic)) {
                //获取父级菜单
                Map<String, Object> fatherDic =  DicUtil.detailDicWithLanguage(selectedType,"owid",dic.get("fid"));
                if (!TextUtils.isEmpty(fatherDic)) {
                    dicList = getFatherCode(finaMap,fatherDic.get("code").toString(),selectedType);
                }
            }
        }
        return dicList;
    }
}
