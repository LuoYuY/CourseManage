package cn.org.test.mapper;

import cn.org.test.pojo.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * Created by lyy on 2020/10/30 下午12:05
 */

@Mapper
public interface GradeMapper {
    ArrayList<Grade> getAllGrade();
    Grade getGradeById(@Param("id")Integer id);
}
