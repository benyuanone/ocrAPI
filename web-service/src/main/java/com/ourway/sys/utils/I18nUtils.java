package com.ourway.sys.utils;

import com.ourway.base.CommonConstants;
import com.ourway.base.utils.CacheUtil;
import com.ourway.base.utils.SessionUtils;
import com.ourway.base.utils.SpringContextUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.sys.model.OurwaySysLanguage;
import com.ourway.sys.model.OurwaySysLanguageMult;
import com.ourway.sys.service.LanguageMultService;

import java.util.List;

/**
 * <p>方法 I18nUtils : <p>
 * <p>说明:国际化，操作缓存</p>
 * <pre>
 * @author JackZhou
 * @date 2017/4/24 23:23
 * </pre>
 */
public class I18nUtils {

    /**
     * <p>方法:getLanguage 获取多语言 </p>
     * <ul>
     * <li> @param key 多语言的key</li>
     * <li>@return com.ourway.sys.model.OurwaySysLanguageMult  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/4/24 23:29  </li>
     * </ul>
     */
    public static OurwaySysLanguageMult getLanguage(String key, String currLanguage) {
        OurwaySysLanguageMult mult = CacheUtil.getVal(CommonConstants.I18N_REDIS_KEY + key + "." + currLanguage, OurwaySysLanguageMult.class);
        if (null == mult) {
            //进入数据库命中并保存至redis
            LanguageMultService languageMultService = (LanguageMultService) SpringContextUtil.getBean("languageMultService");
            mult = languageMultService.queryOneByKey(currLanguage, key);
            if (null == mult)
                return null;
            CacheUtil.setVal(CommonConstants.I18N_REDIS_KEY + key + "." + currLanguage, mult);
        }
        return mult;
    }

    public static String getLanguageContent(String key, String currLanguage) {
        if (TextUtils.isEmpty(currLanguage))
            currLanguage = "chn";
        OurwaySysLanguageMult lan = getLanguage(key, currLanguage);
        if (null == lan)
            return key;
        else
            return lan.getLabelContent();
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
    public static void refreshLanguage(OurwaySysLanguage language) {
        if (null == language.getDataList() || language.getDataList().size() <= 0)
            CacheUtil.clearKey(language.getLabelKey());
        else {
            for (OurwaySysLanguageMult languageMult : language.getDataList()) {
                CacheUtil.setVal(CommonConstants.I18N_REDIS_KEY + language.getLabelKey() + "." + languageMult.getLanguage(), languageMult);
            }
        }
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
    public static void refreshAllLanguage(List<OurwaySysLanguage> languageList) {
        for (OurwaySysLanguage language : languageList) {
            refreshLanguage(language);
        }
    }

    //清除redis
    public static void clearLanguage(){
        CacheUtil.clearKeyPattern(CommonConstants.I18N_REDIS_KEY+"*");
    }

    public static void main(String[] args) {
//        System.out.println(.getLI18nUtils.anguageContent("test", "eng"));
        I18nUtils.clearLanguage();
    }
}
