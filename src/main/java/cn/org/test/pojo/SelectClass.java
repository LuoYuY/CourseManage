package cn.org.test.pojo;

import lombok.Data;

import java.util.Objects;

/**
 * Created by lyy on 2020/11/2 下午4:11
 */
@Data
public class SelectClass {
    private Integer id;
    private Integer studentId;
    private Integer classId;
    private Integer status;

    public SelectClass(Integer classId, Integer studentId, int i) {
        this.classId = classId;
        this.studentId = studentId;
        this.status = i;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelectClass that = (SelectClass) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(studentId, that.studentId) &&
                Objects.equals(classId, that.classId) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentId, classId, status);
    }
}
