package shiro.service.impl;

import org.springframework.stereotype.Service;
import shiro.dao.base.UserDao;
import shiro.domain.UserDO;
import shiro.service.UserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author m748124843
 * @Date 2021-01-24 22:49
 * @Version 1.0
 * 概况：用户业务的实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;


    /**
     * 根据输入的用户名查询数据库得到用户对象
     * @param inName
     * @return
     */
    @Override
    public UserDO getPwdByName(String inName) {
        //初始化对象
        UserDO userDO = new UserDO();
        try {
            //得到数据
            userDO = userDao.getPwdByName(inName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userDO;
    }


    /**
     * 得到用户集合
     * @return
     */
    @Override
    public List<UserDO> listUsers() {
        //初始化用户集合
        List<UserDO> userList = new ArrayList<>();
        try {
            userList = userDao.getUserList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return userList;
    }
}
