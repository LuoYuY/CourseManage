package cn.org.test.controller;

import cn.org.test.pojo.User;
import cn.org.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lyy on 2020/9/8 下午8:40
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserService userService;
    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(String userId) {
        System.out.println("获取到用户的唯一标识:"+userId);
        User user = userService.getUser();
        return user;
    }
}
