package cn.org.test.controller;

import cn.org.test.common.ApplicationStatus;
import cn.org.test.common.ServerResponse;
import cn.org.test.common.UserLoginToken;
import cn.org.test.pojo.CreateApplication;
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

}
