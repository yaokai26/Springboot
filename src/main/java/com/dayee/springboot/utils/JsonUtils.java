package com.dayee.springboot.utils;

import java.io.IOException;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> String obj2String(T obj) {

        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static<T> T string2obj(String str,Class<T> clazz){
        if(StringUtils.isEmpty(str)|| clazz==null){
            return null;
        }
        try{
            return clazz.equals(String.class)?(T) str : objectMapper.readValue(str,clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
