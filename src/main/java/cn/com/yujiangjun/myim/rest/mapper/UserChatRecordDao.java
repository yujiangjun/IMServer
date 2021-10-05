package cn.com.yujiangjun.myim.rest.mapper;

import cn.com.yujiangjun.myim.rest.bean.UserChatRecord;
import cn.com.yujiangjun.myim.rest.my.dto.ChatReqDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserChatRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserChatRecord record);

    int insertSelective(UserChatRecord record);

    UserChatRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserChatRecord record);

    int updateByPrimaryKey(UserChatRecord record);

    List<UserChatRecord> getMyChatRecord(Integer myUserId);

    void deleteMyRecord(ChatReqDto reqDto);
}