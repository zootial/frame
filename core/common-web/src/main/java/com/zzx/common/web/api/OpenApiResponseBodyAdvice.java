package com.zzx.common.web.api;


import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.zzx.openapi.entity.ApiResponseVo;

@ControllerAdvice
public class OpenApiResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return (AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ResponseBody.class) ||
                returnType.hasMethodAnnotation(ResponseBody.class));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {
    	// 返回统一的JSON对象
    	ApiResponseVo resp = null;
        if (ApiResponseVo.class.isAssignableFrom(body.getClass())) {
            resp = (ApiResponseVo) body;
        }
        else {
//            String key = String.valueOf(
//                    ((ServletServerHttpRequest) request).getServletRequest().getAttribute(InterfaseRequest.REQ_KEY));
//            String sysCode = String.valueOf(((ServletServerHttpRequest) request).getServletRequest()
//                    .getAttribute(InterfaseRequest.REQ_SYSCODE));
//            String resultJson = null;

//        	ApiResponseVo
        }
        return resp;
    }

}
