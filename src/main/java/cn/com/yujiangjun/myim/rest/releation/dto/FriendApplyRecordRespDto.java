package cn.com.yujiangjun.myim.rest.releation.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class FriendApplyRecordRespDto {

    private Integer recordId;
    private Integer status;
    private String friendId;
    private String friendName;
    private String friendNickName;
    private String friendAvtor;
    private Date applyTime;
}
