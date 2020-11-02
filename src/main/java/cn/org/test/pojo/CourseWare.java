package cn.org.test.pojo;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * Created by lyy on 2020/11/2 上午10:27
 */
@Data
public class CourseWare {
    private Integer id;
    private Integer courseId;
    private Integer num;//在课程中编号
    private String filepath;
    private String filename;
    private Date uploadDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseWare that = (CourseWare) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(courseId, that.courseId) &&
                Objects.equals(num, that.num) &&
                Objects.equals(filepath, that.filepath) &&
                Objects.equals(filename, that.filename) &&
                Objects.equals(uploadDate, that.uploadDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, num, filepath, filename, uploadDate);
    }
}
