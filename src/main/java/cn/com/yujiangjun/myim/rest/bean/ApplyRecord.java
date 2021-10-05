package cn.com.yujiangjun.myim.rest.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * apply_record
 * @author 
 */
@Data
public class ApplyRecord implements Serializable {
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 要加的好友id
     */
    private Integer friendId;

    /**
     * 群组id
     */
    private Integer groupId;

    /**
     * 类型 1 好友申请 2.群组申请
     */
    private Integer applyType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 1 通过 2 拒绝
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}