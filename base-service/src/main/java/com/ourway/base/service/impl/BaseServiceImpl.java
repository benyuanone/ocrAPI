package com.ourway.base.service.impl;

import com.ourway.base.dao.BaseDao;
import com.ourway.base.dataobject.LogInfo;
import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.log.LogInfoUtil;
import com.ourway.base.model.*;
import com.ourway.base.service.BaseService;
import com.ourway.base.utils.*;
import com.ourway.sys.model.OurwaySysEmploys;
import com.ourway.sys.model.OurwaySysObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.session.Session;
import org.hibernate.Filter;
import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

/**
 * Created by jack on 2017/1/30.
 */
@Transactional
@Service("baseService")
public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    @Autowired
    private BaseDao baseDao;

    private Class<T> entityClass;

    public BaseServiceImpl() {
        entityClass = (Class<T>) BeanUtil.getGenericReturnType(this);
//        if (null != entityClass)
//            System.out.println("=="+entityClass.getName());
    }

    public boolean removeByIds(Serializable... ids) {
        List<T> l = new ArrayList<T>();
        for (Serializable id : ids) {
            Object entity = null;
            try {
                entity = entityClass.newInstance();
            } catch (Exception e) {
                ExceptionUtil.throwException(e, "不能实例化类:{1}", entityClass.getName());
            }
            BeanUtil.setProperty(entity, PrimaryKey.PRIMARY_KEY, id);
            l.add((T) entity);
        }
        HqlStatement hs = HqlUtils.getWhereById(l);
        String hql = "";
        if (doCheckLogs(entityClass.getName())) {
            hql = " from " + entityClass.getName() + " where " + PrimaryKey.PRIMARY_KEY + " in (:owid)";
            List<Object> removeObjs = listObjAllByHql(hql, hs.getParams());
            for (Object removeObj : removeObjs) {
                updateRemoveLog(removeObj, entityClass.getName());
            }
        }
        hql = "delete from " + entityClass.getName() + " where " + PrimaryKey.PRIMARY_KEY + " in (:owid)";
        return this.baseDao.updateHqlByAttribute(hql, hs.getParams()) > 0;
    }

    @Override
    public boolean removeByIds(Object[] ids) {
        List<T> l = new ArrayList<T>();
        for (Object id : ids) {
            Object entity = null;
            try {
                entity = entityClass.newInstance();
            } catch (Exception e) {
                ExceptionUtil.throwException(e, "不能实例化类:{1}", entityClass.getName());
            }
            BeanUtil.setProperty(entity, PrimaryKey.PRIMARY_KEY, id);
            l.add((T) entity);
        }
        HqlStatement hs = HqlUtils.getWhereById(l);
        String hql = "";
        if (doCheckLogs(entityClass.getName())) {
            hql = " from " + entityClass.getName() + " where " + PrimaryKey.PRIMARY_KEY + " in (:owid)";
            List<Object> removeObjs = listObjAllByHql(hql, hs.getParams());
            for (Object removeObj : removeObjs) {
                updateRemoveLog(removeObj, entityClass.getName());
            }
        }
        hql = "delete from " + entityClass.getName() + " where " + PrimaryKey.PRIMARY_KEY + " in (:owid)";
        return this.baseDao.updateHqlByAttribute(hql, hs.getParams()) > 0;
    }

    public List<T> listAll() {
        return baseDao.listAllByClass(entityClass);
    }

    public T getOneById(Serializable id) {
        return baseDao.getOneById(entityClass, id);
    }

    public T getOneByUuid(Serializable uuid) {
        return baseDao.getOneByUUID(entityClass, uuid);
    }

    @Override
    public T getOneByParams(Map<String, Object> params, String sortSr) {
        HqlStatement hql = new HqlStatement(entityClass, params, sortSr);
        return baseDao.getOneByHqlStatement(hql);
    }

    public List<LogInfo> updateEntity(T entity, Set<String> propertyFilterSet, String filterType) {
        if (!(entity instanceof BaseEntity)) {
            ExceptionUtil.throwException("修改的对象不是{0}类！", BaseEntity.class.getName());
        }
        Object id = BeanUtil.getProperty(entity, BaseEntity.PO_ID);
        if (id == null) {
            ExceptionUtil.throwException("{0}:Id必须有值！", entity.getClass().getName());
        }
        /*
         * 确保entity不是持久对象
		 */
        doEvict(entity);
        /*
         * 加载修改前的数据
		 */
        T oldEntity = getOneById((Serializable) id);
        if (oldEntity == null) {
            ExceptionUtil.throwException("请确认{0}对象的id:{1}是否存在或是删除状态！", entity.getClass().getName(), id);
        }
        //添加修改日志的信息
        propertyFilterSet.add("updatetime");
        propertyFilterSet.add("updator");
        propertyFilterSet.add("updatorName");
        updateAuditLog(entity);
        update(entity);//g更新新的model
        BeanUtil.copyBean(entity, oldEntity, propertyFilterSet, filterType);
        return (List<LogInfo>) LogInfoUtil.getInstance().getUpdateLogInfo(entity, oldEntity, propertyFilterSet);
    }

    public T getOneByIdNoWait(Serializable id) {
        return baseDao.findByIdNoWaitLock(entityClass, id);
    }

    public List<T> listAllByHql(String hql, Map<String, ?> params) {

        return baseDao.listHqlByAttribute(hql, params);
    }

    @Override
    public List<T> listAllByParam(Map<String, Object> params, String sortStr) {
        HqlStatement hql = new HqlStatement(entityClass, params, sortStr);
        return baseDao.listHqlByAttribute(hql.getHql(), hql.getParams());
    }

    @Override
    public List<Object[]> listObjsAllByHql(String hql, Map<String, ?> params) {
        return baseDao.listObjsHqlByAttribute(hql, params);
    }

    @Override
    public List<Object> listObjAllByHql(String hql, Map<String, ?> params) {

        return baseDao.listObjHqlByAttribute(hql, params);
    }

    public PageInfo<T> listHqlForPage(String hql, String countHql, Map<String, ?> params, int offsetIndex, int pageSize) {
        return baseDao.listHqlPageByAttribute(hql, countHql, params, offsetIndex, pageSize);
    }

    @Override
    public PageInfo<Object[]> listObjectHqlForPage(String hql, String countHql, Map<String, ?> params, int offsetIndex, int pageSize) {
        return baseDao.listObjsHqlPageByAttribute(hql, countHql, params, offsetIndex, pageSize);
    }

    public int countHqlByParams(String countHql, Map<String, ?> params) {
        return baseDao.countHqlByAttribute(countHql, params);
    }

    public List<LogInfo> removeEntity(T entity) {
        String hql = "";
        if (doCheckLogs(entity.getClass().getName())) {
            hql = " from " + entity.getClass().getName() + " where " + PrimaryKey.PRIMARY_KEY + " in (:" + PrimaryKey.PRIMARY_KEY + ")";
            Object id = BeanUtil.getProperty(entity, BaseEntity.PO_ID);
            Map<String, Object> params = new HashMap<String, Object>(1);
            params.put(PrimaryKey.PRIMARY_KEY, id);
            List<Object> removeObjs = listObjAllByHql(hql, params);
            for (Object removeObj : removeObjs) {
                updateRemoveLog(removeObj, entity.getClass().getName());
            }
        }
        baseDao.removeById(entity);
        return null;
    }

    @Override
    public List<LogInfo> removeByParams(Map<String, Object> params) {
        updateListRemoveLogs(entityClass, params);
        baseDao.removeByParams(entityClass, params);
        return null;
    }

    @Override
    public List<LogInfo> removeByParams(Class claz, Map<String, Object> params) {
        updateListRemoveLogs(claz, params);
        baseDao.removeByParams(claz, params);
        return null;
    }

    public Map<String, List<LogInfo>> removeEntitys(Collection<T> entities) {
        String className = entities.iterator().next().getClass().getName();
        //开始记录日志
        HqlStatement hs = HqlUtils.getWhereById(entities);
        String queryHql = " from " + className + " where 1=1 ";
        queryHql += hs.getHql();
        List<Object> removeObjs = listObjAllByHql(queryHql, hs.getParams());
        for (Object removeObj : removeObjs) {
            updateRemoveLog(removeObj, className);
        }
        //日志记录结束
        String hql = "delete from " + className + " where 1=1 ";
        hql += hs.getHql();
        updateBulk(hql, hs.getParams());
        return null;
    }

    public boolean saveOrUpdate(T entity) {
        updateAuditLog(entity);
        Object id = BeanUtil.getProperty(entity, BaseEntity.PO_ID);
        int newVer = (Integer) BeanUtil.getProperty(entity, BaseEntity.VER);
        boolean flag = doCheckVer(newVer, id, entity.getClass());
        if (!flag)
            return false;

        OurwaySysLog log = updateSaveOrUpdateLogs(entity);
            baseDao.saveOrUpdate(entity);
        if (null != log && TextUtils.isEmpty(log.getLogBuzFlag())) {
            id = BeanUtil.getProperty(entity, BaseEntity.PO_ID);
            log.setLogBuzFlag(id.toString());
        }
        //保存日志
        if (null != log)
            baseDao.save(log);

        return true;
    }

    public boolean save(T entity, Set<String> propertyFilterSet, String filterType) {
        updateAuditLog(entity);
        baseDao.save(entity);
        OurwaySysLog log = updateSaveOrUpdateLogs(entity);
        if (null != log)
            baseDao.save(log);
        return true;
    }

    public boolean updateAuditLog(T entity) {
        if (entity instanceof AbstractAuditLogEntity) {
            AbstractAuditLogEntity ole = (AbstractAuditLogEntity) entity;
            if (null == ole.getDelflg())
                ole.setDelflg(0);
            if (null == ole.getState())
                ole.setState(0);
            /*
             * 判断 是否是新增记录还是更新记录
			 */
            Object id = BeanUtil.getProperty(entity, BaseEntity.PO_ID);
            Session session = ShiroUtils.getSession();
            OurwaySysEmploys employs = (OurwaySysEmploys) session.getAttribute(SessionUtils.USER_KEY);
            if (null == employs)
                return false;
            if (null == id) {
                ole.setCreator(employs.getOwid());
                ole.setCreatetime(new Date());
                ole.setCreatorName(employs.getEmpName());
                ole.setDelflg(0);
                if (null == ole.getState())
                    ole.setState(0);
            } else {
                ole.setLasupdate(new Date());
                ole.setUpdator(employs.getOwid());
                ole.setUpdatorName(employs.getEmpName());
            }

        }

        if (entity instanceof AbstractVerEntity) {
            AbstractVerEntity ole = (AbstractVerEntity) entity;
            Object id = BeanUtil.getProperty(entity, BaseEntity.PO_ID);
            if (null == ole.getVer()) {
                ole.setVer(1);
                ole.setVertime(new Date());
            } else {
                ole.setVer(ole.getVer() + 1);
                ole.setVertime(new Date());
            }

        }
        return true;
    }


    public List<T> listByName(String queryName, Map<String, Object> params) {
        return baseDao.listByNamedQuery(queryName, params);
    }

    public void doFlush() {
        baseDao.flush();
    }

    @Override
    public void doLock(T entity, LockMode lock) {
        baseDao.lock(entity, lock);
    }

    public int countSQLByParams(String countSql, Map<String, ?> params) {
        return baseDao.countSqlByAttribute(countSql, params);
    }

    public PageInfo<T> listSqlForPage(String sql, String countSql, Map<String, ?> params, int offsetIndex, int pageSize) {
        return baseDao.listSqlPageByAttribute(sql, countSql, offsetIndex, pageSize, params);
    }

    public int updateSqlByParams(String sql, Map<String, ?> params) {
        return baseDao.updateSqlByAttribute(sql, params);
    }

    public int updateByNamedQuery(String queryName, Map<String, Object> params) {
        return baseDao.updateByNamedQuery(queryName, params);
    }

    public int getSequence(String sequenceName) {
        return baseDao.getSequence(sequenceName);
    }

    public List<String> listTables(String schemaPattern, String tableNamePattern) {
        return baseDao.listTables(schemaPattern, tableNamePattern);
    }

    public List<String> listColumns(String tableName) {
        return baseDao.listColumns(tableName);
    }

    public Filter doEnableFilter(String name) {
        return baseDao.enableFilter(name);
    }

    public void doDisableFilter(String name) {
        baseDao.disableFilter(name);
    }

    public boolean save(T entity) {
        updateAuditLog(entity);
        baseDao.save(entity);
        OurwaySysLog log = updateSaveOrUpdateLogs(entity);
        if (null != log)
            baseDao.save(log);
        return true;
    }

    public boolean update(T entity) {
        updateAuditLog(entity);
        Object id = BeanUtil.getProperty(entity, BaseEntity.PO_ID);
        int newVer = (Integer) BeanUtil.getProperty(entity, BaseEntity.VER);
        boolean flag = doCheckVer(newVer, id, entity.getClass());
        if (!flag)
            return false;
        OurwaySysLog log = updateSaveOrUpdateLogs(entity);
        baseDao.update(entity);
        if (null != log)
            baseDao.save(log);
        return true;
    }

    public void doClear() {
        baseDao.clear();
    }

    public boolean saveOrUpdate(Collection<T> entities) {
        for (T entity : entities) {
//            updateAuditLog(entity);
            saveOrUpdate(entity);
        }
//        baseDao.saveOrUpdateAll(entities);
        return true;
    }

    public int updateBulk(String hql, Map<String, Object> params) {
        return baseDao.updateHqlByAttribute(hql, params);
    }

    @Override
    public int updateBulk(String hql) {
        return baseDao.updateHql(hql);
    }

    public void doEagerLoad(T entity) {
        baseDao.eagerLoad(entity);
    }

    public void doEvict(T entity) {
        baseDao.evict(entity);
    }

    public T getOneByHql(String hql, Map<String, Object> params) {
        List<T> results = baseDao.listHqlByAttribute(hql, params);
        if (null == results || results.size() <= 0) {
            return null;
        }
        return results.get(0);
    }

    public T getOneByHql(String hql, Object... values) {
        List<T> results = baseDao.listByAttribute(hql, values);
        if (null == results || results.size() <= 0) {
            return null;
        }
        return results.get(0);
    }

    public List<T> listAllByParams(String hql, Object... values) {
        return baseDao.listByAttribute(hql, values);
    }

    public HibernateTemplate getHibernateTemplate() {
        return baseDao.getHibernateTpl();
    }


    @Override
    public List<Map<String, Object>> listAllBySql(String sql, Object... params) {
        return baseDao.listByAttribute(sql, params);
    }

    @Override
    public OurwaySysLog doSysLog(Class claz, Integer operateFlag) {
        return new OurwaySysLog(claz, operateFlag);
    }

    @Override
    public void saveSysLog(OurwaySysLog log) {
        baseDao.save(log);
    }


    @Override
    public List<Map<String, Object>> listBySql(String sql, Object... params) {
        return baseDao.listSqlByParams(sql, params);
    }

    @Override
    public List<Map<String, Object>> queryAllBySql(String sql, Object... params) {
        return baseDao.nativeQuerySQL(sql, params);
    }


    //检查是否要记录日志
    private boolean doCheckLogs(String className) {
        String hql = " from OurwaySysObject where className=:className and classLog=1";
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("className", className);
        List<Object> objs = listObjAllByHql(hql, map);
        if (null != objs && objs.size() > 0)
            return true;
        return false;
    }

    //删除记录日志
    private void updateRemoveLog(Object obj, String className) {
        String refIds = "";
        String jsonData = JsonUtil.toJson(obj);
        Object id = BeanUtil.getProperty(obj, BaseEntity.PO_ID); //获取主键
        Set<String> pros = BeanUtil.getPropertiesName(obj.getClass());
        for (String pro : pros) {
            if (pro.endsWith("RefOwid")) {
                refIds += "," + BeanUtil.getProperty(obj, pro) + ",";
            }
        }
        OurwaySysLog log = new OurwaySysLog();
        log.setLogBuzFlag(id.toString());
        log.setLogClassName(className);
        log.setLogBuzRefFlag(refIds);
        log.setLogType(3); //删除日志
        log.setLogOldValue(jsonData);
        Session session = ShiroUtils.getSession();
        OurwaySysEmploys employs = (OurwaySysEmploys) session.getAttribute(SessionUtils.USER_KEY);
        log.setLogOperateId(employs.getOwid());
        log.setLogOperateName(employs.getEmpName());
        log.setLogOperateTime(new Date());
        log.setVer(1);
        log.setVertime(new Date());
        log.setCreatetime(new Date());
        log.setCreator(employs.getEmpName());
        log.setDelflg(0);
        log.setState(0);
        baseDao.save(log);
    }

    //根据传入的条件，批量增加日志
    private void updateListRemoveLogs(Class claz, Map<String, Object> params) {
        String hql = "";
        if (doCheckLogs(claz.getName())) {
            List<Object> idList = new ArrayList<Object>();
            List<String> whereList = new ArrayList<String>();
            Set<String> set = params.keySet();
            for (String string : set) {
                idList.add(params.get(string));
                whereList.add(" t." + string + "=:" + string);
            }
            hql = "from  " + claz.getName() + " t where " + StringUtils.join(whereList.iterator(), " and ");
            List<Object> removeObjs = listObjAllByHql(hql, params);
            for (Object removeObj : removeObjs) {
                updateRemoveLog(removeObj, claz.getName());
            }
        }
    }

    //记录保存或者更新的日志
    private OurwaySysLog updateSaveOrUpdateLogs(T newEntity) {
        Session session = ShiroUtils.getSession();
        if (null == session)
            return null;
        OurwaySysEmploys employs = (OurwaySysEmploys) session.getAttribute(SessionUtils.USER_KEY);
        if (null == employs)
            return null;
        T oldEntity = null;
        String className = newEntity.getClass().getName();

        boolean flag = doCheckLogs(className);
        if (!flag)
            return null;
        Object id = BeanUtil.getProperty(newEntity, BaseEntity.PO_ID);
        Object groupName = BeanUtil.getProperty(newEntity, BaseEntity.LOGGROUPNAME);
        String refIds = "";
        String jsonOldData = "";
        String jsonNewData = "";
        jsonNewData = JsonUtil.toJson(newEntity);
        if (null != id && !TextUtils.isEmpty(id)) {
            //表示有old的值
            if (id instanceof String)
                oldEntity = getOneById(id.toString());
            if (id instanceof Integer)
                oldEntity = getOneById((Integer) id);
        }

        if (null != oldEntity)
            jsonOldData = JsonUtil.toJson(oldEntity);
        OurwaySysLog log = new OurwaySysLog();
        //日志分组
        if (!TextUtils.isEmpty(groupName))
            log.setLogLabel(groupName.toString());
        if (null != id && !TextUtils.isEmpty(id)) {
            log.setLogBuzFlag(id.toString());
            log.setLogType(2); //删除日志
        } else {
            log.setLogType(1);

        }
        Set<String> pros = BeanUtil.getPropertiesName(newEntity.getClass());
        for (String pro : pros) {
            if (pro.endsWith("RefOwid")) {
                refIds += "," + BeanUtil.getProperty(newEntity, pro) + ",";
            }
        }
        log.setLogClassName(className);
        log.setLogBuzRefFlag(refIds);
        log.setLogOldValue(jsonOldData);
        log.setLogNewValue(jsonNewData);

        log.setLogOperateId(employs.getOwid());
        log.setLogOperateName(employs.getEmpName());
        log.setLogOperateTime(new Date());
        log.setVer(1);
        log.setVertime(new Date());
        log.setDelflg(0);
        log.setState(0);
        if (null != oldEntity)
            baseDao.evict(oldEntity);
        return log;
    }

    //判断版本是否同可以
    private boolean doCheckVer(int newVer, Object id, Class claz) {
        if (null == id)
            return true;
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put(BaseEntity.PO_ID, id);
        Object obj = getOneByHql("from " + claz.getName() + " where " + BaseEntity.PO_ID + "=:" + BaseEntity.PO_ID, params);
        if (null != obj) {
            if (null != BeanUtil.getProperty(obj, BaseEntity.VER)) {
                int oldVer = (Integer) BeanUtil.getProperty(obj, BaseEntity.VER);
                if (newVer - 1 < oldVer)
                    return false;
            }
            baseDao.evict(obj);
        }
        return true;
    }

}
