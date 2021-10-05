package cn.com.yujiangjun.myim.rest.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * user_chat_record
 * @author 
 */
@Data
public class UserChatRecord implements Serializable {
    private Integer id;

    private Integer userId;

    private String userName;

    private Integer friendId;

    private String friendName;

    private String friendAvtor;

    /**
     * 最后聊天数据
     */
    private String content;

    private Date chatTime;

    private static final long serialVersionUID = 1L;
}