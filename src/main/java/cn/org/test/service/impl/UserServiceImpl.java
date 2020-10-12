package cn.org.test.service.impl;

import cn.org.test.mapper.UserMapper;
import cn.org.test.pojo.Email;
import cn.org.test.pojo.User;
import cn.org.test.service.UserService;
import cn.org.test.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jredis.JredisUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

/**
 * Created by lyy on 2020/9/8 下午8:39
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserMapper userMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Autowired
    private RedisUtil redisUtil;



//    @Override
//    public User getUser() {
//        User user = userMapper.getUser();
//        return user;
//    }

    @Override
    public User loginPwd(String username, String password) {
        User user = userMapper.getUser(username);
        if( user!=null && password.equals(user.getPassword()) ){
            return user;
        }
        else return null;
    }

    @Override
    public void sendMail(String address,String ip) throws MessagingException {
        String code = getRandom();
        //创建email对象 向该email地址发送验证码
        Email email = new Email();
        email.setReceiveMailAccount(address);
        email.sendEmail("来自MyCourse的验证码", "验证码为  "+code);

        //将验证码存入redis缓存中 形式为 ip_code :  具体code , 生存时间为60秒
        redisUtil.set(ip+"_code",code,60);
        //System.out.println((String)redisUtil.get(ip+"_code"));
    }

    //生成验证码
    String getRandom() {
        String[] letters = new String[] {
                "q","w","e","r","t","y","u","i","o","p","a","s","d","f","g","h","j","k","l","z","x","c","v","b","n","m",
                "A","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M",
                "0","1","2","3","4","5","6","7","8","9"};
        String code ="";
        for (int i = 0; i < 6; i++) {
            code = code + letters[(int)Math.floor(Math.random()*letters.length)];
        }
        return code;
    }
}
