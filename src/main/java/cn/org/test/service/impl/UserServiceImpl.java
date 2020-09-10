package cn.org.test.service.impl;

import cn.org.test.mapper.UserMapper;
import cn.org.test.pojo.User;
import cn.org.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lyy on 2020/9/8 下午8:39
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserMapper userMapper;

    @Override
    public User getUser() {
        User user = userMapper.getUser();
        return user;
    }
}