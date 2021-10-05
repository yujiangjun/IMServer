package cn.com.yujiangjun.myim.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {


    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> String obj2Json(T t){
        try {
            return objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("json 转换错误，cause:"+e.getMessage());
        }
    }

    public static <T> T json2Obj(String jsonStr,Class<T> clazz){
        try {
            return objectMapper.readValue(jsonStr,clazz);
        } catch (JsonProcessingException e) {
           throw new RuntimeException("json转换错误,cause:"+e.getMessage());
        }
    }
}
