package cn.org.test.service;

import cn.org.test.pojo.Class;
import cn.org.test.pojo.ClassForSelect;
import cn.org.test.pojo.SelectClass;

import java.util.List;

/**
 * Created by lyy on 2020/11/2 下午4:31
 */

public interface StudentService {
    List<ClassForSelect> getAllClassList();

    List<SelectClass> getSelectedCourses(Integer studentId);

    boolean selectClass(Integer classId, Integer studentId);

    boolean disSelectClass(Integer classId, Integer studentId);

    List<Class> getClassesList(Integer studentId);
}
