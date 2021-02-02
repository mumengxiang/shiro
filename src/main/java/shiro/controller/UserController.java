package shiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shiro.domain.UserDO;
import shiro.service.UserService;
import shiro.util.ResponseMsg;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author m748124843
 * @Date 2021-02-01 0:16
 * @Version 1.0
 * 概况：用户的controller
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    /**
     * 用户查询.
     * @return
     */
    @PostMapping("/userList")
    @RequiresPermissions("user:view")//权限管理;
    public ResponseMsg listUsers(){
        //定义返回集合
        List<UserDO> usersList = new ArrayList<>();
        try {
            usersList = userService.listUsers();
            return ResponseMsg.success(usersList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMsg.success("查询有误");
        }
    }


    /**
     * 用户添加;
     * @return
     */
    @PostMapping("/userAdd")
    @RequiresPermissions("user:add")//权限管理;
    public String userInfoAdd(){
        return "userAdd";
    }

    /**
     * 用户删除;
     * @return
     */
    @DeleteMapping("/userDel")
    @RequiresPermissions("user:del")//权限管理;
    public String userDel(){
        return "userDel";
    }

}
