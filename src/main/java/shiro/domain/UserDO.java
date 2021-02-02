package shiro.domain;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 用户表
 */
@Data
@Table(name = "sys_user")
public class UserDO implements Serializable {
    /**
     * 用户主键
     */
    @Id
    @Column(name = "userId")
    private String userId;
    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private String createTime;
    /**
     * 用户邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 失效日期
     */
    @Column(name = "expiredDate")
    private String expiredDate;

    /**
     * 用户中文名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 用户密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 密码加盐
     */
    @Column(name = "salt")
    private String salt;

    /**
     * 状态
     */
    @Column(name = "state")
    private String state;

    /**
     * 用户登录名
     */
    @Column(name = "userName")
    private String userName;


    /**
     * 密码盐. 重新对盐重新进行了定义，用户名+salt，这样就不容易被破解，可以采用多种方式定义加盐
     * @return
     */
    public String getCredentialsSalt() {
        return this.userName + this.salt;
    }
}
