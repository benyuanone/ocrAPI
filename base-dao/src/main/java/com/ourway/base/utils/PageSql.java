package com.ourway.base.utils;


import com.ourway.base.CommonConstants;
import com.ourway.base.dataobject.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * <p>类PageSql : <p>
 * <p>说明: 组装分页的sql语句，根据不同的数据库来进行组织</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/8 0:13
 * </pre>
 */
public class PageSql {
    protected static Log log = LogFactory.getLog(PageSql.class);
    private static final String DB_NAME_MYSQL = "mysql";
    private static final String DB_NAME_ORACLE = "oracle";
    private static final String DB_NAME_MSSQL = "sqlserver";
    private static String dbName = "";


    private String getConnectionedDBName() {
        if (!TextUtils.isEmpty(dbName)) {
            return dbName;
        }
        Properties pro = new Properties();
        InputStream is = null;
        try {
            is = getClass().getResourceAsStream(CommonConstants.RESOURCE_FILE_PATH);
            pro.load(is);
            String jdbcDCN = pro.getProperty(CommonConstants.DB_CONNECTION_TYPE);
            if (jdbcDCN == null || jdbcDCN.trim().length() == 0) {
                return null;
            }
            if (jdbcDCN.toLowerCase().indexOf(DB_NAME_MYSQL) > 0) {
                dbName = DB_NAME_MYSQL;
            } else if (jdbcDCN.toLowerCase().indexOf(DB_NAME_ORACLE) > 0) {
                dbName = DB_NAME_ORACLE;
            } else if (jdbcDCN.toLowerCase().indexOf(DB_NAME_MSSQL) > 0) {
                dbName = DB_NAME_MSSQL;
            }
            return dbName;
        } catch (Exception e) {
            return null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {

                }
            }
        }
    }

    /**
     * <p>方法:getMySQLPageSQL 获取mysql的分页sql语句 </p>
     * <ul>
     * <li> @param sql </li>
     * <li> @param pr 分页对象</li>
     * <li>@return java.lang.String  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 0:16  </li>
     * </ul>
     */
    public static String getMySQLPageSQL(String sql, PageInfo pr) {
        if (sql == null || sql.trim().length() == 0 || pr == null) {
            return null;
        }
        if (!sql.trim().toLowerCase().startsWith("select")) {
            log.info("getMySQLPageSQL(String sql)-->sql不是select语句:" + sql);
            return null;
        }
        long startIdx = (pr.getCurrentIndex() == 0 ? 1 : pr.getCurrentIndex()) * pr.getPageSize() - pr.getPageSize();
        return "select * from (" + sql + ") _pageset_ LIMIT " + startIdx + "," + pr.getPageSize();
    }


    /**
     * <p>方法:getOraclePageSQL 获取oracle的分页 </p>
     * <ul>
     * <li> @param sql </li>
     * <li> @param pr </li>
     * <li>@return java.lang.String  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 0:18  </li>
     * </ul>
     */
    public static String getOraclePageSQL(String sql, PageInfo pr) {
        if (sql == null || sql.trim().length() == 0 || pr == null) {
            log.info("getMySQLPageSQL(String sql)-->sql is null");
            return null;
        }

        if (!sql.trim().toLowerCase().startsWith("select")) {
            // log.info("getMySQLPageSQL(String sql)-->sql不是select语句:" + sql);
            return null;
        }
        long startIdx = (pr.getCurrentIndex() == 0 ? 1 : pr.getCurrentIndex()) * pr.getPageSize() - pr.getPageSize();
        String newSQL = "select * from ("
                + "select _p1_.*,ROWNUM RN from (" + sql + ") _p1_ where ROWNUM <=" + startIdx + pr.getPageSize()
                + ") where RN>=" + startIdx;
        return newSQL;
    }

    /**
     * <p>方法:getMSSQLPageSQL 获取sqlserver的分页sql </p>
     * <ul>
     * <li> @param sql </li>
     * <li> @param pr </li>
     * <li>@return java.lang.String  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 0:18  </li>
     * </ul>
     */
    public static String getMSSQLPageSQL(String sql, PageInfo pr) {
        if (sql == null || sql.trim().length() == 0 || pr == null) {
             log.info("getMySQLPageSQL(String sql)-->sql is null");
            return null;
        }

        if (!sql.trim().toLowerCase().startsWith("select")) {
             log.info("getMySQLPageSQL(String sql)-->sql不是select语句:" + sql);
            return null;
        }
        long startIdx = (pr.getCurrentIndex() - 1) * pr.getPageSize() + 1;
        long endIdx = pr.getPageSize() * pr.getCurrentIndex();
        String newSQL = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY t1.Id) AS row,t1.* FROM (" + sql +
                ") t1) t WHERE t.row>=" + startIdx + " AND t.row<=" + endIdx;
        return newSQL;
    }


    public String getPageSQL(String sql, PageInfo pr) {
        if("".equals(dbName)){
            getConnectionedDBName();
        }
        if (dbName.equals(DB_NAME_MYSQL)) {
            return getMySQLPageSQL(sql, pr);
        } else if (dbName.equals(DB_NAME_ORACLE)) {
            return getOraclePageSQL(sql, pr);
        } else if (dbName.equals(DB_NAME_MSSQL)) {
            return getMSSQLPageSQL(sql, pr);
        } else {
            return null;
        }
    }


}
