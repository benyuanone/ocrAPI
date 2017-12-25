package com.ourway.base.utils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jack on 2017/1/27.
 */
public class CompareUtil {

    private static final Set<Class<?>> dataTypeSet = new HashSet<Class<?>>();

    private static Set<Class<?>> getDataTypeSet() {
        dataTypeSet.add(Date.class);
        dataTypeSet.add(Number.class);
        dataTypeSet.add(String.class);
        dataTypeSet.add(Boolean.class);
        return dataTypeSet;
    }

    /*
    * 判断两个对象是否相等
    * 用于比较数字，字符串，boolean和date四种类型
    * */
    public static boolean isNotEqual(Object srcValue, Object destValue) {
        if (destValue == null && srcValue == null) {
            return false;
        } else if (destValue != null && srcValue == null) {
            return true;
        } else if (destValue == null && srcValue != null) {
            return true;
        } else if (destValue instanceof String && srcValue instanceof String) {
            return !destValue.toString().equals(srcValue.toString());
        } else if (destValue instanceof Date && srcValue instanceof Date) {
            return ((Date) destValue).getTime() != ((Date) srcValue).getTime();
        } else if (destValue instanceof Number && srcValue instanceof Number) {
            double dest = ((Number) destValue).doubleValue();
            double src = ((Number) srcValue).doubleValue();
            return src != dest;
        } else if (destValue instanceof Boolean && srcValue instanceof Boolean) {
            return ((Boolean) destValue).booleanValue() != ((Boolean) srcValue).booleanValue();
        } else {
            return srcValue == null ? false : !destValue.equals(srcValue);
        }
    }

    /*
    *  判断是否是一般数据类型或者在系统中的weapperclass
    *
    * */
    public static boolean isRegDataType(Class<?> dataType) {
        if (dataType.isPrimitive()) {
            return true;
        } else if (getDataTypeSet().contains(dataType)) {
            return true;
        }
        for (Class<?> class1 : getDataTypeSet()) {
            if (dataType.getSuperclass() == class1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Integer i = 100;
        Float b = 100.00f;
        System.out.println(isNotEqual(i, b));
    }

}
