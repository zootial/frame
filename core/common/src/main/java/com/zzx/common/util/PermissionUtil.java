package com.zzx.common.util;

public class PermissionUtil {

	/**
	 * 验证权限
	 * 
	 * @param limitSum
	 *            权限总和 为2的权限指数相加
	 * @param checkVal
	 *            具体权限
	 * @return
	 */
	public static boolean checkLimit(int limitSum, int checkVal) {
		return (limitSum & (1 << checkVal)) > 0;
	}

	/**
	 * 生成权限集
	 * 
	 * @param limits
	 * @return
	 */
	public static int genLimitSet(Integer[] limits) {
		int limitSet = 0;
		for (int i = 0; i < limits.length; i++) {
			limitSet += (1 << limits[i]);
		}
		return limitSet;
	}
}
