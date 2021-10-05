package cn.com.yujiangjun.myim.rest.login.controller;

import cn.com.yujiangjun.myim.rest.BaseController;
import cn.com.yujiangjun.myim.rest.bean.Response;
import cn.com.yujiangjun.myim.rest.login.dto.LoginReqDto;
import cn.com.yujiangjun.myim.rest.login.entity.ImUserEntity;
import cn.com.yujiangjun.myim.rest.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController extends BaseController {

    @Autowired
    LoginService loginService;
    @PostMapping("/login")
    public Response login(@RequestBody LoginReqDto reqDto){
        return success(loginService.login(reqDto));
    }

    @PostMapping("/register")
    public Response register(@RequestBody ImUserEntity imUser){
        loginService.register(imUser);
        return success();
    }
}
