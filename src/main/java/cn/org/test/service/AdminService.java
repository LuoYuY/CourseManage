package cn.org.test.service;

import cn.org.test.pojo.CreateApplicationAdmin;
import cn.org.test.pojo.CreateClassAppliAdmin;

import java.util.List;

/**
 * Created by lyy on 2020/10/28 下午4:34
 */

public interface AdminService {
    List<CreateApplicationAdmin> getCreList();

    boolean dealCreApply(Integer id, Integer choice);

    List<CreateClassAppliAdmin> getCreClassList();

    boolean dealCreClassApply(Integer id, Integer choice);
}
