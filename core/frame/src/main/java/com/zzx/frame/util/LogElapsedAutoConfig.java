package com.zzx.frame.util;

import javax.annotation.PostConstruct;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import com.zzx.frame.util.annotation.Elapsed;

@Configuration
@ConditionalOnProperty(name = "log.elapsed", havingValue = "true")
public class LogElapsedAutoConfig extends AbstractPointcutAdvisor {

	private static final long serialVersionUID = 9115308754008226304L;

	protected Log log = LogFactory.getLog(getClass());
	
	private Pointcut pointcut;
	 
    private Advice advice;
    
    @PostConstruct
    public void init() {
    	log.info("init LogElapsedAutoConfiguration start");
        this.pointcut = new AnnotationMatchingPointcut(null, Elapsed.class);
        this.advice = new LogMethodInterceptor();
        log.info("init LogElapsedAutoConfiguration end");
    }
    
	@Override
	public Pointcut getPointcut() {
		return this.pointcut;
	}

	@Override
	public Advice getAdvice() {
		return this.advice;
	}

	class LogMethodInterceptor implements MethodInterceptor {
	    @Override
	    public Object invoke(MethodInvocation invocation) throws Throwable {
	    	Class<?> clazz = invocation.getMethod().getDeclaringClass();
	    	String methodName = "";
	    	if(clazz != null) {
	    		methodName = clazz.getName() + ".";
	    	}
	    	methodName += invocation.getMethod().getName();
	        long start = System.currentTimeMillis();
	        Object result = invocation.proceed();
	        long end = System.currentTimeMillis();
	        log.info(String.format("method: %s | cost: %s", methodName, (end - start)));
	        return result;
	    }
	}
}
