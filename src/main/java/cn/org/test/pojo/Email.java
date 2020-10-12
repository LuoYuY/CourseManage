package cn.org.test.pojo;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * Created by lyy on 2020/10/12 下午2:22
 */

public class Email {
    //成员属性
    private String emailAccount = "1653611386@qq.com";   //发件人的邮箱
    private String emailPassword = "perksmdbbjgredec";  //发件人邮箱授权码
    private String emailSMTPHost = "smtp.qq.com";       //发件人邮箱服务地址
    private String receiveMailAccount = "";             //收件人邮箱
    //构造方法
    public Email() { }
    public Email(String emailAccount, String emailPassword, String emailSMTPHost, String receiveMailAccount) {
        this.emailAccount = emailAccount;
        this.emailPassword = emailPassword;
        this.emailSMTPHost = emailSMTPHost;
        this.receiveMailAccount = receiveMailAccount;
    }
    //setter, getter方法
    public void setEmailAccount(String emailAccount) { this.emailAccount = emailAccount; }
    public void setEmailPassword(String emailPassword) { this.emailPassword = emailPassword; }
    public void setEmailSMTPHost(String emailSMTPHost) { this.emailSMTPHost = emailSMTPHost; }
    public void setReceiveMailAccount(String receiveMailAccount) { this.receiveMailAccount = receiveMailAccount; }
    public String getEmailAccount() { return emailAccount; }
    public String getEmailPassword() { return emailPassword; }
    public String getEmailSMTPHost() { return emailSMTPHost; }
    public String getReceiveMailAccount() { return receiveMailAccount; }

    //发送邮件；参数1：邮件主题，参数2：邮件内容
    public void sendEmail(String subject, String text) throws MessagingException {
        //设置连接邮件服务器的会话信息
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");    //设置使用SMTP协议
        properties.setProperty("mail.smtp.host", emailSMTPHost);      //设置邮箱服务器地址
        properties.setProperty("mail.smtp.auth", "true");             //设置进行身份验证
        //设置邮件发送端口为465
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //设置邮件信息
        Session session = Session.getDefaultInstance(properties);     //创建Session实例
        MimeMessage message = new MimeMessage(session);               //创建MimeMessage实例
        message.setFrom(new InternetAddress(emailAccount));           //设置发件人
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiveMailAccount));  //设置收件人
        message.setSentDate(new Date());                              //设置发发送时间
        message.setSubject(subject);                                  //设置邮件主题
        message.setText(text);                                        //设置邮件内容（6位验证码）
        message.saveChanges();                                        //保存并生成邮件
        session.setDebug(true);                                       //设置Debug模式
        //发送邮件
        Transport transport = session.getTransport("smtp");  //获取TranSport实例
        transport.connect(emailAccount, emailPassword);               //设置发件人和授权码
        transport.sendMessage(message, message.getAllRecipients());   //发送邮件
        transport.close();
    }


}
