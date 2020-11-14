package cn.org.test.controller;
import cn.org.test.common.ServerResponse;
import cn.org.test.pojo.Grade;
import cn.org.test.pojo.Semester;
import cn.org.test.req.CreateCourseReq;
import cn.org.test.service.ApplicationService;
import cn.org.test.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by lyy on 2020/10/27 下午2:01
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CourseService courseService;
    @Autowired
    public ApplicationService applicationService;


    @ResponseBody
    @GetMapping(value = "/semesterList")
    public ServerResponse getSemesterList(HttpServletResponse response){
        ArrayList<Semester> list = courseService.getSemesterList();
        return ServerResponse.createBySuccess(list);
    }

    @ResponseBody
    @GetMapping(value = "/gradeList")
    public ServerResponse getGradeList(HttpServletResponse response){
        ArrayList<Grade> list = courseService.getGradeList();
        return ServerResponse.createBySuccess(list);
    }

    @ResponseBody
    @PostMapping(value = "/createApply")
    public ServerResponse createApply(CreateCourseReq createCourseReq, HttpServletResponse response){
        applicationService.createCourseApply(createCourseReq);
        return ServerResponse.createBySuccess();
    }

    @ResponseBody
    @GetMapping(value = "/courseWareDownload")
    public ServerResponse download(HttpServletRequest request, HttpServletResponse response, Integer courseId,String file) throws FileNotFoundException, IOException, UnsupportedEncodingException {
        String deposeFilesDir = "/home/lyy/IdeaProjects/com.lyy/blog/courseWare/"+courseId+"/";
        System.out.println(deposeFilesDir);
        String filePathName = deposeFilesDir+file;
        //3 下载
        String zipFileName = filePathName;
        String filename = filePathName;
        //设置文件类型
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        //设置Content-Disposition
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
        InputStream in = new FileInputStream(zipFileName);
        OutputStream out = response.getOutputStream();
        //写文件
        int b;
        while ((b = in.read()) != -1) {
            out.write(b);
        }
        out.flush();
        in.close();//先关闭输入流才能删除
        out.close();
        return ServerResponse.createBySuccess();
    }

    @ResponseBody
    @GetMapping(value = "/taskWareDownload")
    public ServerResponse taskWareDownload(HttpServletRequest request, HttpServletResponse response, Integer taskId,String file) throws FileNotFoundException, IOException, UnsupportedEncodingException {
        String deposeFilesDir = "/home/lyy/IdeaProjects/com.lyy/blog/task/"+taskId+"/";
        System.out.println(deposeFilesDir);
        String filePathName = deposeFilesDir+file;
        //3 下载
        String zipFileName = filePathName;
        String filename = filePathName;
        //设置文件类型
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        //设置Content-Disposition
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
        InputStream in = new FileInputStream(zipFileName);
        OutputStream out = response.getOutputStream();
        //写文件
        int b;
        while ((b = in.read()) != -1) {
            out.write(b);
        }
        out.flush();
        in.close();//先关闭输入流才能删除
        out.close();
        return ServerResponse.createBySuccess();
    }
    @ResponseBody
    @GetMapping(value = "/taskDownload")
    public ServerResponse taskDownload(HttpServletRequest request, HttpServletResponse response, Integer studentId,String file) throws FileNotFoundException, IOException, UnsupportedEncodingException {
        String deposeFilesDir = "/home/lyy/IdeaProjects/com.lyy/blog/studentTask/"+studentId+"/";
        System.out.println(deposeFilesDir);
        String filePathName = deposeFilesDir+file;
        //3 下载
        String zipFileName = filePathName;
        String filename = filePathName;
        //设置文件类型
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        //设置Content-Disposition
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
        InputStream in = new FileInputStream(zipFileName);
        OutputStream out = response.getOutputStream();
        //写文件
        int b;
        while ((b = in.read()) != -1) {
            out.write(b);
        }
        out.flush();
        in.close();//先关闭输入流才能删除
        out.close();
        return ServerResponse.createBySuccess();
    }

}

