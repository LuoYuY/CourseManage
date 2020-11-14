package cn.org.test.service;

import cn.org.test.common.TaskScore;
import cn.org.test.pojo.*;
import cn.org.test.pojo.Class;
import cn.org.test.req.CreateCourseReq;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyy on 2020/10/27 下午2:03
 */

public interface CourseService {
    ArrayList<Semester> getSemesterList();

    ArrayList<Grade> getGradeList();

    Course getCourseDetail(Integer courseId);

    List<Class> getClassListTch(Integer courseId);

    List<Task> getTasksList(Integer classId);

    Task getTaskDetail(Integer taskId);

    List<TaskWare> getTaskWare(Integer taskId);

    TaskStu getStuTaskDetail(Integer taskId, Integer studentId);

    List<TaskStu> getStuTaskStatus(Integer taskId);

    Class getClassByTaskId(Integer taskId);

    List<User> getStuListInClass(Integer id);

    List<TaskScore> createDownloadExcel(Integer taskId);

    void saveTaskScore(TaskScore taskScore, Integer taskId, Integer classId);

    void updateStuTaskScore(TaskScore taskScore, Integer taskId);

    void deleteCourseWare(Integer courseWareId);

    Course getCourseByClassId(Integer classId);
}
