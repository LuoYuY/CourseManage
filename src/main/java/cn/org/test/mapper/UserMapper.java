package cn.org.test.mapper;

import cn.org.test.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by lyy on 2020/9/8 下午8:38
 */

public interface UserMapper {
//    public User getUser();
    User getUserByEmail(@Param("address") String email);
    Integer addUser(@Param("user") User user);
    User getUserById(@Param("id") Integer id);
}
