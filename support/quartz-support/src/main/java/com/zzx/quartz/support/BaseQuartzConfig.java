package com.zzx.quartz.support;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.util.StringUtils;



public abstract class BaseQuartzConfig implements EnvironmentAware {
	public final static String QUARTZ_CONF = "quartz.properties";
	public final static String QUARTZ_CONF_P = "quartz-${spring.profiles.active}.properties";
	
	private Environment env;
	
	protected abstract List<Class<? extends Job>> getTaskList();
	
	@Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
		AutowireSpringBeanJobFactory jobFactory = new AutowireSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    /**
     * 调度工厂bean
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory) throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        List<Class<? extends Job>> taskList = getTaskList();
        if(taskList == null) {
        	taskList = new ArrayList<Class<? extends Job>>();
        }
        //注册任务
        List<JobDetail> jobDetails = QuartzUtil.initializeJobDetails(taskList);
        factory.setJobDetails(jobDetails.toArray(new JobDetail[jobDetails.size()]));
        //注册触发器
        List<Trigger> triggerList = QuartzUtil.initializeTriggers(taskList);
        factory.setTriggers(triggerList.toArray(new Trigger[triggerList.size()]));
        factory.setJobFactory(jobFactory);
        Properties props = quartzProperties();
        factory.setAutoStartup(Boolean.valueOf(props.getProperty("quartz.autoStartup", "true")));
        factory.setStartupDelay(Integer.valueOf(props.getProperty("quartz.startupDelay", "20")));
        factory.setOverwriteExistingJobs(Boolean.valueOf(props.getProperty("quartz.overwriteExistingJobs", "false")));
        factory.setBeanName(props.getProperty("org.quartz.scheduler.instanceName", "quartzScheduler"));
        factory.setQuartzProperties(props);
        return factory;
    }

    public Properties quartzProperties() throws IOException {
    	String confPath = env.getProperty("Constant.CONF_PATH");
    	String fileName = null;
		try {
			fileName = env.resolveRequiredPlaceholders(QUARTZ_CONF_P);
		} catch (IllegalArgumentException e) {
			fileName = QUARTZ_CONF;
		}
    	Resource res = null;
    	if(confPath != null){
    		res = new FileSystemResource(StringUtils.cleanPath(confPath) + File.separator + fileName);
    	} else {
    		res = new ClassPathResource(fileName);
    	}
    	if(!res.exists()){
    		throw new IllegalStateException(String.format("Quartz configuration file [%s] not exists!", res.getURL()));
    	}
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(res);
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }
    
	static class AutowireSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

		private transient AutowireCapableBeanFactory beanFactory;

		@Override
		public void setApplicationContext(final ApplicationContext context) {
			beanFactory = context.getAutowireCapableBeanFactory();
		}

		@Override
		protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
			final Object job = super.createJobInstance(bundle);
			beanFactory.autowireBean(job);
			return job;
		}
	}

	@Override
	public void setEnvironment(Environment environment) {
		env = environment;
	}
}
