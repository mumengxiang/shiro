package shiro.service;

import shiro.domain.UserDO;

import java.util.List;

/**
 * @Author m748124843
 * @Date 2021-01-24 22:49
 * @Version 1.0
 * 概况：用户的的实现接口
 */
public interface UserService {

    /**
     * 根据输入的用户名查询数据库得到用户对象
     * @param inName
     * @return
     */
    UserDO getPwdByName(String inName);


    /**
     * 得到用户集合
     * @return
     */
    List<UserDO> listUsers();
}
