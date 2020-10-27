package cn.org.test.mapper;

import cn.org.test.pojo.CreateApplication;
import cn.org.test.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by lyy on 2020/10/27 下午3:39
 */
@Mapper
public interface CreateApplicationMapper {
    Integer addCreateApplication(@Param("newApplication") CreateApplication newApplication);
}
