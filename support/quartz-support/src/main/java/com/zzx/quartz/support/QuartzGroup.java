package com.zzx.quartz.support;

import java.util.ArrayList;
import java.util.List;

public enum QuartzGroup {

	TEST;
	
	public static List<String> vals() {
		List<String> list = new ArrayList<String>();
		for(QuartzGroup e : QuartzGroup.values()){
			list.add(e.name());
		}
		return list;
	}
}
