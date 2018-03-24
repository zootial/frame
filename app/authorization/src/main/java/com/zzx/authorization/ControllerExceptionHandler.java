package com.zzx.authorization;


import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 控制层异常处理
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) {
        ModelAndView mav = new ModelAndView();
        
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        StringBuilder exinfo = new StringBuilder();
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            exinfo.append("\r\n");
            exinfo.append(stackTraceElement.toString());
            exinfo.append("\r\n");
        }

        mav.addObject("message", e.getMessage());
        mav.addObject("exception", exinfo.toString());
        mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        mav.addObject("url", req.getRequestURL() + "/" + req.getQueryString());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

}
