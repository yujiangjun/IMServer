package cn.com.yujiangjun.myim.rest.mapper;

import cn.com.yujiangjun.myim.rest.bean.ApplyRecord;
import cn.com.yujiangjun.myim.rest.releation.dto.FriendApplyRecordRespDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApplyRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ApplyRecord record);

    int insertSelective(ApplyRecord record);

    ApplyRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ApplyRecord record);

    int updateByPrimaryKey(ApplyRecord record);

    List<FriendApplyRecordRespDto> getMyFriendApplyRecord(Integer myUserId);
}