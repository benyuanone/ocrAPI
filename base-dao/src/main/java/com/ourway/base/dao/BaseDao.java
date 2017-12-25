package com.ourway.base.dao;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.utils.HqlStatement;
import org.hibernate.Filter;
import org.hibernate.LockMode;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * <p>接口 BaseDao.java : <p>
 * <p>说明：数据库操作底层类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/7 1:09
 * </pre>
 */
public interface BaseDao {

    /**
     * <p>方法:getHibernateTpl 返回HibernateTemplate对象 </p>
     * <ul>
     * <li>@return org.springframework.orm.hibernate5.HibernateTemplate  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 1:09  </li>
     * </ul>
     */
    public abstract HibernateTemplate getHibernateTpl();


    /**
     * <p>方法:listByAttribute  根据类中某几个属性的值获取符合记录的对象列表 </p>
     * <ul>
     * <li> @param example 当前的对象以及需要进行条件判断的属性值，如user.setName("Jack") user.setGendar("male"),则查找姓名是jack和性别是male的数据列表</li>
     * <li>@return java.util.List<T>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 22:34  </li>
     * </ul>
     */
    public abstract <T> List<T> listByAttribute(T example);


    /**
     * <p>方法:listAllByClass 获取指定对象的所有数据 </p>
     * <ul>
     * <li> @param entityClass 指定的对象</li>
     * <li>@return java.util.List<T>  所有数据库中指定为T类型的表记录 </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 22:38  </li>
     * </ul>
     */
    public abstract <T> List<T> listAllByClass(Class<T> entityClass);


    /**
     * <p>方法:getOneById 根据指定ID返回数据记录，如果没找到指定数据记录则会返回null值。 </p>
     * <ul>
     * <li> @param entityClass 制定的class，其主键的ID必须为id</li>
     * <li> @param id entityClass主键值</li>
     * <li>@return T  符合指定id的唯一数据库记录，或null值</li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 22:40  </li>
     * </ul>
     */
    public abstract <T> T getOneById(Class<T> entityClass, Serializable id);

    public abstract <T> T getOneByHqlStatement(HqlStatement hqlStatement);

    /**
     * <p>方法:getOneByUUID 根据指定UUID返回数据记录，如果没有找到指定数据记录则会返回null值。 </p>
     * <ul>
     * <li> @param entityClass 主键值,其id必须为id</li>
     * <li> @param uuid 唯一UUID</li>
     * <li>@return T  符合指定UUID的唯一数据库记录，或null值</li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 22:42  </li>
     * </ul>
     */
    public abstract <T> T getOneByUUID(Class<T> entityClass, Serializable uuid);


    /**
     * <p>方法:listHqlByAttribute 根据指定的查找记录数量的HQL语句，返回找到的符合条件的数据库记录. </p>
     * <ul>
     * <li> @param hql 类型的SQL语句</li>
     * <li> @param params hql中对应参数的值,key为属性，value为值</li>
     * <li>@return java.util.List<T>  返回符合条件的数据库记录.</li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 22:45  </li>
     * </ul>
     */
    public abstract <T> List<T> listHqlByAttribute(final String hql, final Map<String, ?> params);

    public abstract List<Object[]> listObjsHqlByAttribute(final String hql, final Map<String, ?> params);

    public abstract List<Object> listObjHqlByAttribute(final String hql, final Map<String, ?> params);

    /**
     * <p>方法:listByAttribute 查询hql </p>
     * <ul>
     * <li> @param hql </li>
     * <li> @param values hql中对应”？“中的占位符参数值</li>
     * <li>@return java.util.List<T>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 0:39  </li>
     * </ul>
     */
    public abstract <T> List<T> listByAttribute(String hql, Object[] values);

    /**
     * <p>方法:countHqlByAttribute 根据指定的查找记录数量的HQL语句，返回找到的符合条件的数据库记录数 </p>
     * <ul>
     * <li> @param countHql 类似于 select count(*) from .... 类型的SQL语句</li>
     * <li> @param params countHql中对应参数的值,key为属性，value为值</li>
     * <li>@return java.lang.Integer  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 22:46  </li>
     * </ul>
     */
    public abstract Integer countHqlByAttribute(final String countHql, final Map<String, ?> params);

    /**
     *
     * */
    /**
     * <p>方法:listHqlPageByAttribute 通过hql来获取分页数据 </p>
     * <ul>
     * <li> @param hql 用户查询的hql语句</li>
     * <li> @param countHql 统计数量的hql语句</li>
     * <li> @param params 参数的值,key为属性，value为值</li>
     * <li> @param pageNo 取第几页的值，为空，自动取第一页</li>
     * <li> @param pageSize 每页的行数，为空获取0 取全部的数据</li>
     * <li>@return PageInfo<T>  返回PageInfo对象 </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 22:49  </li>
     * </ul>
     */
    public abstract <T> PageInfo<T> listHqlPageByAttribute(final String hql, final String countHql,
                                                           final Map<String, ?> params, final Integer pageNo,
                                                           final Integer pageSize);

    /**
     * <p>方法:listSqlPageByAttribute 用于多表查询联合查询的分页 </p>
     * <ul>
     * <li> @param sql TODO</li>
     * <li> @param countSql TODO</li>
     * <li> @param offsetIndex TODO</li>
     * <li> @param pageSize TODO</li>
     * <li> @param params TODO</li>
     * <li>@return com.ourway.base.dataobject.PageInfo<java.lang.Object>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/30 10:40  </li>
     * </ul>
     */
    public abstract PageInfo<Object[]> listObjsHqlPageByAttribute(final String hql, final String countHql,
                                                                  final Map<String, ?> params, final Integer pageNo,
                                                                  final Integer pageSize);

    /**
     * <p>方法:removeById 删除指定id的T对象 </p>
     * <ul>
     * <li> @param entity 指定的t对象，必须设置id的值</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 22:53  </li>
     * </ul>
     */
    public abstract <T> void removeById(T entity);

    public abstract <T> void removeById(Class<T> claz, String... entityIds);

    public void removeByParams(Class claz, Map<String, Object> params);

    /**
     * <p>方法:removeByIds 多选对象进行删除,支持对象主键为id，且id为数字或者字符类型 </p>
     * <ul>
     * <li> @param entities 传入的对象集合，每个对象必须设置其id，主键的值</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 23:01  </li>
     * </ul>
     */
    public abstract <T> void removeByIds(Collection<T> entities);


    /**
     * <p>方法:saveOrUpdate 保存或更新指定T类型的数据库记录，根据obj的主键ID是否为null来自动判断采用insert或者update方法,如果是insert方法，则会更新obj的主键ID为最新的值 </p>
     * <ul>
     * <li> @param entity 数据库记录实例</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 23:03  </li>
     * </ul>
     */
    public abstract <T> void saveOrUpdate(T entity);


    /**
     * <p>方法:saveOrUpdate 批量保存或更新指定T类型的数据库记录，根据entities每个主键ID是否为null来自动判断采用insert或者update方法如果是insert方法，则会更新obj的主键ID为最新的值 </p>
     * <ul>
     * <li> @param entities T类型的数据库记录实例集合</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 23:04  </li>
     * </ul>
     */
    public abstract <T> void saveOrUpdateAll(Collection<T> entities);


    /**
     * <p>方法:updateHqlByAttribute 以HQL语句方式进行insert, update, delete操作， 可以方便进行批量数据操作。例如批量删除符合某个条件的记录 </p>
     * <ul>
     * <li> @param hql 进行insert, update, delete操作的HQL语句</li>
     * <li> @param params HQL语句中参数的值,Map<参数名称，参数值></li>
     * <li>@return java.lang.Integer  该语句操作涉及到的数据库记录数</li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 23:33  </li>
     * </ul>
     */
    public abstract Integer updateHqlByAttribute(final String hql, final Map<String, ?> params);


    /**
     * <p>方法:updateHql 以HQL语句方式进行insert, update, delete操作， 可以方便进行批量数据操作。例如批量删除符合某个条件的记录 </p>
     * <ul>
     * <li> @param hql 进行insert, update, delete操作的HQL语句</li>
     * <li>@return java.lang.Integer  该语句操作涉及到的数据库记录数</li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 23:34  </li>
     * </ul>
     */
    public abstract Integer updateHql(final String hql);


    /**
     * <p>方法:eagerLoad 强制Hibernate立刻加载该obj对象数据实例 </p>
     * <ul>
     * <li> @param entity </li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 23:34  </li>
     * </ul>
     */
    public abstract <T> void eagerLoad(T entity);


    /**
     * <p>方法:evict 将指定的obj对象数据实例从Hibernate Session关联中脱离 </p>
     * <ul>
     * <li> @param entity </li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/7 23:34  </li>
     * </ul>
     */
    public abstract <T> void evict(T entity);


    /**
     * <p>方法:findByIdNoWaitLock 根据指定ID返回数据库记录，但是用一个NO_WAIT锁的方式进行锁定。 </p>
     * 适用于当针对某个对象进行业务操作时（往往具有流程性），在方法入口处就使用该方法读取记录。
     * <ul>
     * <li> @param entityClass po对象</li>
     * <li> @param id entityClass主键值</li>
     * <li>@return T  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 0:02  </li>
     * </ul>
     */
    public abstract <T> T findByIdNoWaitLock(Class<T> entityClass, Serializable id);


    /**
     * <p>方法:findByNamedQuery 使用Hibernate Named Query查找数据库对象 </p>
     * <ul>
     * <li> @param queryName 命名在hbm.xml配置文件中的查询语句</li>
     * <li> @param params 查询语句的参数值</li>
     * <li>@return java.util.List<T>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 0:03  </li>
     * </ul>
     */
    public abstract <T> List<T> listByNamedQuery(String queryName, Map<String, Object> params);


    /**
     * <p>方法:flush 手工主动flush Hibernate缓存数据到数据库中 </p>
     * <ul>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 0:05  </li>
     * </ul>
     */
    public abstract void flush();


    /**
     * <p>方法:lock 将指定的实体加锁 </p>
     * <ul>
     * <li> @param entity 实体</li>
     * <li> @param lock Hibernate的锁模式</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 0:05  </li>
     * </ul>
     */
    public abstract <T> void lock(T entity, LockMode lock);


    /**
     * <p>方法:countSqlByAttribute 根据指定的查找记录数量的SQL语句，返回找到的符合条件的数据库记录数 </p>
     * <ul>
     * <li> @param countSql 类似于 select count(*) from ....</li>
     * <li> @param params countSql中对应参数的值，key为字段名，value是对应字段的值</li>
     * <li>@return java.lang.Integer  返回符合条件的数据库记录数</li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 0:06  </li>
     * </ul>
     */
    public abstract Integer countSqlByAttribute(final String countSql, final Map<String, ?> params);


    /**
     * <p>方法:countSqlByAttribute 根据指定的查找记录数量的SQL语句，返回找到的符合条件的数据库记录数 </p>
     * <ul>
     * <li> @param countSql sql</li>
     * <li> @param params 带“？”对应的占位符参数值</li>
     * <li>@return long  异常返回-1，否则返回大于等于0的值</li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 0:09  </li>
     * </ul>
     */
    public abstract long countSqlByAttribute(String countSql, Object[] params);


    /**
     * <p>方法:listSqlPageByAttribute 通过SQL方式自动加载分页功能，但是没有使用Hibernate SQLQuery功能中所提供的分页， 因为其有Bug，请参见 </p>
     * http://opensource.atlassian.com/projects/hibernate/browse/HHH-2383
     * <ul>
     * <li> @param sql 查询sql</li>
     * <li> @param countSql 查询记录数量用的SQL语句</li>
     * <li> @param offsetIndex 返回的第一条记录位置，分页用。 如果不需要，可以为0</li>
     * <li> @param pageSize 分页的每页记录数，如果不需要，可以为0，则返回全部符合条件的记录</li>
     * <li> @param params SQL语句对应的参数</li>
     * <li>@return PageInfo<T>  根据查询条件返回的记录内容，如果有分Sqlsql页需求，则PageResult中包含分页所需的各种信息 </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 0:31  </li>
     * </ul>
     */
    public abstract <T> PageInfo<T> listSqlPageByAttribute(final String sql, final String countSql, final Integer offsetIndex, final Integer pageSize, final Object... params);


    /**
     * <p>方法:updateSqlByAttribute SQL语句方式进行更有效率 以SQL语句方式进行insert,
     * update, delete操作， 可以方便进行批量数据操作。例如批量删除符合某个条件的记录 批量更新符合某个条件的记录等等。 </p>
     * <ul>
     * <li> @param sql 进行insert, update, delete操作的SQL语句</li>
     * <li> @param params SQL语句中参数的值，key为字段名称，value是值</li>
     * <li>@return java.lang.Integer  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 0:32  </li>
     * </ul>
     */
    public abstract Integer updateSqlByAttribute(final String sql, final Map<String, ?> params);


    /**
     * <p>方法:updateByNamedQuery 使用Hibernate Named Query更新数据库对象 </p>
     * <ul>
     * <li> @param queryName 命名在hbm.xml配置文件中的查询语句</li>
     * <li> @param params 查询语句的参数值</li>
     * <li>@return java.lang.Integer  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 0:34  </li>
     * </ul>
     */
    public abstract Integer updateByNamedQuery(String queryName, Map<String, Object> params);


    /**
     * <p>方法:getSequence  获取指定Sequence的新值。仅限于oracle,db2等 </p>
     * <ul>
     * <li> @param sequenceName TODO</li>
     * <li>@return int  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 0:34  </li>
     * </ul>
     */
    public abstract int getSequence(String sequenceName);


    /**
     * <p>方法:listTables 取得schema的表名称 </p>
     * <ul>
     * <li> @param schemaPattern schema名称</li>
     * <li> @param tableNamePattern 表名称</li>
     * <li>@return java.util.List<java.lang.String>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 0:35  </li>
     * </ul>
     */
    public abstract List<String> listTables(String schemaPattern, String tableNamePattern);


    /**
     * <p>方法:listColumns 根据表名取得column </p>
     * <ul>
     * <li> @param tableName </li>
     * <li>@return java.util.List<java.lang.String>  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 0:36  </li>
     * </ul>
     */
    public abstract List<String> listColumns(String tableName);


    /**
     * <p>方法:enableFilter 开启Hibernate过滤条件 </p>
     * <ul>
     * <li> @param name TODO</li>
     * <li>@return org.hibernate.Filter  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 0:37  </li>
     * </ul>
     */
    public abstract Filter enableFilter(String name);


    /**
     * <p>方法:disableFilter 关闭Hibernate过滤条件 </p>
     * <ul>
     * <li> @param name </li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 0:37  </li>
     * </ul>
     */
    public abstract void disableFilter(String name);


    /**
     * <p>方法:merage 合并entity </p>
     * <ul>
     * <li> @param entity </li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 0:37  </li>
     * </ul>
     */
    public abstract <T> void merage(T entity);


    /**
     * <p>方法:clear 清除hibernate缓存中的pojo对象 </p>
     * <ul>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 0:37  </li>
     * </ul>
     */
    public abstract void clear();

    public abstract void clean(Object object);


    /**
     * <p>方法:save 仅做新增记录（与saveOrUpdate不同） </p>
     * <ul>
     * <li> @param entity </li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 0:37  </li>
     * </ul>
     */
    public abstract <T> void save(T entity);


    /**
     * <p>方法:update 仅做update </p>
     * <ul>
     * <li> @param entity </li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 0:38  </li>
     * </ul>
     */
    public abstract <T> void update(T entity);


    /**
     * <p>方法:listSqlByParams sql语句查询 </p>
     * <ul>
     * <li> @param sql  查询用的SQL语句</li>
     * <li> @param params SQL语句?对应的参数</li>
     * <li>@return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>  根据查询条件返回的记录内容</li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/8 0:40  </li>
     * </ul>
     */
    public abstract List<Map<String, Object>> listSqlByParams(final String sql, final Object... params);


    List<Map<String, Object>> nativeQuerySQL(String sql, Object[] params);
}