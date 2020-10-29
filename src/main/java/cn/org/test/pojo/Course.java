package cn.org.test.pojo;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * Created by lyy on 2020/10/29 下午4:58
 */
@Data
public class Course {
    private Integer id;
    private String name;
    private Integer teacherId;
    private String teacherName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) &&
                Objects.equals(name, course.name) &&
                Objects.equals(teacherId, course.teacherId) &&
                Objects.equals(teacherName, course.teacherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, teacherId, teacherName);
    }
}
