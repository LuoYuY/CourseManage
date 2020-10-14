package cn.org.test.mapper;

import cn.org.test.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by lyy on 2020/9/8 下午8:38
 */

public interface UserMapper {
//    public User getUser();
    User getUser(@Param("username") String username);
    Integer addUser(@Param("user") User user);
}
