package com.ourway.base.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * <p>方法 ConfigInfotorByProperties : <p>
 * <p>说明:对接口configinfotor的实现类，通过配置文件来获取系统的配置信息</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/6 23:45
 * </pre>
 */
public class ConfigInfotorByProperties implements ConfigInfotor {
    private String configFileName = "config.properties";
    private Map<String, Properties> propertiesMap = new HashMap<String, Properties>();
    //    默认的配置文件
    private static final String PLATFORM_CONFIG_FILE_NAME = "/app/config.properties";

    private static Log log = LogFactory.getLog(ConfigInfotorByProperties.class);
    /*
     * jdbc.properties file path
     * default in classes root
     */
    private String configPath = "/app/";

    public String getConfigPath() {
        return configPath;
    }

    /**
     * 设置properties的配置路径不含文件名，若未指定默认路在classpath root下面
     *
     * @param configPath 存放在一起各config文件目录名称
     */
    public void setConfigPath(String configPath) {
        this.configPath = configPath;
        loadProperties();
    }

    /**
     * @param configPath jdbc.properties文件的路径（不含configs.properties名称）
     */
    public ConfigInfotorByProperties(String configPath) {
        this.configPath = configPath;
        loadProperties();
    }

    public ConfigInfotorByProperties() {
        loadProperties();
    }


    /**
     * 加载config.properties文件
     */
    private void loadProperties() {

        if (log.isDebugEnabled()) {
            URL url = ConfigInfotorByProperties.class.getClassLoader().getResource(PLATFORM_CONFIG_FILE_NAME);
            if (url != null) {
                log.debug("加载configs配置文件:" + url.getFile());
            } else {
                log.debug("无法加载configs配置文件！");
            }
        }
        if (propertiesMap != null && !propertiesMap.isEmpty()) {
            return;
        }
        String tmpPath = null;
        URL url = ConfigInfotorByProperties.class.getClassLoader().getResource(PLATFORM_CONFIG_FILE_NAME);
        if (url == null) {
            log.info("在当前的ClassLoader下找不到配置管理器配置文件(config.properties文件)");
            return;
        }
        InputStream is = null;
        InputStreamReader isr = null;
        try {
//            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(PLATFORM_CONFIG_FILE_NAME);
            is = ConfigInfotorByProperties.class.getClassLoader().getResourceAsStream(PLATFORM_CONFIG_FILE_NAME);
            isr = new InputStreamReader(is);
            Properties pros = new Properties();
            pros.load(isr);
            propertiesMap.put(configFileName, pros);
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
        tmpPath = url.getPath().replace(PLATFORM_CONFIG_FILE_NAME, "");
        tmpPath = tmpPath.replace("%20", " ");

        File path = new File(tmpPath);
        File[] files = path.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String file) {
                Pattern p = Pattern.compile("configs[\\w-]*\\.properties", Pattern.CASE_INSENSITIVE);
                if (p.matcher(file).find()) {
                    return true;
                }
                return false;
            }
        });

        if (files == null || files.length == 0) {
            return;
        }

        for (File file : files) {
            if (!file.exists() || file.getPath().endsWith(PLATFORM_CONFIG_FILE_NAME)) {
                continue;
            }
            Properties p = new Properties();
            p.load(file.getPath());
//            String tmpP = file.getPath();
//            String namingSpace = tmpP.substring(tmpP.lastIndexOf(File.separator) + 1);
            propertiesMap.put(configFileName, p);
        }
    }

    /* (non-Javadoc)
     * @see com.seedo.platform.config.ConfigInfotor#getProperty(java.lang.String, java.lang.String[])
     */
    public <T> T getProperty(String key, String... namingSpace) {

        T retu = null;
        if (namingSpace == null || namingSpace.length == 0) {
            if (propertiesMap.get(configFileName) == null) {
                return null;
            }
            retu = propertiesMap.get(configFileName).getProperty(key);
        }
        for (String string : namingSpace) {
            Properties p = propertiesMap.get(string);
            retu = p.getProperty(key);
            if (retu != null) {
                break;
            }
        }
        return retu;
    }

    /* (non-Javadoc)
     * @see com.seedo.platform.config.ConfigInfotor#putProperty(java.lang.String, java.lang.String)
     */
    public void putProperty(String key, String value) {
        propertiesMap.get(configFileName).setProperty(key, value);
        propertiesMap.get(configFileName).store();
    }

    /* (non-Javadoc)
     * @see com.seedo.platform.config.ConfigInfotor#putProperty(java.lang.String, java.lang.String, java.lang.String)
     */
    public void putProperty(String key, String value, String namingSpace) {
        if (namingSpace == null || namingSpace.trim().length() == 0) {
            return;
        }
        if (propertiesMap.get(namingSpace) != null) {
            propertiesMap.get(namingSpace).setProperty(key, value);
            propertiesMap.get(namingSpace).store();
        } else {
            log.error("找不到namingSpace=" + namingSpace + ",不能保存key=" + key + "  value=" + value);
        }
    }

    public static void main(String[] args) {
    }

}
