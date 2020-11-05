package cn.org.test.mapper;

import cn.org.test.pojo.TaskWare;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lyy on 2020/11/5 下午4:28
 */
@Mapper
public interface TaskWareMapper {

    Integer addTaskWare(@Param("file") TaskWare file);
    List<TaskWare> getFilesByTaskId(@Param("taskId")Integer taskId);
}
