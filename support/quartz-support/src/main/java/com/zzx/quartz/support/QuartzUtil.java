package com.zzx.quartz.support;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.ScheduleBuilder;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.util.CollectionUtils;

public class QuartzUtil {

	public static List<Trigger> initializeTriggers(List<Class<? extends Job>> TASKLIST){
    	List<Trigger> list = new ArrayList<Trigger>();
    	for(Class<? extends Job> clz : TASKLIST){
    		JobSchedule schedule = clz.getAnnotation(JobSchedule.class);
    		if(schedule == null) continue;
    		List<Trigger> triggers = createTrigger(schedule);
    		if(!CollectionUtils.isEmpty(triggers))
    			list.addAll(triggers);
    	}
    	return list;
    }
    
    public static List<JobDetail> initializeJobDetails(List<Class<? extends Job>> TASKLIST){
    	List<JobDetail> list = new ArrayList<JobDetail>();
    	for(Class<? extends Job> clz : TASKLIST){
    		JobDetail jobDetail = createJobDetail(clz);
    		if(jobDetail != null)
    			list.add(jobDetail);
    	}
    	return list;
    }

    /**
     * 创建任务
     */
    static JobDetail createJobDetail(Class<? extends Job> jobClass) {
    	JobSchedule schedule = jobClass.getAnnotation(JobSchedule.class);
    	if(schedule == null) return null;
        JobBuilder jb = JobBuilder.newJob(jobClass);
		jb.withIdentity(schedule.jobName(), schedule.group().name());
		jb.withDescription(schedule.desc());
		jb.requestRecovery();
		jb.storeDurably();
		JobDetail jobDetail = jb.build();
		return jobDetail;
    }
    
    /**
     * 创建定时器
     */
    static List<Trigger> createTrigger(JobSchedule schedule) {
    	List<Trigger> list = new ArrayList<Trigger>();
    	String[] crons = schedule.cron();
    	List<String> cronList = null;
    	if(crons != null) {
    		cronList = Arrays.asList(crons);
    	}
    	
    	long fixedRate = schedule.fixedRate();
    	if(CollectionUtils.isEmpty(cronList) && fixedRate <= 0){
    		return null;
    	}
    	JobKey jobKey = new JobKey(schedule.jobName(), schedule.group().name());
		Trigger trigger = null;
		if(!CollectionUtils.isEmpty(cronList)){
			for(String cron : cronList){
				TriggerBuilder<Trigger> tb = 
						newTriggerBuilder(schedule.group().name(), cron.replaceAll(" ", "_"), schedule.initialDelay());
				ScheduleBuilder<CronTrigger> schBuider = CronScheduleBuilder.cronSchedule(cron);
				trigger = tb.withSchedule(schBuider)
				.withDescription("cronExpression: " + cron)
				.forJob(jobKey)
				.build();
				list.add(trigger);
			}
		}
		else if(fixedRate > 0){
			TriggerBuilder<Trigger> tb = 
					newTriggerBuilder(schedule.group().name(), "fixedRate" + schedule.fixedRate() + "ms", schedule.initialDelay());
			SimpleScheduleBuilder schBuider = SimpleScheduleBuilder.simpleSchedule();
			schBuider.withIntervalInMilliseconds(schedule.fixedRate())
			.repeatForever();
			trigger = tb.withSchedule(schBuider)
			.withDescription("fixedRate: " + schedule.fixedRate() + "ms")
			.forJob(jobKey)
			.build();
			list.add(trigger);
		}
        return list;
    }
    
    private static TriggerBuilder<Trigger> newTriggerBuilder(String groupName, String triggerName, int initialDelay){
    	TriggerBuilder<Trigger> tb = TriggerBuilder.newTrigger();
		tb.withIdentity(triggerName, groupName);
		if(initialDelay <= 0){
			tb.startNow();
		} else {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MILLISECOND, (int)initialDelay);
			tb.startAt(cal.getTime());
		}
		return tb;
    }
}
