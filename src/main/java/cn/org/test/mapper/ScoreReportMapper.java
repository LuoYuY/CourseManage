package cn.org.test.mapper;

import cn.org.test.pojo.ScoreReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by lyy on 2020/11/9 上午1:23
 */
@Mapper
public interface ScoreReportMapper {
    void addTaskScore(@Param("studentId") Integer studentId, @Param("taskId")Integer taskId,@Param("classId") Integer classId,@Param("score") Integer score);

    ScoreReport getScoreReportById(@Param("studentId")Integer studentId, @Param("taskId")Integer taskId);

    Integer updateScore(@Param("studentId")Integer studentId, @Param("taskId")Integer taskId, @Param("score")Integer score);
}
