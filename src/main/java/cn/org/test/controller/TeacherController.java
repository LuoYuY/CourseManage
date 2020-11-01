package cn.org.test.controller;

import cn.org.test.common.ApplicationStatus;
import cn.org.test.common.ServerResponse;
import cn.org.test.common.UserLoginToken;
import cn.org.test.pojo.Course;
import cn.org.test.pojo.CreateApplication;
import cn.org.test.pojo.CreateClassApplication;
import cn.org.test.req.CreateClassReq;
import cn.org.test.service.ApplicationService;
import cn.org.test.service.TeacherService;
import cn.org.test.utils.EnumUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
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
    public ApplicationService applicationService;

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
}
