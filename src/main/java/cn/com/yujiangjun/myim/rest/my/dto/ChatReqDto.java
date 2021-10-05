package cn.com.yujiangjun.myim.rest.my.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChatReqDto {

    private Integer userId;
    private Integer recordId;
}
