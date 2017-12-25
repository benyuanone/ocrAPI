package com.ourway.base.config;


/**
 * <p>接口 ConfigInfotor.java : <p>
 * <p>说明：通用的配置存储管理器</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/6 22:45
 * </pre>
 */
public interface ConfigInfotor {

    /**
     * <p>方法:getProperty 取得配置key对应的信息 </p>
     * <ul>
     * <li> @param key 必须和namingSpace组成唯一的一个字串</li>
     * <li> @param namingSpace 命名空间【可选】，如果有指定则在这些命名空间中搜索。
     * 如不为空且指定的Provider是文件存取方式(properties)命名空间则是文件名称（不含路径）
     * 如不为空且指定的Provider是DB形式命名空间则是DB表中namingSpace字段中的值
     * 如为空则系统默认在系统配置路径中取config properties文件中内容。</li>
     * <li>@return T  成功返回String或Integer或Double对象否则返回null</li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/6 23:41  </li>
     * </ul>
     */
    <T> T getProperty(String key, String... namingSpace);


    /**
     * <p>方法:putProperty 在默认的spring/config.properties文件中保存key、value对应信息 </p>
     * <ul>
     * <li> @param key </li>
     * <li> @param value </li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/6 23:40  </li>
     * </ul>
     */
    public void putProperty(String key, String value);

    /**
     * <p>方法:putProperty 在namingSpace中保存key、value对应信息 </p>
     * <ul>
     * <li> @param key key</li>
     * <li> @param value value</li>
     * <li> @param namingSpace 命名空间
     * 指定的Provider是文件存取方式(properties)命名空间则是文件名称（不含路径）
     * 指定的Provider是DB形式命名空间则是DB表中namingSpace字段中的值</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/6 23:43  </li>
     * </ul>
     */
    void putProperty(String key, String value, String namingSpace);

}
