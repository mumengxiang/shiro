package shiro.service;

import shiro.domain.RoleDO;

import java.util.List;

/**
 * @Author m748124843
 * @Date 2021-01-24 22:52
 * @Version 1.0
 * 概况：角色的实现接口
 */
public interface RoleService {

    /**
     * 根据用户ID得到所有的角色
     * @param userId
     * @return
     */
    List<RoleDO> getRoleListById(String userId);
}
