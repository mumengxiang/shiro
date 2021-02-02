package shiro.service.impl;

import shiro.service.*;
import shiro.dao.base.PermissionDao;
import shiro.domain.PermissionDO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author m748124843
 * @Date 2021-01-24 23:07
 * @Version 1.0
 * 概况：权限表（资源表）的实现类
 */
public class PermissionServiceImpl implements PermissionService {

    @Resource
    PermissionDao permissionDao;

    /**
     * 根据角色ID获得权限
     * @param roleId
     * @return
     */
    @Override
    public List<PermissionDO> getPermissionByRoleId(String roleId) {
       //根据角色ID获得权限
        List<PermissionDO> permissionDOList = new ArrayList<>();
        try {
            //得到数据集
            permissionDOList = permissionDao.getPermissionList(roleId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return permissionDOList;
    }
}
