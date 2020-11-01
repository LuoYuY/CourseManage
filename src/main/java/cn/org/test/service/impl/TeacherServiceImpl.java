package cn.org.test.service.impl;

import cn.org.test.mapper.CourseMapper;
import cn.org.test.mapper.CreateApplicationMapper;
import cn.org.test.mapper.CreateClassApplicationMapper;
import cn.org.test.pojo.Course;
import cn.org.test.pojo.CreateApplication;
import cn.org.test.pojo.CreateClassApplication;
import cn.org.test.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.zip.CheckedOutputStream;

/**
 * Created by lyy on 2020/10/27 下午6:51
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    public CreateApplicationMapper createApplicationMapper;

    @Autowired
    public CreateClassApplicationMapper createClassApplicationMapper;

    @Autowired
    public CourseMapper courseMapper;

    @Override
    public List<CreateApplication> getCreListFromTch(Integer teacherId) {
        List<CreateApplication> list = createApplicationMapper.getListById(teacherId);
//        System.out.println("service:"+list);
        return list;
    }

    @Override
    public List<Course> getCoursesList(Integer teacherId) {
        List<Course> list = courseMapper.getCoursesById(teacherId);
        return list;
    }

    @Override
    public List<CreateClassApplication> getCreClassListFromTch(Integer teacherId) {
        List<CreateClassApplication> list = createClassApplicationMapper.getListById(teacherId);
//        System.out.println("service:"+list);
        return list;
    }
}
