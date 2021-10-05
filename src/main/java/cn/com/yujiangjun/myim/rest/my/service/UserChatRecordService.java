package cn.com.yujiangjun.myim.rest.my.service;

import cn.com.yujiangjun.myim.rest.bean.UserChatRecord;
import cn.com.yujiangjun.myim.rest.my.dto.ChatReqDto;

import java.util.List;

/**
 * 聊天记录
 */
public interface UserChatRecordService {

    /**
     * 获取我的聊天记录
     * @param myUserId
     * @return
     */
    List<UserChatRecord> getMyChatRecord(Integer myUserId);

    /**
     * 删除好友聊天记录
     * @param reqDto
     */
    void deleteMyRecord(ChatReqDto reqDto);
}
