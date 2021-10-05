package cn.com.yujiangjun.myim.rest.releation;

import lombok.Data;

import javax.annotation.Nonnull;

@Data
public class UserGroupReqDto {
    @Nonnull
    private Integer myId;
    @Nonnull
    private String friendName;
    @Nonnull
    private Integer chatType;
}
