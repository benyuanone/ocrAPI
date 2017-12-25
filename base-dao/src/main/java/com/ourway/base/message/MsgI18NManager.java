package com.ourway.base.message;

import com.ourway.base.log.LogFactory;
import com.ourway.base.log.Logging;
import com.ourway.base.utils.SpringContextUtil;
import com.ourway.base.config.ConfigFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

/**
 * <p>方法 MsgI18NManager : <p>
 * <p>说明:国际化工厂类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/11 23:17
 * </pre>
 */
public class MsgI18NManager {
    private static Logging log = LogFactory.getInstance();
    private static MsgI18N msgI18N;
    private MsgI18NManager() {
    }

    /**
     * 取得MsgI18N的实现者，切换配置可到configs.properties中的msgI18n.provider项配置
     * 如未在configs.properties未指定则默认com.ourway.base.service.impl.MsgI18NRedisImpl
     * @return 返回MsgI18N的实现者
     */
    public static MsgI18N getProvider() {
        if (msgI18N == null) {
            String providerClz = ConfigFactory.getDefaultProvider().getProperty("msgI18n.provider");
            if (providerClz == null || providerClz.trim().length() == 0) {
                providerClz = "com.ourway.base.service.impl.MsgI18NRedisImpl";
            }
            Class<?> providerClass = null;
            try {
                providerClass = Class.forName(providerClz);
            } catch (ClassNotFoundException e) {
                log.error(e);
            }
            Service s = AnnotationUtils.findAnnotation(providerClass, Service.class);
            if (s == null) {
                throw new RuntimeException(providerClass.getName() + "没有在spring注册Service请确认！");
            }
            String v = s.value();
            if (v == null || v.length() == 0) {
                v = providerClass.getSimpleName().substring(0, 1).toLowerCase()
                        + providerClass.getSimpleName().substring(1);
            }
            msgI18N = (MsgI18N) SpringContextUtil.getBean(v);
        }
        return msgI18N;
    }
}
