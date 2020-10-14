package cn.org.test.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * Created by lyy on 2020/10/14 上午11:13
 */


public interface UserRoleMapper {
    Integer add(@Param("user_id") Integer userId, @Param("role_id") Integer roleId) ;
}
