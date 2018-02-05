package com.zzx.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static final Pattern LINE_PATTERN = Pattern.compile("[_-](\\w)");
	
	public static String toCamelCase(String src) {
		return toCamelCase(src, LINE_PATTERN);
	}
	
	public static String toCamelCase(String src, Pattern pattern) {
		Matcher matcher = pattern.matcher(src);
		StringBuffer build = new StringBuffer();
		while(matcher.find()){
			matcher.appendReplacement(build, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(build);
		return build.toString();
	}
	
	public static String firstCharUpperCase(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str.length());
		sb.append(Character.toUpperCase(str.charAt(0)));
		sb.append(str.substring(1));
		return sb.toString();
	}
	
	public static String firstCharLowerCase(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str.length());
		sb.append(Character.toLowerCase(str.charAt(0)));
		sb.append(str.substring(1));
		return sb.toString();
	}
}
