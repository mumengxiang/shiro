package shiro.service.impl;

import org.springframework.stereotype.Service;
import shiro.service.*;
import shiro.dao.base.RoleDao;
import shiro.domain.RoleDO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author m748124843
 * @Date 2021-01-24 22:52
 * @Version 1.0
 * 概况：角色的接口实现类
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleDao roleDao;

    /**
     * 根据用户ID得到所有的角色
     * @param userId
     * @return List<RoleDO>
     */
    @Override
    public List<RoleDO> getRoleListById(String userId) {
        //初始化结构结果集
        List<RoleDO> roleDOList = new ArrayList<>();
        try {
            //得到结果集
            roleDOList = roleDao.getRoleListByUserId(userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return roleDOList;
    }
}
