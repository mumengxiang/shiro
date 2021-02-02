package shiro.service;

import shiro.domain.PermissionDO;

import java.util.List;

/**
 * @Author m748124843
 * @Date 2021-01-24 23:07
 * @Version 1.0
 * 概况：
 */
public interface PermissionService {

    /**
     * 根据角色ID获得权限
     * @param roleId
     * @return
     */
    List<PermissionDO> getPermissionByRoleId(String roleId);
}
