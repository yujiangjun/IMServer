package cn.com.yujiangjun.myim.rest.bean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response<T> {

    private int code;
    private String message;
    private T data;
}
