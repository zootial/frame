package com.zzx.common.web.api;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zzx.common.SysConstant;

//@Configuration
public class ApiWebConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        OpenApiRequestArgumentResolver resolver = new OpenApiRequestArgumentResolver();
        argumentResolvers.add(resolver);
        super.addArgumentResolvers(argumentResolvers);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf(converter -> converter instanceof MappingJackson2HttpMessageConverter);
        MappingJackson2HttpMessageConverter converter = new OpenApiMessageConverter();
        converter.setDefaultCharset(Charset.forName(SysConstant.DEFAULT_CHARSET));
        converters.add(converter);
        super.extendMessageConverters(converters);
    }
    
    @Bean
    public FilterRegistrationBean httpRequestWapperFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new ApiHttpWapperFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }
    
    @Bean
    OpenApiInterceptor apiInterceptor() {
        return new OpenApiInterceptor();
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
