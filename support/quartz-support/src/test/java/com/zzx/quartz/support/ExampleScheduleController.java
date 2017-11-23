package com.zzx.quartz.support;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ExampleScheduleController extends AbstractQuartzScheduleController {

	@Autowired
	private Scheduler quartzScheduler;
	
	@Override
	protected Scheduler getQuartzScheduler() {
		return quartzScheduler;
	}

	
//	@RequestMapping("/index")
//	public ModelAndView index(){
//		ModelAndView view = new ModelAndView("common/quartz");
//		view.addObject("groups", QuartzGroup.vals());
//		return view;
//	}
}
