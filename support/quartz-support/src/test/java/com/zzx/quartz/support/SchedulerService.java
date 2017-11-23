package com.zzx.quartz.support;

import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;

public interface SchedulerService {
	public void addJob(String group, String jobName, String className, String desc) throws SchedulerException, ClassNotFoundException;
	void addJob(String group, String jobName, Class<Job> jobCls, String desc) throws SchedulerException;
	void addJob(String group, String jobName, Class<Job> jobCls, Map<String, Object> parameterMap, String description) throws SchedulerException;
	void addJobOrOverride(String group, String jobName, Class<Job> jobCls, Map<String, Object> parameterMap, String description) throws SchedulerException;
	boolean jobExists(String group, String jobName) throws SchedulerException;
	public JobDetail getJobDetail(String group, String jobName)  throws SchedulerException;
	void delJob(String group, String jobName) throws SchedulerException;
	List<JobDetail> getJobList(String group) throws SchedulerException;
	
	void addTrigger(String group, String jobName, String triggerName, String cronExpression) throws SchedulerException;
	boolean triggerExists(String group, String triggerName) throws SchedulerException;
	List<Trigger> getTriggersByJob(String group, String jobName) throws SchedulerException;
	TriggerState getTriggerState(String group, String triggerName) throws SchedulerException;
	void delTrigger(String group, String triggerName) throws SchedulerException;
	
	void executeJob(String group, String jobName) throws SchedulerException;
	void toggleTrigger(String group, String triggerName) throws SchedulerException;
}
