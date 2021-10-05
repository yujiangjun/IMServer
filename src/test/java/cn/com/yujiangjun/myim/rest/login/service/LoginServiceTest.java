package cn.com.yujiangjun.myim.rest.login.service;

import cn.com.yujiangjun.myim.rest.login.dto.LoginReqDto;
import cn.com.yujiangjun.myim.rest.login.entity.ImUserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginServiceTest {

    @Autowired
    LoginService loginService;
    @Test
    void login() {
        LoginReqDto reqDto = new LoginReqDto();
        reqDto.setUserName("1396607376");
        reqDto.setPassword("123456");
        ImUserEntity login = loginService.login(reqDto);
        System.out.println(login);
    }
}