package cn.org.test.controller;

/**
 * Created by lyy on 2020/11/2 下午4:18
 */

import cn.org.test.common.ServerResponse;
import cn.org.test.common.UserLoginToken;
import cn.org.test.pojo.Class;
import cn.org.test.pojo.ClassForSelect;
import cn.org.test.service.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    //login with the username and password
//    @UserLoginToken
    @ResponseBody
    @GetMapping(value = "/selectClassList")
    public ServerResponse selectClassList() {
        List<ClassForSelect> list = studentService.getAllClassList();
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(list));
        return ServerResponse.createBySuccess(array);
    }
}
