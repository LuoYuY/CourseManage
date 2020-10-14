package cn.org.test.utils;

import cn.org.test.common.BaseEnum;

/**
 * Created by lyy on 2020/10/14 上午10:57
 */

public final class EnumUtil {

    /**
     * 判断枚举值是否存在于指定枚举数组中
     *
     * @param enums 枚举数组
     * @param value 枚举值
     * @return boolean
     */
    public static boolean isExist(BaseEnum[] enums, Integer value) {
        if (value == null) {
            return false;
        }
        for (BaseEnum e : enums) {
            if (value.equals(e.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据枚举值获取其对应的name
     *
     * @param enums 枚举数组
     * @param value 枚举值
     * @return 枚举值获取其对应的name
     */
    public static String getNameByValue(BaseEnum[] enums, Integer value) {
        if (value == null) {
            return "";
        }
        for (BaseEnum e : enums) {
            if (value.equals(e.getValue())) {
                return e.getName();
            }
        }
        return "";
    }
}
