package cn.com.yujiangjun.myim.rest.my.service;

import cn.com.yujiangjun.myim.rest.login.entity.ImUserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyServiceTest {

    @Autowired
    MyService myService;

    @Test
    void getMyFriends() {
        List<ImUserEntity> myFriends = myService.getMyFriends(3);
        myFriends.forEach(System.out::println);
    }
}