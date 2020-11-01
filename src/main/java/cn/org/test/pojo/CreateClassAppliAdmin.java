package cn.org.test.pojo;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * Created by lyy on 2020/10/30 下午4:10
 */
@Data
public class CreateClassAppliAdmin {
    private Integer id;
    private String teacher;
    private String courseName;
    private Date startDate;
    private Date endDate;
    private String semesterName;
    private String gradeName;
    private Integer maxNum;
    private Integer status;
    private Date createDate;
    private Date finishDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateClassAppliAdmin that = (CreateClassAppliAdmin) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(teacher, that.teacher) &&
                Objects.equals(courseName, that.courseName) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(semesterName, that.semesterName) &&
                Objects.equals(gradeName, that.gradeName) &&
                Objects.equals(maxNum, that.maxNum) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(finishDate, that.finishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teacher, courseName, startDate, endDate, semesterName, gradeName, maxNum, status, createDate, finishDate);
    }
}
