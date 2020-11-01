package cn.org.test.service.impl;

import cn.org.test.mapper.GradeMapper;
import cn.org.test.mapper.SemesterMapper;
import cn.org.test.pojo.Grade;
import cn.org.test.pojo.Semester;
import cn.org.test.service.CourseService;
import cn.org.test.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
}
