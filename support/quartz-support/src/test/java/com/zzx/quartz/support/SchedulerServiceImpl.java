package com.zzx.quartz.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;

public class SchedulerServiceImpl implements SchedulerService {

	private Scheduler quartzScheduler;
	public SchedulerServiceImpl(Scheduler quartzScheduler){
		this.quartzScheduler = quartzScheduler;
	}
	
	@Override
	public void addJob(String group, String jobName, String className, String desc) throws SchedulerException, ClassNotFoundException {
		@SuppressWarnings("unchecked")
		Class<Job> jobCls = (Class<Job>)Class.forName(className);
		addJob(group, jobName, jobCls, desc);
	}
	@Override
	public void addJob(String group, String jobName, Class<Job> jobCls, String desc) throws SchedulerException {
		addJob(group, jobName, jobCls, null, desc);
	}

	@Override
	public void addJob(String group, String jobName, Class<Job> jobCls, Map<String, Object> parameterMap,
			String description) throws SchedulerException {
		addJob(group, jobName, jobCls, parameterMap, description, false);
	}

	@Override
	public void addJobOrOverride(String group, String jobName, Class<Job> jobCls, Map<String, Object> parameterMap,
			String description) throws SchedulerException {
		addJob(group, jobName, jobCls, parameterMap, description, true);
	}
	
	private void addJob(String group, String jobName, Class<Job> jobCls, Map<String, Object> parameterMap,
			String description, boolean replace) throws SchedulerException {
		JobBuilder jb = JobBuilder.newJob(jobCls);
		jb.withIdentity(jobName, group);
		if(parameterMap != null){
			JobDataMap map = new JobDataMap();
			map.putAll(parameterMap);
			jb.usingJobData(map);
		}
		jb.storeDurably();
		jb.requestRecovery();
		jb.withDescription(description);
		JobDetail jobDetail = jb.build();
		quartzScheduler.addJob(jobDetail, replace);
	}
	
	@Override
	public boolean jobExists(String group, String jobName) throws SchedulerException {
		JobKey key = new JobKey(jobName, group);
		return quartzScheduler.checkExists(key);
	}
	
	@Override
	public JobDetail getJobDetail(String group, String jobName)  throws SchedulerException {
		JobKey key = new JobKey(jobName, group);
		return quartzScheduler.getJobDetail(key);
	}

	@Override
	public void delJob(String group, String jobName) throws SchedulerException {
		JobKey key = new JobKey(jobName, group);
		quartzScheduler.deleteJob(key);
	}

	@Override
	public List<JobDetail> getJobList(String group) throws SchedulerException {
		List<JobDetail> list = new ArrayList<JobDetail>();
		GroupMatcher<JobKey> matcher = GroupMatcher.groupEquals(group);
		Set<JobKey> set = quartzScheduler.getJobKeys(matcher);
		for(JobKey jobKey : set) {
			JobDetail detail= quartzScheduler.getJobDetail(jobKey);
			list.add(detail);
	    }
		return list;
	}

	@Override
	public void addTrigger(String group, String jobName, String triggerName, String cronExpression)
			throws SchedulerException {
		JobKey jobKey = new JobKey(jobName, group);
		TriggerBuilder<Trigger> tb = TriggerBuilder.newTrigger();
		tb.withIdentity(triggerName, group);
		tb.startNow();
		ScheduleBuilder<CronTrigger> schBuider = CronScheduleBuilder.cronSchedule(cronExpression);
		tb.withSchedule(schBuider);
		tb.withDescription("cronExpression: " + cronExpression);
		tb.forJob(jobKey);
		Trigger trigger = tb.build();
		quartzScheduler.scheduleJob(trigger);
	}

	@Override
	public boolean triggerExists(String group, String triggerName) throws SchedulerException {
		TriggerKey triggerKey = new TriggerKey(triggerName, group);
		return quartzScheduler.checkExists(triggerKey);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trigger> getTriggersByJob(String group, String jobName) throws SchedulerException {
		JobKey key = new JobKey(jobName, group);
		return (List<Trigger>)quartzScheduler.getTriggersOfJob(key);
	}

	@Override
	public TriggerState getTriggerState(String group, String triggerName) throws SchedulerException {
		Trigger.TriggerState state= quartzScheduler.getTriggerState(new TriggerKey(triggerName, group));
		return state;
	}
	
	

	@Override
	public void delTrigger(String group, String triggerName) throws SchedulerException {
		TriggerKey key = new TriggerKey(triggerName, group);
		quartzScheduler.unscheduleJob(key);
	}
	@Override
	public void executeJob(String group, String jobName) throws SchedulerException {
		JobKey key = new JobKey(jobName, group);
		quartzScheduler.triggerJob(key);
	}

	@Override
	public void toggleTrigger(String group, String triggerName) throws SchedulerException {
		TriggerKey key = new TriggerKey(triggerName, group);
		Trigger.TriggerState state = quartzScheduler.getTriggerState(key);
		if(state == TriggerState.PAUSED){
			quartzScheduler.resumeTrigger(key);
		}
		else if(state == TriggerState.NORMAL){
			quartzScheduler.pauseTrigger(key);
		}
	}
	
}
