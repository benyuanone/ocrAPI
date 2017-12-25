package com.ourway.base.dao.impl;

import com.ourway.base.CommonConstants;
import com.ourway.base.dao.BaseDao;
import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.log.LogFactory;
import com.ourway.base.log.Logging;
import com.ourway.base.model.BaseEntity;
import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.PageSql;
import org.apache.commons.lang.StringUtils;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.*;
import java.util.*;

/**
 * 数据库操作实现类
 */
@Repository("baseDao")
public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {
    protected final Logging log = LogFactory.getInstance();

    @Autowired
    public void setHibernateSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }


    @Override
    public HibernateTemplate getHibernateTpl() {
        return super.getHibernateTemplate();
    }

    @Override
    public <T> List<T> listByAttribute(T example) {
        return super.getHibernateTemplate().findByExample(example);
    }

    @Override
    public <T> List<T> listAllByClass(Class<T> entityClass) {
        String hql = "from " + BeanUtil.getRealClassFromHibernate(entityClass).getSimpleName() + " t";
        return listByAttribute(hql, new Object[]{});
    }

    @Override
    public <T> T getOneById(Class<T> entityClass, Serializable id) {
        String hql = "from " + entityClass.getSimpleName() + " t where t.owid=:owid";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BaseEntity.PO_ID, id);
        List<T> l = listHqlByAttribute(hql, params);
        if (l == null || l.isEmpty()) {
            return null;
        }
        return l.get(0);
    }

    @Override
    public <T> T getOneByHqlStatement(final HqlStatement hqlStatement) {
        List<T> content = (List<T>) getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        Query query = session.createQuery(hqlStatement.getHql());
                        if (null != hqlStatement.getParams())
                            query.setProperties(hqlStatement.getParams());
                        query.setFirstResult(0);
                        query.setMaxResults(1);
                        return query.list();
                    }
                });
        if (null != content && content.size() > 0)
            return content.get(0);
        else
            return null;
    }

    @Override
    public <T> T getOneByUUID(Class<T> entityClass, Serializable uuid) {
        String hql = "from " + BeanUtil.getRealClassFromHibernate(entityClass).getSimpleName() + " t where t.uuid=:uuid";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(BaseEntity.PO_ID, uuid);
        List<T> l = listHqlByAttribute(hql, params);
        if (l == null || l.isEmpty()) {
            return null;
        }
        return l.get(0);
    }

    @Override
    public <T> List<T> listHqlByAttribute(final String hql, final Map<String, ?> params) {
        List<T> content = (List<T>) getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        Query query = session.createQuery(hql);
                        if (null != params)
                            query.setProperties(params);
                        return query.list();
                    }
                });
        return content;
    }

    @Override
    public List<Object[]> listObjsHqlByAttribute(final String hql, final Map<String, ?> params) {
        List<Object[]> content = (List<Object[]>) getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        Query query = session.createQuery(hql);
                        if (null != params)
                            query.setProperties(params);
                        return query.list();
                    }
                });
        return content;
    }

    @Override
    public List<Object> listObjHqlByAttribute(final String hql, final Map<String, ?> params) {
        List<Object> content = (List<Object>) getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        Query query = session.createQuery(hql);
                        if (null != params)
                            query.setProperties(params);
                        return query.list();
                    }
                });
        return content;
    }

    @Override
    public <T> PageInfo<T> listHqlPageByAttribute(final String hql, final String countHql, final Map<String, ?> params, final Integer pageNo, final Integer pageSize) {
        PageInfo<T> pr = new PageInfo<T>();
        pr.setCurrentIndex(pageNo == null ? 1 : pageNo);
        pr.setPageSize(pageSize == null ? 0 : pageSize);
        if (null != countHql) {
            pr.setTotalCount(countHqlByAttribute(countHql, params));
        }
        if (pageSize != null && pageSize != 0) {
            pr.setTotalPage((int) ((pr.getTotalCount() + pageSize - 1) / pageSize));
            pr.setCurrentPage((int) (((pageNo == 0 ? 1 : pageNo) * pageSize) / pageSize));
        }
        List<T> content = (List<T>) getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        Query query = session.createQuery(hql);
                        if (null != params)
                            query.setProperties(params);
                        if (pageSize != 0) {
                            query.setFirstResult((pageNo == 0 ? 1 : pageNo) * pageSize - pageSize);
                            query.setMaxResults(pageSize);
                        }
                        return query.list();
                    }
                });
        pr.setRecords(content);
        return pr;
    }

    @Override
    public PageInfo<Object[]> listObjsHqlPageByAttribute(final String hql, final String countHql, final Map<String, ?> params, final Integer pageNo, final Integer pageSize) {
        PageInfo<Object[]> pr = new PageInfo<Object[]>();
        pr.setCurrentIndex(pageNo == null ? 1 : pageNo);
        pr.setPageSize(pageSize == null ? 0 : pageSize);
        if (null != countHql) {
            pr.setTotalCount(countHqlByAttribute(countHql, params));
        }
        if (pageSize != null && pageSize != 0) {
            pr.setTotalPage((int) ((pr.getTotalCount() + pageSize - 1) / pageSize));
            pr.setCurrentPage((int) (((pageNo == 0 ? 1 : pageNo) * pageSize) / pageSize));
        }
        List<Object[]> content = (List<Object[]>) getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        Query query = session.createQuery(hql);
                        if (null != params)
                            query.setProperties(params);
                        if (pageSize != 0) {
                            query.setFirstResult((pageNo == 0 ? 1 : pageNo) * pageSize - pageSize);
                            query.setMaxResults(pageSize);
                        }
                        return query.list();
                    }
                });
        pr.setRecords(content);
        return pr;
    }

    @Override
    public Integer countHqlByAttribute(final String countHql, final Map<String, ?> params) {
        List result_list = (List) getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        Query query = session.createQuery(countHql);
                        if (null != params)
                            query.setProperties(params);
                        return query.list();
                    }
                });
        return ((Long) result_list.get(0)).intValue();
    }

    @Override
    public <T> void removeById(T entity) {
        if (null == entity)
            return;
        String hql = "delete " + BeanUtil.getRealClassFromHibernate(entity.getClass()).getSimpleName() + " t where t.owid=?";
        Object id = BeanUtil.getProperty(entity, "owid");
        super.getHibernateTemplate().bulkUpdate(hql, id);
    }

    @Override
    public <T> void removeById(Class<T> claz, String... entityIds) {
        List<Object> idList = new ArrayList<Object>();
        List<String> whereList = new ArrayList<String>();
        for (String string : entityIds) {
            idList.add(string);
            whereList.add(" t." + BaseEntity.PO_ID + "=? ");
        }
        String hql = "delete " + claz.getName() + " t where " + StringUtils.join(whereList.iterator(), " or ");
        super.getHibernateTemplate().bulkUpdate(hql, idList.toArray());
    }

    @Override
    public <T> void removeByIds(Collection<T> entities) {
        List<Object> idList = new ArrayList<Object>();
        List<String> whereList = new ArrayList<String>();
        String clzName = "";
        for (T t : entities) {
            Object id = BeanUtil.getProperty(t, BaseEntity.PO_ID);
            if (id instanceof String || id instanceof Number) {
                idList.add(id);
                whereList.add(" t." + BaseEntity.PO_ID + "=? ");
            }
            clzName = t.getClass().getSimpleName();
        }
        String hql = "delete " + clzName + " t where " + StringUtils.join(whereList.iterator(), " or ");
        super.getHibernateTemplate().bulkUpdate(hql, idList.toArray());
    }

    @Override
    public <T> void saveOrUpdate(T entity) {
        super.getHibernateTemplate().saveOrUpdate(entity);
    }

    @Override
    public <T> void saveOrUpdateAll(final Collection<T> entities) {
        getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        int index = 0;
                        for (T entity : entities) {
                            session.saveOrUpdate(entity);
                            index++;
                            //做批量更新
                            if (index % CommonConstants.BATCH_MAX_ROW == 0) {
                                session.flush();
                                session.clear();
                            }
                        }
                        session.flush();
                        session.clear();
                        return entities.size();
                    }
                });
    }

    @Override
    public Integer updateHqlByAttribute(final String hql, final Map<String, ?> params) {
        Integer updateCount = (Integer) super.getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        Query query = session.createQuery(hql);
                        query.setProperties(params);
                        return new Integer(query.executeUpdate());
                    }
                });
        return updateCount.intValue();
    }

    @Override
    public Integer updateHql(final String hql) {

        Integer updateCount = (Integer) super.getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        Query query = session.createQuery(hql);
                        return new Integer(query.executeUpdate());
                    }
                });
        return updateCount.intValue();
    }

    @Override
    public <T> void eagerLoad(T entity) {
        super.getHibernateTemplate().initialize(entity);
    }

    @Override
    public <T> void evict(T entity) {
        super.getHibernateTemplate().evict(entity);
    }

    @Override
    public <T> T findByIdNoWaitLock(Class<T> entityClass, Serializable id) {
        return (T) super.getHibernateTemplate().load(entityClass, id,
                LockMode.UPGRADE_NOWAIT);
    }

    @Override
    public <T> List<T> listByNamedQuery(String queryName, Map<String, Object> params) {
        Session session = getSessionFactory().openSession();
        Query query = session.getNamedQuery(queryName);
        query.setProperties(params);
        return query.list();
    }

    @Override
    public void flush() {
        super.getHibernateTemplate().flush();
    }

    @Override
    public <T> void lock(T entity, LockMode lock) {
        super.getHibernateTemplate().lock(entity, lock);
    }

    @Override
    public Integer countSqlByAttribute(final String countSql, final Map<String, ?> params) {
        return (Integer) getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        SQLQuery sqlQuery = session.createSQLQuery(countSql);
                        sqlQuery.setProperties(params);
                        List list = sqlQuery.list();
                        if (list != null && !list.isEmpty()) {
                            Object cnt = list.get(0);
                            if (cnt instanceof BigInteger) {
                                return ((BigInteger) cnt).intValue();
                            } else if (cnt instanceof Long) {
                                return ((Long) cnt).intValue();
                            } else if (cnt instanceof Integer) {
                                return ((Integer) cnt).intValue();
                            }
                        }
                        return null;
                    }
                });
    }

    @Override
    public long countSqlByAttribute(String countSql, Object[] params) {
        if (countSql == null) {
            log.error("nativeQueryCountSQL()-->sql参数==null");
            return -1;
        }
        List<Map<String, Object>> l = listSqlByParams(countSql, params);
        if (l == null) {
            log.error("nativeQueryCountSQL()-->count sql不正确.");
            return -1;
        }
        Map<String, ?> _map = l.get(0);
        Set set = _map.keySet();
        for (Object _obj : set) {
            if (null == _map.get(_obj.toString()))
                return 0;
            else
                return new Long(_map.get(_obj.toString()).toString());
        }

        return 0;
    }

    @Override
    public <T> PageInfo<T> listSqlPageByAttribute(final String sql, final String countSql, final Integer offsetIndex, final Integer pageSize, final Object... params) {
        PageInfo<T> pr = new PageInfo<T>();
        pr.setCurrentIndex(offsetIndex);
        pr.setPageSize(pageSize);
        if (null != countSql) {
            pr.setTotalCount(countSqlByAttribute(countSql, params));
        } else {
            pr.setTotalCount(countSqlByAttribute("select count(*) from (" + (sql) + ") _tt_", params));
        }
        if (pageSize != null && 0 != pageSize) {
            pr.setTotalPage((int) ((pr.getTotalCount() + pageSize - 1) / pageSize));
            pr.setCurrentPage((int) (((offsetIndex == 0 ? 1 : offsetIndex) * pageSize) / pageSize));
        }
        PageSql pageSql = new PageSql();
        String newSQL = pageSql.getPageSQL(sql, pr);
        try {
            List<Map<String, Object>> resultList = null;
            if (null != params)
                resultList = listSqlByParams(newSQL, params);
            else
                resultList = listSqlByParams(newSQL);
            pr.setRecords((List<T>) resultList);
            return pr;
        } catch (Exception e) {
            log.error("执行nativeQuerySQL()分页出错：" + newSQL, e);
            return null;
        }
    }

    @Override
    public Integer updateSqlByAttribute(final String sql, final Map<String, ?> params) {
        Integer result = (Integer) super.getHibernateTemplate().execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException {
                        SQLQuery sqlQuery = session.createSQLQuery(sql);
                        if (null != params)
                            sqlQuery.setProperties(params);
                        return Integer.valueOf(sqlQuery.executeUpdate());
                    }
                });
        return result.intValue();
    }

    @Override
    public Integer updateByNamedQuery(String queryName, Map<String, Object> params) {
        Session session = getSessionFactory().openSession();
        Query query = session.getNamedQuery(queryName);
        query.setProperties(params);
        return query.executeUpdate();
    }

    @Override
    public int getSequence(String sequenceName) {
        Session session = getHibernateTemplate().getSessionFactory()
                .getCurrentSession();
        String sql = "select " + sequenceName + ".Nextval as seq from dual";
        return (Integer) session.createSQLQuery(sql).list().get(0);
    }

    @Override
    public List<String> listTables(String schemaPattern, String tableNamePattern) {
        DataSource ds = SessionFactoryUtils.getDataSource(getHibernateTemplate().getSessionFactory());
        List<String> tables = new ArrayList<String>();
        Connection con = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();//super.getSession().connection();
            DatabaseMetaData meta = con.getMetaData();
            rs = meta.getTables(null, schemaPattern, tableNamePattern, new String[]{"TABLE"});
            while (rs.next()) {
                tables.add(rs.getString(3));
            }
        } catch (SQLException e) {
            log.error("listTables()", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    log.error("listTables()", e);
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    log.error("listTables()", e);
                }
            }
        }
        return tables;
    }

    @Override
    public List<String> listColumns(String tableName) {
        DataSource ds = SessionFactoryUtils.getDataSource(getHibernateTemplate().getSessionFactory());
        List<String> columns = new ArrayList<String>();

        String sql = "SELECT * FROM " + tableName + " WHERE 1 != 1";
        Connection con = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();//super.getSession().connection();
            rs = con.createStatement().executeQuery(sql);
            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();
            for (int i = 1; i <= cols; i++) {
                columns.add(meta.getColumnName(i));
            }
        } catch (SQLException e) {
            log.error("listColumns()", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    log.error("listColumns()", e);
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    log.error("getColumns()", e);
                }
            }
        }
        return columns;
    }

    @Override
    public Filter enableFilter(String name) {
        return getHibernateTemplate().enableFilter(name);
    }

    @Override
    public void disableFilter(String name) {
        getSessionFactory().getCurrentSession().disableFilter(name);
    }

    @Override
    public <T> void merage(T entity) {
        super.getHibernateTemplate().merge(entity);
    }

    @Override
    public void clear() {
        getHibernateTemplate().clear();
    }

    @Override
    public void clean(Object object) {
        getHibernateTemplate().getSessionFactory().getCurrentSession().refresh(object);
    }

    @Override
    public <T> void save(T entity) {
        super.getHibernateTemplate().save(entity);
    }

    @Override
    public <T> void update(T entity) {
        super.getHibernateTemplate().update(entity);
    }

    @Override
    public <T> List<T> listByAttribute(final String hql, final Object[] values) {
        return (List<T>) getHibernateTemplate().find(hql, values);
    }

    @Override
    public List<Map<String, Object>> listSqlByParams(String sql, Object... params) {
        DataSource ds = SessionFactoryUtils.getDataSource(getHibernateTemplate().getSessionFactory());
        Connection conn = null;
        PreparedStatement ps = null;
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        try {
            conn = ds.getConnection();
            String newsql = sql;
            log.info("listSqlByParams:" + newsql);
            ps = conn.prepareStatement(newsql);
            if (params != null) {
                int pIdx = 0;
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(pIdx + 1, params[i]);
                    pIdx++;
                }
            }
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    if (rs.getObject(i) == null
                            || "null".equals(rs.getObject(i))
                            || "".equals(rs.getObject(i)))
                        map.put(rsmd.getColumnLabel(i).toUpperCase(), "");
                    else
                        map.put(rsmd.getColumnLabel(i).toUpperCase(),
                                rs.getObject(i));
                }
                resultList.add(map);
            }
            rs.close();
            return resultList;
        } catch (SQLException e) {
            log.error(e);
            return null;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                log.error(e);
            }
        }
    }

    @Override
    public List<Map<String, Object>> nativeQuerySQL(String sql, Object[] params) {
        DataSource ds = SessionFactoryUtils.getDataSource(getHibernateTemplate().getSessionFactory());
        Connection conn = null;
        PreparedStatement ps = null;
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        try {
            conn = ds.getConnection();
            String newsql = sql;
            log.info("naviteSQL:" + newsql);
            ps = conn.prepareStatement(newsql);
            if (params != null) {
                int pIdx = 0;
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(pIdx + 1, params[i]);
                    pIdx++;
                }
            }
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    if (rs.getObject(i) == null
                            || "null".equals(rs.getObject(i))
                            || "".equals(rs.getObject(i)))
                        map.put(rsmd.getColumnLabel(i).toUpperCase(), "");
                    else {
                        map.put(rsmd.getColumnLabel(i).toUpperCase(),
                                rs.getObject(i));
                    }
                    // if(null!=rs.getBlob(i)){
                    // map.put(rsmd.getColumnLabel(i).toUpperCase(),
                    // rs.getBlob(i));
                    // }
                }
                resultList.add(map);
            }
            rs.close();
            return resultList;
        } catch (SQLException e) {
            log.error(e);
            return null;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                log.error(e);
            }
        }
    }

    @Override
    public void removeByParams(Class claz, Map<String, Object> params) {
        List<Object> idList = new ArrayList<Object>();
        List<String> whereList = new ArrayList<String>();
        Set<String> set = params.keySet();
        for (String string : set) {
            idList.add(params.get(string));
            whereList.add(" t." + string + "=? ");
        }
        String hql = "delete " + claz.getName() + " t where " + StringUtils.join(whereList.iterator(), " and ");
        super.getHibernateTemplate().bulkUpdate(hql, idList.toArray());
    }
}
