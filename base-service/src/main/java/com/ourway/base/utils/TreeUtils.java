package com.ourway.base.utils;

import com.ourway.base.model.BaseTree;

/**
 * <p>方法 ValidateUtils : <p>
 * <p>说明:t通用校验类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/4/12 20:00
 * </pre>
 */
public class TreeUtils {


    public static BaseTree convert(Object obj, String[] condition) {
        if (null == condition)
            return null;
        BaseTree tree = new BaseTree();
        for (String s : condition) {
            String[] _tmp = s.split("\\-");
            Object _obj = BeanUtil.getProperty(obj, _tmp[0]);
            BeanUtil.setProperty(tree, _tmp[1], _obj);
        }
        return tree;
    }

    public static void main(String[] args) {

    }
}
