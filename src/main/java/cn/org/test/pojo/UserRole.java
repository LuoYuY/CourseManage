package cn.org.test.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by lyy on 2020/10/14 上午11:16
 */
@Data
public class UserRole implements Serializable {
    private Integer id;
    private Integer user_id;
    private Integer role_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(id, userRole.id) &&
                Objects.equals(user_id, userRole.user_id) &&
                Objects.equals(role_id, userRole.role_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, role_id);
    }
}
