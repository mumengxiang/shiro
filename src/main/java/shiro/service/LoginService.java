package shiro.service;

import com.alibaba.fastjson.JSONObject;
import shiro.BO.UserBO;
import shiro.domain.UserDO;

import java.util.List;

/**
 * @Author m748124843
 * @Date 2021-01-31 22:25
 * @Version 1.0
 * 概况：用户登录接口
 */
public interface LoginService {

    /**
     * 登录接口
     * @param json
     */
    void login(JSONObject json);

    /**
     * 得到用户和角色
     * @return
     */
    UserBO getCookieUser();

    /**
     * 用户登出
     */
    void LogOut();

}
