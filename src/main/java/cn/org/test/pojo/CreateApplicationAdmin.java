package cn.org.test.pojo;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * Created by lyy on 2020/10/28 下午5:03
 */
@Data
public class CreateApplicationAdmin {
    private Integer id;
    private String teacher;
    private String name;
    private String reason;
    private Date createDate;
    private Integer status;
    private Date finishDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateApplicationAdmin that = (CreateApplicationAdmin) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(teacher, that.teacher) &&
                Objects.equals(name, that.name) &&
                Objects.equals(reason, that.reason) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(status, that.status) &&
                Objects.equals(finishDate, that.finishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teacher, name, reason, createDate, status, finishDate);
    }
}
