package cn.org.test.service;

import cn.org.test.req.CreateClassReq;
import cn.org.test.req.CreateCourseReq;

/**
 * Created by lyy on 2020/10/27 下午3:47
 */

public interface ApplicationService {
    void createCourseApply(CreateCourseReq createCourseReq);

    void createClassApply(CreateClassReq createClassReq);
}
