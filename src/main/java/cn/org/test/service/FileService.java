package cn.org.test.service;

import cn.org.test.pojo.CourseWare;
import cn.org.test.pojo.Task;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by lyy on 2020/11/2 上午9:19
 */

public interface FileService {
    boolean saveFiles(Integer courseId, MultipartFile[] uploadFile);

    List<CourseWare> getCourseWareList(Integer courseId);

    boolean addTask(Task t, MultipartFile[] uploadFile);
}
