package cn.org.test.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by lyy on 2020/10/29 下午5:01
 */
@Mapper
public interface CourseMapper {
    Integer addCourse(@Param("name")String name, @Param("teacherId")Integer teacherId, @Param("teacherName")String teacherName);
}
