package cn.org.test.mapper;

import cn.org.test.pojo.Task;
import cn.org.test.pojo.TaskStu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by lyy on 2020/11/6 上午1:12
 */
@Mapper
public interface TaskStuMapper {
    Integer addTaskStu(@Param("task")TaskStu task);
    TaskStu getTaskStuByTaskAndUser(@Param("taskId")Integer taskId, @Param("userId")Integer userId);
    Integer deleteById(@Param("id")Integer id);
}