package cn.org.test.req;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by lyy on 2020/10/30 下午3:04
 */
@Data
public class CreateClassReq {
    private Integer teacherId;
    private Integer courseId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private Integer semesterId;
    private Integer gradeId;
    private Integer maxNum;
}
