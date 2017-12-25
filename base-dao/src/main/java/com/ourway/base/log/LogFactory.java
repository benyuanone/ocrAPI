package com.ourway.base.log;

/**
 * <p>方法 LogFactory : 对日志类进行二次封装的工厂类<p>
 * <p>说明:对日志类进行二次封装的工厂类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/7 0:19
 * </pre>
 */
public final class LogFactory {
    private static Logging getInstance(Class<?> logClz) {
        return new Logging(logClz);
    }

    /**
     * <p>方法:getInstance 获取指定类的日志实例 </p>
     * <ul>
     * <li>@return Logging  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 0:20  </li>
     * </ul>
     */
    public static Logging getInstance() {
        Throwable t = new Throwable();
        StackTraceElement[] stes = t.getStackTrace();
        try {
            if (stes.length > 1) {
                String className = stes[1].getClassName();
                Class clz = Class.forName(className);
                return getInstance(clz);
            }
            String className = stes[0].getClassName();
            Class clz = Class.forName(className);
            return getInstance(clz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
