package com.ourway.base;


import java.util.Locale;

/**
 * <p>接口 CommonConstants.java : <p>
 * <p>接口说明：定义系统常用的常量</p>
 * <pre>
 * @author JackZhou
 * @date 2017/2/27 0:17
 * </pre>
 */
public final class CommonConstants {
    /*国际化是否打开*/
    public static final String I18N_RECORD_ON_KEY = "i18n.record.on";
    /*国际化在redis中的缓存前缀,所有的国际化必须通过这个前缀加入到reids中*/
    public static final String I18N_REDIS_KEY = "i18n.key.";
    public static final String DIC_REDIS_KEY = "sys.dic.key.";
    /*默认的语言*/
    public static final String DEFAULT_LANGUAGE = "chn";

    /*多个国际化label做并和的分隔符，多个label之间用该符号进行合并*/
    public static final String I18N_LABEL_SPLIT = "+";

    public static final String MSGTYPE_DEFAULT_VALUE = "UNDEFINE_TYPE";
    public static final String MSGTYPE_ERROR_MESSAGE = "ERROR_MESSAGAE";

    /*批量保存的时候，每次批量的最大数量*/
    public static final Integer BATCH_MAX_ROW = 50;
    /*系统的配置文件，放在classes目录下的系统配置文件*/
    public static final String RESOURCE_FILE_PATH = "/app/config.properties";
    /*数据库驱动名称*/
    public static final String DB_CONNECTION_TYPE = "proxool.driverClassName";
    public static final String DEFAULT_PREFERRED_LOCALE = Locale.getDefault().getLanguage() +
            "_" + Locale.getDefault().getCountry();
    /*
     * 默认分页大小
     */
    public static int PAGERESULTINFO_PAGE_SIZE = 20;
    /*
     * 分页最大数量
     */
    public static int PAGERESULTINFO_PAGE_SIZE_MAX = 100;
    /*
     * Loginfo使用bean对象属性新增、删除、修改时过滤属性的类型(策略)
	 */
    public static final String FILTER_TYPE_INCLUDE = "include";
    public static final String FILTER_TYPE_EXCLUDE = "exclude";
    /*
	 * 每张表中的createBy,updateBy如果当前没有登录者id，则系统自动加此字串作为标识
	 */
    public static final String NONE_USER_ID = "-1";
    /*
	 * 程序中所用到字符集
	 */
    public static final String CHARSET_ISO8859_1 = "ISO8859-1";
    public static final String CHARSET_UTF_8 = "UTF-8";
    public static final String CHARSET_GB2312 = "CHARSET_GB2312";
    public static final String CHARSET_GBK = "CHARSET_GBK";
    public static final String CHARSET_GB18030 = "CHARSET_GB18030";
    /*
	 * 路径分割符
	 */
    public static final String PATH_SPLITTER = "/";

    /*
     * 在configs.properties中配置ExceptionUtil 抛出异常时是否需要一起用log记录到文件中。
	 */
    public static final String EXCEPTION_UTIL_SWITCH_LOG_OPEN = "exceptionutil.switchlog.open";


    public static final String SEEDOREVERSESTRATEGY_SEQSQL_PATH = "hibernate.tools.createSequence.sqlFilePath";

    public static final Integer ROLES_TYPE=3;

    /*网页截图插件物理地址*/
    public static String SYSTEM_FILE_PATH = "F:/phantomjs/phantomjs-2.1.1-windows/bin";
    /*网页截图完之后的图片存储地址*/
    public static String IMGPATH = SYSTEM_FILE_PATH + "/img/";

    /**
     * 带附件发送邮件
     */
    /*============================================================*/
    /*邮件服务器*/
    public static String mailHost = "smtp.qq.com";
    /*端口号 qq邮箱需要使用SSL 端口号465或587*/
    public static String mailPort = "465";
    /*服务器超时时间*/
    public static String mailTimeOut = "50000";
    /*starttls 是对纯文本通信协议的扩展。它提供一种方式将纯文本连接升级为加密连接（TLS或SSL）*/
    public static String mailSmtpStarttlsEnable = "true";
    /*开启控制台打印服务器相应信息（日志）*/
    public static String mailDebug = "true";
    /*登录用户名，如果是qq，则填写qq号*/
    public static String mailUsername = "1198606625";
    /*授权码（例如，qq 开启 SMTP 授权码，qq授权码16位）*/
    public static String mailPassword = "fakluwzfcfpkgdif";
    /*是否需要验证码*/
    public static String mailSmtpAuth = "true";
    /*发件人地址（qq邮箱 A）*/
    public static String mailFrom = "1198606625@qq.com";
    /*邮件标题*/
//    public static String mailSubject = "Testing Subject";
    /*==============================================================*/
}
