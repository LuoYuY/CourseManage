package cn.org.test.mapper;

import cn.org.test.pojo.CreateApplication;
import cn.org.test.pojo.CreateApplicationAdmin;
import cn.org.test.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by lyy on 2020/10/27 下午3:39
 */
@Mapper
public interface CreateApplicationMapper {
    Integer addCreateApplication(@Param("newApplication") CreateApplication newApplication);

    List<CreateApplication> getListById(@Param("teacherId")Integer teacherId);

    List<CreateApplicationAdmin> getAllList();

    Integer updateStatus(@Param("id")Integer id, @Param("status")Integer value, @Param("finishDate")Date date);

    CreateApplication getAlcById(@Param("id")Integer id);
}
