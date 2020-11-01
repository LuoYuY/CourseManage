package cn.org.test.service.impl;

import cn.org.test.common.ApplicationStatus;
import cn.org.test.common.Grade;
import cn.org.test.mapper.*;
import cn.org.test.pojo.CreateApplication;
import cn.org.test.pojo.CreateClassApplication;
import cn.org.test.pojo.Semester;
import cn.org.test.req.CreateClassReq;
import cn.org.test.req.CreateCourseReq;
import cn.org.test.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lyy on 2020/10/27 下午3:46
 */
@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    public CreateApplicationMapper createApplicationMapper;

    @Autowired
    public CourseMapper courseMapper;

    @Autowired
    public GradeMapper gradeMapper;

    @Autowired
    public SemesterMapper semesterMapper;

    @Autowired
    public CreateClassApplicationMapper createClassApplicationMapper;

    @Override
    public void createCourseApply(CreateCourseReq createCourseReq) {
        Integer teacherId = createCourseReq.getTeacherId();
        String name = createCourseReq.getName();
        String reason = createCourseReq.getReason();

        CreateApplication apply = new CreateApplication();
        apply.setTeacherId(teacherId);
        apply.setName(name);
        apply.setReason(reason);
        apply.setStatus(ApplicationStatus.WAITING.getValue());
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//        System.out.println(formatter.format(date));
        apply.setCreateDate(new Date());
        createApplicationMapper.addCreateApplication(apply);
    }

    @Override
    public void createClassApply(CreateClassReq createClassReq) {
        Integer teacherId = createClassReq.getTeacherId();
        Integer courseId = createClassReq.getCourseId();
        String courseName = courseMapper.getCourseById(courseId).getName();
        Date endDate = createClassReq.getEndDate();
        Integer gradeId = createClassReq.getGradeId();
        String gradeName = gradeMapper.getGradeById(gradeId).getName();
        Integer maxNum = createClassReq.getMaxNum();
        Integer semesterId = createClassReq.getSemesterId();
        Semester semesterTemp = semesterMapper.getSemesterById(semesterId);
        String semesterName = semesterTemp.getYear()+"学年 第"+semesterTemp.getTerm()+"学期";
        Date startDate = createClassReq.getStartDate();

        CreateClassApplication apply = new CreateClassApplication();
        apply.setTeacherId(teacherId);
        apply.setCourseId(courseId);
        apply.setCourseName(courseName);
        apply.setCreateDate(new Date());
        apply.setEndDate(endDate);
        apply.setGradeId(gradeId);
        apply.setGradeName(gradeName);
        apply.setMaxNum(maxNum);
        apply.setSemesterId(semesterId);
        apply.setSemesterName(semesterName);
        apply.setStartDate(startDate);
        apply.setStatus(ApplicationStatus.WAITING.getValue());

        createClassApplicationMapper.addCreateClassApplication(apply);
    }
}
