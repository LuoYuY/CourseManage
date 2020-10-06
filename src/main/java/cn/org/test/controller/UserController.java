package cn.org.test.controller;

import cn.org.test.pojo.User;
import cn.org.test.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lyy on 2020/9/8 下午8:40
 */

@RestController
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserService userService;
//
//    @RequestMapping("/getUser")
//    @ResponseBody
//    public User getUser(String userId) {
//        System.out.println("获取到用户的唯一标识:" + userId);
//        User user = userService.getUser();
//        return user;
//    }

    //login with the username and password
    @ResponseBody
    @PostMapping(value="/loginPwd")
    public User loginPwd(String username,String password) {
        User user = userService.loginPwd(username,password);
        return user;
    }

}
