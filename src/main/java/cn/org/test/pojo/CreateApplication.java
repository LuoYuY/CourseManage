package cn.org.test.pojo;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * Created by lyy on 2020/10/27 下午3:31
 */
@Data
public class CreateApplication {
    private Integer id;
    private Integer teacherId;
    private String name;
    private String reason;
    private Date createDate;
    private Integer status;
    private Date finishDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateApplication that = (CreateApplication) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(teacherId, that.teacherId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(reason, that.reason) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(status, that.status) &&
                Objects.equals(finishDate, that.finishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teacherId, name, reason, createDate, status, finishDate);
    }
}
