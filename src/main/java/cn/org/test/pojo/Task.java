package cn.org.test.pojo;

import lombok.Data;

/**
 * Created by lyy on 2020/11/5 下午3:46
 */
@Data
public class Task {
    private Integer id;
    private Integer classId;
    private Integer num;
    private String title;
    private String content;
    private String startTime;
    private String endTime;
    private Integer sum;
    private Integer finishedSum;
}
