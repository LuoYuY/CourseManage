package cn.org.test.mapper;

import cn.org.test.pojo.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lyy on 2020/10/29 下午5:01
 */
@Mapper
public interface CourseMapper {
    Integer addCourse(@Param("name")String name, @Param("teacherId")Integer teacherId, @Param("teacherName")String teacherName);
    List<Course> getCoursesById(@Param("teacherId")Integer teacherId);
    Course getCourseById(@Param("id")Integer id);
}
