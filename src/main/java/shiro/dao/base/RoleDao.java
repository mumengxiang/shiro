package shiro.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import shiro.domain.RoleDO;

import java.util.List;

/**
 * @Author m748124843
 * @Date 2021-01-24 22:54
 * @Version 1.0
 * 概况：角色的数据库查询
 */
@Mapper
public interface RoleDao {

    @Select(" select * from sys_role where roleId in ( " +
            " select b.roleId from sys_user a ,sys_user_role b  " +
            " where a.userId=b.userId and a.userId='${userId}' " +
            " )")
    List<RoleDO> getRoleListByUserId(@Param("userId") String userId);
}
