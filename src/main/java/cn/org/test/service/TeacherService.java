package cn.org.test.service;

import cn.org.test.pojo.Course;
import cn.org.test.pojo.CreateApplication;
import cn.org.test.pojo.CreateClassApplication;

import java.util.List;

/**
 * Created by lyy on 2020/10/27 下午6:50
 */

public interface TeacherService {
    List<CreateApplication> getCreListFromTch(Integer teacherId);

    List<Course> getCoursesList(Integer teacherId);

    List<CreateClassApplication> getCreClassListFromTch(Integer teacherId);
}
