package com.ourway.sys;

import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.ClassTools;

import java.beans.PropertyDescriptor;
import java.util.Set;

/**
 * Created by Administrator on 2017/2/26.
 */
public class App {

    public  static  void  main(String[] args){
        Set<Class<?>> set = ClassTools.getClasses("com.ourway.sys.model");
        for(Class aClass:set){
            PropertyDescriptor[] props = BeanUtil.getPropertyDescriptors(aClass);
            for(PropertyDescriptor prop:props){
                System.out.println(prop.getName());
                System.out.println(prop.getPropertyType().getName());

            }

            //System.out.println(aClass.getName());
        }
    }
}
