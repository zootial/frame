package com.zzx.quartz.support;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PersistJobDataAfterExecution
public abstract class BaseTask implements Job {

	protected final Logger log = LoggerFactory.getLogger(getClass());
//	@Autowired
//	private SysTaskLogService logService;
	
	public abstract String executeJob(JobExecutionContext context) throws Exception;
	
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
//		SysTaskLog taskLog = new SysTaskLog();
//		JobDetail jobDetail = context.getJobDetail();
//		String jobName = jobDetail.getKey().getName();
//		String groupName = jobDetail.getKey().getGroup();
//		Trigger trigger = context.getTrigger();
//		String triggerName = trigger.getKey().getName();
//		
//		taskLog.setGroupName(groupName);
//		taskLog.setJobName(jobName);
//		taskLog.setTriggerName(triggerName);
//		taskLog.setStartTime(new Date());
//		
//		long lstartTime = System.currentTimeMillis();
//		long lendTime = 0l;
//		try {
//			String msg = executeJob(context);
		
			try {
				executeJob(context);
			} catch (Exception e) {
				throw new JobExecutionException(e);
			}
			
//			lendTime = System.currentTimeMillis();
//			taskLog.setRunStatus(Constant.STATUS_SUCCESS);
//			taskLog.setEndTime(new Date());
//			taskLog.setMessage(msg);
//		} catch(Exception ex) {
//			log.error("执行任务出错:" + ex.getMessage());
//			lendTime = System.currentTimeMillis();
//			taskLog.setRunStatus(Constant.STATUS_FAILURE);
//			taskLog.setEndTime(new Date());
//			taskLog.setMessage(ExceptionUtil.getDetailExInfo(ex));
//		}
//		taskLog.setElapsedTime( (int)(lendTime - lstartTime) /1000 );
//		
//		try {
//			logService.add(taskLog);
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.error("保存任务日志出错:" + e.getMessage());
//		}
	}
	
	
}
