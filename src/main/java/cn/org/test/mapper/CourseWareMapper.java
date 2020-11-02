package cn.org.test.mapper;

import cn.org.test.pojo.CourseWare;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lyy on 2020/11/2 上午10:33
 */
@Mapper
public interface CourseWareMapper {
    Integer addCourseWare(@Param("file") CourseWare file);

    List<CourseWare> getFilesByCourseId(@Param("courseId")Integer courseId);
}
