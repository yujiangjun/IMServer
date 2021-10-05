package cn.com.yujiangjun.myim.rest.login.service.impl;

import cn.com.yujiangjun.myim.rest.exception.IMWebException;
import cn.com.yujiangjun.myim.rest.exception.PasswordErrException;
import cn.com.yujiangjun.myim.rest.exception.UserNotFoundException;
import cn.com.yujiangjun.myim.rest.login.dao.ImUserDao;
import cn.com.yujiangjun.myim.rest.login.dto.LoginReqDto;
import cn.com.yujiangjun.myim.rest.login.entity.ImUserEntity;
import cn.com.yujiangjun.myim.rest.login.service.LoginService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    final ImUserDao imUserDao;

    public LoginServiceImpl(ImUserDao imUserDao) {
        this.imUserDao = imUserDao;
    }

    @Override
    public ImUserEntity login(LoginReqDto reqDto) {
        ImUserEntity imUserEntity = imUserDao.selectByUserName(reqDto.getUserName());
        if (imUserEntity==null){
            throw new UserNotFoundException("该用户不存在");
        }
        String pwFromDb = imUserEntity.getPassword();
        String inputPass =DigestUtils.md5DigestAsHex(reqDto.getPassword().getBytes(StandardCharsets.UTF_8));

        if (!Objects.equals(pwFromDb,inputPass)){
            throw new PasswordErrException("用户名或密码输入错误");
        }
        return imUserEntity;
    }

    @Override
    public void register(ImUserEntity imUser) {
        ImUserEntity userEntity = imUserDao.selectByUserName(imUser.getUserName());
        if (userEntity!=null){
            throw new IMWebException("用户名已存在");
        }
        if (StringUtils.isEmpty(imUser.getAvtor())){
            imUser.setAvtor("http://192.168.211.130:4545/%E9%BE%99%E7%8C%AB3.jpeg");
        }
        imUser.setPassword(DigestUtils.md5DigestAsHex(imUser.getPassword().getBytes(StandardCharsets.UTF_8)));
        imUserDao.insert(imUser);
    }
}
