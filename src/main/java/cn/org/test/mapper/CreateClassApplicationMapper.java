package cn.org.test.mapper;

import cn.org.test.pojo.CreateApplication;
import cn.org.test.pojo.CreateApplicationAdmin;
import cn.org.test.pojo.CreateClassAppliAdmin;
import cn.org.test.pojo.CreateClassApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by lyy on 2020/10/30 下午3:13
 */
@Mapper
public interface CreateClassApplicationMapper {
    Integer addCreateClassApplication(@Param("apply")CreateClassApplication apply);
    List<CreateClassApplication> getListById(@Param("teacherId")Integer teacherId);
    List<CreateClassAppliAdmin> getAllList();
    Integer updateStatus(@Param("id")Integer id, @Param("status")Integer value, @Param("finishDate") Date date);
    CreateClassApplication getAlcById(@Param("id")Integer id);
}
