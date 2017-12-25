package com.ourway.base.zk.listener;

import com.ourway.base.zk.BaseConstants;
import com.ourway.base.zk.ZKConstants;
import com.ourway.base.zk.utils.TextUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.util.WebAppInit;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

/**
 * Created by jack on 2017/1/31.
 */
public class BCKJWebAppInit implements WebAppInit {
    private Log log = LogFactory.getLog(BCKJWebAppInit.class);
    public static final String PLATFORM_CONFIG_FILE_NAME = "config.properties";

    @Override
    public void init(WebApp webApp) throws Exception {
        log.info("=======loading ZK WEB==========");
        URL url = BCKJWebAppInit.class.getClassLoader().getResource(PLATFORM_CONFIG_FILE_NAME);
        if (url == null) {
            log.info("在当前的ClassLoader下找不到配置管理器配置文件(config.properties文件)");
            return;
        }
        InputStream is = null;
        InputStreamReader isr = null;
        try {
            is = BCKJWebAppInit.class.getClassLoader().getResourceAsStream(PLATFORM_CONFIG_FILE_NAME);
            isr = new InputStreamReader(is);
            Properties pros = new Properties();
            pros.load(isr);
            setConfig(pros);
        } catch (Exception e) {
            log.error("加载出错！", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error(e);
                }
            }
        }
    }

    //配置系统变量
    private void setConfig(Properties pros){
      if(!TextUtils.isEmpty(pros.get("client.SYSTEM_MESS")))
          ZKConstants.SYSTEM_MESS = pros.get("client.SYSTEM_MESS").toString();
      if(!TextUtils.isEmpty(pros.get("client.SYSTEM_FILE_PATH")))
          ZKConstants.SYSTEM_FILE_PATH = pros.get("client.SYSTEM_FILE_PATH").toString();
      if(!TextUtils.isEmpty(pros.get("client.REPORT_IMG_PATH")))
          ZKConstants.REPORT_IMG_PATH = pros.get("client.REPORT_IMG_PATH").toString();

      if(!TextUtils.isEmpty(pros.get("client.SQL_PATH")))
          ZKConstants.SQL_PATH = pros.get("client.SQL_PATH").toString();

      if(!TextUtils.isEmpty(pros.get("client.SYSTEM_FILE_URL")))
          ZKConstants.SYSTEM_FILE_URL = pros.get("client.SYSTEM_FILE_URL").toString();

      if(!TextUtils.isEmpty(pros.get("client.APP_KEY")))
          BaseConstants.APP_KEY = pros.get("client.APP_KEY").toString();

      if(!TextUtils.isEmpty(pros.get("client.URL_HOST")))
          BaseConstants.URL_HOST = pros.get("client.URL_HOST").toString();

      if(!TextUtils.isEmpty(pros.get("client.APP_SECTRECT")))
          BaseConstants.APP_SECTRECT = pros.get("client.APP_SECTRECT").toString();

      if(!TextUtils.isEmpty(pros.get("client.WEBSOCKET_URL")))
          BaseConstants.WEBSOCKET_URL = pros.get("client.WEBSOCKET_URL").toString();

      if(!TextUtils.isEmpty(pros.get("client.WEBSOCKET_SOCKET_URL")))
          BaseConstants.WEBSOCKET_SOCKET_URL = pros.get("client.WEBSOCKET_SOCKET_URL").toString();

        log.info("=======loading ZK WEB finish==========");
    }
}
