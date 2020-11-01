package cn.org.test.pojo;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * Created by lyy on 2020/10/30 下午2:56
 */
@Data
public class Class {
    private Integer id;
    private Integer courseId;
    private String courseName;
    private Date startDate;
    private Date endDate;
    private Integer semesterId;
    private Integer gradeId;
    private Integer maxNum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class aClass = (Class) o;
        return Objects.equals(id, aClass.id) &&
                Objects.equals(courseId, aClass.courseId) &&
                Objects.equals(courseName, aClass.courseName) &&
                Objects.equals(startDate, aClass.startDate) &&
                Objects.equals(endDate, aClass.endDate) &&
                Objects.equals(semesterId, aClass.semesterId) &&
                Objects.equals(gradeId, aClass.gradeId) &&
                Objects.equals(maxNum, aClass.maxNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, courseName, startDate, endDate, semesterId, gradeId, maxNum);
    }
}


