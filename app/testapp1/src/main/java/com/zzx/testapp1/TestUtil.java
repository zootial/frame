package com.zzx.testapp1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zzx.common.util.Hex64Util;

public class TestUtil {

	

	public static void main(String[] args) {
		test2();

	}

	static void test1() {
		String str = "in('99999', '88888')";

		Pattern p = Pattern.compile("\'([^\']+)\'");

		Matcher m = p.matcher(str);

		while (m.find()) {
			System.out.println(m.group(1));
		}
	}

	static void test2() {
		int i = 1000000;
		System.out.println(Hex64Util.encode(i));
		System.out.println(Hex64Util.decode(Hex64Util.encode(i)));
	}
	
	
	
}
