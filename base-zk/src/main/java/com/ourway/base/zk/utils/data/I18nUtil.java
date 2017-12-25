package com.ourway.base.zk.utils.data;

import com.ourway.base.utils.TextUtils;
import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.listener.BCKJWebAppInit;
import com.ourway.base.zk.models.LanguageVO;
import com.ourway.base.zk.models.PublicData;
import com.ourway.base.zk.models.ResponseMessage;
import com.ourway.base.zk.oscache.OSCacheFactory;
import com.ourway.base.zk.oscache.ZKBaseCache;
import com.ourway.base.zk.utils.HttpUtils;
import com.ourway.base.zk.utils.JsonUtil;
import com.ourway.base.zk.utils.ZKSessionUtils;
import net.sf.ezmorph.bean.MorphDynaBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>方法 PageDataUtil : <p>
 * <p>说明:多语言</p>
 * <pre>
 * @author JackZhou
 * @date 2017/4/25 22:03
 * </pre>
 */
public class I18nUtil {
    static Log log = LogFactory.getLog(I18nUtil.class);
    /*页面对应的控件获取api方法*/
    public static String PAGE_COMPONENTS = "sysLanguageApi/realLan";
    public static String MULT_SEPARATE = ",";//多个关键字之间的分割


    /**
     * <p>方法:getLanguage 获取指定key的多语言 </p>
     * <ul>
     * <li> @param key 指定的key</li>
     * <li>@return com.ourway.base.zk.models.LanguageVO 返回空则为传入的key </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/2 22:31  </li>
     * </ul>
     */
    public static LanguageVO getLanguage(String key) {
        String currentKey = key+"."+ZKSessionUtils.getZkUser().getLanguage();
        Object _obj = OSCacheFactory.getInstance().get(currentKey);
        if (null != _obj) {
            log.info("==命中本地缓存==" + key);
            return (LanguageVO) _obj;
        }
        PublicData data = PublicData.instantce();
        data.setMethod(PAGE_COMPONENTS);
        Map<String, String> ppt = new HashMap<String, String>();
        ppt.put("labelKey", key);
        data.setData(JsonUtil.toJson(ppt));
        try {
            String result = HttpUtils.doPost(data, BaseConstants.UTF8, false);
            ResponseMessage message = JsonUtil.fromJson(result, ResponseMessage.class);
            if (null!=message&&message.getBackCode() == 0) {
                LanguageVO lan = JsonUtil.fromJson((MorphDynaBean) message.getBean(), LanguageVO.class);
                //存入本地的oscache
                OSCacheFactory.getInstance().put(currentKey, lan);
                return lan;
            }else{
                //存在本地库
                OSCacheFactory.getInstance().put(currentKey, LanguageVO.instance(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getLabelContent(String key) {
        StringBuilder sb = new StringBuilder();
        if (TextUtils.isEmpty(key))
            return "";
        if (key.contains(MULT_SEPARATE)) {
            String[] keys = key.split(MULT_SEPARATE);
            for (String s : keys) {
                LanguageVO lan = getLanguage(s);
                if (null == lan)
                    sb.append(s);
                else
                    sb.append(lan.getLabelContent());
            }
        } else {
            LanguageVO lan = getLanguage(key);
            if (null == lan)
                sb.append(key);
            else
                sb.append(lan.getLabelContent());
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        I18nUtil.getLanguage("test");
    }
}
