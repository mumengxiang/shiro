package shiro.domain;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 权限表（资源表）和角色对照表
 */
@Data
@Table(name = "sys_role_permission")
public class Role_PermissionDO implements Serializable {

    /**
     * 权限表（资源表）和角色对照表主键
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 权限ID
     */
    @Column(name = "permissionId")
    private String permissionId;

    /**
     * 角色ID
     */
    @Column(name = "roleId")
    private String roleId;


}
