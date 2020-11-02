package cn.org.test.service.impl;

import cn.org.test.common.ApplicationStatus;
import cn.org.test.mapper.*;
import cn.org.test.pojo.*;
import cn.org.test.pojo.Class;
import cn.org.test.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by lyy on 2020/10/28 下午4:34
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    public CreateApplicationMapper createApplicationMapper;

    @Autowired
    public CreateClassApplicationMapper createClassApplicationMapper;

    @Autowired
    public CourseMapper courseMapper;

    @Autowired
    public UserMapper userMapper;

    @Autowired
    public ClassMapper classMapper;
    @Override
    public List<CreateApplicationAdmin> getCreList() {
        List<CreateApplicationAdmin> list = createApplicationMapper.getAllList();
//        System.out.println("service:"+list);
        return list;
    }

    @Override
    public boolean dealCreApply(Integer id, Integer choice) {
        //pass 1; reject 2
        try {
            if (choice == 1) {
                createApplicationMapper.updateStatus(id, ApplicationStatus.VERIFIED.getValue(), new Date());
                CreateApplication alc = createApplicationMapper.getAlcById(id);
                String name = alc.getName();
                User teacher = userMapper.getUserById(alc.getTeacherId());
                Integer teacherId = teacher.getId();
                String teacherName = teacher.getUserName();
                courseMapper.addCourse(name,teacherId,teacherName);
                return true;
            } else if (choice == 2) {
                createApplicationMapper.updateStatus(id, ApplicationStatus.REJECTED.getValue(), new Date());
                return true;
            } else return false;
        }
        catch (Exception exception) {
            return false;
        }
    }

    @Override
    public List<CreateClassAppliAdmin> getCreClassList() {
        List<CreateClassAppliAdmin> list = createClassApplicationMapper.getAllList();
//        System.out.println("service:"+list);
        return list;
    }

    @Override
    public boolean dealCreClassApply(Integer id, Integer choice) {
        //pass 1; reject 2
        try {
            if (choice == 1) {
                createClassApplicationMapper.updateStatus(id, ApplicationStatus.VERIFIED.getValue(), new Date());
                CreateClassApplication alc = createClassApplicationMapper.getAlcById(id);
                Class newClass = new Class();
                newClass.setCourseId(alc.getCourseId());
                System.out.println("adminSerive Name:"+alc.getName());
                newClass.setName(alc.getName());
                newClass.setCourseName(alc.getCourseName());
                String teacherName = userMapper.getUserById(alc.getTeacherId()).getUserName();
                newClass.setTeacherName(teacherName);
                newClass.setStartDate(alc.getStartDate());
                newClass.setEndDate(alc.getEndDate());
                newClass.setSemesterId(alc.getSemesterId());
                newClass.setGradeId(alc.getGradeId());
                newClass.setMaxNum(alc.getMaxNum());
                classMapper.addClass(newClass);
                return true;
            } else if (choice == 2) {
                createClassApplicationMapper.updateStatus(id, ApplicationStatus.REJECTED.getValue(), new Date());
                return true;
            } else return false;
        }
        catch (Exception exception) {
            return false;
        }
    }
}
