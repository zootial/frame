package com.zzx.common.web.api;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.zzx.openapi.entity.ApiRequestVo;

public class OpenApiRequestArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return ApiRequestVo.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
//        String systemCode = (String) request.getAttribute(InterfaseRequest.REQ_SYSCODE);
//        String json = (String) request.getAttribute(InterfaseRequest.REQ_DATA);
//        Object reqvo = GsonUtils.toObject(json, parameter.getParameterType());
        ApiRequestVo requstVo = null; //(ApiRequestVo)reqvo;
//        requstVo.setSystemCode(systemCode);
        // 组装Controller方法参数对象
        return requstVo;
    }

}
