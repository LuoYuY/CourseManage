package cn.org.test.pojo;

import lombok.Data;

import java.util.Objects;

/**
 * Created by lyy on 2020/10/27 下午1:55
 */
@Data
public class Semester {
    private Integer id;
    private String year;
    private String term;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return Objects.equals(id, semester.id) &&
                Objects.equals(year, semester.year) &&
                Objects.equals(term, semester.term);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, term);
    }
}
