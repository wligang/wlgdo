package com.wlgdo.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * java反射帮助类 提供对象,属性,方法之间转换的常用方法 <br>
 * 在对象或者类中,有的属性不可访问,可以通过 xxx.setAccessible(true);
 * @author: Ligang.Wang[wang_lg@suixingpay.com] 
 * @date:  2017年11月20日 上午10:25:51
 * @Copyright ©2017 Suixingpay. All rights reserved.
 */
public class ReflectionUtils extends org.springframework.util.ReflectionUtils {

    /**
     * 通过对象,方法名称以及参数 来 调用方法
     */
    public static Object invokeMethod(Object owner, String methodName, Object... args) throws Exception {
        Class<?> clazz = owner.getClass();
        Method method = findMethod(clazz, methodName);
        method.setAccessible(true);
        return method.invoke(owner, args);
    }

    /**
     * 通过对象,方法名称以及参数 来 调用方法
     */
    public static Object invokeMethod(Object owner, String methodName, Class<?>[] parameterTypes, Object... args)
                                                                                                                 throws Exception {
        Class<?> clazz = owner.getClass();
        Method method = findMethod(clazz, methodName, parameterTypes);
        method.setAccessible(true);
        return method.invoke(owner, args);
    }

    public static Class<?> getGenericClass(Class<?> clazz) {
        return getGenericClass(clazz, 0);
    }

    public static Class<?> getGenericClass(Class<?> clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        if (genType instanceof ParameterizedType) {
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            if ((params != null) && (params.length >= index - 1)) {
                return ((Class<?>) params[index]);
            }
        }
        return null;
    }

    /**
     * 获取所有申明的字段,不包含Object类的
     * 
     * @param recursion
     *            是否递归
     */
    public static Field[] getDeclaredFields(Class<?> clazz, boolean recursion) {
        List<Field> list = new ArrayList<>();
        try {
            while (clazz != null && !clazz.isPrimitive() && clazz != Object.class) {// 当父类为null的时候说明到达了最上层的父类(Object类).
                list.addAll(0, Arrays.asList(clazz.getDeclaredFields()));
                clazz = clazz.getSuperclass(); // 得到父类,然后赋给自己
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list.toArray(new Field[] {});
    }

    /**
     * 获取所有申明的方法,不包含Object类的
     * 
     * @param recursion
     *            是否递归
     */
    public static Method[] getDeclaredMethods(Class<?> clazz, boolean recursion) {
        List<Method> list = new ArrayList<>();
        try {
            while (clazz != null && !clazz.isPrimitive() && clazz != Object.class) {// 当父类为null的时候说明到达了最上层的父类(Object类).
                list.addAll(0, Arrays.asList(clazz.getDeclaredMethods()));
                clazz = clazz.getSuperclass(); // 得到父类,然后赋给自己
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list.toArray(new Method[] {});
    }

    /**
     * 通过一个对象 以及 一个属性名称,取出该属性的值
     */
    public static Object getPropertyValue(Object owner, String fieldName) throws Exception {
        if (owner != null) {
            Class<?> clazz = owner.getClass();
            Field field = findField(clazz, fieldName);
            field.setAccessible(true);
            return field.get(owner);
        }
        return null;
    }
}