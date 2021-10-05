package cn.com.yujiangjun.myim.rest.releation.service;


import cn.com.yujiangjun.myim.rest.releation.UserGroupReqDto;
import cn.com.yujiangjun.myim.rest.releation.dto.FriendApplyRecordRespDto;

import java.util.List;

/**
 * 好友 群组
 */
public interface RelationService {

    /**
     * 查询好友/群组存在
     * @param reqDto
     * @return
     */
    boolean friendGroupExist(UserGroupReqDto reqDto);

    /**
     * 添加好友
     * @param reqDto
     */
    void addFriend(UserGroupReqDto reqDto);

    /**
     * 获取我的好友申请列表
     * @param myUserId 用户id
     * @return
     */
    List<FriendApplyRecordRespDto> getMyFriendApplyRecord(Integer myUserId);

    /**
     * 通过好友申请
     * @param recordId
     */
    void passAddFriend(Integer recordId);

    /**
     * 拒绝好友申请
     * @param recordId
     */
    void rejectAddFriend(Integer recordId);
}
