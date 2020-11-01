package cn.org.test.service.impl;

import cn.org.test.common.ApplicationStatus;
import cn.org.test.mapper.CourseMapper;
import cn.org.test.mapper.CreateApplicationMapper;
import cn.org.test.mapper.CreateClassApplicationMapper;
import cn.org.test.mapper.UserMapper;
import cn.org.test.pojo.*;
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
}
