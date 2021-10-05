package cn.com.yujiangjun.myim.rest.login.dao;

import cn.com.yujiangjun.myim.rest.login.entity.ImUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ImUserDao {
    int deleteByPrimaryKey(Integer userId);

    int insert(ImUserEntity record);

    int insertSelective(ImUserEntity record);

    ImUserEntity selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(ImUserEntity record);

    int updateByPrimaryKey(ImUserEntity record);

    ImUserEntity selectByUserName(@Param("userName")String userName);

    List<ImUserEntity> getMyFriends(@Param("myUserId") Integer myUserId);
}