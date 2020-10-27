package cn.org.test.mapper;

import cn.org.test.pojo.Semester;
import cn.org.test.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * Created by lyy on 2020/10/27 下午1:56
 */
@Mapper
public interface SemesterMapper {
    ArrayList<Semester> getAllSemester();
}
