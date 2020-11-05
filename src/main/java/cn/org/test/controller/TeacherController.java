package cn.org.test.controller;

import cn.org.test.common.ServerResponse;
import cn.org.test.common.UserLoginToken;
import cn.org.test.pojo.*;
import cn.org.test.pojo.Class;
import cn.org.test.req.CreateClassReq;
import cn.org.test.service.ApplicationService;
import cn.org.test.service.CourseService;
import cn.org.test.service.FileService;
import cn.org.test.service.TeacherService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.aspectj.apache.bcel.generic.TABLESWITCH;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lyy on 2020/9/8 下午8:40
 */

@RestController
@RequestMapping("/tch")
public class TeacherController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public TeacherService teacherService;
    @Autowired
    public FileService fileService;

    @Autowired
    public ApplicationService applicationService;

    @Autowired
    public CourseService courseService;

    //login with the username and password
    @UserLoginToken
    @ResponseBody
    @GetMapping(value = "/applyCreList")
    public ServerResponse getCreList(Integer teacherId, HttpServletResponse response) {
        List<CreateApplication> list = teacherService.getCreListFromTch(teacherId);
        JSONArray arr=new JSONArray();
        Iterator<CreateApplication> iter = list.iterator();
        int i=0;
        while (iter.hasNext()) {
            CreateApplication item = (CreateApplication)iter.next();
            JSONObject object=new JSONObject();
            object.put("name",item.getName());
            object.put("reason",item.getReason());
            SimpleDateFormat formatter = new SimpleDateFormat("-yyyy-MM-dd");
            object.put("createDate",formatter.format(item.getCreateDate()));
//            System.out.println("control:"+formatter.format(item.getCreateDate()));
            object.put("status", item.getStatus());
            arr.add(object);
        }
        return ServerResponse.createBySuccess(arr);
    }

    //login with the username and password
    @UserLoginToken
    @ResponseBody
    @GetMapping(value = "/getCoursesList")
    public ServerResponse getCoursesList(Integer teacherId, HttpServletResponse response) {
        List<Course> list = teacherService.getCoursesList(teacherId);
        JSONArray arr=new JSONArray();
        Iterator<Course> iter = list.iterator();
        int i=0;
        while (iter.hasNext()) {
            Course item = (Course)iter.next();
            JSONObject object=new JSONObject();
            object.put("id",item.getId());
            object.put("name",item.getName());
            arr.add(object);
        }
        return ServerResponse.createBySuccess(arr);
    }

    //login with the username and password
    @UserLoginToken
    @ResponseBody
    @PostMapping(value = "/creClassApply")
    public ServerResponse creClassApply(CreateClassReq createClassReq, HttpServletResponse response) {
        applicationService.createClassApply(createClassReq);
        return ServerResponse.createBySuccess();
    }


    @UserLoginToken
    @ResponseBody
    @GetMapping(value = "/applyCreClassList")
    public ServerResponse getCreClassList(Integer teacherId, HttpServletResponse response) {
        List<CreateClassApplication> list = teacherService.getCreClassListFromTch(teacherId);
        JSONArray arr=new JSONArray();
        Iterator<CreateClassApplication> iter = list.iterator();
        int i=0;
        while (iter.hasNext()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            CreateClassApplication item = (CreateClassApplication)iter.next();
            JSONObject object=new JSONObject();
            object.put("name",item.getName());
            object.put("courseName",item.getCourseName());
            object.put("startDate",formatter.format(item.getStartDate()));
            object.put("endDate",formatter.format(item.getEndDate()));
            object.put("semesterName",item.getSemesterName());
            object.put("gradeName",item.getGradeName());
            object.put("maxNum",item.getMaxNum());
            object.put("createDate",formatter.format(item.getCreateDate()));
            object.put("status",item.getStatus());
            arr.add(object);
        }
        return ServerResponse.createBySuccess(arr);
    }

    @UserLoginToken
    @ResponseBody
    @GetMapping(value = "/getCourseDetail")
    public ServerResponse getCourseDetail(Integer courseId, HttpServletResponse response) {
        Course course = courseService.getCourseDetail(courseId);
        List<Class> classList = courseService.getClassListTch(courseId);
        List<CourseWare> couseWareList = fileService.getCourseWareList(courseId);
        JSONObject result =new JSONObject();
        JSONArray arr=new JSONArray();
        Iterator<Class> iter = classList.iterator();
        int i=0;
        while (iter.hasNext()) {
            Class item = iter.next();
            JSONObject object=new JSONObject();
            object.put("id",item.getId());
            object.put("name",item.getName());
            arr.add(object);
        }

        JSONArray array = new JSONArray();
        Iterator<CourseWare> itr = couseWareList.iterator();
        int j=0;
        while (itr.hasNext()) {
            CourseWare item = itr.next();
            JSONObject object=new JSONObject();
            object.put("id",item.getId());
            object.put("filename",item.getFilename());
            object.put("filepath",item.getFilepath());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            object.put("uploadDate",formatter.format(item.getUploadDate()));
            array.add(object);
        }
        result.put("classes",arr);
        result.put("course",course);
        result.put("courseWare",array);
        return ServerResponse.createBySuccess(result);
    }


    @UserLoginToken
    @ResponseBody
    @PostMapping(value = "/uploadFile")
    public ServerResponse uploadFile(@RequestParam("courseId")Integer courseId, @RequestParam("uploadFile")MultipartFile[] uploadFile, HttpServletRequest request, HttpServletResponse response) {
//        System.out.println("courseId:"+courseId);
//        for (MultipartFile multipartFile : uploadFile) {
//            System.out.println(multipartFile);
//        }
        if(fileService.saveFiles(courseId,uploadFile))
            return ServerResponse.createBySuccess();
        else return ServerResponse.createByErrorCodeMessage(2,"上传失败");
    }

    @UserLoginToken
    @ResponseBody
    @GetMapping(value = "/getClassDetail")
    public ServerResponse getClassDetail(Integer classId, HttpServletResponse response) {
        return ServerResponse.createBySuccess();
    }


    @UserLoginToken
    @ResponseBody
    @PostMapping(value = "/createTask")
    public ServerResponse createTask(
            @RequestParam("classId")Integer classId,
            @RequestParam("uploadFile")MultipartFile[] uploadFile,
            @RequestParam("title")String title,
            @RequestParam("content")String content,
            @RequestParam("date")String date,
            @RequestParam("time")String time,
            HttpServletRequest request, HttpServletResponse response) {
        Task t = new Task();
        t.setClassId(classId);
        t.setTitle(title);
        t.setContent(content);
        t.setEndTime(date+" "+time);
        t.setStatus(1); //进行中，可提交
        System.out.println("date+time:"+t.getEndTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        t.setStartTime(formatter.format(new Date()));
        System.out.println("startTime:"+t.getStartTime());

        if(fileService.addTask(t,uploadFile))
            return ServerResponse.createBySuccess();
        else return ServerResponse.createByErrorCodeMessage(2,"上传失败");
    }


    //login with the username and password
    @UserLoginToken
    @ResponseBody
    @GetMapping(value = "/getTaskList")
    public ServerResponse getTaskList(Integer classId, HttpServletResponse response) {
        List<Task> list = courseService.getTasksList(classId);
        JSONArray arr=new JSONArray();
        Iterator<Task> iter = list.iterator();
        int i=0;
        while (iter.hasNext()) {
            Task item = iter.next();
            JSONObject object=new JSONObject();
            object.put("id",item.getId());
            object.put("name",item.getTitle());
            arr.add(object);
        }
        return ServerResponse.createBySuccess(arr);
    }

    //login with the username and password
//    @UserLoginToken
    @ResponseBody
    @GetMapping(value = "/getTaskDetail")
    public ServerResponse getTaskDetail(Integer taskId, HttpServletResponse response) {
        Task t = courseService.getTaskDetail(taskId);
//        JSONArray arr=new JSONArray();
//        Iterator<Task> iter = list.iterator();
//        int i=0;
//        while (iter.hasNext()) {
//            Task item = iter.next();
//            JSONObject object=new JSONObject();
//            object.put("id",item.getId());
//            object.put("name",item.getTitle());
//            arr.add(object);
//        }
        return ServerResponse.createBySuccess(t);
    }
}
