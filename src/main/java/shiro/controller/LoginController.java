package shiro.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shiro.domain.UserDO;
import shiro.service.LoginService;
import shiro.util.RequestUtil;
import shiro.util.ResponseMsg;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author m748124843
 * @Date 2021-01-31 22:13
 * @Version 1.0
 * 概况：登录的controller
 */
@Controller
public class LoginController {
    @Autowired
    LoginService loginService;

    /**
     * 用户登录操作
     * @return
     */
    @PostMapping("/login")
    public ResponseMsg login(HttpServletRequest request) {
        try {
            //得到json参数
            JSONObject json = RequestUtil.getInputParam(request);
            loginService.login(json);
            return ResponseMsg.success(loginService.getCookieUser());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMsg.failure(-1, "登录失败，请检查用户名密码是否正确");
        }
    }

    /**
     * 用户登出
     * @return
     */
    @PostMapping("/logout")
    public ResponseMsg LogOut() {
        loginService.LogOut();
        return ResponseMsg.success("登出成功！");
    }



    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * create time: 2019/7/3 14:53
     * @return
     */
    @PostMapping("/un_auth")
    public ResponseMsg unAuth() {
        return ResponseMsg.failure(-1, "用户未登录！");
    }

    /**
     * 未授权，无权限，此处返回未授权状态信息由前端控制跳转页面
     * create time: 2019/7/3 14:53
     * @return
     */
    @PostMapping("/unauthorized")
    public ResponseMsg unauthorized() {
        return ResponseMsg.failure(-2, "用户无权限！");
    }

}
