package cn.com.yujiangjun.myim.rest.login.service;

import cn.com.yujiangjun.myim.rest.login.dto.LoginReqDto;
import cn.com.yujiangjun.myim.rest.login.entity.ImUserEntity;

public interface LoginService {

     ImUserEntity login(LoginReqDto reqDto);

    void register(ImUserEntity imUser);
}
