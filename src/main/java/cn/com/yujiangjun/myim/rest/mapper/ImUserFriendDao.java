package cn.com.yujiangjun.myim.rest.mapper;

import cn.com.yujiangjun.myim.rest.bean.ImUserFriend;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImUserFriendDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ImUserFriend record);

    int insertSelective(ImUserFriend record);

    ImUserFriend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImUserFriend record);

    int updateByPrimaryKey(ImUserFriend record);
}