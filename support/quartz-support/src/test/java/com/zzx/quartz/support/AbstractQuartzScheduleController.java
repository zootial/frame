package com.zzx.quartz.support;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.springframework.stereotype.Controller;

@Controller
//@RequestMapping("/quartz")
public abstract class AbstractQuartzScheduleController extends BaseController {

	private SchedulerService service;
	
	private SchedulerService getService(){
		if(this.service == null){
			Scheduler sched = getQuartzScheduler();
			assert sched != null;
			this.service = new SchedulerServiceImpl(sched);
			return this.service;
		} else {
			return this.service;
		}
	}
	
	protected abstract Scheduler getQuartzScheduler();
	
//	@RequestMapping("/index")
//	public ModelAndView index(){
//		ModelAndView view = new ModelAndView("common/quartz");
//		view.addObject("groups", QuartzGroup.vals());
//		return view;
//	}
	
/*	*//**
	 * for JSON
	 * @throws SchedulerException  
	 *//*
	@RequestMapping(method=RequestMethod.POST, value="/job/list",
			produces="application/json", consumes="application/json")
	@ResponseBody
	public JsonResult listJobs(@RequestBody String content) throws SchedulerException {
		QuartzParam param = JSONObject.parseObject(content, QuartzParam.class);
		List<Map<String, Object>> jobList = new ArrayList<Map<String, Object>>();
		for(JobDetail d : getService().getJobList(param.getGroup())){
			jobList.add(convertJobDetail(d));
		}
		return JsonResult.success(jobList);
	}
	
	*//**
	 * for JSON
	 * @throws SchedulerException  
	 *//*
	@RequestMapping(method=RequestMethod.POST, value="/trigger/list",
			produces="application/json", consumes="application/json")
	@ResponseBody
	public JsonResult listTriggers(@RequestBody String content) throws SchedulerException {
		QuartzParam param = JSONObject.parseObject(content, QuartzParam.class);
		List<Trigger> triggers = getService().getTriggersByJob(param.getGroup(), param.getJobName());
		List<Map<String, Object>> triggerList = new ArrayList<Map<String, Object>>();
		for(Trigger trig : triggers){
			TriggerState state = getService().getTriggerState(trig.getKey().getGroup(), trig.getKey().getName());
			triggerList.add(convertTrigger(trig, state));
		}
		return JsonResult.success(triggerList);
	}
	
	*//**
	 * for JSON
	 * @throws SchedulerException  
	 *//*
	@RequestMapping(method=RequestMethod.POST, value="/job/execute",
			produces="application/json", consumes="application/json")
	@ResponseBody
	public JsonResult executeJob(@RequestBody String content) throws SchedulerException{
		QuartzParam param = JSONObject.parseObject(content, QuartzParam.class);
		boolean exists = getService().jobExists(param.getGroup(), param.getJobName());
		if(!exists){
			return JsonResult.fail("不存在，请添加", param);
		}
		getService().executeJob(param.getGroup(), param.getJobName());
		return JsonResult.success();
	}
	
	*//**
	 * for JSON
	 * @throws SchedulerException  
	 *//*
	@RequestMapping(method=RequestMethod.POST, value="/trigger/toggle",
			produces="application/json", consumes="application/json")
	@ResponseBody
	public JsonResult toggleTrigger(@RequestBody String content) throws SchedulerException{
		QuartzParam param = JSONObject.parseObject(content, QuartzParam.class);
		boolean exists = getService().triggerExists(param.getGroup(), param.getTriggerName());
		if(!exists) {
			return JsonResult.fail("不存在，请添加");
		}
		getService().toggleTrigger(param.getGroup(), param.getTriggerName());
		return JsonResult.success();
	}
	
	*//**
	 * for JSON
	 * @throws SchedulerException 
	 *//*
	@RequestMapping(method=RequestMethod.POST, value="/job/add",
			produces="application/json", consumes="application/json")
	@ResponseBody
	public JsonResult addJob(@RequestBody String content) throws SchedulerException {
		QuartzParam param = JSONObject.parseObject(content, QuartzParam.class);
		return doAddingJob(param);
	}
	
	*//**
	 * for Form submit
	 * @throws SchedulerException 
	 *//*
	@RequestMapping(method=RequestMethod.POST, value="/job/add",
			produces="application/json", consumes="application/x-www-form-urlencoded")
	@ResponseBody
	public JsonResult addJob(QuartzParam param) throws SchedulerException {
		return doAddingJob(param);
	}
	
	private JsonResult doAddingJob(QuartzParam param) throws SchedulerException {
		JobDetail jobDetail = getService().getJobDetail(param.getGroup(), param.getJobName());
		boolean exists = jobDetail != null;
		if(exists) {
			return JsonResult.fail("已经存在", convertJobDetail(jobDetail));
		}
		try {
			getService().addJob(param.getGroup(), param.getJobName(), param.getJobClassName(), param.getDesc());
		} catch (ClassNotFoundException e) {
			return JsonResult.fail(String.format("任务类%s不存在", param.getJobClassName()));
		}
		return JsonResult.success();
	}
	
	*//**
	 * for JSON
	 * @throws SchedulerException 
	 *//*
	@RequestMapping(method=RequestMethod.POST, value="/trigger/add",
			produces="application/json", consumes="application/json")
	@ResponseBody
	public JsonResult addTrigger(@RequestBody String content) throws SchedulerException {
		QuartzParam param = JSONObject.parseObject(content, QuartzParam.class);
		return doAddingTrigger(param);
	}
	
	*//**
	 * for Form submit
	 * @throws SchedulerException 
	 *//*
	@RequestMapping(method=RequestMethod.POST, value="/trigger/add",
			produces="application/json", consumes="application/x-www-form-urlencoded")
	@ResponseBody
	public JsonResult addTrigger(QuartzParam param) throws SchedulerException {
		return doAddingTrigger(param);
	}
	
	private JsonResult doAddingTrigger(QuartzParam param) throws SchedulerException {
		if(!getService().jobExists(param.getGroup(), param.getJobName())){
			return JsonResult.fail(String.format("job任务%s_%s不存在", param.getGroup(), param.getJobName()));
		}
		boolean exists = getService().triggerExists(param.getGroup(), param.getTriggerName());
		if(exists) {
			getService().delTrigger(param.getGroup(), param.getTriggerName());
		}
		getService().addTrigger(param.getGroup(), param.getJobName(), param.getTriggerName(), param.getCronExpression());
		return JsonResult.success();
	}
	
	*//**
	 * for JSON
	 * @throws SchedulerException 
	 *//*
	@RequestMapping(method=RequestMethod.POST, value="/job/modify",
			produces="application/json", consumes="application/json")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public JsonResult modifyJob(@RequestBody String content) throws SchedulerException {
		QuartzParam param = JSONObject.parseObject(content, QuartzParam.class);
		boolean exists = getService().jobExists(param.getGroup(), param.getJobName());
		if(!exists){
			return JsonResult.fail("不存在，请添加", param);
		}
		Class<Job> jobCls = null;
		try {
			jobCls = (Class<Job>)Class.forName(param.getJobClassName());
		} catch (ClassNotFoundException e) {
			return JsonResult.fail(String.format("任务类%s不存在", param.getJobClassName()));
		}
		getService().addJobOrOverride(param.getGroup(), param.getJobName(), jobCls, param.getJobDataMap(), param.getDesc());
		return JsonResult.success();
	}
	
	*//**
	 * for JSON
	 * @throws SchedulerException 
	 *//*
	@RequestMapping(method=RequestMethod.POST, value="/job/del",
			produces="application/json")
	@ResponseBody
	public JsonResult removeJob(@RequestBody String content) throws SchedulerException {
		QuartzParam param = JSONObject.parseObject(content, QuartzParam.class);
		if(!getService().jobExists(param.getGroup(), param.getJobName())){
			return JsonResult.fail(String.format("job任务%s_%s不存在", param.getGroup(), param.getJobName()));
		}
		getService().delJob(param.getGroup(), param.getJobName());
		return JsonResult.success();
	}
	
	*//**
	 * for JSON
	 * @throws SchedulerException 
	 *//*
	@RequestMapping(method=RequestMethod.POST, value="/trigger/del",
			produces="application/json")
	@ResponseBody
	public JsonResult removeTrigger(@RequestBody String content) throws SchedulerException {
		QuartzParam param = JSONObject.parseObject(content, QuartzParam.class);
		if(!getService().triggerExists(param.getGroup(), param.getTriggerName())){
			return JsonResult.fail(String.format("触发器%s_%s不存在", param.getGroup(), param.getTriggerName()));
		}
		getService().delTrigger(param.getGroup(), param.getTriggerName());
		return JsonResult.success();
	}
	*/
	private static Map<String, Object> convertJobDetail(JobDetail d){
		Map<String, Object> detailMap = new LinkedHashMap<String, Object>();
		detailMap.put("group", d.getKey().getGroup());
		detailMap.put("jobName", d.getKey().getName());
		detailMap.put("desc", d.getDescription());
		detailMap.put("jobClass", d.getJobClass());
		return detailMap;
	}
	
	private static Map<String, Object> convertTrigger(Trigger trig, TriggerState state){
		Map<String, Object> trigger = new LinkedHashMap<String, Object>();
		trigger.put("group", trig.getJobKey().getGroup());
		trigger.put("jobName", trig.getJobKey().getName());
		trigger.put("triggerName", trig.getKey().getName());
		trigger.put("triggerType", trig.getClass().getSimpleName());
		trigger.put("desc", trig.getDescription());
		trigger.put("state", state.name());
		return trigger;
	}

}
