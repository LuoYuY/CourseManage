package cn.org.test.pojo;

import lombok.Data;

/**
 * Created by lyy on 2020/11/9 上午1:22
 */
@Data
public class ScoreReport {
    private Integer id;
    private Integer studentId;
    private Integer taskId;
    private Integer classId;
    private Integer score;
}
