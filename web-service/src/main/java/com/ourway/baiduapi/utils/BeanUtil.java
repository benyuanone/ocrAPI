package com.ourway.baiduapi.utils;

import net.sf.json.JSONNull;
import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>方法 BeanUtil : <p>
 * <p>说明:通用的工具类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/11 21:53
 * </pre>
 */
public class BeanUtil {
    private static Log log = LogFactory.getLog(BeanUtil.class);
    /**
     * 用于定义Hibernate Annotation中字段长度的属性名
     */
    private static final String COLUMN_LENGTH = "length";
    private static final String COLUMN_NULLABLE = "nullable";
    /**
     * Hibernate代理类名特征
     */
    private static final String CGLIB_CLASS_SUFFIX = "$$EnhancerByCGLIB$$";

    private static Map<Class<?>, PropertyDescriptor[]> pdMap = null;


    /**
     * <p>方法:getBeanProperty 获取指定类中的属性 </p>
     * <ul>
     * <li> @param bean 对象</li>
     * <li> @param property 属性</li>
     * <li>@return java.lang.Object  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 21:54  </li>
     * </ul>
     */
    public static Object getBeanProperty(Object bean, String property)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        try {
            return PropertyUtils.getProperty(bean, property);
        } catch (NestedNullException e) {
            return null;
        }
    }

    /**
     * 把最先转换成MAP
     *
     * @param source    源对象
     * @param upperCase true 表示大写，false表示小写
     */
    /**
     * <p>方法:obj2Map 把对象转成Map对象 </p>
     * <ul>
     * <li> @param source 源对象</li>
     * <li> @param upperCase 是否需要key大写，true大写，false小写</li>
     * <li>@return java.util.Map<java.lang.String,java.lang.Object>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 21:55  </li>
     * </ul>
     */
    public static Map<String, Object> obj2Map(Object source, boolean upperCase) {
        Map<String, Object> map = new HashMap<String, Object>();
        Set<String> pns = getPropertiesName(source);
        for (String s : pns) {
            if (upperCase)
                map.put(s.toUpperCase(), getProperty(source, s));
            else
                map.put(s.toUpperCase(), getProperty(source, s));

        }
        return map;
    }

    /**
     * <p>方法:obj2Map 对象中指定属性转换成map对象 </p>
     * <ul>
     * <li> @param source TODO</li>
     * <li> @param includePropeties TODO</li>
     * <li>@return java.util.Map<java.lang.String,java.lang.Object>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 21:56  </li>
     * </ul>
     */
    public static Map<String, Object> obj2Map(Object source, String[] includePropeties) {
        Map<String, Object> map = new HashMap<String, Object>();
        for (String s : includePropeties) {
            map.put(s, getProperty(source, s));
        }
        return map;
    }

    /**
     * <p>方法:obj2Map 对象转换为map </p>
     * <ul>
     * <li> @param source TODO</li>
     * <li> @param ppt TODO</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/23 21:49  </li>
     * </ul>
     */
    public static void obj2Map(Object source, Map<String, Object> ppt) {
        Set<String> keys = BeanUtil.getPropertiesName(source);
        for (String s : keys) {
            ppt.put(s, getProperty(source, s));
        }
    }



    /**
     * 如果是Hibernate代理类，则获得其父类，应就是真实的Model类
     *
     * @param clazz 原类
     * @return Model类
     */
    public static Class<?> getRealClassFromHibernate(Class<?> clazz) {
        // 是代理类
//        if (clazz.getName().indexOf(CGLIB_CLASS_SUFFIX) > -1) {
//            return clazz.getSuperclass();
//        } else {
        return clazz;
//        }
    }


    /**
     * 设置bean对象property属性的值
     *
     * @param bean     　bean对象
     * @param property 属性名称
     * @param value    　设置property的值
     */
    public static void setProperty(Object bean, String property, Object value) {
        try {
            if (value instanceof JSONNull)
                value = null;
            PropertyUtils.setProperty(bean, property, value);
        } catch (Exception e) {
            throw new RuntimeException("setProperty()出错!" + property, e);
        }
    }

    /**
     * 判断bean是否存在property属性
     *
     * @param bean     bean对象
     * @param property 属性名称
     * @return 存在返回true, 否则返回false
     */
    public static boolean isExistProperty(Object bean, String property) {
        try {
            PropertyUtils.getProperty(bean, property);
        } catch (IllegalAccessException e) {
            log.error(e);
        } catch (InvocationTargetException e) {
            log.error(e);
        } catch (NoSuchMethodException e) {
            return false;
        }
        return true;
    }

    /**
     * 取得bean对象property属性的值
     *
     * @param bean     　bean对象
     * @param property 　属性名称
     * @return　存在返回属性值否则返回null
     */
    public static Object getProperty(Object bean, String property) {
        try {
            return PropertyUtils.getProperty(bean, property);
        } catch (Exception e) {
            log.error("getProperty()出错!", e);
            return null;
        }
    }

    /**
     * 把对象中指定属性的值放入到map中去
     */
    public static void copy2Map(Map<String, Object> map, Object bean, Object... copyProperties) {
        for (Object obj : copyProperties) {
            map.put(obj.toString(), BeanUtil.getProperty(bean, obj.toString()));
        }
    }


    public static void copyBean(Object srcEntity, Object destEntity, String... keys) {
        if (destEntity == null || srcEntity == null) {
            return;
        }
        for (String key : keys) {
            try {
                Object _obj = BeanUtil.getBeanProperty(srcEntity, key);
                if (null != _obj) {
                    BeanUtil.setProperty(destEntity, key, _obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void copy2Bean(Object srcEntity, Object destEntity, String[] condition) {
        if (destEntity == null || srcEntity == null) {
            return;
        }
        if (null == condition)
            return;
        for (String s : condition) {
            String[] _tmp = s.split("\\-");
            Object _obj = BeanUtil.getProperty(srcEntity, _tmp[0]);
            BeanUtil.setProperty(destEntity, _tmp[1], _obj);
        }
    }

    /**
     * 判断基础数据是否为兼容类型如：boolean与Boolean为相同返回true
     *
     * @param srcType
     * @param targetType
     * @return
     */
    private static boolean isCompatibleType(Class<?> srcType, Class<?> targetType) {
        if (srcType == null || targetType == null) {
            return false;
        }
        Map<String, String> dataTypeMap = new HashMap<String, String>();
        dataTypeMap.put("boolean", Boolean.class.getName());
        dataTypeMap.put("int", Integer.class.getName());
        dataTypeMap.put("float", Float.class.getName());
        dataTypeMap.put("double", Double.class.getName());
        dataTypeMap.put("long", Long.class.getName());

        dataTypeMap.put(Boolean.class.getName(), "boolean");
        dataTypeMap.put(Integer.class.getName(), "int");
        dataTypeMap.put(Float.class.getName(), "float");
        dataTypeMap.put(Double.class.getName(), "double");
        dataTypeMap.put(Long.class.getName(), "long");
        if (dataTypeMap.get(srcType.getName()) == null || dataTypeMap.get(targetType.getName()) == null) {
            return false;
        }
        return dataTypeMap.get(srcType.getName()).equals(targetType.getName()) &&
                dataTypeMap.get(targetType.getName()).equals(srcType.getName());
    }

    /**
     * 获取class 类的包名
     *
     * @param clz
     * @return 使用方法：
     */
    public static String getPackageName(Class<?> clz) {
        String clzName = clz.getName().replace("." + clz.getSimpleName(), "");
        return clzName;
    }

    /**
     * 查找cls的绝对路径
     *
     * @param cls class类
     * @return 返回绝对路径否则报错
     */
    public static URL getClassLocationURL(final Class<?> cls) {
        if (cls == null) {
            throw new RuntimeException("参数不为空");
        }
        URL result = null;
        final String clsAsResource = cls.getName().replace('.', '/').concat(".class");
        final ProtectionDomain pd = cls.getProtectionDomain();
        if (pd != null) {
            final CodeSource cs = pd.getCodeSource();
            if (cs != null) {
                result = cs.getLocation();
            }
            if (result != null) {
                if ("file".equals(result.getProtocol())) {
                    try {
                        if (result.toExternalForm().toLowerCase().endsWith(".jar")
                                || result.toExternalForm().toLowerCase().endsWith(".zip")) {
                            result = new URL("jar:".concat(
                                    result.toExternalForm()).concat("!/")
                                    .concat(clsAsResource));
                        } else if (new File(result.getFile()).isDirectory()) {
                            result = new URL(result, clsAsResource);
                        }
                    } catch (MalformedURLException ignore) {
//                        log.error("获取URL出错!",ignore);
                    }
                }
            }
        }

        if (result == null) {
            final ClassLoader clsLoader = cls.getClassLoader();
            result = clsLoader != null ? clsLoader.getResource(clsAsResource)
                    : ClassLoader.getSystemResource(clsAsResource);
        }
        return result;
    }


    /**
     * 获取cls所在的目录，如果该cls在jar文件中则返回jar文件所在的目录
     *
     * @param cls
     * @return 使用方法：
     */
    public static String findClassInDir(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        String[] path = getClassLocationURL(cls).getPath().split("[!]");
        if (path == null) {
            return null;
        }
        path[0] = path[0].replace("file://", "").replace("file:/", "").replace("%20", " ");
        if (path[0].toLowerCase().endsWith(".jar")) {
            /*
             * in the jar file
			 */
            path[0] = path[0].substring(0, path[0].lastIndexOf("/"));
        } else {
            /*
             * not in the jar file
			 */
            if (path[0].toLowerCase().endsWith(".class")) {
                path[0] = path[0].substring(0, path[0].toLowerCase().lastIndexOf(".class"));//replace(cls.getSimpleName(), "").replace(".class", "").replace(".CLASS", "");
            }
        }
        return path[0].indexOf(":") == 2 ? path[0].substring(1) : path[0];
    }

    /**
     * 获取cls所在jar文件的名称，不含路径。（如需要路径请与findClassInDir()配合使用）
     *
     * @param cls
     * @return 使用方法：
     */
    public static String getJarName(Class<?> cls) {
        String[] path = getClassLocationURL(cls).getPath().split("[!]");
        if (path == null || path.length == 1) {
            return null;
        }
        path[0] = path[0].replace("file:/", "").replace("%20", " ");
        String jarRegex = "/[\\.\\w_-]{1,}\\.jar$";
        Pattern p = Pattern.compile(jarRegex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(path[0]);
        if (m.find()) {
            /*
             * in the jar file
			 */
            path[0] = m.group();
            return path[0].substring(1);
        }
        return null;
    }

    /**
     * 取得obj指定泛型类名
     *
     * @param obj
     * @return <br>使用方法：<br>
     * <ol>
     * <li>
     * 例子：UserService<BasTsUser> us=....;
     * Class c=getGenericReturnType(us);//BasTsUser.class
     * </li>
     * </ol>
     */
    public static Class<?> getGenericReturnType(Object obj) {
        if (ParameterizedType.class.isAssignableFrom(obj.getClass().getGenericSuperclass().getClass())) {
            Type[] actualTypeArguments = ((ParameterizedType) obj.getClass()
                    .getGenericSuperclass()).getActualTypeArguments();
            return (Class<?>) actualTypeArguments[0];
        }
        return null;
    }

    /**
     * 取得方法级别的泛型类
     *
     * @param method 查找该方法类型的方法
     * @param index  要获取的第几个泛型参数
     * @return 返回泛型类
     * <br>使用方法：<br>
     * <ol>
     * <li>xx</li>
     * </ol>
     */
    public static Class<?> getMethodGenericReturnType(Method method, int index) {
        Type returnType = method.getGenericReturnType();
        if (returnType instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) returnType;
            Type[] typeArguments = type.getActualTypeArguments();
            if (index >= typeArguments.length || index < 0) {
                throw new RuntimeException("你输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
            }
            return (Class<?>) typeArguments[index];
        }
        return Object.class;
    }

    private static Map<String, HashSet<String>> getSubsClassProName(String... names) {
        if (names == null || names.length == 0) {
            return new HashMap<String, HashSet<String>>();
        }
        Map<String, HashSet<String>> hasSubclass = new HashMap<String, HashSet<String>>();
        for (String name : names) {
            if (name.indexOf(".") > 0) {
                String[] subclassProName = name.split("\\.");
                if (subclassProName.length == 2) {
                    if (hasSubclass.get(subclassProName[0]) != null) {
                        hasSubclass.get(subclassProName[0]).add(name);
                    } else {
                        HashSet<String> proSet = new HashSet<String>();
                        proSet.add(name);
                        hasSubclass.put(subclassProName[0], proSet);
                    }
                }
            }
        }
        return hasSubclass;
    }

    /**
     * 获取bean中如果include=true则只查找bean中含子级属性的属性名称集合,include=false则反着
     *
     * @param bean
     * @param include
     * @param names
     * @return
     */
    public static String[] getBeanPros(Object bean, boolean include, String... names) {
        if (bean == null) {
            return null;
        }
        return getBeanPros(bean.getClass(), include, names);
    }



    /**
     * 把c类中的属性名称与它对应的类型存到map上(含继承父类的属性)
     *
     * @param c 要把属性存到Map对象的源
     * @return 成功返回Map<属性名，类型>map对象
     */
    public static Map<String, Class<?>> getPropertiesTypeMap(Class<?> c) {
        if (c == null) {
            return null;
        }
        Map<String, Class<?>> m = new HashMap<String, Class<?>>();
        PropertyDescriptor[] pds = getPropertyDescriptors(c);
        for (PropertyDescriptor pd : pds) {
            m.put(pd.getName(), pd.getPropertyType());
        }
        return m;
    }

    /**
     * 把c类中的属性名称与它对应的类型存到map上
     *
     * @param c          要把属性存到Map对象的源
     * @param isDeclared true不含继承父类的属性,false包含
     * @return 成功返回Map<属性名，类型>map对象
     */
    public static Map<String, Class<?>> getPropertiesTypeMap(Class<?> c, boolean isDeclared) {
        Map<String, Class<?>> result = new HashMap<String, Class<?>>();
        Method[] pros = isDeclared ? c.getDeclaredMethods() : c.getMethods();
        for (Method mth : pros) {
            String proName = mth.getName();
            if (proName.startsWith("set") || proName.startsWith("is")) {
                if (mth.getParameterTypes() == null || mth.getParameterTypes().length != 1) {
                    continue;
                }
                proName = proName.startsWith("set") ? proName.replaceFirst("set", "") : proName.replaceFirst("is", "");
                proName = proName.substring(0, 1).toLowerCase().concat(proName.substring(1));
                result.put(proName, mth.getParameterTypes()[0]);
            }
        }
        return result;
    }

    /**
     * 把c类中的属性名称与它对应的类型存到map上(不含继承父类的属性)
     *
     * @param c 要把属性存到Map对象的源
     * @return 成功返回Map<属性名，类型>map对象
     */
    public static Map<String, Class<?>> getDeclaredPropertiesTypeMap(Class<?> c) {
        return getPropertiesTypeMap(c, true);
    }

    /**
     * 获取c类中的getsetter属性(不含get,set前缀)
     *
     * @param c 获取的属性源
     * @return 不存在返回null或empty, 否则返回实际属性名称
     */
    public static Set<String> getPropertiesName(Class<?> c) {
        if (c == null) {
            return null;
        }
        Object bean = null;
        try {
            bean = c.newInstance();
        } catch (Exception e) {
            log.error("getPropertiesName()-->newInstance()出错了:" + c.getName(), e);
        }
        return getPropertiesName(bean);
    }

    public static Set<String> getPropertiesName(Object bean) {
        if (bean == null) {
            return null;
        }
        Set<String> result = new HashSet<String>();

        PropertyDescriptor[] pds = getPropertyDescriptors(bean.getClass());
        for (PropertyDescriptor pd : pds) {
            if ("class".equals(pd.getName()))
                continue;
            result.add(pd.getName());
        }
        return result;
    }

    /**
     * 查找bean对象属性名为getterPropertyName的数据类型
     *
     * @param bean
     * @param getterPropertyName
     * @return 失败返回null
     */
    public static Class<?> getReturnType(Object bean, String getterPropertyName) {
        if (bean == null || getterPropertyName == null) {
            return null;
        }
        PropertyDescriptor[] pds = getPropertyDescriptors(bean.getClass());
        for (PropertyDescriptor pd : pds) {
            if (pd.getName().equals(getterPropertyName)) {
                return pd.getPropertyType();
            }
        }
        return null;
    }


    /**
     * 获取bean对象的PropertyDescriptor信息,首先从缓存取出否则反射取出
     *
     * @param bean
     * @return
     */
    public static PropertyDescriptor[] getPropertyDescriptors(Object bean) {
        if (bean == null) {
            return null;
        }
        return getPropertyDescriptors(bean.getClass());
    }





    /**
     * <p>方法:map2Obj map转换为对象 </p>
     * <ul>
     * <li> @param datas TODO</li>
     * <li> @param claz TODO</li>
     * <li>@return T  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/3 0:24  </li>
     * </ul>
     */
    public static <T> T map2Obj(Map<String, Object> datas, Class<T> claz) {
        try {
            T t = claz.newInstance();
            Set<String> set = getPropertiesName(t);
            Map<String, Class<?>> typeMap = BeanUtil.getPropertiesTypeMap(claz);
            for (String s : set) {
                if (null != datas.get(s)) {
                    Object _obj = convert(typeMap.get(s), datas.get(s));
                    if (null != _obj)
                        setProperty(t, s, _obj);
                }
            }
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    //根据不同的类型进行转换
    public static Object convert(Class clz, Object obj) {
        if (null == obj || obj instanceof JSONNull)
            return null;
//        System.out.println(clz.getName());
        if ("java.lang.Integer".equals(clz.getName())) {
            if (TextUtils.isEmpty(obj.toString()))
                return null;
            else
                return Integer.valueOf(obj.toString());
        }
        if ("java.lang.Double".equals(clz.getName())) {
            if (TextUtils.isEmpty(obj.toString()))
                return null;
            else
                return Double.valueOf(obj.toString());
        }
        if ("java.lang.Short".equals(clz.getName())) {
            if (TextUtils.isEmpty(obj.toString()))
                return null;
            else
                return Short.valueOf(obj.toString());
        }
        if ("java.lang.String".equals(clz.getName())) {
            if (obj instanceof ArrayList) {
                return JsonUtil.toJson(((List) obj).toArray());
            }
            return obj.toString();
        }
        if ("java.lang.Byte".equals(clz.getName())) {
            if (TextUtils.isEmpty(obj.toString()))
                return null;
            else
                return new Byte(obj.toString());
        }
        if ("java.lang.BigDecimal".equals(clz.getName())) {
            if (TextUtils.isEmpty(obj.toString()))
                return null;
            else
                return new BigDecimal(obj.toString());
        }
        if ("java.math.BigDecimal".equals(clz.getName())) {
            if (TextUtils.isEmpty(obj.toString()))
                return null;
            else
                return new BigDecimal(obj.toString());
        }
        if ("java.util.Date".equals(clz.getName())) {
            if (TextUtils.isEmpty(obj.toString()))
                return null;
            else {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
                try {
                    return  sdf.parse(obj.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
//                return new BigDecimal(obj.toString());
//                return DateUtil.getDate(obj.toString(), "yyyy-MM-dd");
        }
        return obj;
    }


    public static void main(String[] args) {
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("owid","123");
//        map.put("language","123");


//		IRender re=new BooleanRender("真","假","无");
//		Map<String,IRender> m=new HashMap<String,IRender>();
//		m.put("isDeleted", re);
//		BasTsUser us=new BasTsUser();
//		us.setId("userid");
//		BasTsUserInf uif=new BasTsUserInf();
//		uif.setId("uid1");
//		uif.setAccountExpired(true);
//		uif.setPostcode("postcode");
//		BasTsDepartment dp=new BasTsDepartment();
//		dp.setId("depid1");
//		dp.setDepartmentName("departmentName");
//		uif.setBasTsDepartment(dp);
//		us.setUserInf(uif);
//		String[] filterProperties=new String[]{"id","isDeleted","password","userInf.postcode","userInf.accountExpired"};
//		System.out.println(JsonUtil.toString(toMap(us, true, true, filterProperties, m)));
    }


}
