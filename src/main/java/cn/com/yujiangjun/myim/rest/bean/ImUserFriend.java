package cn.com.yujiangjun.myim.rest.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * im_user_friend
 * @author 
 */
@Data
public class ImUserFriend implements Serializable {
    private Integer id;

    private Integer friendId;

    private Integer selfId;

    private static final long serialVersionUID = 1L;
}