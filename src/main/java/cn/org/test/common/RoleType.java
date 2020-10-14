package cn.org.test.common;

import lombok.Data;

/**
 * Created by lyy on 2020/10/14 上午10:32
 */
public enum RoleType implements BaseEnum {


    STUDENT(0, "STUDENT"),
    TEACHER(2, "TEACHER"),
    ADMIN(3, "ADMIN");


    private Integer value;
    private String name;


    RoleType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }


    @Override
    public Integer getValue() {
        return value;
    }


    @Override
    public String getName() {
        return name;
    }


    public void setValue(Integer value) {
        this.value = value;
    }


    public void setName(String name) {
        this.name = name;
    }
}
