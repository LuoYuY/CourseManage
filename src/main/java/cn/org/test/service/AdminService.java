package cn.org.test.service;

import cn.org.test.pojo.CreateApplicationAdmin;

import java.util.List;

/**
 * Created by lyy on 2020/10/28 下午4:34
 */

public interface AdminService {
    List<CreateApplicationAdmin> getCreList();

    boolean dealCreApply(Integer id, Integer choice);
}
