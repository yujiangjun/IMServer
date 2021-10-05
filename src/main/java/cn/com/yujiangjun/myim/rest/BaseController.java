package cn.com.yujiangjun.myim.rest;

import cn.com.yujiangjun.myim.constants.ResponseConstants;
import cn.com.yujiangjun.myim.rest.bean.Response;

public class BaseController {



    public Response success(){
        return Response.builder().code(ResponseConstants.SUCCESS_CODE)
                .message(ResponseConstants.SUCCESS_MESSAGE).build();
    }

    public <T> Response success(T t){
        return Response.builder().code(ResponseConstants.SUCCESS_CODE)
                .message(ResponseConstants.SUCCESS_MESSAGE).data(t).build();
    }
    public Response error(){
        return Response.builder().code(ResponseConstants.FAIL_CODE)
                .message(ResponseConstants.FAIL_MESSAGE).build();
    }
}
