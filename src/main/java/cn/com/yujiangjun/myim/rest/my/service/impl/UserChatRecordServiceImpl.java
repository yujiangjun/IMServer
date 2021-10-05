package cn.com.yujiangjun.myim.rest.my.service.impl;

import cn.com.yujiangjun.myim.rest.bean.UserChatRecord;
import cn.com.yujiangjun.myim.rest.mapper.UserChatRecordDao;
import cn.com.yujiangjun.myim.rest.my.dto.ChatReqDto;
import cn.com.yujiangjun.myim.rest.my.service.UserChatRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserChatRecordServiceImpl implements UserChatRecordService {

    final UserChatRecordDao userChatRecordDao;

    public UserChatRecordServiceImpl(UserChatRecordDao userChatRecordDao) {
        this.userChatRecordDao = userChatRecordDao;
    }

    @Override
    public List<UserChatRecord> getMyChatRecord(Integer myUserId) {
        return userChatRecordDao.getMyChatRecord(myUserId);
    }

    @Override
    public void deleteMyRecord(ChatReqDto reqDto) {
        userChatRecordDao.deleteMyRecord(reqDto);
    }
}
