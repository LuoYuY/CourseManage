package cn.org.test.common;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * Created by lyy on 2020/11/8 下午5:39
 */
@Data
public class TaskScore {
    @ExcelProperty("id")
    private Integer id;
    @ExcelProperty("学号")
    private String userId;
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("得分")
    private Integer score;
    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String ignore;
}
