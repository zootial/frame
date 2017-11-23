package com.zzx.common.web.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class OpenApiInterceptor extends HandlerInterceptorAdapter {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String error = null;
        if(WebAsyncUtils.getAsyncManager(request).hasConcurrentResult()) {
            return true;
        }
        try {
            if(200 != response.getStatus()) {
                return false;
            }
            // 解析请求参数
        }
        catch (Exception ex) {
        	// 解析请求参数异常
        	error = "";
        }
        if (error != null) {
            return false;
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        if (ex != null) {
            // 处理业务异常
        }
        super.afterCompletion(request, response, handler, ex);
    }

}
