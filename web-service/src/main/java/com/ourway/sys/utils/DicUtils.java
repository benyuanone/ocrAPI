package com.ourway.sys.utils;

import com.ourway.base.CommonConstants;
import com.ourway.base.utils.*;
import com.ourway.sys.model.OurwaySysDic;
import com.ourway.sys.model.OurwaySysDicValue;
import com.ourway.sys.model.OurwaySysLanguage;
import com.ourway.sys.model.OurwaySysLanguageMult;
import com.ourway.sys.service.DicService;
import com.ourway.sys.service.DicValueService;
import com.ourway.sys.service.LanguageMultService;

import java.util.List;

/**
 * <p>方法: 系统统一字典表处理 </p>
 * <ul>
 * <li>@author JackZhou </li>
 * <li>@date 2017/6/22 22:05  </li>
 * </ul>
 */
public class DicUtils {

    /**
     * <p>方法:getLanguage 获取多语言 </p>
     * <ul>
     * <li> @param key 多语言的key</li>
     * <li>@return com.ourway.sys.model.OurwaySysLanguageMult  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/4/24 23:29  </li>
     * </ul>
     */
    public static OurwaySysDicValue getDicVal(String key) {
        OurwaySysDicValue val = CacheUtil.getVal(CommonConstants.DIC_REDIS_KEY + key, OurwaySysDicValue.class);
        if (null == val) {
            //进入数据库命中并保存至redis
            DicValueService dicValueService = (DicValueService) SpringContextUtil.getBean("dicValue");
            val = dicValueService.detailValueByid(key);
            if (null == val)
                return null;
            CacheUtil.setVal(CommonConstants.DIC_REDIS_KEY + key, val);
        }
        return val;
    }

    /*獲取多條件*/
    public static String getDicVals(String key, String valKey,String currLanguage) {
        //进入数据库命中并保存至redis
        String label = "";
        Object _obj = "";
        DicValueService dicValueService = (DicValueService) SpringContextUtil.getBean("dicValue");
        if (key.indexOf(",") >= 0) {
            String[] keys = key.split("\\,");
            for (String s : keys) {
                OurwaySysDicValue dicValue = dicValueService.detailValueByid(s);
                if (null != dicValue) {
                    _obj = BeanUtil.getProperty(dicValue, valKey);
                    if (!TextUtils.isEmpty(_obj)) {
                        label += I18nUtils.getLanguageContent(_obj.toString(),currLanguage) + ",";
                    }
                }
            }
        }
        return label;
    }


    /**
     * <p>方法:refreshLanguage 刷新单个语言 </p>
     * <ul>
     * <li> @param language 语言</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/6/22 22:04  </li>
     * </ul>
     */
    public static void refreshDic(OurwaySysDicValue dic) {
        CacheUtil.setVal(CommonConstants.DIC_REDIS_KEY + dic.getDicRefOwid(), dic);
    }

    /**
     * <p>方法:refreshLanguage 刷新所有语言 </p>
     * <ul>
     * <li> @param language 语言</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/6/22 22:04  </li>
     * </ul>
     */
    public static void refreshAllDic(List<OurwaySysDicValue> dicValueList) {
        for (OurwaySysDicValue dic : dicValueList) {
            refreshDic(dic);
        }
    }

    public static void main(String[] args) {

    }
}
