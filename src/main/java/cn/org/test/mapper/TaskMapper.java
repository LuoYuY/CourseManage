package cn.org.test.mapper;

import cn.org.test.pojo.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lyy on 2020/11/5 下午4:01
 */
@Mapper
public interface TaskMapper {
    Integer addTask(@Param("task") Task task);
    List<Task> getTasksByClassId(@Param("classId")Integer classId);
    Task getTaskById(@Param("taskId")Integer taskId);
    Integer updateTaskState(@Param("taskId")Integer taskId);
    void updateFinishedNum(@Param("taskId")Integer taskId);
}
