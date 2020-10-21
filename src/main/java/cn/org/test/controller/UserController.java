package cn.org.test.controller;

import cn.org.test.common.ServerResponse;
import cn.org.test.pojo.User;
import cn.org.test.req.RegisterReq;
import cn.org.test.service.TokenService;
import cn.org.test.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * Created by lyy on 2020/9/8 下午8:40
 */

@RestController
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserService userService;

    @Autowired
    public TokenService tokenService;
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
    public ServerResponse loginPwd(String email, String password, HttpServletResponse response) throws UnsupportedEncodingException {

        System.out.println("password:"+password);
        User userForBase = new User();
        User userTemp = userService.findUserByEmail(email);
        System.out.println("userTemp:"+userTemp.getAddress()+userTemp.getPassword());
        userForBase.setId(userTemp.getId());
        userForBase.setAddress(userTemp.getAddress());
        userForBase.setPassword(userTemp.getPassword());

        if (!userForBase.getPassword().equals(password)) {
            return ServerResponse.createByErrorCodeMessage(1, "用户名或密码错误！");
        } else {
            JSONObject jsonObject = new JSONObject();
            String token = tokenService.getToken(userForBase);
            jsonObject.put("token", token);
            User user = userService.loginPwd(email, password);
            user.setPassword("");
            jsonObject.put("currentUser", user);

            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);
            return ServerResponse.createBySuccess(jsonObject);
        }

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



//
//
//    /***
//     * 这个请求需要验证token才能访问
//     *
//     * @author: qiaoyn
//     * @date 2019/06/14
//     * @return String 返回类型
//     */
//    @UserLoginToken
//    @ApiOperation(value = "获取信息", notes = "获取信息")
//    @RequestMapping(value = "/getMessage" ,method = RequestMethod.GET)
//    public String getMessage() {
//
//        // 取出token中带的用户id 进行操作
//        System.out.println(TokenUtil.getTokenUserId());
//
//        return "您已通过验证";
//    }
}
