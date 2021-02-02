package shiro.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import shiro.service.PermissionService;
import shiro.service.RoleService;
import shiro.service.UserService;
import shiro.BO.UserBO;
import shiro.domain.PermissionDO;
import shiro.domain.RoleDO;
import shiro.domain.UserDO;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 创建Realm
 * 自定义 Realm, 主要是重写其认证、授权
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    UserService userService;
    @Resource
    RoleService roleService;
    @Resource
    PermissionService permissionService;

    /**
     * 执行授权逻辑
     * 权限信息，包括角色以及权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //如果身份认证的时候没有传入User对象，这里只能取到userName
        //也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要User对象
        //得到需要授权的对象
        UserDO userDO = (UserDO) principalCollection.getPrimaryPrincipal();

        //根据用户ID得到该用户的所有角色
        List<RoleDO> roleDOList = roleService.getRoleListById(userDO.getUserId());
        //循环角色
        for (RoleDO roleDO : roleDOList) {
            //然后根据角色查询权限
            List<PermissionDO> permissionDOList =  permissionService.getPermissionByRoleId(roleDO.getRoleId());
            //循环权限
            for (PermissionDO permissionDO : permissionDOList) {
                //放入权限
                info.addStringPermission(permissionDO.getPermission());
            }
        }
        return info;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //编写shiro判断逻辑，判断用户名和密码
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //得到输入的用户名
        String inName = token.getUsername();
        //根据输入的用户名查询数据库得到用户对象
        UserDO userDO  = userService.getPwdByName(inName);
        //得到用户的BO类
        UserBO userBO = new UserBO();
        userBO.setUser(userDO);
        //根据用户ID得到角色的集合
        List<RoleDO> roleDOList = roleService.getRoleListById(userDO.getUserId());
        Set<String> roles = new HashSet<String>();
        for (RoleDO role : roleDOList) {
            roles.add(role.getRole());
        }
        //放入角色的集合
        userBO.setRoles(roles);
        //返回数据
        return new SimpleAuthenticationInfo(
                // 这里传入的是userBO对象，让取得时候既有用户信息也有角色信息
                userBO,
                // 密码
                userDO.getPassword(),
                // salt = username + salt
                ByteSource.Util.bytes(userDO.getCredentialsSalt()),
                // realm name
                getName()
        );
    }

}




