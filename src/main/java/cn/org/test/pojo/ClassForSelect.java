package cn.org.test.pojo;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * Created by lyy on 2020/11/2 下午4:41
 */
@Data
public class ClassForSelect {
    private Integer id;
    private String name;
    private String courseName;
    private String teacherName;
    private String startDate;
    private String endDate;
    private String semesterName;
    private String gradeName;
    private Integer maxNum;
    private Integer status;
    private Integer num;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassForSelect that = (ClassForSelect) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(courseName, that.courseName) &&
                Objects.equals(teacherName, that.teacherName) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(semesterName, that.semesterName) &&
                Objects.equals(gradeName, that.gradeName) &&
                Objects.equals(maxNum, that.maxNum) &&
                Objects.equals(status, that.status) &&
                Objects.equals(num, that.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, courseName, teacherName, startDate, endDate, semesterName, gradeName, maxNum, status, num);
    }



}
