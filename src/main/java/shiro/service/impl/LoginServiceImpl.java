package shiro.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import shiro.BO.UserBO;
import shiro.dao.base.UserDao;
import shiro.domain.UserDO;
import shiro.service.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author m748124843
 * @Date 2021-01-31 22:32
 * @Version 1.0
 * 概况：
 */
@Service
public class LoginServiceImpl implements LoginService {

    /**
     * 登录接口
     * @param json
     */
    @Override
    public void login(JSONObject json) {
        try {
            //得到用户名和密码
            String loginName = json.getString("loginName");
            String password = json.getString("password");
            //1.获取Subject
            final Subject subject = SecurityUtils.getSubject();
            //2.封装用户数据
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(loginName, password);
            //3.执行登录方法
            //如果没有异常 则表示登录成功
            //此处登录方法会进入到UserRealm的doGetAuthenticationInfo方法中
            subject.login(usernamePasswordToken);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 得到用户和角色
     * @return
     */
    @Override
    public UserBO getCookieUser() {
        //定义用户BO
        UserBO userBO = new UserBO();
        try {
            userBO =  (UserBO) SecurityUtils.getSubject().getPrincipal();
        }catch (Exception e){
            e.printStackTrace();
        }
        return userBO;
    }


    /**
     * 用户登出
     */
    @Override
    public void LogOut() {
        //用户登出
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
