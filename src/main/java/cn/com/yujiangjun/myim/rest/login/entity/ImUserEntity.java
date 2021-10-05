package cn.com.yujiangjun.myim.rest.login.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.annotation.Nonnull;

/**
 * im_user
 * @author 
 */
@Data
public class ImUserEntity implements Serializable {
    private Integer userId;

    @Nonnull
    private String userName;

    @Nonnull
    private String nickName;

    @Nonnull
    private String password;

    /**
     * 个性签名
     */
    private String personalSign;

    private String sex;

    private Date birthDate;

    private Integer age;

    /**
     * 头像
     */
    private String avtor;

    private static final long serialVersionUID = 1L;
}