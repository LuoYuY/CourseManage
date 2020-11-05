package cn.org.test.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created by lyy on 2020/11/5 下午3:49
 */
@Data
public class TaskWare {
    private Integer id;
    private Integer taskId;
    private String filename;
    private String filepath;
    private Date uploadDate;
}
