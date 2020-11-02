package cn.org.test.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by lyy on 2020/11/2 上午9:19
 */

public interface FileService {
    boolean saveFiles(Integer courseId, MultipartFile[] uploadFile);
}
