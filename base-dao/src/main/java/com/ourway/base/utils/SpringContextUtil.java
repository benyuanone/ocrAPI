package com.ourway.base.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;

/**
 * 处理spring的注解工具类
 * 通过实现这个applicationconfextaware这个接口后，可以获取spring中所有的
 * Created by jack on 2017/1/26.
 */
@Repository("springContextUtil")
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    private static TransactionDefinition txDefinition;
    private static PlatformTransactionManager transactionManager;

    public static TransactionDefinition getTxDefinition() {
        return txDefinition;
    }

    public static void setTxDefinition(TransactionDefinition txDefinition) {
        SpringContextUtil.txDefinition = txDefinition;
    }

    public static PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public static void setTransactionManager(PlatformTransactionManager transactionManager) {
        SpringContextUtil.transactionManager = transactionManager;
    }

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name)
            throws BeansException {
        checkApplicationContext();
        return applicationContext.getBean(name);
    }

    public static Object ObjectgetBean(String name, Class<?> requiredType)
            throws BeansException {
        return applicationContext.getBean(name, requiredType);
    }

    public static <T> T getBean(Class<? extends T> service) {
        return applicationContext.getBean(service);
    }

    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    public static boolean isSingleton(String name)
            throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(name);
    }

    public static Class<?> getType(String name)
            throws NoSuchBeanDefinitionException {
        return applicationContext.getType(name);
    }

    public static String[] getAliases(String name)
            throws NoSuchBeanDefinitionException {
        return applicationContext.getAliases(name);
    }

    private static void checkApplicationContext() {
        if (applicationContext == null)
            throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextUtil");
    }

}
