package cn.org.test.service.impl;

import cn.org.test.mapper.CourseMapper;
import cn.org.test.mapper.CourseWareMapper;
import cn.org.test.pojo.CourseWare;
import cn.org.test.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by lyy on 2020/11/2 上午9:19
 */
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    public CourseMapper courseMapper;
    @Autowired
    public CourseWareMapper courseWareMapper;

    @Override
    public boolean saveFiles(Integer courseId, MultipartFile[] files) {
        //        System.out.println("courseId:"+courseId);
//        for (MultipartFile multipartFile : uploadFile) {
//            System.out.println(multipartFile);
//        }

        // 先设定一个放置上传文件的文件夹(该文件夹可以不存在，下面会判断创建)
        String deposeFilesDir = "/home/lyy/IdeaProjects/com.lyy/blog/courseWare/"+courseId+"/";
        for (MultipartFile file : files) {
            CourseWare courseWare = new CourseWare();
            // 判断文件是否有内容
            if (file.isEmpty()) {
                System.out.println("该文件无任何内容!!!");
            }
            // 获取附件原名(有的浏览器如chrome获取到的是最基本的含 后缀的文件名,如myImage.png)
            // 获取附件原名(有的浏览器如ie获取到的是含整个路径的含后缀的文件名，如C:\\Users\\images\\myImage.png)
            String fileName = file.getOriginalFilename();
            // 如果是获取的含有路径的文件名，那么截取掉多余的，只剩下文件名和后缀名
            if (fileName.indexOf("\\") > 0) {
                int index = fileName.lastIndexOf("\\");
                fileName = fileName.substring(index + 1);
            }
            // 判断单个文件大于1M
            long fileSize = file.getSize();
            if (fileSize > 1024 * 1024) {
                System.out.println("文件大小为(单位字节):" + fileSize);
                System.out.println("该文件大于1M");
            }
            // 当文件有后缀名时
            if (fileName.indexOf(".") >= 0) {
                // split()中放正则表达式; 转义字符"\\."代表 "."
                String[] fileNameSplitArray = fileName.split("\\.");
                // 加上random戳,防止附件重名覆盖原文件
//                fileName = fileNameSplitArray[0] + (int) (Math.random() * 100000) + "." + fileNameSplitArray[1];
                fileName = fileNameSplitArray[0]  + "." + fileNameSplitArray[1];

            }
            // 当文件无后缀名时(如C盘下的hosts文件就没有后缀名)
            if (fileName.indexOf(".") < 0) {
                // 加上random戳,防止附件重名覆盖原文件
                fileName = fileName + (int) (Math.random() * 100000);
            }
            System.out.println("fileName:" + fileName);
            // 根据文件的全路径名字(含路径、后缀),new一个File对象dest
            File dest = new File(deposeFilesDir + fileName);
            // 如果pathAll路径不存在，则创建相关该路径涉及的文件夹;
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                // 将获取到的附件file,transferTo写入到指定的位置(即:创建dest时，指定的路径)
                file.transferTo(dest);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            courseWare.setCourseId(courseId);
            courseWare.setFilename(fileName);
            courseWare.setFilepath("http://localhost:8080/download/courseWare/"+courseId+"/"+ fileName);
            courseWare.setUploadDate(new Date());
            courseWareMapper.addCourseWare(courseWare);
            System.out.println("文件的全路径名字(含路径、后缀)>>>>>>>" + deposeFilesDir + fileName);
        }
        return true;
    }

    @Override
    public List<CourseWare> getCourseWareList(Integer courseId) {
        List<CourseWare> list = courseWareMapper.getFilesByCourseId(courseId);
        return list;
    }

}
