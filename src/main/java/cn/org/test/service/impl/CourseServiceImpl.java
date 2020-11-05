package cn.org.test.service.impl;

import cn.org.test.mapper.*;
import cn.org.test.pojo.*;
import cn.org.test.pojo.Class;
import cn.org.test.service.CourseService;
import cn.org.test.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lyy on 2020/10/27 下午2:03
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    public SemesterMapper semesterMapper;

    @Autowired
    public GradeMapper gradeMapper;

    @Autowired
    public CourseMapper courseMapper;

    @Autowired
    public ClassMapper classMapper;
    @Autowired
    public TaskMapper taskMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public ArrayList<Semester> getSemesterList() {
        ArrayList<Semester> list = semesterMapper.getAllSemester();

        //将list存在redis中
        return list;
    }

    @Override
    public ArrayList<Grade> getGradeList() {
        //将list存在redis中
        ArrayList<Grade> list = gradeMapper.getAllGrade();
        return list;
    }

    @Override
    public Course getCourseDetail(Integer courseId) {
        Course c = courseMapper.getCourseById(courseId);
        return c;
    }

    @Override
    public List<Class> getClassListTch(Integer courseId) {
        List<Class> list = classMapper.getClassByCourseId(courseId);
        return list;
    }

    @Override
    public List<Task> getTasksList(Integer classId) {
        List<Task> list = taskMapper.getTasksByClassId(classId);
        return list;
    }

    @Override
    public Task getTaskDetail(Integer taskId) {
        Task t = taskMapper.getTaskById(taskId);

        if(t.getStatus()==1) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = t.getEndTime();
            Date endTime = null;
            try {
                endTime = sdf.parse(dateString);
            }catch (Exception e){
                System.out.println("parse error");
            }
            Date currentDate = new Date();
            if( !currentDate.before(endTime)) {
                taskMapper.updateTaskState(taskId);
                t.setStatus(2);
            }
        }
        return t;
    }
}
