package cn.org.test.service.impl;

import cn.org.test.mapper.ClassMapper;
import cn.org.test.mapper.GradeMapper;
import cn.org.test.mapper.SelectClassMapper;
import cn.org.test.mapper.SemesterMapper;
import cn.org.test.pojo.*;
import cn.org.test.pojo.Class;
import cn.org.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lyy on 2020/11/2 下午4:31
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    public ClassMapper classMapper;
    @Autowired
    public SelectClassMapper selectClassMapper;
    @Autowired
    public SemesterMapper semesterMapper;
    @Autowired
    public GradeMapper gradeMapper;

    @Override
    public List<ClassForSelect> getAllClassList() {
        List<Class> list = classMapper.getClassListToSelect();
        List<ClassForSelect> result = new ArrayList<>();
        Iterator<Class> iter = list.iterator();
        while(iter.hasNext())
        {
            ClassForSelect obj = new ClassForSelect();
            Class item = iter.next();
            obj.setCourseName(item.getCourseName());
            obj.setId(item.getId());
            obj.setName(item.getName());
            obj.setTeacherName(item.getTeacherName());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            obj.setStartDate(formatter.format(item.getStartDate()));
            obj.setEndDate(formatter.format(item.getEndDate()));
            Semester s = semesterMapper.getSemesterById(item.getSemesterId());
            obj.setSemesterName(s.getYear()+"学年 第"+s.getTerm()+"学期");
            Grade g = gradeMapper.getGradeById(item.getGradeId());
            obj.setGradeName(g.getName());
            obj.setMaxNum(item.getMaxNum());
            obj.setStatus(item.getStatus());
            obj.setNum(item.getNum());
            result.add(obj);
        }
        return result;
    }

    @Override
    public List<SelectClass> getSelectedCourses(Integer studentId) {
        List<SelectClass> list = selectClassMapper.getSelectedCoursesByStuId(studentId);
        return list;
    }

    @Override
    public boolean selectClass(Integer classId, Integer studentId) {
        try{
            //添加选课记录
            selectClassMapper.addSelectClass(classId,studentId,0);
            //class中 选课人数+1，class表更新
            classMapper.addNum(classId);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean disSelectClass(Integer classId, Integer studentId) {
        try{
            //添加选课记录
            selectClassMapper.deleteSelectClass(classId,studentId);
            //class中 选课人数+1，class表更新
            classMapper.deleteNum(classId);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Class> getClassesList(Integer studentId) {
        List<SelectClass> list  = selectClassMapper.getSelectedCoursesByStuId(studentId);
        List<Class> res = new ArrayList<>();
        Iterator<SelectClass> itr = list.iterator();
        while(itr.hasNext())
        {
            SelectClass item = itr.next();
            Class obj = classMapper.getClassById(item.getClassId());
            res.add(obj);
        }
        return res;
    }
}
