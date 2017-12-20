package com.wlgdo.common.utils;

import java.lang.reflect.Field;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;

/**
 * 一组getBean方法 .<br>
 * 
 * @author dk <br>
 */
public class SpringUtils implements BeanFactoryAware {
	static Logger log = LoggerFactory.getLogger(SpringUtils.class);

	private static BeanFactory beanFactory;
	private static ApplicationContext applicationContext;

	/**
	 * 通过bean的id从上下文中拿出该对象
	 */
	public static Object getBean(String beanId) {
		return beanFactory == null ? applicationContext.getBean(beanId) : beanFactory.getBean(beanId);
	}

	/**
	 * 通过bean的id从上下文中拿出该对象
	 */
	public static <T> T getBean(Class<T> clazz, String beanId) {
		return beanFactory == null ? applicationContext.getBean(beanId, clazz) : clazz.cast(beanFactory.getBean(beanId));
	}

	/**
	 * 通过bean的type从上下文中拿出该对象
	 */
	public static <T> T getBean(Class<T> clazz) {
		return beanFactory == null ? applicationContext.getBean(clazz) : beanFactory.getBean(clazz);
	}

	public static <T> Map<String, T> getBeanOfType(Class<T> clazz) {
		return beanFactory == null ? applicationContext.getBeansOfType(clazz) : ((DefaultListableBeanFactory) beanFactory).getBeansOfType(clazz);
	}

	public static String[] getBeanNamesForType(Class<?> clazz) {
		return beanFactory == null ? applicationContext.getBeanNamesForType(clazz) : ((DefaultListableBeanFactory) beanFactory).getBeanNamesForType(clazz);
	}
	
	public static Class<?> getType(String type){
		return beanFactory == null ? applicationContext.getType(type) : ((DefaultListableBeanFactory) beanFactory).getType(type);
	}

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		SpringUtils.beanFactory = beanFactory;
		log.info("SpringUtils has been saved beanFactory in a static variable!");
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringUtils.applicationContext = applicationContext;
		log.info("SpringUtils has been saved applicationContext in a static variable!");
	}

	/**
	 * 获取 目标对象
	 * 
	 * @param proxy
	 *            代理对象
	 */
	public static Object getTarget(Object proxy) {

		if (!AopUtils.isAopProxy(proxy)) {
			return proxy;// 不是代理对象
		}

		try {
			if (AopUtils.isJdkDynamicProxy(proxy)) {
				return getJdkDynamicProxyTargetObject(proxy);
			} else { // cglib
				return getCglibProxyTargetObject(proxy);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
		Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
		h.setAccessible(true);
		Object dynamicAdvisedInterceptor = h.get(proxy);

		Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
		advised.setAccessible(true);

		Object target = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();

		return target;
	}

	private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
		Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
		h.setAccessible(true);
		AopProxy aopProxy = (AopProxy) h.get(proxy);

		Field advised = aopProxy.getClass().getDeclaredField("advised");
		advised.setAccessible(true);

		Object target = ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();

		return target;
	}
}