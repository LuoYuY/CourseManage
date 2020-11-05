package cn.org.test.mapper;

import cn.org.test.pojo.SelectClass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lyy on 2020/11/2 下午4:13
 */
@Mapper
public interface SelectClassMapper {
    List<SelectClass> getSelectedCoursesByStuId(@Param("studentId") Integer studentId);

    Integer addSelectClass(@Param("classId")Integer classId,@Param("studentId")Integer studentId,@Param("status")int i);

    Integer deleteSelectClass(@Param("classId")Integer classId,@Param("studentId")Integer studentId);

}
