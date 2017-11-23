package com.zzx.quartz.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JobSchedule {

	String[] cron() default {};
	
	int initialDelay() default -1;
	
	int fixedRate() default -1;
	
	QuartzGroup group();
	
	String jobName();
	
	String desc() default "";
}
