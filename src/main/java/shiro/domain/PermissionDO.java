package shiro.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 权限表（资源表）
 */
@Data
@Table(name = "sys_permission")
public class PermissionDO implements Serializable {

    /**
     * 权限主键
     */
    @Id
    @Column(name = "permissionId")
    private String permissionId;

    /**
     * 该资源是否可以被获取
     */
    @Column(name = "available")
    private String available;

    /**
     * 菜单ID
     */
    @Column(name = "parentId")
    private String parentId;

    /**
     * 菜单路径
     */
    @Column(name = "parentIds")
    private String parentIds;

    /**
     * 用户权限
     */
    @Column(name = "permission")
    private String permission;

    /**
     * 用户权限名称
     */
    @Column(name = "permissionName")
    private String permissionName;

    /**
     * 资源类型
     */
    @Column(name = "resourceType")
    private String resourceType;

    /**
     * 资源url
     */
    @Column(name = "url")
    private String url;

}
