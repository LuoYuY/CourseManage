package cn.org.test.service.impl;

import cn.org.test.mapper.CreateApplicationMapper;
import cn.org.test.pojo.CreateApplication;
import cn.org.test.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lyy on 2020/10/27 下午6:51
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    public CreateApplicationMapper createApplicationMapper;

    @Override
    public List<CreateApplication> getCreListFromTch(Integer teacherId) {
        List<CreateApplication> list = createApplicationMapper.getListById(teacherId);
//        System.out.println("service:"+list);
        return list;
    }
}
