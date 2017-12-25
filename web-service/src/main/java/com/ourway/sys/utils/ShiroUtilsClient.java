package com.ourway.sys.utils;

import com.ourway.base.utils.ShiroUtils;
import com.ourway.sys.model.OurwaySysEmploys;
import org.apache.shiro.SecurityUtils;

/**
 * Shiro工具类
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月12日 上午9:49:19
 */
public class ShiroUtilsClient extends ShiroUtils {

    public static OurwaySysEmploys getUserEntity() {
        return (OurwaySysEmploys) SecurityUtils.getSubject().getPrincipal();
    }

    public static String getUserId() {
        return getUserEntity().getEmpId();
    }


}
