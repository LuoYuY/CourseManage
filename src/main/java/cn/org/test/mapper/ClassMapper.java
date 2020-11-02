package cn.org.test.mapper;
import cn.org.test.pojo.Class;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by lyy on 2020/11/1 下午4:03
 */
@Mapper
public interface ClassMapper {
    Integer addClass(@Param("newClass")Class newClass);
    List<Class> getClassByCourseId(@Param("courseId")Integer courseId);
    Class getClassById(@Param("id")Integer id);

    List<Class> getClassListToSelect();
}
