package cn.org.test.controller;

import cn.org.test.common.ServerResponse;
import cn.org.test.pojo.CreateApplication;
import cn.org.test.pojo.CreateApplicationAdmin;
import cn.org.test.service.AdminService;
import cn.org.test.service.TeacherService;
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
 * Created by lyy on 2020/10/28 下午4:31
 */

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public AdminService adminService;

    @ResponseBody
    @GetMapping(value = "/allApplyCreList")
    public ServerResponse getCreList(HttpServletResponse response) {
        List<CreateApplicationAdmin> list = adminService.getCreList();
        JSONArray arr = new JSONArray();
        Iterator<CreateApplicationAdmin> iter = list.iterator();
        int i = 0;
        while (iter.hasNext()) {
            CreateApplicationAdmin item = (CreateApplicationAdmin) iter.next();
            JSONObject object = new JSONObject();
            object.put("id",item.getId());
            object.put("teacher",item.getTeacher());
            object.put("name", item.getName());
            object.put("reason", item.getReason());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            object.put("createDate", formatter.format(item.getCreateDate()));
//            System.out.println("control:"+formatter.format(item.getCreateDate()));
            object.put("status", item.getStatus());
            if(item.getFinishDate()!=null)
            {
                object.put("finishDate", formatter.format(item.getFinishDate()));
            }
            else object.put("finishDate", "");

            arr.add(object);
        }
        return ServerResponse.createBySuccess(arr);
    }

    @ResponseBody
    @PostMapping(value = "/dealCreApply")
    public ServerResponse dealCreApply(Integer id,Integer choice,HttpServletResponse response) {
        if(adminService.dealCreApply(id,choice)) {
            return ServerResponse.createBySuccess();
        }else return ServerResponse.createByErrorCodeMessage(1,"操作失败");
    }

}

