package cn.org.test.service;

import cn.org.test.pojo.User;
import cn.org.test.req.RegisterReq;
import com.sun.webkit.dom.HTMLAnchorElementImpl;

import javax.mail.MessagingException;

/**
 * Created by lyy on 2020/9/8 下午8:38
 */

public interface UserService {
//    public User getUser();
   User loginPwd(String email, String password);

   void sendMail(String address,String ip) throws MessagingException;

   User registerStudent(RegisterReq registerReq,String ip);

   User findUserById(String userId);

   User findUserByEmail(String email);
}
