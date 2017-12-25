package com.ourway.base.interceptor;

import com.ourway.base.utils.*;
import com.ourway.sys.model.OurwaySysEmploys;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Properties;


/**<p>方法 AuthorityInterceptor : <p>
*<p>说明:用于拦截请求，判断是否有权限访问接口</p>
*<pre>
*@author JackZhou
*@date 2017/4/12 14:14
</pre>
*/
public class AuthorityInterceptor extends HandlerInterceptorAdapter {
    final Logger logger = LoggerFactory.getLogger(getClass());
    private static  String key = "";        //8e9dc0e83f799ba54f289bb29527e022

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*读取私钥配置*/
//        Properties pro = null;
//        InputStream in = null;
//         try {
//            pro = new Properties();
////          in = new FileInputStream("classpath:app/accessKey.properties");
//             in = this.getClass().getClassLoader().getResourceAsStream("app/accessKey.properties");
//            pro.load(in);
//            key = pro.getProperty("key");
//         } catch (Exception e) {
//             e.printStackTrace();
//         } finally {
//            in.close();
//         }
//        logger.debug("======验证接口访问权限======");
//        String publicKey = request.getParameter("privateKey");
//        String randNum = request.getParameter("randNum");
//        if(TextUtils.isEmpty(publicKey) || TextUtils.isEmpty(randNum) || !publicKey.equals(TextUtils.MD5(key+randNum))){
//            throw new Exception("没有权限！");
//        }

        //这里需要判断zk段进入还是zk端的js进来，zk段的js进来需要自动做用户登录操作，如果没有登陆过的话。
//        String deviceType = request.getParameter("deviceType");
//        if(!TextUtils.isEmpty(deviceType)&&"zkJs".equalsIgnoreCase(deviceType)){
//            Object o = ShiroUtils.getSubject().getPrincipal();
//            Session session = ShiroUtils.getSession();
//            if(null==session.getAttribute(SessionUtils.USER_KEY)) {
//                //给当前session赋值，表示成功登陆的。
//                String empId = request.getParameter("openId");
//                if(!TextUtils.isEmpty(empId)){
//                    OurwaySysEmploys employs =  CacheUtil.getVal(empId, OurwaySysEmploys.class);
//                    session.setAttribute(SessionUtils.USER_KEY,employs);
//                    ShiroUtils.setSessionAttribute(SessionUtils.USER_KEY,employs);
//                }else
//                    return false;
//
//            }
//        }


        return true;
    }
//    public static void main(String[] args) {
//        String MD5key = MD5Util.MD5Encode(key,"utf-8");
//        System.out.println(MD5key);
//    }
}
