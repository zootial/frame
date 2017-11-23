package com.zzx.common.web.api;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class OpenApiMessageConverter extends MappingJackson2HttpMessageConverter {

    @Override
    protected void writeInternal(Object o, Type type, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
//        if (InterfaseResponse.class.isAssignableFrom(o.getClass())) {
//            super.writeInternal(o, InterfaseResponse.class, outputMessage);
//        } else {
//            super.writeInternal(o, type, outputMessage);
//        }
    }

}