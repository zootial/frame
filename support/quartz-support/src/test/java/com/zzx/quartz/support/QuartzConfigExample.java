package com.zzx.quartz.support;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;


/**
 * quartz配置示例
 */
//@Configuration
public class QuartzConfigExample extends BaseQuartzConfig {
	private final static List<Class<? extends Job>> TASKLIST = new ArrayList<Class<? extends Job>>();
	
	static {
//		TASKLIST.add(VendorHotelSyncTask.class);
	}

	@Override
	protected List<Class<? extends Job>> getTaskList() {
		return TASKLIST;
	}
	
}
