package cn.org.test.common;

/**
 * Created by lyy on 2020/10/30 下午3:39
 */

public enum Grade implements BaseEnum {
    ONE(1, "本科生一年级"),
    TWO(2, "本科生二年级"),
    THREE(3, "本科生三年级"),
    FOUR(4, "本科生四年级"),
    FIVE(5, "研究生一年级"),
    SIX(6, "研究生二年级"),
    SEVEN(7, "研究生三年级");

    private Integer value;
    private String name;

    Grade(Integer value, String name) {
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
