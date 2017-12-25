package com.ourway.base.config;

/**

 * 版本：v1.0.0
 * 日期：2012-2-17
 *
 * @author wuzhisong
 *         功能说明：seedo platform配置管理器工厂
 *         使用说明：
 */
public class ConfigFactory {

    //	private static Log log=LogFactory.getLog(ConfigFactory.class);
    private static ConfigInfotor configInfotor;

    /**
     * 取得配置信息提供者,调用无参构造函数（目前只提供file properties和DB两种形式）
     *
     * @param providerClass 实现的提供者类
     * @return 返回由providerClass提供的ConfigInfotor实例（单例）
     */
    public static ConfigInfotor getInstance(Class <? extends ConfigInfotor> providerClass) {
        if (configInfotor == null) {
            if (providerClass.equals(ConfigInfotorByProperties.class)) {
                configInfotor = new ConfigInfotorByProperties();
            } else {
//				TODO 数据库实现方式
            }
        }

        return configInfotor;
    }

    /**
     * 取得配置信息提供者,调用有参构造函数（目前只提供file properties和DB两种形式）
     *
     * @param providerClass   实现的提供者类
     * @param constructParams providerClass实现类构参数。注：必须与构造参数顺序一致。
     * @return 返回由providerClass提供的ConfigInfotor实例（单例）
     */
    public static ConfigInfotor getInstance(Class <? extends ConfigInfotor> providerClass, Object... constructParams) {
        if (configInfotor == null) {
            if (providerClass.equals(ConfigInfotorByProperties.class)) {
                if (constructParams[0] != null) {
                    configInfotor = new ConfigInfotorByProperties(constructParams[0].toString());
                }
            } else {
//				TODO 用数据库方式实现的配置
            }
        }
        return configInfotor;
    }

    /**
     * 返回当前配置信息提供者类
     *
     * @param <T> 必须是extend的类
     * @return 存在返回提供者类否则返回null
     */
    public static String getConfigInfoProvider() {
        if (configInfotor == null) {
            return null;
        }
        return configInfotor.getClass().getName();
    }

    /**
     * 取得配置管理器(properties),如果之前已经有被初始化过配置管理器则返回之前的，否则返回Properties配置管理器
     *
     * @return 返回Properties配置管理器
     */
    public static ConfigInfotor getDefaultProvider() {
        if (configInfotor == null) {
            configInfotor = getInstance(ConfigInfotorByProperties.class);
        }
        return configInfotor;
    }


    public static void main(String[] args) {
//		Integer i=ConfigFactory.getDefaultProvider().getProperty("upload.fileMaxEntity");
//		System.out.println(i);
    }

}
