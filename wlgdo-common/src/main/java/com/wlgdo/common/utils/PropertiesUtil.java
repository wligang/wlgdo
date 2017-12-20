package com.wlgdo.common.utils;

import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.PropertyPlaceholderHelper;

public class PropertiesUtil extends PropertyPlaceholderConfigurer {
	private static Properties globalProps;

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper(this.placeholderPrefix, this.placeholderSuffix);
		for (Entry<Object, Object> entry : props.entrySet()) {
			String key = StringUtil.toString(entry.getKey());
			String value = StringUtil.toString(entry.getValue());
			props.setProperty(key, helper.replacePlaceholders(value, props));
		}
		globalProps = props;
	}

	public static String prop(String name) {
		return StringUtil.toString(globalProps.get(name));
	}
}
