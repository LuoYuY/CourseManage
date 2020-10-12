package cn.org.test.service;

import cn.org.test.pojo.User;

import javax.mail.MessagingException;

/**
 * Created by lyy on 2020/9/8 下午8:38
 */

public interface UserService {
//    public User getUser();
    public User loginPwd(String username, String password);

    public void sendMail(String address,String ip) throws MessagingException;
}
