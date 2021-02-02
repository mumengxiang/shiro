package shiro.dao.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import shiro.domain.UserDO;

import java.util.List;

/**
 * @Author m748124843
 * @Date 2021-01-21 22:56
 * @Version 1.0
 * 概况：用户的Dao
 */
@Mapper
public interface UserDao {

    /**
     * 根据输入的用户名查询数据库得到用户对象
     * @param inName
     * @return
     */
    @Select("select top 1 * from sys_user where userName='${inName}' ")
    UserDO getPwdByName(@Param("inName") String inName);

    /**
     * 得到用户的集合
     * @return
     */
    @Select("select * from sys_user ")
    List<UserDO> getUserList();
}
