package cn.com.yujiangjun.myim.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

@Slf4j
public class ConvertUtil {

    public static <T> T convert(Object src,Class<T> desClazz){
        T t = null;
        try {
            t = desClazz.newInstance();
        } catch (Exception e) {
           log.info("创建对象失败,cause:",e);
        }
        BeanUtils.copyProperties(src,t);
        return t;
    }
}
