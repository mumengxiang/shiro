package shiro.BO;

import lombok.Data;
import shiro.domain.UserDO;

import java.io.Serializable;
import java.util.Set;

/**
 * @Author m748124843
 * @Date 2021-01-21 22:50
 * @Version 1.0
 * 概况：用户对象的BO类
 */
@Data
public class UserBO implements Serializable {

    private static final long serialVersionUID = 1L;

    private UserDO user;
    /**
     * 角色
     */
    private Set<String> roles;
}
