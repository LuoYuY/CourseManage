package cn.org.test.mapper;

import cn.org.test.pojo.Semester;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * Created by lyy on 2020/10/27 下午1:56
 */
@Mapper
public interface SemesterMapper {
    ArrayList<Semester> getAllSemester();
    Semester getSemesterById(@Param("id")Integer id);

}
