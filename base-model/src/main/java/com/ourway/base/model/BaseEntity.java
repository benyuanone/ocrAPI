package com.ourway.base.model;

import com.ourway.base.utils.BeanUtil;
import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 所有实体对象的基类
 * Created by jack on 2017/1/28.
 */
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String PO_ID =  PrimaryKey.PRIMARY_KEY;
    public static final String VER =  PrimaryKey.VER_KEY;
    public static final String LOGGROUPNAME =  PrimaryKey.LOG_GROUP_NAME;

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        Object id = null;
        try {
            id = BeanUtil.getProperty(this, PO_ID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Object otherId = null;
        Object id = null;
        try {
            otherId = BeanUtil.getProperty(obj, PO_ID);
            id = BeanUtil.getProperty(this, PO_ID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (id != null) {
            return id.equals(otherId);
        } else if (id == null && otherId == null) {
            PropertyDescriptor[] pds = BeanUtil.getPropertyDescriptors(this.getClass());
            Map <String, Object> src = new HashMap <String, Object>();
            for (PropertyDescriptor pd : pds) {
                Object v = null;
                try {
                    v = PropertyUtils.getProperty(this, pd.getName());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                if (v == null) {
                    continue;
                }
                src.put(pd.getName(), v);
            }

            PropertyDescriptor[] pds1 = BeanUtil.getPropertyDescriptors(obj.getClass());
            Map <String, Object> dest = new HashMap <String, Object>();
            for (PropertyDescriptor pd : pds1) {
                Object v = null;
                try {
                    v = PropertyUtils.getProperty(obj, pd.getName());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if (v == null) {
                    continue;
                }
                dest.put(pd.getName(), v);
            }

            if (src.keySet().size() != dest.keySet().size()) {
                return false;
            }
            for (String key : src.keySet()) {
                if (!src.get(key).equals(dest.get(key))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        /*
         * 如有需要可变此方法
		 */
        Object id = null;
        try {
            id = PropertyUtils.getProperty(this, PO_ID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (id == null) {
            return super.toString();
        }
        return id.toString();
    }

}
