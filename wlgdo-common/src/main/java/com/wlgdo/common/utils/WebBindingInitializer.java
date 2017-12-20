package com.wlgdo.common.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * 继承了ConfigurableWebBindingInitializer类型转换器
 * 
 * @author dk
 */
public class WebBindingInitializer extends ConfigurableWebBindingInitializer {

	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {

		super.initBinder(binder, request);

		// 自行扩展
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
		binder.registerCustomEditor(Boolean.class, new CustomBooleanEditor(true));
		binder.registerCustomEditor(Short.class, new CustomNumberEditor(Short.class, true));
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
		binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, true));
		binder.registerCustomEditor(Float.class, new CustomNumberEditor(Float.class, true));
		binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
		binder.registerCustomEditor(BigInteger.class, new CustomNumberEditor(BigInteger.class, true));
		binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, true));
	}
}