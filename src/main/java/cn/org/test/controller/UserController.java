package cn.org.test.controller;

import cn.org.test.common.ServerResponse;
import cn.org.test.pojo.User;
import cn.org.test.req.RegisterReq;
import cn.org.test.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

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
    @PostMapping(value = "/loginPwd")
    public ServerResponse<User> loginPwd(String username, String password) {
        User user = userService.loginPwd(username, password);
        if (user != null) {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorCodeMessage(1, "用户名或密码错误！");
    }

    @ResponseBody
    @PostMapping(value = "/verify")
    public ServerResponse<String> verify(String address, HttpServletRequest request) {
        try {
            //获取ip地址 作为存在redis中的key
            //String test = request.getHeader("x-forwarded-for");
            String verifyKey = request.getRemoteAddr();

            userService.sendMail(address,verifyKey);
        } catch (MessagingException e) {
            return ServerResponse.createByErrorCodeMessage(1, "邮件发送失败");
        }
        return ServerResponse.createBySuccess("send success");
    }

    @ResponseBody
    @PostMapping(value = "/registerStudent")
    public ServerResponse<User> registerStudent(RegisterReq registerReq, HttpServletRequest request) {
        String verifyKey = request.getRemoteAddr();
        User user = userService.registerStudent(registerReq,verifyKey);
        if(user!=null)
            return ServerResponse.createBySuccess(user);
        else return ServerResponse.createByErrorCodeMessage(2,"注册失败");
    }
}
