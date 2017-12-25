package com.ourway.base.zk.utils.data;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.models.*;
import com.ourway.base.zk.utils.HttpUtils;
import com.ourway.base.zk.utils.JsonUtil;
import net.sf.json.JSONNull;

import java.util.*;

/**
 * <p>方法 PageDataUtil : <p>
 * <p>说明:获取页面配置信息数据类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/4/25 22:03
 * </pre>
 */
public class PageDataUtil {
    /*页面对应的控件获取api方法*/
    public static String PAGE_COMPONENTS = "sysPageApi/listControls";
    public static String DETAIL_COMPONENTS = "sysPageLayoutApi/detailPageControl";
    public static String DETAIL_TAB_COMPONENTS = "sysPageLayoutApi/detailTabPageControl";
    public static String DETAIL_DATALIST_COMPONENTS = "sysPageLayoutApi/detailDataListPageControl";
    public static String DETAIL_COMPONENTS_OUT = "sysPageLayoutApi/detailPageControlWithoutComponent"; //无控件的，只有列表
    public static String DETAIL_PAGE_BYCA = "sysPageApi/detailPageByCa";
    public static String DETAIL_PAGE_COMPONENTS = "sysPageApi/listLabelByCa";

    /**
     * <p>方法:getControls 根据pageca获取当前page下面的控件 </p>
     * <ul>
     * <li> @param pageCA pageca</li>
     * <li>@return java.util.List<com.ourway.base.zk.models.PageControl>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/4/25 23:57  </li>
     * </ul>
     */
    public static boolean getControls(String pageCA, List<PageControlVO> pageControls) {
        PublicData data = PublicData.instantce();
        data.setMethod(PAGE_COMPONENTS);
        Map<String, String> ppt = new HashMap<String, String>();
        ppt.put("pageCa", pageCA);
        data.setData(JsonUtil.toJson(ppt));
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null == mess)
                return false;
            if (mess.getBackCode() == -2)
                return false;
            if (mess.getBackCode() == -1)
                pageControls = null;
            else {
                if (mess.getBean() instanceof JSONNull)
                    mess.setBean(null);
                if (null != mess.getBean()) {
                    List<Map<String, Object>> datas = (List<Map<String, Object>>) mess.getBean();
                    for (Map<String, Object> map : datas) {
                        System.out.println("============================"+map.get("kjName"));
                        pageControls.add(BeanUtil.map2Obj(map, PageControlVO.class));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }


    /**
     * <p>方法:deteailControl 获取指定控件下面的控件列表 </p>
     * <ul>
     * <li> @param pageCA 页面ca</li>
     * <li> @param compId 布局控件id</li>
     * <li>@return java.util.Map<java.lang.String,java.lang.Object>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/9 10:58  </li>
     * </ul>
     */
    public static PageLayoutVO deteailControl(String pageCA, String compId) {
        PublicData data = PublicData.instantce();
        data.setMethod(DETAIL_COMPONENTS);
        Map<String, String> ppt = new HashMap<String, String>();
        ppt.put("pageCa", pageCA);
        ppt.put("componentId", compId);
        data.setData(JsonUtil.toJson(ppt));
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            System.out.println(result);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (mess.getBackCode() == -1)
                return null;
            else {

                if (null != mess.getBean()) {
                    if (mess.getBean() instanceof JSONNull) {
                        return null;
                    } else
                        return map2Layout((Map<String, Object>) mess.getBean());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<PageLayoutVO> deteailDataListControl(String pageCA, String startCompId) {
        List<PageLayoutVO> pageLayoutVOList = new ArrayList<PageLayoutVO>();
        PublicData data = PublicData.instantce();
        data.setMethod(DETAIL_DATALIST_COMPONENTS);
        Map<String, String> ppt = new HashMap<String, String>();
        ppt.put("pageCa", pageCA);
        ppt.put("componentId", startCompId);
        data.setData(JsonUtil.toJson(ppt));
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            System.out.println(result);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (mess.getBackCode() == -1)
                return null;
            else {

                if (null != mess.getBean()) {
                    if (mess.getBean() instanceof JSONNull) {
                        return null;
                    } else {
                        List<Map<String, Object>> _datas = (List<Map<String, Object>>) mess.getBean();
                        for (Map<String, Object> map : _datas) {
                            pageLayoutVOList.add(map2Layout(map));
                        }
                    }
                    return pageLayoutVOList;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<PageLayoutVO> deteailTabControl(String pageCA) {
        PublicData data = PublicData.instantce();
        data.setMethod(DETAIL_TAB_COMPONENTS);
        Map<String, String> ppt = new HashMap<String, String>();
        ppt.put("pageCa", pageCA);
        data.setData(JsonUtil.toJson(ppt));
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            System.out.println(result);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (mess.getBackCode() == -1)
                return null;
            else {

                if (null != mess.getBean()) {
                    if (mess.getBean() instanceof JSONNull) {
                        return null;
                    } else {
                        List<Map<String, Object>> datas = (List<Map<String, Object>>) mess.getBean();
                        List<PageLayoutVO> vos = new ArrayList<PageLayoutVO>();
                        for (Map<String, Object> vo : datas) {
                            vos.add(map2Layout(vo));
                        }
                        return vos;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //把map对象返回layoutvo
    private static PageLayoutVO map2Layout(Map<String, Object> bean) {
        PageLayoutVO layoutVO = new PageLayoutVO();
        Map<String, Class<?>> keyType = BeanUtil.getDeclaredPropertiesTypeMap(PageLayoutVO.class);
        Set<String> keys = keyType.keySet();
        Object _obj = null;
        for (String key : keys) {
            if (!"layOutComponents".equals(key)) {
                if (bean.get(key) instanceof JSONNull) {

                } else {
                    _obj = keyType.get(key).cast(bean.get(key));
                    BeanUtil.setProperty(layoutVO, key, _obj);
                }
            } else {
                Map<String, List<PageControlVO>> layOutComponents = new HashMap<String, List<PageControlVO>>();
                if (TextUtils.isEmpty(bean.get("layOutComponents")))
                    continue;
                Map<String, List<Map<String, Object>>> beanMap = (Map<String, List<Map<String, Object>>>) bean.get("layOutComponents");
                Set<String> set = beanMap.keySet();
                for (String s : set) {
                    List<Map<String, Object>> datas = beanMap.get(s);
                    List<PageControlVO> vos = convertVO(datas);
                    layOutComponents.put(s, vos);
                }
//                for (int index = 1; index <= beanMap.size(); index++) {
//                    List<Map<String, Object>> datas = beanMap.get(index + "");
//                    if(null==datas||datas.size()<=0)
//                        continue;
//                    List<PageControlVO> vos = convertVO(datas);
//                    layOutComponents.put(index + "", vos);
//                }
                layoutVO.setLayOutComponents(layOutComponents);
            }
        }
        return layoutVO;
    }

    private static List<PageControlVO> convertVO(List<Map<String, Object>> datas) {
        List<PageControlVO> pcos = new ArrayList<PageControlVO>();
        Object _obj = null;
        for (Map<String, Object> data : datas) {
            PageControlVO pvo = new PageControlVO();
            Map<String, Class<?>> keyType = BeanUtil.getDeclaredPropertiesTypeMap(PageControlVO.class);
            Set<String> keys = keyType.keySet();
            for (String key : keys) {
                if (!"layoutComponent".equals(key)) {
                    if (data.get(key) instanceof JSONNull) {

                    } else {
                        if ("kjDefaultData".equals(key)) {
                            _obj = data.get(key);
                            if (_obj instanceof ArrayList) {
                                BeanUtil.setProperty(pvo, key, com.ourway.base.utils.JsonUtil.toJson(((List) _obj).toArray()));
                            } else
                                BeanUtil.setProperty(pvo, key, data.get(key));
                        } else {
                            _obj = BeanUtil.convert(keyType.get(key), data.get(key));
//                            _obj = keyType.get(key).cast(data.get(key));
                            BeanUtil.setProperty(pvo, key, _obj);
                        }
                    }
                } else {
                    Map<String, Object> _dataMap = (Map<String, Object>) data.get("layoutComponent");
                    PageLayoutControlVO pcvo = new PageLayoutControlVO();
                    Map<String, Class<?>> _keyTypes = BeanUtil.getDeclaredPropertiesTypeMap(PageLayoutControlVO.class);
                    Set<String> _keys = _keyTypes.keySet();
                    for (String s : _keys) {
                        if (_dataMap.get(s) instanceof JSONNull) {

                        } else {
                            try {

                                _obj = _keyTypes.get(s).cast(_dataMap.get(s));
                                BeanUtil.setProperty(pcvo, s, _obj);
                            } catch (Exception e) {
                                System.out.println(s);
                                e.printStackTrace();
                            }

                        }
                    }
                    pvo.setLayoutComponent(pcvo);
                }
            }
            pcos.add(pvo);
        }
        return pcos;
    }


    //页面详情
    public static PageVO detailPageByCa(String pageCA) {
        PublicData data = PublicData.instantce();
        data.setMethod(DETAIL_PAGE_BYCA);
        List<Object> params = new ArrayList<Object>(1);
        params.add(pageCA);
        FilterModel model = FilterModel.instance("pageCa", FilterModel.EQUALS, params);
        List<FilterModel> models = new ArrayList<FilterModel>(1);
        models.add(model);
        data.setData(JsonUtil.toJson(models.toArray()));
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            System.out.println(result);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null == mess || mess.getBackCode() == -1)
                return null;
            else {
                if (null != mess.getBean()) {
                    Map<String, Object> _datas = (Map<String, Object>) mess.getBean();
                    PageVO vo = com.ourway.base.utils.JsonUtil.map2Bean(_datas, PageVO.class);
                    return vo;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Map<String,Object> listAllComponentLabelId(String pageCA) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        PublicData data = PublicData.instantce();
        data.setMethod(DETAIL_PAGE_COMPONENTS);
        Map<String,Object> params = new HashMap<String,Object>(1);
        params.put("pageCa",pageCA);
        data.setData(JsonUtil.toJson(params));
        String result = "";
        try {
            result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage mess = JsonUtil.getResponseMsg(result);
            if (null == mess || mess.getBackCode() == -1)
                return null;
            else {
                if (null != mess.getBean()) {
                    List<Map<String, Object>> _datas = (List<Map<String, Object>>) mess.getBean();
                    for(Map<String,Object> map:_datas){
                        resultMap.put(map.get("kjLabelid").toString(),I18nUtil.getLabelContent(map.get("kjLabelid").toString()));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }
    public static void main(String[] args) {
        List<PageControlVO> pageControls = new ArrayList<PageControlVO>();
        PageDataUtil.getControls("/login.do", pageControls);
    }
}
