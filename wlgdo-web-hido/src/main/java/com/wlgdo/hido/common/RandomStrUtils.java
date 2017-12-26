
package com.wlgdo.hido.common;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 包装自己的random工具
 * @author wlgdo[wlgchun@163.com] 2016年12月11日
 */
public class RandomStrUtils extends RandomStringUtils {

	static char chars[] = new char[] { '1', '2', '5', '6', '0','3', '9','8' };
	public static final String WLG_FIX="wyc";//回忆是最美好的纪念
	/**
	 * 生成定制化随机数
	 * @author wlgdo[wlgchun@163.com] 2016年12月11日 
	 * @param lenght
	 * @return String
	 */
	
	public static String getRandomSting(int lenght){
		return WLG_FIX+RandomStringUtils.random(lenght, chars);
	}

}
