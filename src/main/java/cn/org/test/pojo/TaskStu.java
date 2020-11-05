package cn.org.test.pojo;

import lombok.Data;

/**
 * Created by lyy on 2020/11/6 上午1:03
 */
@Data
public class TaskStu {
    private Integer id;
    private Integer studentId;
    private Integer taskId;
    private Integer status;
    private String filename;
    private String filepath;
    private String uploadTime;
    private Integer score;
}
