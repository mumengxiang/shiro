package shiro.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import shiro.domain.PermissionDO;

import java.util.List;

/**
 * @Author m748124843
 * @Date 2021-01-24 23:05
 * @Version 1.0
 * 概况：权限表（资源表的数据库操作）
 */
@Mapper
public interface PermissionDao {

    @Select(" select * from sys_permission WHERE permissionId in ( " +
            " select b.permissionId from sys_role a,sys_role_permission b WHERE a.roleId=b.roleId and a.roleId='${roleId}' " +
            " )")
    List<PermissionDO> getPermissionList(@Param("roleId")  String roleId);
}
