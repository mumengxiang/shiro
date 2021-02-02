package shiro.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 用户角色关联表
 */
@Data
@Table(name = "sys_user_role")
public class User_RoleDO implements Serializable {

    /**
     * 关联主键
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 用户id
     */
    @Id
    @Column(name = "userId")
    private String userId;

    /**
     * 角色主键
     */
    @Id
    @Column(name = "roleId")
    private String roleId;
}
