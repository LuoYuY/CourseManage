package cn.org.test.service.impl;

import cn.org.test.mapper.ClassMapper;
import cn.org.test.mapper.GradeMapper;
import cn.org.test.mapper.SelectClassMapper;
import cn.org.test.mapper.SemesterMapper;
import cn.org.test.pojo.*;
import cn.org.test.pojo.Class;
import cn.org.test.service.StudentService;
import cn.org.test.utils.RedisUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lyy on 2020/11/2 下午4:31
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    public ClassMapper classMapper;
    @Autowired
    public SelectClassMapper selectClassMapper;
    @Autowired
    public SemesterMapper semesterMapper;
    @Autowired
    public GradeMapper gradeMapper;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<ClassForSelect> getAllClassList() {
        List<Class> list = classMapper.getClassListToSelect();
        List<ClassForSelect> result = new ArrayList<>();
        Iterator<Class> iter = list.iterator();
        while(iter.hasNext())
        {
            ClassForSelect obj = new ClassForSelect();
            Class item = iter.next();
            obj.setCourseName(item.getCourseName());
            obj.setId(item.getId());
            obj.setName(item.getName());
            obj.setTeacherName(item.getTeacherName());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            obj.setStartDate(formatter.format(item.getStartDate()));
            obj.setEndDate(formatter.format(item.getEndDate()));
            Semester s = semesterMapper.getSemesterById(item.getSemesterId());
            obj.setSemesterName(s.getYear()+"学年 第"+s.getTerm()+"学期");
            Grade g = gradeMapper.getGradeById(item.getGradeId());
            obj.setGradeName(g.getName());
            obj.setMaxNum(item.getMaxNum());
            obj.setStatus(item.getStatus());
            obj.setNum(item.getNum());
            result.add(obj);
        }
        return result;
    }

    @Override
    public List<SelectClass> getSelectedCourses(Integer studentId) {
        List<SelectClass> list = selectClassMapper.getSelectedCoursesByStuId(studentId);
        return list;
    }

//    @Override
//    public synchronized boolean selectClass(Integer classId, Integer studentId) {
//        try{
//            Class c = classMapper.getClassById(classId);
//            int stock = c.getMaxNum() - c.getNum();
//            //添加选课记录
//            if (stock > 0) {
//                int result = classMapper.addNum(classId);
//                if (result == 1) {
//                    //创建订单
//                    selectClassMapper.addSelectClass(classId,studentId,0);
//                }
//                return result > 0 ? true : false;
//            }
//            return true;
//
//        }catch (Exception e) {
//            return false;
//        }
//    }


    @Override
    public boolean selectClass(Integer classId, Integer studentId) {
        try{
            DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
            redisScript.setResultType(Long.class);
            redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("select.lua")));
//            Long result = redisTemplate.execute(redisScript, Collections.singletonList(classId + ""), studentId + "");
            Long result = redisUtil.execute(redisScript, Collections.singletonList(classId + ""), studentId + "");

            if (result == 1) {
                System.out.println("result:" + studentId);
                SelectClass selectClass = new SelectClass(classId,studentId,0);
                kafkaTemplate.send("stu-menage", JSONObject.toJSONString(selectClass));
//                selectClassMapper.addSelectClass(classId,studentId,0);
            }
            return result > 0 ? true : false;
        }catch (Exception e) {
            return false;
        }
    }
//    String id() default "";
//
//    String containerFactory() default "";
//
//    String[] topics() default {};
//
//    String topicPattern() default "";
//
//    TopicPartition[] topicPartitions() default {};
//
//    String group() default "";
    //kafka消费者
    @KafkaListener(groupId = "id0",topics = "stu-menage")
    public void consumer(ConsumerRecord<?, String> record) {
        String value = record.value();
        System.out.println("result:" + value);
        //字符串转对象
        JSONObject sJson = JSONObject.parseObject(value);
        SelectClass selectClass = JSON.toJavaObject(sJson,SelectClass.class);
//        SelectClass selectClass = JSONUtil.stringToObj(value, SelectClass.class);
        selectClassMapper.addSelectClass(selectClass.getClassId(),selectClass.getStudentId(),0);
    }

    @Override
    public boolean disSelectClass(Integer classId, Integer studentId) {
        try{
            //添加选课记录
            selectClassMapper.deleteSelectClass(classId,studentId);
            //class中 选课人数+1，class表更新
            classMapper.deleteNum(classId);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Class> getClassesList(Integer studentId) {
        List<SelectClass> list  = selectClassMapper.getSelectedCoursesByStuId(studentId);
        List<Class> res = new ArrayList<>();
        Iterator<SelectClass> itr = list.iterator();
        while(itr.hasNext())
        {
            SelectClass item = itr.next();
            Class obj = classMapper.getClassById(item.getClassId());
            res.add(obj);
        }
        return res;
    }
}
