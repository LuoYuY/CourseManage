package cn.org.test.service;

import cn.org.test.pojo.Grade;
import cn.org.test.pojo.Semester;
import cn.org.test.req.CreateCourseReq;

import java.util.ArrayList;

/**
 * Created by lyy on 2020/10/27 下午2:03
 */

public interface CourseService {
    ArrayList<Semester> getSemesterList();

    ArrayList<Grade> getGradeList();
}
