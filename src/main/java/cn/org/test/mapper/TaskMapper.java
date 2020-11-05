package cn.org.test.mapper;

import cn.org.test.pojo.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by lyy on 2020/11/5 下午4:01
 */
@Mapper
public interface TaskMapper {
    Integer addTask(@Param("task") Task task);
}
