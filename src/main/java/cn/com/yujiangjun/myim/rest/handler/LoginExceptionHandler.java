package cn.com.yujiangjun.myim.rest.handler;

import cn.com.yujiangjun.myim.constants.ResponseConstants;
import cn.com.yujiangjun.myim.rest.bean.Response;
import cn.com.yujiangjun.myim.rest.exception.IMWebException;
import cn.com.yujiangjun.myim.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@ControllerAdvice
@Slf4j
public class LoginExceptionHandler {


    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public String userNotFound(Exception e){
        log.error("发生异常,cause:{}",e);
        HashMap<String, Object> resp = new HashMap<>();
        resp.put(ResponseConstants.CODE_KEY,ResponseConstants.FAIL_CODE);
        resp.put(ResponseConstants.MESSAGE_KEY,e.getMessage());
        return JsonUtil.obj2Json(resp);
    }


    @ExceptionHandler(IMWebException.class)
    @ResponseBody
    public Response webException(IMWebException exception){
        log.error("发生异常,cause:{}",exception);
        return Response.builder().code(ResponseConstants.FAIL_CODE)
                .message(exception.getMessage()).build();
    }
}
