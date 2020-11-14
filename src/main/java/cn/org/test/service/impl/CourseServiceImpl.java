package cn.org.test.service.impl;

import cn.org.test.common.TaskScore;
import cn.org.test.mapper.*;
import cn.org.test.pojo.*;
import cn.org.test.pojo.Class;
import cn.org.test.service.CourseService;
import cn.org.test.utils.RedisUtil;
import com.alibaba.excel.EasyExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
    public CourseWareMapper courseWareMapper;

    @Autowired
    public CourseMapper courseMapper;
    @Autowired
    public UserMapper userMapper;

    @Autowired
    public ClassMapper classMapper;
    @Autowired
    public TaskMapper taskMapper;
    @Autowired
    public TaskWareMapper taskWareMapper;
    @Autowired
    public TaskStuMapper taskStuMapper;

    @Autowired
    public SelectClassMapper selectClassMapper;

    @Autowired
    public ScoreReportMapper scoreReportMapper;

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

    @Override
    public List<TaskWare> getTaskWare(Integer taskId) {
        List<TaskWare> list = taskWareMapper.getFilesByTaskId(taskId);
        return list;
    }

    @Override
    public TaskStu getStuTaskDetail(Integer taskId, Integer studentId) {
        TaskStu taskStu = taskStuMapper.getTaskStuByTaskAndUser(taskId,studentId);
        return taskStu;
    }

    @Override
    public List<TaskStu> getStuTaskStatus(Integer taskId) {
        List<TaskStu> list = taskStuMapper.getTaskStuByTaskId(taskId);
        return list;
    }

    @Override
    public Class getClassByTaskId(Integer taskId) {
        Task t = taskMapper.getTaskById(taskId);
        Class c = classMapper.getClassById(t.getClassId());
        return c;
    }

    @Override
    public List<User> getStuListInClass(Integer id) {
        List<SelectClass> list = selectClassMapper.getSelectClassByClassId(id);
        Iterator<SelectClass> itr = list.iterator();
        List<User> studentList = new ArrayList<>();
        while(itr.hasNext()) {
            SelectClass item = itr.next();
            User u = userMapper.getUserById(item.getStudentId());
            studentList.add(u);
        }
        return studentList;
    }

    @Override
    public  List<TaskScore> createDownloadExcel(Integer taskId) {
        Task task = taskMapper.getTaskById(taskId);
        Class c = classMapper.getClassById(task.getClassId());
        List<TaskScore> list = new ArrayList<>();
        List<User> studentList = getStuListInClass(c.getId());
        Iterator<User> itr = studentList.iterator();
        while (itr.hasNext()){
            User user = itr.next();
            TaskScore temp = new TaskScore();
            temp.setId(user.getId());
            temp.setUserId(user.getUserId());
            temp.setName(user.getUserName());
            list.add(temp);
        }
        return list;


//        String fileName = "作业成绩单" + c.getName() + "-"+ task.getTitle() + ".xlsx";
//        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
//        // 如果这里想使用03 则 传入excelType参数即可
//        EasyExcel.write(fileName, TaskScore.class).sheet(task.getTitle()).doWrite(list);
    }

    @Override
    public void saveTaskScore(TaskScore taskScore, Integer taskId, Integer classId) {
        Integer studentId = taskScore.getId();
        Integer score = taskScore.getScore();
        ScoreReport temp = scoreReportMapper.getScoreReportById(studentId,taskId);
        if(temp!=null) {
            scoreReportMapper.updateScore(studentId,taskId,score);
        }
        else scoreReportMapper.addTaskScore(studentId,taskId,classId,score);

    }

    @Override
    public void updateStuTaskScore(TaskScore taskScore, Integer taskId) {
        Integer studentId = taskScore.getId();
        Integer score = taskScore.getScore();
        taskStuMapper.updateScore(studentId,taskId,score);
    }

    @Override
    public void deleteCourseWare(Integer courseWareId) {
        courseWareMapper.deleteById(courseWareId);
    }

    @Override
    public Course getCourseByClassId(Integer classId) {
        Class c = classMapper.getClassById(classId);
        Course course = courseMapper.getCourseById(c.getCourseId());
        return course;
    }
}
