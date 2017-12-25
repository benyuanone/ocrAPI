package com.ourway.base.utils;


import com.ourway.base.log.Logging;
import com.ourway.base.model.BaseEntity;
import com.ourway.base.log.LogFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HqlUtils {
    private static Logging log = LogFactory.getInstance();
    private static final String OPERATION_TYPE_AND = "and";
    private static final String OPERATION_TYPE_OR = "or";
    public static final String MATCH_TYPE_LK = " like ";
    public static final String MATCH_TYPE_E = " = ";
    public static final String MATCH_TYPE_G = " > ";
    public static final String MATCH_TYPE_L = " < ";
    public static final String MATCH_TYPE_G_E = " >= ";
    public static final String MATCH_TYPE_L_E = " <= ";
    public static final String MATCH_TYPE_N_E = " != ";

    /**
     * Oracle模糊查询符
     */
    public static final String LIKE_CHAR = "%";

    public static String JDBC_CONNECTION_CFG_FILE_NAME = "applicationConfig.properties";
    public static final String JDBC_CONNECTION_CFG_URL_KEY = "jdbc.url";

    /**
     * 将一个查询记录的select语句，自动转变成查询符合条件的记录数量的 select count语句
     *
     * @param originalHql  Have a form of "select ... from ... ..." or "from ..."
     * @param distinctName 用做count(distinctName)，可以为null
     * @return transform to "select count(*) from ... ..."
     */
    public static String generateCountHql(String originalHql,
                                          String distinctName) {

        String loweredOriginalHql = originalHql.toLowerCase();
        int beginPos = loweredOriginalHql.indexOf(" from ");
        if (beginPos == -1) {
            beginPos = loweredOriginalHql.indexOf("from ");
            if (beginPos == -1) {
                throw new IllegalArgumentException("not a valid hql string");
            }
        }
        // 去除Order by进行优化.注意可能在含有order子查询的时候出现错误
        int endPos = loweredOriginalHql.lastIndexOf("order by");
        if (endPos == -1) {
            endPos = loweredOriginalHql.length();
        }
        String countField = null;
        if (distinctName != null) {
            countField = /*" distinct " +*/ distinctName;
        } else {
            countField = " * ";
        }

        return "select count("
                + countField
                + ") "
                + originalHql.substring(beginPos, endPos).replaceAll("join fetch ",
                "join ");
    }

    /**
     * 生成hql(可自定义操作符的)
     * <p>
     * 请参考：String generateHql(String hql,Map<String,Object> params)
     *
     * @param queryFields       hql的select片断例如：select t
     * @param fromJoinSubClause hql的from片断例如：from tsUser t
     * @param whereBodyMap      hql的where查询条件如果map中的value为null则操作符默认为and操作。whereBodyMap<查询条件,操作符>
     *                          例如：whereBodyMap.put("t.userName = :name","or");whereBodyMap.put("t.userNameZh = :nameZh",null);
     * @param orderField        排序的属性名称。例如：t.createDate
     * @param orderDirection    排序类型例如：asc
     * @param params            where查询条件的具体参数。如果params对象中的key对应的值为null则在whereBodyMap中的对应查询条件不生成
     * @return 生成hql
     */
    @Deprecated
    public static String generateHqlByOp(final String queryFields,
                                         final String fromJoinSubClause, final Map<String, String> whereBodyMap,
                                         final String orderField, final String orderDirection,
                                         final Map<String, ?> params) {
        StringBuffer sb = new StringBuffer();
        if (queryFields != null && queryFields.trim().length() > 0) {
            sb.append(queryFields).append(" ");
        }
        sb.append(fromJoinSubClause);
        sb.append(" ").append(generateHqlWhereClause(whereBodyMap, params));
        sb.append(" ").append(
                generateHqlOrderClause(orderField, orderDirection));
        String finalHql = sb.toString();
        log.debug("HQL: " + finalHql);
        return finalHql;
    }

    /**
     * 组织并返回完整的HQL语句。
     * <p>
     * <b>注意：</b> 通常参数whereBodies与params对应出现，。
     * 请参考：String generateHql(String hql,Map<String,Object> params)
     *
     * @param queryFields       HQL中的返回字段指定语句，允许null值，即获取整个对象。
     * @param fromJoinSubClause HQL中的From子句及连接查询语句,不允许null值
     * @param whereBodies       条件语句的数组，数组元素通常写为"prop1=:prop1Key"
     * @param orderField        排序条件
     * @param orderDirection    排序方向，ASC升序，DESC降序
     * @param params            对应于whereBodies的参数值，map元素通常有如prop1Key=prop1Value的值。
     * @return 完整的HQL语句
     */
    @Deprecated
    public static String generateHql(final String queryFields,
                                     final String fromJoinSubClause, final String[] whereBodies,
                                     final String orderField, final String orderDirection,
                                     final Map<String, ?> params) {
        StringBuffer sb = new StringBuffer();
        if (queryFields != null && queryFields.trim().length() > 0) {
            sb.append(queryFields).append(" ");
        }
        sb.append(fromJoinSubClause);
        sb.append(" ").append(generateHqlWhereClause(whereBodies, params));
        sb.append(" ").append(
                generateHqlOrderClause(orderField, orderDirection));
        String finalHql = sb.toString();
        log.debug("HQL: " + finalHql);
        return finalHql;
    }

    /**
     * 根据HQL单个排序条件及生成对应的HQL排序语句。
     *
     * @param orderField     排序字段
     * @param orderDirection 升/降序条件
     * @return 如果搜索字段为null或空，则返回空字符串；正常返回格式形如" ORDER BY XX ASC"
     */
    @Deprecated
    private static String generateHqlOrderClause(String orderField,
                                                 String orderDirection) {
        if (StringUtils.isBlank(orderField)) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" order by ");
        stringBuffer.append(orderField).append(" ");
        if (orderDirection != null) {
            stringBuffer.append(orderDirection);
        }

        return stringBuffer.toString();
    }

    /**
     * 根据多个"AND"运算的参数化的HQL where条件语句，及其对应的参数值Map，生成完整的HQL WHERE语句。
     * 请参考：String generateHql(String hql,Map<String,Object> params)
     *
     * @param whereBodies 查询过滤条件的数组，类似"userName=:userName"
     * @param params      whereBodies中参数名称对应的条件值
     * @return 完整的HQL WHERE语句（包含"where" HQL 关键字）
     */
    @Deprecated
    public static String generateHqlWhereClause(final String[] whereBodies,
                                                final Map<String, ?> params) {
        StringBuffer sb = new StringBuffer();
        String andOp = " and ";
        if (whereBodies != null && whereBodies.length > 0) {
            for (String whereBody : whereBodies) {
                String paramName = getWhereBodyParamName(whereBody);
                if (paramName != null) {
                    if (params != null && params.containsKey(paramName)) {
                        // parameterized condition, remove non set parameters.
                        if (params.get(paramName) != null && params.get(paramName).toString().length() > 0) {
                            // if param is not null , append
                            // "and propName=propValue"
                            sb.append(andOp).append("(").append(whereBody)
                                    .append(")");
                        }
                    }
                } else {
                    // unparameterized condition, just append together.
                    sb.append(andOp).append("(").append(whereBody).append(")");
                }
            }
            if (sb.length() > 0) {
                sb.replace(0, andOp.length(), " where ");
            }
        }
        return sb.toString();
    }

    /**
     * 返回HQL语句中需要使用的参数化Where条件语句中的参数名称。
     * <p>
     * 如：有条件语句param1=:param1Name作为参数传入，则返回该条件语句的参数名称param1Name
     * <p>
     * <b>注意：</b>参数只接受一条Where条件语句
     *
     * @param original HQL参数化Where条件语句
     * @return Where条件语句的参数名称
     */
    @Deprecated
    private static String getWhereBodyParamName(String original) {
        if (!original.contains(":")) {
            return null;
        }
        String[] oris = original.split("[:()]");
        if (oris.length == 1) {
            return null;
        } else {
            return oris[oris.length - 1].trim();
        }
    }


    /**
     * 将输入的前后附加上模糊查询符，并且小写化，以支持忽略大小写模糊查询
     *
     * @param ori Object,一般应该是String
     * @return
     */
    public static String fullILike(Object ori) {
        if (ori == null) {
            return null;
        } else {
            return LIKE_CHAR + ori.toString().toLowerCase() + LIKE_CHAR;
        }
    }

    /**
     * 将日期加一天，时间不改变（一般是0点）。用于页面查询的“结束日期”。
     *
     * @param ori
     * @return 如果是null，返回null.
     */
    public static Date parseEndDate(Date ori) {
        if (ori == null) {
            return null;
        }

        return DateUtils.addDays(ori, 1);
    }

    /**
     * 将日期加一周，时间不改变（一般是某一周的第一天的0点）。用于页面查询的“结束周”。
     *
     * @param ori
     * @return 如果是null，返回null.
     */
    public static Date parseEndWeekDate(Date ori) {
        if (ori == null) {
            return null;
        }

        return DateUtils.addWeeks(ori, 1);
    }

    /**
     * 生成where条件hql片断(可自定义操作符的)
     * 请参考：String generateHql(String hql,Map<String,Object> params)
     *
     * @param whereBodyMap
     * @param params
     * @return
     */
    @Deprecated
    public static String generateHqlWhereClause(final Map<String, String> whereBodyMap,
                                                final Map<String, ?> params) {
        StringBuffer sb = new StringBuffer();
        if (whereBodyMap != null && !whereBodyMap.isEmpty()) {
            for (String whereBody : whereBodyMap.keySet()) {
                String paramName = getWhereBodyParamName(whereBody);
                if (paramName != null) {
                    if (params != null && params.containsKey(paramName)) {
                        // parameterized condition, remove non set parameters.
                        if (params.get(paramName) != null && params.get(paramName).toString().length() > 0) {
                            // if param is not null , append
                            // "and propName=propValue"
                            String op = whereBodyMap.get(whereBody);
                            if (StringUtils.isBlank(op)) {
                                op = OPERATION_TYPE_AND;
                            }
                            sb.append(" " + op + " ").append("(").append(whereBody)
                                    .append(")");
                        } else {
                            // if param is null , condition ignored.
                        }
                    }
                } else {
                    // unparameterized condition, just append together.
                    String op = whereBodyMap.get(whereBody);
                    if (StringUtils.isBlank(op)) {
                        op = OPERATION_TYPE_AND;
                    }
                    sb.append(" " + op + " ").append("(").append(whereBody).append(")");
                }
            }
            if (sb.length() > 0) {
                if (sb.indexOf(" " + OPERATION_TYPE_AND + " ") == 0) {
                    sb.replace(0, (" " + OPERATION_TYPE_AND + " ").length(), " where ");
                } else if (sb.indexOf(" " + OPERATION_TYPE_OR + " ") == 0) {
                    sb.replace(0, (" " + OPERATION_TYPE_OR + " ").length(), " where ");
                }
            }
        }
        return sb.toString();
    }

    /**
     * 生成hql的from片断代码
     * @param clz 生成hql的类名
     * @param aliasName 需要带别名的名称，如不需要则可为null
     * @return 成功返回 hql from代码片断
     * <br>使用方法：<br>
     * <ol>
     *   <li>generateHqlFromClause(TsUser.class,"t")="from TsUser t"</li>
     * </ol>
     */


    /**
     * 生成hql的delete片断代码
     *
     * @param clz       生成hql的类名
     * @param aliasName 需要带别名的名称，如不需要则可为null
     * @return 成功返回 hql from代码片断
     * <br>使用方法：<br>
     * <ol>
     * <li>generateHqlDeleteClause(TsUser.class,"t")="delete TsUser t"</li>
     * </ol>
     */
    public static String generateHqlDeleteClause(Class<? extends BaseEntity> clz, String aliasName) {
        if (clz == null) {
            return "";
        }
        String hql = "delete " + clz.getSimpleName() + (aliasName != null ? " " + aliasName : "");
        return hql;
    }


    /**
     * 过虑hql中无用的where条件。
     * 根据params表单提交的查询条件，去除hql(一个功能查询包含所有可能的查询条件)where条件中
     * 不在params参数中的查询条件（即动态生成过虑where条件）
     *
     * @param hql    含全部where条件的hql
     * @param params 表单提交过来的查询条件参数集合
     * @return <br>使用方法：<br>
     * <ol>
     * <li></li>
     * </ol>
     */
    public static String generateHql(String hql, Map<String, Object> params) {
        final String notWherePlaceholder = "@8@";
        final String wherePlaceholder = "@9@";
        /*
         * regex表达式详解
		 * 最外层括号存在但最里层括号存在说明在条件表达式含有别名
		 * 最外层括号且最里层不存在说明在条件表达但不含别名
		 * 括号里外不存在但存在其它表达式说明hql中的占位符不在条件表达式中，可能是select,order by,group by等
		 */
        String regex = "((\\w+\\.)?(\\w+\\s*(!=|=|<>|<|>)\\s*))?:\\s*\\w+\\s*";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(hql);
        while (m.find()) {
            String matcherStr = StringUtil.trimST(m.group(0));
            if (matcherStr.indexOf(":") == 0
                    && getValueFromPlaceholder(matcherStr, params) == null) {
                /*
                 * 替换不含逻辑运行算的表达式
				 */
                hql = hql.replace(matcherStr, notWherePlaceholder);
                m.reset(hql);
            } else if (getValueFromPlaceholder(matcherStr, params) == null) {
                /*
                 * 只替换逻辑运行算符的条件表达式（对于逻辑运算字符[or,and]串不替换）
				 */
                hql = hql.replace(matcherStr, wherePlaceholder);
                m.reset(hql);
            }
        }
        /*
         * 处理出现在右边表达式的条件逻辑运行符和表达式替换成占位符
		 */
        regex = "(\\s+" + wherePlaceholder + "\\s*(and|or)\\s+)";
        p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        m = p.matcher(hql);
        while (m.find()) {
            hql = hql.replace(m.group(0), wherePlaceholder);
            m.reset(hql);
        }

		/*
		 * 处理出现在左边表达式的条件逻辑运行符和表达式替换成占位符
		 */
        regex = "\\s*(and|or)\\s+" + wherePlaceholder;
        p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        m = p.matcher(hql);
        while (m.find()) {
            hql = hql.replace(m.group(0), wherePlaceholder);
            m.reset(hql);
        }

		/*
		 * 替换select中字段名和右边”，“一起替换为占位符
		 */
        regex = notWherePlaceholder + "\\s*(,)";
        p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        m = p.matcher(hql);
        while (m.find()) {
            hql = hql.replace(m.group(0), notWherePlaceholder);
            m.reset(hql);
        }

		/*
		 * 替换select中字段名和左边”，“一起替换为占位符
		 */
        regex = "\\s*(,)\\s*" + notWherePlaceholder;
        p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        m = p.matcher(hql);
        while (m.find()) {
            hql = hql.replace(m.group(0), notWherePlaceholder);
            m.reset(hql);
        }
        return hql.replaceAll(notWherePlaceholder, "").replaceAll(wherePlaceholder, " ").trim();
    }
//	public static void main(String[] args) {
//		String hql="select :field1, name1  , name2 , :field2" +
//				" from table t where name1=name1 and t.name2=:name2 and " +
//				"t.name3<> :name3 group by :bb, :aa order by :derby1, :derby1";
//		Map<String,Object> params=new HashMap<String, Object>();
//		params.put("name1", "aaa");
//		params.put("name2", "aaa");
//		params.put("field2", "aaa");
//		System.out.println(generateHql(hql,params));
//		System.out.println(":field1".split(":").length);
//	}

    /**
     * 取得占位符对应的参数值
     *
     * @param placeholder
     * @param params
     * @return <br>使用方法：<br>
     * <ol>
     * <li>xx</li>
     * </ol>
     */
    private static Object getValueFromPlaceholder(String placeholder, Map<String, Object> params) {
        String[] strs = placeholder.split(":");
        if (strs.length != 2) {
//            ExceptionUtil.throwException("解析hql语句出错，占位符参数不正确！{0}", placeholder);
        }
        return params.get(strs[1].trim());
    }


    private static String getDateType2StrFunc(String jdbcUrl, Boolean isDateTime) {
        if (jdbcUrl == null || jdbcUrl.trim().length() == 0) {
            return null;
        }
        Map<String, String[]> databaseTypeList = new HashMap<String, String[]>();
        databaseTypeList.put("mysql", new String[]{"DATE_FORMAT(xxx,'%Y-%m-%d %T')", "DATE_FORMAT(xxx,'%Y-%m-%d %T')"});
        databaseTypeList.put("oracle", new String[]{"to_char(xxx,'yyyy-mm-dd')", "to_char(xxx,'yyyy-mm-dd hh24:mi:ss')"});
        databaseTypeList.put("postgre", new String[]{"to_char(xxx,'yyyy-mm-dd')", "to_char(xxx,'yyyy-mm-dd hh24:mi:ss')"});
        databaseTypeList.put("sybase", new String[]{"CONVERT(varchar(20), xxx, 23)", "CONVERT(varchar(20), xxx, 120)"});
        databaseTypeList.put("sqlserver", new String[]{"CONVERT(varchar(20), xxx, 23)", "CONVERT(varchar(20), xxx, 120)"});
        databaseTypeList.put("db2", new String[]{"to_char(xxx,'yyyy-mm-dd')", "to_char(xxx,'yyyy-mm-dd hh24:mi:ss')"});
        String dateType2StrFunc = null;
        for (String dbtype : databaseTypeList.keySet()) {
            if (jdbcUrl.indexOf(dbtype) > -1) {
                dateType2StrFunc = (isDateTime == null || !isDateTime) ? databaseTypeList.get(dbtype)[0] : databaseTypeList.get(dbtype)[1];
                break;
            }
        }
        return dateType2StrFunc;
    }

    public static <T> HqlStatement getWhereById(Collection<T> entities) {
        Map<String, Object> params = new HashMap<String, Object>();
        List<Object> idList = new ArrayList<Object>();
        for (T t : entities) {
            Object id = null;
            try {
                id = BeanUtil.getBeanProperty(t, BaseEntity.PO_ID);
            } catch (Exception e) {
//                ExceptionUtil.throwException(e, "获得id值出错可能没有此属性!");
            }
            idList.add(id);
        }
        params.put(BaseEntity.PO_ID, idList.toArray(new Object[]{}));
        String condition = " and "+BaseEntity.PO_ID+" in (:"+BaseEntity.PO_ID+")";
        HqlStatement hs = new HqlStatement(condition, params);
        return hs;
    }

}
