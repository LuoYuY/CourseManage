package cn.org.test.req;

import lombok.Data;

/**
 * Created by lyy on 2020/10/27 下午3:48
 */
@Data
public class CreateCourseReq {
    private Integer teacherId;
    private String name;
    private String reason;
}
