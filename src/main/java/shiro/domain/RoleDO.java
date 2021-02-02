package shiro.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 角色表
 */
@Data
@Table(name = "sys_role")
public class RoleDO implements Serializable {

    /**
     * 角色主键
     */
    @Id
    @Column(name = "roleId")
    private String roleId;

    /**
     * 该角色使用有效(0为有效)
     */
    @Column(name = "available")
    private String available;

    /**
     * 角色的描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 角色名称
     */
    @Column(name = "role")
    private String role;

}
