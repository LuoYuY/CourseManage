package cn.org.test.req;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;

/**
 * Created by lyy on 2020/10/12 下午8:35
 */


@Data
public class RegisterReq {
    private String user_id;
    private String user_name;
    private String password;
    private String address;
    private String verifyCode;
}
