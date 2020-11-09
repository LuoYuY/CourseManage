package cn.org.test.controller;

/**
 * Created by lyy on 2020/11/2 下午4:18
 */

import cn.org.test.common.ServerResponse;
import cn.org.test.pojo.*;
import cn.org.test.pojo.Class;
import cn.org.test.service.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by lyy on 2020/9/8 下午8:40
 */

@RestController
@RequestMapping("/stu")
public class StudentController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public StudentService studentService;
    @Autowired
    public CourseService courseService;

    @Autowired
    public FileService fileService;

    //login with the username and password
//    @UserLoginToken
    @ResponseBody
    @GetMapping(value = "/selectClassList")
    public ServerResponse selectClassList() {
        List<ClassForSelect> list = studentService.getAllClassList();
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(list));
        return ServerResponse.createBySuccess(array);
    }

    @ResponseBody
    @GetMapping(value = "/getSelectedCourses")
    public ServerResponse getSelectedCourses(Integer studentId) {
        List<SelectClass> list = studentService.getSelectedCourses(studentId);
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(list));
        return ServerResponse.createBySuccess(array);
    }

    @ResponseBody
    @PostMapping(value = "/selectClass")
    public ServerResponse selectClass(Integer classId,Integer studentId) {
        if(studentService.selectClass(classId,studentId))
            return ServerResponse.createBySuccess();
        else return ServerResponse.createByError();
    }

    @ResponseBody
    @PostMapping(value = "/disSelectClass")
    public ServerResponse disSelectClass(Integer classId,Integer studentId) {
        if(studentService.disSelectClass(classId,studentId))
            return ServerResponse.createBySuccess();
        else return ServerResponse.createByError();
    }

    @ResponseBody
    @GetMapping(value = "/getClassesList")
    public ServerResponse getClassesList(Integer studentId) {
        List<Class> list = studentService.getClassesList(studentId);
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(list));
        return ServerResponse.createBySuccess(array);
    }

    @ResponseBody
    @GetMapping(value = "/getTaskList")
    public ServerResponse getTaskList(Integer classId) {
        List<Task> list = courseService.getTasksList(classId);
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(list));
        return ServerResponse.createBySuccess(array);
    }


    @ResponseBody
    @PostMapping(value = "/uploadTask")
    public ServerResponse uploadTask(
            @RequestParam("taskId")Integer taskId,
            @RequestParam("uploadFile") MultipartFile uploadFile,
            @RequestParam("userId")Integer userId)
    {
        boolean result = fileService.uploadTask(taskId,userId,uploadFile);
        if(result) return ServerResponse.createBySuccess();
        else return ServerResponse.createByError();
    }


    @ResponseBody
    @GetMapping(value = "/getStuTaskDetail")
    public ServerResponse getStuTaskDetail(Integer taskId,Integer studentId) {
        TaskStu taskStu = courseService.getStuTaskDetail(taskId,studentId);
        JSONObject obj = new JSONObject();
        if(taskStu!=null) {
            obj.put("taskStu",taskStu);
        }
        else obj.put("taskStu",null);
        return ServerResponse.createBySuccess(obj);
    }


}
