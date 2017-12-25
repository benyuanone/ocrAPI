package com.ourway.base.service;


import com.ourway.base.model.BaseEntity;
import com.ourway.base.model.OurwaySysLog;
import com.ourway.base.dataobject.LogInfo;
import com.ourway.base.dataobject.PageInfo;
import org.hibernate.Filter;
import org.hibernate.LockMode;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * <p>接口 BaseService.java : <p>
 * <p>说明：系统通用的service类，其他所有业务的都必须先继承他</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/10 22:51
 * </pre>
 */
public interface BaseService<T extends BaseEntity> {
    /**
     * <p>方法:getOneById 根据指定ID返回数据记录，如果没找到指定数据记录则会返回null值。 </p>
     * <ul>
     * <li> @param id 记录ID可为数字或字串类型等</li>
     * <li>@return T  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 22:53  </li>
     * </ul>
     */
    public abstract T getOneById(Serializable id);


    /**
     * <p>方法:getOneByIdNoWait 根据指定ID返回数据库记录，但是用一个NO_WAIT锁的方式进行锁定,适用于当针对某个对象进行业务操作时（往往具有流程性），在方法入口处就使用该方法读取记录。 </p>
     * <ul>
     * <li> @param id 记录ID可为数字或字串类型等</li>
     * <li>@return T  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 22:56  </li>
     * </ul>
     */
    public abstract T getOneByIdNoWait(Serializable id);

    /**
     * <p>方法:getOneByUuid 根据指定UUID返回数据记录，如果没找到指定数据记录则会返回null值,注：调用该方法 必须保证 该pojo类中有uuid字段 </p>
     * <ul>
     * <li> @param uuid 记录UUID可谓数字或字符串类型</li>
     * <li>@return T  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 22:54  </li>
     * </ul>
     */
    public abstract T getOneByUuid(Serializable uuid);

    /**
     * <p>方法:getOneByParams 根据参数自动组成hql获取返回值 </p>
     * <ul>
     * <li> @param claz TODO</li>
     * <li> @param params TODO</li>
     * <li>@return T  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/22 13:01  </li>
     * </ul>
     */
    public abstract T getOneByParams(Map<String, Object> params, String sortSr);

    /**
     * <p>方法:listAll 查找所有类型为T的数据库记录 </p>
     * <ul>
     * <li>@return java.util.List<T>  所有数据库中指定为T类型的表记录</li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 22:53  </li>
     * </ul>
     */
    public abstract List<T> listAll();

    /**
     * <p>方法:listAllByHql 根据指定的查找记录数量的HQL语句，返回找到的符合条件的数据库记录. </p>
     * <ul>
     * <li> @param hql 类型的HQL语句</li>
     * <li> @param params hql中对应参数的值</li>
     * <li>@return java.util.List<T>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 22:56  </li>
     * </ul>
     */
    public abstract List<T> listAllByHql(String hql, Map<String, ?> params);

    /**
     * <p>方法:listAllByParam 获取列表 </p>
     * <ul>
     * <li> @param params 参数</li>
     * <li> @param sortStr 排序</li>
     * <li>@return java.util.List<T>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/22 13:21  </li>
     * </ul>
     */
    public abstract List<T> listAllByParam( Map<String, Object> params, String sortStr);

    /**
     * <p>方法:listObjsAllByHql 多表联合查询 </p>
     * <ul>
     * <li> @param hql TODO</li>
     * <li> @param params TODO</li>
     * <li>@return java.util.List<java.lang.Object[]>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/22 13:27  </li>
     * </ul>
     */
    public abstract List<Object[]> listObjsAllByHql(String hql, Map<String, ?> params);

    public abstract List<Object> listObjAllByHql(String hql,Map<String,?> params);

    /**
     * <p>方法:removeByIds 根据id批量进行删除记录 </p>
     * <ul>
     * <li> @param ids </li>
     * <li>@return boolean  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 22:52  </li>
     * </ul>
     */
    public abstract boolean removeByIds(Serializable... ids);

    public abstract boolean removeByIds(Object[] ids);


    /**
     * <p>方法:queryHqlForPage 根据指定的HQL语句，HQL计数语句，查询约束条件值的Map，返回找到的符合条件的数据库记录数，该记录封装于中 </p>
     * <ul>
     * <li> @param hql 查询用的HQL语句</li>
     * <li> @param countHql 查询记录数量用的HQL语句，可以通过HqlUtils.parseHqlCount方法生成。如果不需要返回数量，可以为null</li>
     * <li> @param params HQL语句对应的参数,<b>请注意：params中的key名称必须匹配到hql中的占位符名称</b></li>
     * <li> @param offsetIndex 返回的第一条记录位置，分页用。 如果不需要，可以为0</li>
     * <li> @param pageSize 分页的每页记录数，如果不需要，可以为0，则返回全部符合条件的记录</li>
     * <li>@return PageInfo<T>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 22:57  </li>
     * </ul>
     */
    public abstract PageInfo<T> listHqlForPage(String hql, String countHql,
                                               Map<String, ?> params, int offsetIndex,
                                               int pageSize);

    public abstract PageInfo<Object[]> listObjectHqlForPage(String hql, String countHql,
                                                            Map<String, ?> params, int offsetIndex,
                                                            int pageSize);


    /**
     * <p>方法:updateEntity 修改entity对象。只修改entity做变更过的对象属性，没有变更过的不做修改。
     * 当执行update则对entity中的updateBy,updateDate进行自动赋值，updateBy则为当前登录者用户Id(String类型)，
     * updateDate为当前日期； </p>
     * <ul>
     * <li> @param entity entity对象且entity.id必须在数据库中有对应的记录</li>
     * <li> @param propertyFilterSet 修改的属性名称</li>
     * <li> @param filterType 修改属性的策略类型
     * 策略类型：CommonConstant.FILTER_TYPE_INCLUDE则只修改propertyFilterSet中的属性值
     * 策略类型：CommonConstant.FILTER_TYPE_EXCLUDE则只修改propertyFilterSet中以外的属性值</li>
     * <li>@return java.util.List<LogInfo>  成功返回变更过的属性名称的日志记录列表</li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:01  </li>
     * </ul>
     */
    public abstract List<LogInfo> updateEntity(T entity, Set<String> propertyFilterSet, String filterType);


    /**
     * <p>方法:countHqlByParams 根据指定的查找记录数量的HQL语句，返回找到的符合条件的数据库记录数 </p>
     * <ul>
     * <li> @param countHql 类似于 select count(*) from ....</li>
     * <li> @param params countHql中对应参数的值 key为属性</li>
     * <li>@return int  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:04  </li>
     * </ul>
     */
    public abstract int countHqlByParams(String countHql, Map<String, ?> params);


    /**
     * <p>方法:removeEntity 根据 entity id单个删除 </p>
     * <ul>
     * <li> @param entity </li>
     * <li>@return java.util.List<LogInfo>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:11  </li>
     * </ul>
     */
    public abstract List<LogInfo> removeEntity(T entity);

    /**
     * <p>方法:removeEntitys 批量删除指定的数据库记录 </p>
     * <ul>
     * <li> @param entities 数据库记录实例集合，集合中的每个对象主键ID不可为null</li>
     * <li>@return   </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:12  </li>
     * </ul>
     */
    public abstract Map<String, List<LogInfo>> removeEntitys(Collection<T> entities);


    /**
     * <p>方法:saveOrUpdate  保存或更新指定T类型的数据库记录，根据entity的主键ID是否为null来自动判断采用insert或者update方法,如果是insert方法，则会更新entity的主键ID为最新的值 </p>
     * <ul>
     * <li> @param entity 数据库记录实例</li>
     * <li>@return boolean  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:12  </li>
     * </ul>
     */
    public abstract boolean saveOrUpdate(T entity);


    /**
     * <p>方法:save 保存或更新指定T类型的数据库记录并返回日志记录 </p>
     * <ul>
     * <li> @param entity po对象</li>
     * <li> @param propertyFilterSet 保存的属性名称集合，如果为空则保存全部属性</li>
     * <li> @param filterType 等于CommonConstant.FILTER_TYPE_INCLUDE保存propertyFilterSet中的属性值，等于CommonConstant.FILTER_TYPE_EXCLUDE则不保存propertyFilterSet中的属性值</li>
     * <li>@return java.util.List<LogInfo>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:17  </li>
     * </ul>
     */
    public abstract boolean save(T entity, Set<String> propertyFilterSet, String filterType);


    /**
     * <p>方法:updateAuditLog 更新entity的 updatedBy，updatedDate等用户操作信息 </p>
     * <ul>
     * <li> @param entity </li>
     * <li>@return boolean  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:18  </li>
     * </ul>
     */
    public abstract boolean updateAuditLog(T entity);


    /**
     * <p>方法:listByName 使用Hibernate Named Query查找数据库对象 </p>
     * <ul>
     * <li> @param queryName 命名在hbm.xml配置文件中的查询语句</li>
     * <li> @param params 查询语句的参数值</li>
     * <li>@return java.util.List<T>  匹配符合条件的记录列表</li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:19  </li>
     * </ul>
     */
    public abstract List<T> listByName(String queryName,
                                       Map<String, Object> params);

    /**
     * <p>方法:doFlush 手工主动flush Hibernate缓存数据到数据库中 </p>
     * <ul>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:20  </li>
     * </ul>
     */
    public abstract void doFlush();


    /**
     * <p>方法:doLock 将指定的实体加锁 </p>
     * <ul>
     * <li> @param entity 实体</li>
     * <li> @param lock Hibernate的锁模式</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:20  </li>
     * </ul>
     */
    public abstract void doLock(T entity, LockMode lock);


    /**
     * <p>方法:countSQLByParams 建议使用HQL模式的queryCount方法，但是某些特殊的数据库操作以SQL语句方式进行更有效率
     * 根据指定的查找记录数量的SQL语句，返回找到的符合条件的数据库记录数 </p>
     * <ul>
     * <li> @param countSql 类似于 select count(*) from ....</li>
     * <li> @param params countSql中对应参数的值</li>
     * <li>@return int  返回符合条件的数据库记录数</li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:21  </li>
     * </ul>
     */
    public abstract int countSQLByParams(String countSql,
                                         Map<String, ?> params);


    /**
     * <p>方法:listSqlForPage 通过SQL方式自动加载分页功能，但是没有使用Hibernate SQLQuery功能中所提供的分页 </p>
     * <ul>
     * <li> @param sql 查询用的HQL语句</li>
     * <li> @param countSql 查询记录数量用的HQL语句，可以通过HqlUtils.parseHqlCount方法生成。如果不需要返回数量，可以为null</li>
     * <li> @param params  HQL语句对应的参数</li>
     * <li> @param offsetIndex 返回的第一条记录位置，分页用。 如果不需要，可以为0</li>
     * <li> @param pageSize 分页的每页记录数，如果不需要，可以为0，则返回全部符合条件的记录</li>
     * <li>@return PageInfo<T>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:21  </li>
     * </ul>
     */
    public abstract PageInfo<T> listSqlForPage(String sql,
                                               String countSql, Map<String, ?> params,
                                               int offsetIndex, int pageSize);


    /**
     * <p>方法:updateSqlByParams 建议使用HQL模式的bulkUpdate方法，但是某些特殊的数据库操作以SQL语句方式进行更有效率 以SQL语句方式进行insert,
     * update, delete操作， 可以方便进行批量数据操作。例如批量删除符合某个条件的记录 批量更新符合某个条件的记录等等。 </p>
     * <ul>
     * <li> @param sql 进行insert, update, delete操作的SQL语句</li>
     * <li> @param params SQL语句中参数的值</li>
     * <li>@return int  该语句操作涉及到的数据库记录数</li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:22  </li>
     * </ul>
     */
    public abstract int updateSqlByParams(String sql, Map<String, ?> params);


    /**
     * <p>方法:updateByNamedQuery 使用Hibernate Named Query更新数据库对象 </p>
     * <ul>
     * <li> @param queryName 命名在hbm.xml配置文件中的查询语句</li>
     * <li> @param params 查询语句的参数值</li>
     * <li>@return int  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:23  </li>
     * </ul>
     */
    public abstract int updateByNamedQuery(String queryName, Map<String, Object> params);


    /**
     * <p>方法:getSequence 获取指定Sequence的新值。 </p>
     * <ul>
     * <li> @param sequenceName </li>
     * <li>@return int  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:23  </li>
     * </ul>
     */
    public abstract int getSequence(String sequenceName);


    /**
     * <p>方法:listTables 取得schema的表名称 </p>
     * <ul>
     * <li> @param schemaPattern </li>
     * <li> @param tableNamePattern </li>
     * <li>@return java.util.List<java.lang.String>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:24  </li>
     * </ul>
     */
    public abstract List<String> listTables(String schemaPattern, String tableNamePattern);


    /**
     * <p>方法:listColumns 根据表名取得column </p>
     * <ul>
     * <li> @param tableName 表名</li>
     * <li>@return java.util.List<java.lang.String>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:24  </li>
     * </ul>
     */
    public abstract List<String> listColumns(String tableName);


    /**
     * <p>方法:doEnableFilter 开启Hibernate过滤条件 </p>
     * <ul>
     * <li> @param name </li>
     * <li>@return org.hibernate.Filter  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:25  </li>
     * </ul>
     */
    public abstract Filter doEnableFilter(String name);


    /**
     * <p>方法:doDisableFilter 关闭Hibernate过滤条件 </p>
     * <ul>
     * <li> @param name </li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:25  </li>
     * </ul>
     */
    public abstract void doDisableFilter(String name);


    /**
     * <p>方法:save 仅新增entity </p>
     * <ul>
     * <li> @param entity </li>
     * <li>@return boolean  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:25  </li>
     * </ul>
     */
    public abstract boolean save(T entity);


    /**
     * <p>方法:update 仅更新entity </p>
     * <ul>
     * <li> @param entity TODO</li>
     * <li>@return boolean  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:26  </li>
     * </ul>
     */
    public abstract boolean update(T entity);


    /**
     * <p>方法:doClear 清除hibernate缓存 </p>
     * <ul>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:26  </li>
     * </ul>
     */
    public abstract void doClear();


    /**
     * <p>方法:saveOrUpdate 批量保存或更新指定T类型的数据库记录，根据entity的主键ID是否为null来自动判断采用insert或者update方法
     * 如果是insert方法，则会更新entity的主键ID为最新的值 </p>
     * <ul>
     * <li> @param entities T类型的数据库记录实例集合</li>
     * <li>@return boolean  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:26  </li>
     * </ul>
     */
    public abstract boolean saveOrUpdate(Collection<T> entities);


    /**
     * <p>方法:updateBulk 以HQL语句方式进行insert, update, delete操作， 可以方便进行批量数据操作。例如批量删除符合某个条件的记录
     * 批量更新符合某个条件的记录等等。 </p>
     * <ul>
     * <li> @param hql 进行insert, update, delete操作的HQL语句</li>
     * <li> @param params HQL语句中参数的值</li>
     * <li>@return int  该语句操作涉及到的数据库记录数</li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:27  </li>
     * </ul>
     */
    public abstract int updateBulk(String hql, Map<String, Object> params);


    /**
     * <p>方法:updateBulk 以HQL语句方式进行insert, update, delete操作， 可以方便进行批量数据操作。例如批量删除符合某个条件的记录
     * 批量更新符合某个条件的记录等等。 </p>
     * <ul>
     * <li> @param hql 进行insert, update, delete操作的HQL语句</li>
     * <li>@return int  该语句操作涉及到的数据库记录数</li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:27  </li>
     * </ul>
     */
    public abstract int updateBulk(String hql);


    /**
     * <p>方法:doEagerLoad 强制Hibernate立刻加载该entity对象数据实例 </p>
     * <ul>
     * <li> @param entity 如果entity==null则不做处理</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:28  </li>
     * </ul>
     */
    public abstract void doEagerLoad(T entity);


    /**
     * <p>方法:doEvict 将指定的entity对象数据实例从Hibernate Session关联中脱离 </p>
     * <ul>
     * <li> @param entity </li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:28  </li>
     * </ul>
     */
    public abstract void doEvict(T entity);


    /**
     * <p>方法:getOneByHql 根据hql查找唯一一记录 </p>
     * <ul>
     * <li> @param hql </li>
     * <li> @param params hql中对应的名称占位符所需的参数。</li>
     * <li>@return T  存在返回对象，不存在或不唯一报错。</li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:29  </li>
     * </ul>
     */
    public abstract T getOneByHql(String hql, Map<String, Object> params);

    /**
     * <p>方法:getOneByHql 根据hql查找唯一一记录 </p>
     * <ul>
     * <li> @param hql </li>
     * <li> @param values hql中对应的名称占位符所需的参数。</li>
     * <li>@return T  存在返回对象，不存在或不唯一报错。</li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:29  </li>
     * </ul>
     */
    public abstract T getOneByHql(String hql, Object... values);


    /**
     * <p>方法:listAllByParams 查询hql </p>
     * <ul>
     * <li> @param hql </li>
     * <li> @param values hql中对应”？“中的占位符参数值</li>
     * <li>@return java.util.List<T>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:30  </li>
     * </ul>
     */
    public abstract List<T> listAllByParams(String hql, Object... values);


    /**
     * <p>方法:getHibernateTemplate 获取hiberTemplatede </p>
     * <ul>
     * <li>@return org.springframework.orm.hibernate5.HibernateTemplate  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:30  </li>
     * </ul>
     */
    public abstract HibernateTemplate getHibernateTemplate();


    /**
     * <p>方法:listAllBySql 用sql语句获取列表 </p>
     * <ul>
     * <li> @param sql </li>
     * <li> @param params ?的参数</li>
     * <li>@return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/10 23:31  </li>
     * </ul>
     */
    public abstract List<Map<String, Object>> listAllBySql(final String sql, final Object... params);

    /**
     * <p>方法:doSysLog 根据业务类和操作类型产生业务日志，该方法专门用户自定义实现业务逻辑，不调用系统中的save，update，remove等方法 </p>
     * <ul>
     * <li> @param claz 业务日志类</li>
     * <li> @param operateFlag 操作类型(OurwaySysLog类中的NEW_FLAG，UPDATE_FLAG,REMOVE_FLAG)</li>
     * <li>@return com.ourway.base.model.OurwaySysLog  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/29 22:00  </li>
     * </ul>
     */
    public abstract OurwaySysLog doSysLog(Class claz, Integer operateFlag);


    /**
     * <p>方法:saveSysLog 存储单独的业务日志类，调用者必须对ourwaysyslog进行赋值后进行保存 </p>
     * <ul>
     * <li> @param log 业务日志实例</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/29 22:02  </li>
     * </ul>
     */
    public abstract void saveSysLog(OurwaySysLog log);


    public List<LogInfo> removeByParams(Map<String, Object> params);

    public List<LogInfo> removeByParams(Class  claz, Map<String, Object> params);

    public List<Map<String,Object>> listBySql (final String sql, final Object... params);

    public List <Map <String, Object>> queryAllBySql(String sql, Object... params);

}
