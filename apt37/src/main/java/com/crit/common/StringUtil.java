package com.crit.common;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具类
 */
public class StringUtil {
	// 定义script的正则表达式
	private static String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
	// 定义style的正则表达式
	private static String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
	// 定义HTML标签的正则表达式
	private static String regEx_html = "<[^>]+>";
	private static String randomStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	// 邮箱正则
	private static final String regEx_email = "^([a-zA-Z0-9_\\-\\.]+)@[^!@#$%^&*()+|.]+\\.{1}[^!@#$%^&*()+|.]+(\\.{1}[^!@#$%^&*()+|.]+)*?$";
	// 手机正则
	private static final String regEx_mobile = "^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$";
	// 整数正则
	private static String regEx_Int = "^-?\\d+$";

	/**
	 * 判断是否为空
	 */
	public static boolean isEmpty(Object str) {
		return str == null || "".equals(str.toString().trim());
	}

	/**
	 * 字符串转化为整数
	 */
	public static Integer str2Int(String value) {
		try {
			if (isEmpty(value)) {
				return null;
			}
			return Integer.valueOf(value.split("\\.")[0]);
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 字符串转化为长整数
	 */
	public static Long str2Long(String value) {
		try {
			if (isEmpty(value)) {
				return null;
			}
			return Long.parseLong(value);
		} catch (Throwable e) {
			return null;
		}
	}

	/**
	 * 字符串转化为实数
	 */
	public static Double str2Double(String value) {
		try {
			if (isEmpty(value)) {
				return null;
			}
			return Double.parseDouble(value);
		} catch (Throwable e) {
			return null;
		}
	}

	public static BigDecimal str2BigDecimal(Double value) {
		try {
			if (isEmpty(value)) {
				return null;
			}
			return BigDecimal.valueOf(value);
		} catch (Throwable e) {
			return null;
		}
	}

	public static boolean isNum(String value) {
		try {
			Integer.parseInt(value);
		} catch (Throwable e) {
			return false;
		}
		return true;
	}

	public static boolean isDouble(String value) {
		try {
			Double.valueOf(value);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean isInt(String value) {
		Pattern rgx = Pattern.compile(regEx_Int);
		return rgx.matcher(value).matches();
	}

	/**
	 * 对象转化为字符串
	 */
	public static String toString(Object str) {
		return isEmpty(str) ? "" : str.toString();
	}

	/**
	 * 字符数组转整数数组
	 */
	public static Integer[] toInt(String[] params) {
		Integer[] result = new Integer[params.length];
		for (int i = 0; i < params.length; i++) {
			try {
				Integer val = Integer.parseInt(params[i]);
				result[i] = val;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return result;
	}

	/**
	 * 数组arr拼接成以sep分割的字符串
	 */
	public static String join(Object[] arr, String sep) {
		if (arr == null || arr.length <= 0) {
			return "";
		}
		StringBuilder str = new StringBuilder();
		if (arr instanceof String[]) {
			for (Object val : arr) {
				str.append("'" + val + "'");
				str.append(sep);
			}
		} else {
			for (Object val : arr) {
				str.append(val);
				str.append(sep);
			}
		}
		int index = str.lastIndexOf(sep);
		if (index >= 0) {
			str = str.replace(index, (index + sep.length()), "");
		}
		return str.toString();
	}

	/**
	 * 去除html标签
	 */
	public static String delHTMLTag(String htmlStr) {
		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		return htmlStr.trim(); // 返回文本字符串
	}

	// 注册时随即生成字符串为用户名
	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	// 注册时随即生成字符串为验证码
	public static String getRandomCode(int length) { // length表示生成字符串的长度
		String base = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static String getRandomNum(int length, String randomV) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		String v = StringUtils.isEmpty(randomV) ? StringUtil.randomStr : randomV;
		for (int i = 0; i < length; i++) {
			sb.append(v.charAt(random.nextInt(v.length())));
		}
		return sb.toString().toLowerCase();
	}

	public static String encodeUtf8ToIso8859(String str) {
		try {
			return new String(str.getBytes("utf-8"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return str;
		}
	}

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	public static boolean isEmail(String email) {
		Pattern rgx = Pattern.compile(regEx_email);
		return rgx.matcher(email).matches();
	}

	public static boolean isMobile(String mobile) {
		Pattern rgx = Pattern.compile(regEx_mobile);
		return rgx.matcher(mobile).matches();
	}

	public static void main(String[] args) {
		String aa = "/pa/page.do";
		System.out.println(aa.substring(aa.indexOf("/"), aa.indexOf("/", 1) + 1));
	}
}
