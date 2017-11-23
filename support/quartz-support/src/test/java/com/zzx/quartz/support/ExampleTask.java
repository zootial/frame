package com.zzx.quartz.support;

import org.quartz.JobExecutionContext;

@JobSchedule(cron={
		
	},
	group=QuartzGroup.TEST, jobName="ExampleTask", desc="示例任务"
)
public class ExampleTask extends SerializableTask {

	
	@Override
	public String executeJob(JobExecutionContext context) throws Exception {
		
		return "任务执行结果信息";
	}

	
}
