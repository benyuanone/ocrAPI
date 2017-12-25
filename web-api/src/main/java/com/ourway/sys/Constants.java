package com.ourway.sys;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>方法 Constants : <p>
 * <p>说明:系统中的常量</p>
 * <pre>
 * @author JackZhou
 * @date 2017/4/24 23:30
 * </pre>
 */
public class Constants {

    /*国际化的关键字的前缀*/
    public static final String I18N_PREFIX = "i18n.key.";
    /*参数未传入*/
    public static final String PARAM_LACK = "未传入参数";
    public static final String NO_MESS = "无信息";


    /*系统字典表的类型*/
    public class DicType {
        /*页面配置时候的事件类()（1 编号，2 值，3 说明）*/
        public static final int TYPE_0 = 0;
        /*1： 页面的配置的控件类型(1:编号 2 值 3 说明)*/
        public static final int TYPE_1 = 1;
        /*2： 页面初始化事件类*/
        public static final int TYPE_2 = 2;
        /*3： 角色类别（编号dicVal1，名称dicVal2，备注dicVal3）*/
        public static final int TYPE_3 = 3;
        /*4： 用户类别（编号dicVal1，名称dicVal2，备注dicVal3）*/
        public static final int TYPE_4 = 4;
        /*5： 用户职位（编号dicVal1，名称dicVal2，备注dicVal3）*/
        public static final int TYPE_5 = 5;
        /*6:   控件初始化实现类(编号dicVal1，类名dicVal2，说明dicVal3，备注dicVal4)*/
        public static final int TYPE_6 = 6;
        /*7 ：单据编号规则： dicVal1 , 单据类型，dicVal2: 单据名称，dicVal3 常量，dicVal4( 无，yyyyMMdd,yyyyMM,yyyy,yyMMdd,yyMM,yy )  dicVal5，（长度）dicVal6，（表格）dicVal7，（字段）*/
        public static final int TYPE_7 = 7;
    }

    /* 定义导入类的时候，公用属性的默认值 */
    public static final Map<String,String> PROPERTY_MAP = new HashMap<String,String>();
    static{
        PROPERTY_MAP.put("owid","主键-public.sys.owid");
        PROPERTY_MAP.put("fid","父主键-public.sys.fid");
        PROPERTY_MAP.put("language","语言-public.sys.language");
        PROPERTY_MAP.put("lasupdate","更新时间-public.sys.lasupdate");
        PROPERTY_MAP.put("updator","更新人主键-public.sys.updator");
        PROPERTY_MAP.put("updatorName","更新人姓名-public.sys.updatorName");
        PROPERTY_MAP.put("ver","版本号-public.sys.ver");
        PROPERTY_MAP.put("vertime","版本时间-public.sys.vertime");
        PROPERTY_MAP.put("creator","创建人主键-public.sys.creator");
        PROPERTY_MAP.put("creatorName","创建人姓名-public.sys.creatorName");
        PROPERTY_MAP.put("createtime","创建时间-public.sys.createtime");
        PROPERTY_MAP.put("exp1","扩展1-public.sys.exp1");
        PROPERTY_MAP.put("exp2","扩展2-public.sys.exp2");
        PROPERTY_MAP.put("exp3","扩展3-public.sys.exp3");
        PROPERTY_MAP.put("exp4","扩展4-public.sys.exp4");
        PROPERTY_MAP.put("exp5","扩展5-public.sys.exp5");
        PROPERTY_MAP.put("exp6","扩展6-public.sys.exp6");
        PROPERTY_MAP.put("exp7","扩展7-public.sys.exp7");
        PROPERTY_MAP.put("exp8","扩展8-public.sys.exp8");
        PROPERTY_MAP.put("exp9","扩展9-public.sys.exp9");
        PROPERTY_MAP.put("exp10","扩展10-public.sys.exp10");
        PROPERTY_MAP.put("state","状态-public.sys.state");
        PROPERTY_MAP.put("delflg","删除标记-public.sys.delflg");
        PROPERTY_MAP.put("deptId","部门id-public.sys.deptId");
        PROPERTY_MAP.put("deptPath","部门路径-public.sys.deptPath");
    }



}
