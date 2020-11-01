package cn.org.test.pojo;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * Created by lyy on 2020/10/30 下午3:14
 */
@Data
public class CreateClassApplication {
    private Integer id;
    private Integer teacherId;
    private Integer courseId;
    private String courseName;
    private Date startDate;
    private Date endDate;
    private Integer semesterId;
    private String semesterName;
    private Integer gradeId;
    private String gradeName;
    private Integer maxNum;
    private Integer status;
    private Date createDate;
    private Date finishDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateClassApplication that = (CreateClassApplication) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(teacherId, that.teacherId) &&
                Objects.equals(courseId, that.courseId) &&
                Objects.equals(courseName, that.courseName) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(semesterId, that.semesterId) &&
                Objects.equals(semesterName, that.semesterName) &&
                Objects.equals(gradeId, that.gradeId) &&
                Objects.equals(gradeName, that.gradeName) &&
                Objects.equals(maxNum, that.maxNum) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(finishDate, that.finishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teacherId, courseId, courseName, startDate, endDate, semesterId, semesterName, gradeId, gradeName, maxNum, status, createDate, finishDate);
    }
}
