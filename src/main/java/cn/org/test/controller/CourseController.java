package cn.org.test.controller;
import cn.org.test.common.ServerResponse;
import cn.org.test.pojo.Semester;
import cn.org.test.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
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


    @ResponseBody
    @GetMapping(value = "/semesterList")
    public ServerResponse getSemesterList(HttpServletResponse response){
        ArrayList<Semester> list = courseService.getSemesterList();
        return ServerResponse.createBySuccess(list);
    }
}

