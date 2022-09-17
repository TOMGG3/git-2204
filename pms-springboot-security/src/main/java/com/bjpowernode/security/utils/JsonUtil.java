package com.bjpowernode.security.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * jackson工具类
 * 提供对象与json串之间的转换方法
 */
public class JsonUtil {

    public static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 将对象转换为json
     */
    public static String toString(Object obj){
        if(obj == null){
            return null;
        }
        if(obj.getClass() == String.class){
            return (String) obj;
        }
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将字符串转换对象
     */
    public static <T> T toBean(String json, Class<T> targetClass){
        try {
            return mapper.readValue(json, targetClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将字节流（传输json串）转换对象
     */
    public static <T> T toBean(InputStream in, Class<T> targetClass){
        try {
            return mapper.readValue(in, targetClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
