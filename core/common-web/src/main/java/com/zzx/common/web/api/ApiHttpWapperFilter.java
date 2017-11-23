package com.zzx.common.web.api;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.async.WebAsyncUtils;

public class ApiHttpWapperFilter implements Filter {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        chain.doFilter(request, response);
        if(WebAsyncUtils.getAsyncManager(request).isConcurrentHandlingStarted()) {
            return;
        }
        
    }

}
