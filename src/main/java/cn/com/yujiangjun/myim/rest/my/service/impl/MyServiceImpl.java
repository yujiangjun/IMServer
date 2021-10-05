package cn.com.yujiangjun.myim.rest.my.service.impl;

import cn.com.yujiangjun.myim.rest.login.dao.ImUserDao;
import cn.com.yujiangjun.myim.rest.login.entity.ImUserEntity;
import cn.com.yujiangjun.myim.rest.my.service.MyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyServiceImpl implements MyService {

    final ImUserDao imUserDao;

    public MyServiceImpl(ImUserDao imUserDao) {
        this.imUserDao = imUserDao;
    }

    @Override
    public List<ImUserEntity> getMyFriends(Integer myUserId) {
        return imUserDao.getMyFriends(myUserId);
    }

    @Override
    public ImUserEntity getDetail(Integer userId) {
        return imUserDao.selectByPrimaryKey(userId);
    }
}
