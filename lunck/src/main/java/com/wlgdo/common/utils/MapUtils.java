package com.wlgdo.common.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 纯净版map与Object之间的转化工具，没有三方包的依赖
 * 
 * @author Ligang.Wang[wang_lg@suixingpay.com]
 * @date 2017年9月25日 下午3:07:59
 */
public class MapUtils {
    /**
     * 将对象转化未map(常见于对象得合并分发)
     * 
     * @author Ligang.Wang[wang_lg@suixingpay.com]
     * @date 2017年9月25日下午3:17:32
     * @param map
     * @param obj
     * @return Map<String,Object>
     */
    public static Map<String, Object> objectToMap(Map<String, Object> map, Object obj) {
        if (map == null) {
            return null;
        }

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (key.compareToIgnoreCase("class") == 0) {
                    continue;
                }
                Method getter = property.getReadMethod();
                Object value = getter != null ? getter.invoke(obj) : null;
                map.put(key, value);
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * map转为对象
     * 
     * @author Ligang.Wang[wang_lg@suixingpay.com]
     * @date 2017年9月26日上午11:10:22
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     *             Object
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null) {
            return null;
        }
        Object obj = beanClass.newInstance();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            Method setter = property.getWriteMethod();
            if (setter != null) {
                setter.invoke(obj, map.get(property.getName()));
            }
        }
        return obj;
    }

}
