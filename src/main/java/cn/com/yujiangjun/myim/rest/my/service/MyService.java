package cn.com.yujiangjun.myim.rest.my.service;

import cn.com.yujiangjun.myim.rest.login.entity.ImUserEntity;

import java.util.List;

public interface MyService {

    List<ImUserEntity> getMyFriends(Integer myUserId);

    ImUserEntity getDetail(Integer userId);
}
