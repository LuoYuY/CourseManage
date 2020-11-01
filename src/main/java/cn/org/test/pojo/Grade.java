package cn.org.test.pojo;

import lombok.Data;

import java.util.Objects;

/**
 * Created by lyy on 2020/10/30 下午12:02
 */
@Data
public class Grade {
    private Integer id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return Objects.equals(id, grade.id) &&
                Objects.equals(name, grade.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
