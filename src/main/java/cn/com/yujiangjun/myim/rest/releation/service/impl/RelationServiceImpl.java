package cn.com.yujiangjun.myim.rest.releation.service.impl;

import cn.com.yujiangjun.myim.constants.ApplyStatus;
import cn.com.yujiangjun.myim.constants.ApplyType;
import cn.com.yujiangjun.myim.constants.ChatType;
import cn.com.yujiangjun.myim.rest.bean.ApplyRecord;
import cn.com.yujiangjun.myim.rest.bean.ImUserFriend;
import cn.com.yujiangjun.myim.rest.exception.IMWebException;
import cn.com.yujiangjun.myim.rest.login.dao.ImUserDao;
import cn.com.yujiangjun.myim.rest.login.entity.ImUserEntity;
import cn.com.yujiangjun.myim.rest.mapper.ApplyRecordDao;
import cn.com.yujiangjun.myim.rest.mapper.ImUserFriendDao;
import cn.com.yujiangjun.myim.rest.releation.dto.FriendApplyRecordRespDto;
import cn.com.yujiangjun.myim.rest.releation.service.RelationService;
import cn.com.yujiangjun.myim.rest.releation.UserGroupReqDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class RelationServiceImpl implements RelationService {

    final ImUserDao imUserDao;

    final ApplyRecordDao applyRecordDao;

    final ImUserFriendDao imUserFriendDao;


    public RelationServiceImpl(ImUserDao imUserDao,ApplyRecordDao applyRecordDao,ImUserFriendDao imUserFriendDao) {
        this.imUserDao = imUserDao;
        this.applyRecordDao=applyRecordDao;
        this.imUserFriendDao=imUserFriendDao;
    }

    @Override
    public boolean friendGroupExist(UserGroupReqDto reqDto) {
        if (Objects.equals(reqDto.getChatType(),ChatType.SINGLE.getCode())){
            ImUserEntity imUser = imUserDao.selectByUserName(reqDto.getFriendName());
            return imUser!=null;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addFriend(UserGroupReqDto reqDto) {
        ImUserEntity imUser = imUserDao.selectByUserName(reqDto.getFriendName());
        if (imUser==null){
            throw new IMWebException("为找到该用户");
        }
        ApplyRecord record = new ApplyRecord();
        record.setUserId(reqDto.getMyId());
        record.setFriendId(imUser.getUserId());
        record.setCreateTime(new Date());
        record.setApplyType(ApplyType.FRIEND.getCode());
        applyRecordDao.insert(record);
    }

    @Override
    public List<FriendApplyRecordRespDto> getMyFriendApplyRecord(Integer myUserId) {
        return applyRecordDao.getMyFriendApplyRecord(myUserId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void passAddFriend(Integer recordId) {
        log.info("class:{},method:{},params:{}","RelationServiceImpl","passAddFriend",recordId);
        ApplyRecord record = applyRecordDao.selectByPrimaryKey(recordId);
        if (record==null){
            throw new IMWebException("该申请记录不存在");
        }
        record.setStatus(ApplyStatus.PASS.getCode());
        applyRecordDao.updateByPrimaryKey(record);

        ImUserFriend newFriend = new ImUserFriend();
        newFriend.setSelfId(record.getUserId());
        newFriend.setFriendId(record.getFriendId());
        imUserFriendDao.insert(newFriend);
    }

    @Override
    public void rejectAddFriend(Integer recordId) {
        ApplyRecord record = applyRecordDao.selectByPrimaryKey(recordId);
        if (record==null){
            throw new IMWebException("该申请记录不存在");
        }
        record.setStatus(ApplyStatus.REJECT.getCode());
        applyRecordDao.updateByPrimaryKey(record);
    }
}
