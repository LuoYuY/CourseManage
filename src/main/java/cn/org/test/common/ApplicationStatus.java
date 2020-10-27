package cn.org.test.common;

/**
 * Created by lyy on 2020/10/27 下午3:35
 */

import lombok.Data;


public enum ApplicationStatus implements BaseEnum {


    WAITING(0, "WATING"),
    VERIFIED(1, "VERIFIED"),
    REJECTED(2, "REJECTED");


    private Integer value;
    private String name;


    ApplicationStatus(Integer value, String name) {
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
