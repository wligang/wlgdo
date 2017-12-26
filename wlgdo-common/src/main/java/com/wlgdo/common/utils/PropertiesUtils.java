package com.wlgdo.common.utils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * 
 * 资源文件工具类 <br>
 *
 * 创建日期:2016年2月18日<br>
 * 修改历史:<br>
 * 1. [2016年2月18日]创建文件 by zhujingbo<br>
 */
public class PropertiesUtils {

	private static final String DEFAULT_RESOURCE = "config";
	private static final Map<String, ResourceBundle> bundleMap = new HashMap<String, ResourceBundle>();

	/**
	 * 
	 * 获取资源文件中的key<br>
	 * 
	 * @param key
	 *            资源文件中定义的键
	 * @param resource
	 *            资源文件名字,不指定使用DEFAULT_RESOURCE(config)<br>
	 *            ps:第一次指定时将resource载入内存,以后读这个文件的资源均从内存中加载
	 * @return
	 */
	public static String getVal(String key, String... resource) {
		String resouceName = (resource != null && resource.length > 0) ? resource[0] : DEFAULT_RESOURCE;
		if (!bundleMap.containsKey(resouceName)) {
			bundleMap.put(resouceName, ResourceBundle.getBundle(resouceName));
		}
		try {
			return bundleMap.get(resouceName).getString(key);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * 获取资源文件中的key并格式化<br>
	 * 
	 * @param key
	 *            资源文件中定义的键
	 * @param params
	 *            资源参数
	 * @param resource
	 *            资源文件名字,不指定使用DEFAULT_RESOURCE(config)<br>
	 *            ps:第一次指定时将resource载入内存,以后读这个文件的资源均从内存中加载
	 * @return
	 */
	public static String getFormatVal(String key, Object[] params, String... resource) {
		String val = getVal(key, resource);
		return MessageFormat.format(val, params);
	}

	/**
	 * 
	 * 刷新resource<br>
	 *
	 * @param resource
	 *            指定resource文件名称,不指定使用DEFAULT_RESOURCE(config)
	 */
	public static void reloadResource(String... resource) {
		String resouceName = (resource != null && resource.length > 0) ? resource[0] : DEFAULT_RESOURCE;
		bundleMap.put(resouceName, ResourceBundle.getBundle(resouceName));
	}

}
