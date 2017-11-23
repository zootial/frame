package com.zzx.common.util;

import java.util.Arrays;

public class Hex64Util {

	private final static char[] E = { '+', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '=', 'A', 'B', 'C', 'D', 'E',
			'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
			'v', 'w', 'x', 'y', 'z' };
	
	public static String encode(long value) {
		long positive = Math.abs(value);
		int idx = 0;
		int radix = 64;
		char[] charArr = new char[11];
		for (idx = 1; idx <= radix; idx++){
			charArr[charArr.length - idx] = E[(int)(positive % radix)];
			positive /= radix;
			if (positive == 0) { break; }
		}
		return new String(charArr, charArr.length - idx, idx);
	}
	
	public static long decode(String value) {
		int fromBase = 64;
		String v = null;
		if(value == null || (v = value.trim()) == ""){
			throw new IllegalArgumentException(value);
		}
		long result = 0;
		for (int i = 0; i < v.length(); i++){
			char vi = v.charAt(v.length() - i - 1);
			int idx = Arrays.binarySearch(E, 0, E.length, vi);
			if(idx < 0){
				throw new IllegalArgumentException(String.format("The char \"%s\" is invalid", vi));
			}
			result += (long)Math.pow(fromBase, i) * idx;
		}
		
		return result;
	}
}
