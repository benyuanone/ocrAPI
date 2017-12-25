package com.ourway.base.service.impl;

import com.ourway.base.CommonConstants;
import com.ourway.base.utils.CacheUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.message.MsgI18N;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/3/12.
 */
@Service("msgI18N")
public class MsgI18NRedisImpl implements MsgI18N {

    @Override
    public String getMsg(String msg, String language) {
        if (TextUtils.isEmpty(msg))
            return "";
        if (msg.contains(CommonConstants.I18N_LABEL_SPLIT)) {
            StringBuilder sb = new StringBuilder();
            String[] _msg = msg.split(CommonConstants.I18N_LABEL_SPLIT);
            for (String s : _msg) {
                sb.append(CacheUtil.getVal(CommonConstants.I18N_REDIS_KEY + msg.trim() + "." + language));
            }
            return sb.toString();
        } else
            return CacheUtil.getVal(CommonConstants.I18N_REDIS_KEY + msg.trim());
    }

    @Override
    public String getMsg(String msg) {
        return msg;
    }
}
