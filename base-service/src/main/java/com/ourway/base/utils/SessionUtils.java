package com.ourway.base.utils;

import com.ourway.base.CommonConstants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Created by Administrator on 2017/3/12.
 */
public class SessionUtils {

    public static final String USER_LANGUAGE = "key_user_language";
    public static final String USER_KEY = "key_user";

    /**
     * <p>方法:getCurrLanguage 获取当前用户所选择的语言，没有则返回chn </p>
     * <ul>
     * <li>@return java.lang.String  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/12 2:47  </li>
     * </ul>
     */
    public static String getCurrLanguage() {

        Subject subject = SecurityUtils.getSubject();
        System.out.println("currlanguage:"+ subject.getSession().getAttribute(USER_LANGUAGE));
        if (null == subject.getSession().getAttribute(USER_LANGUAGE))
            return CommonConstants.DEFAULT_LANGUAGE;
        else
            return subject.getSession().getAttribute(USER_LANGUAGE).toString();
    }

}
