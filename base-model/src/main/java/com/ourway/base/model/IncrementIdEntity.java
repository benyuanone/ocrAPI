package com.ourway.base.model;

import com.ourway.base.utils.MapUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.lang.reflect.Method;
import java.util.Map;

/**<p>方法 IncrementIdEntity : <p>
*<p>说明:hibernate维护的自动新增</p>
*<pre>
*@author JackZhou
*@date 2017/3/11 16:28
</pre>
*/
@MappedSuperclass
public abstract class IncrementIdEntity<T extends BaseEntity> extends AbstractVerEntity {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(generator = "generator")
    @GenericGenerator(name="generator",strategy="increment")
    @Id
    @Column(name = "OWID", unique = true, nullable = false, precision = 10, scale = 0)
    protected Integer owid;

    public Integer getOwid() {
        return owid;
    }

    public void setOwid(Integer owid) {
        this.owid = owid;
    }

    /**
     * <p>功能描述：easySet 对象属性保存的时候，可缩减大量set型的语句</p>
     * <ul> 用法：model类继承IncrementIdEntity加上泛型指定类 AppBizPurchase extends IncrementIdEntity<AppBizPurchase>
     * <li>用法：在调用方法时，参数中写的显示需要set的属性名，然后在是值例如："appBizUserCode","123"</li>
     * <li>@param [args]</li>
     * <li>@return T</li>
     * <li>@throws </li>
     * <li>@author jackson</li>
     * <li>@date 17-4-8 下午9:19</li>
     * </ul>
     */
    public T easySet(Object... args) {
        Map<? extends Object, Object> paramMap = MapUtils.getMapFromArgs(args);
        transformObject(paramMap);
        return (T) this;
    }

    /**
     *<p>功能描述：easySetByMap 通过map为对象设值</p>
     *<ul>
     *<li>@param [paramMap]</li>
     *<li>@return T</li>
     *<li>@throws </li>
     *<li>@author jackson</li>
     *<li>@date 17-4-14 下午7:51</li>
     *</ul>
     */
    public T easySetByMap(Map paramMap){
        transformObject(paramMap);
        return (T)this;
    }

    private void transformObject(Map paramMap){
        // 取出bean里的所有方法
        Method[] methods = this.getClass().getMethods();
        for (int i = 0; i < methods.length; i++) {
            // 取方法名
            String method = methods[i].getName();
            // 取出方法的类型
            Class[] cc = methods[i].getParameterTypes();
            if (cc.length != 1)
                continue;

            // 如果方法名没有以set开头的则退出本次for
            if (method.indexOf("set") < 0)
                continue;
            // 类型
            String type = cc[0].getSimpleName();
            try {
                // 转成小写
                // Object value = method.substring(3).toLowerCase();
                Object value = method.substring(3, 4).toLowerCase() + method.substring(4);
                // 如果map里有该key
                if (paramMap.containsKey(value) && paramMap.get(value) != null) {
                    // 调用其底层方法
                    MapUtils.setValue(type, paramMap.get(value), i, methods, this);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
