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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ClassMapper classMapper;
    @Autowired
    public SelectClassMapper selectClassMapper;
    @Autowired
    public SemesterMapper semesterMapper;
    @Autowired
    public GradeMapper gradeMapper;

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





    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public static final String TOPIC = "select-class";

    /**
     * @methodsName: selectClass
     * @description: 选课
     * @param:  classId 课程号
     * @param:  studentId 学号
     **/
    @Override
    public boolean selectClass(Integer classId, Integer studentId) {
        try{
            //选课代码逻辑，判断课程可选人数->是否能选课
            DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
            redisScript.setResultType(Long.class);
            redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("select.lua")));
            Long result = redisUtil.execute(redisScript, Collections.singletonList(classId + ""), studentId + "");

            //若选课脚本返回结果为1，则说明选课成功，需要持久化选课记录
            if (result == 1) {
                SelectClass selectClass = new SelectClass(classId,studentId,0);
                //方法一：在选课模块中直接进行选课记录持久化、日志记录等耗时操作
//                selectClassMapper.addSelectClass(selectClass.getClassId(),selectClass.getStudentId(),0);
//                logger.info("successful select:"+ studentId+ ":" + classId);

                //方法二：将选课内容作为消息传递给Kafka，由Kafka在消费消息的过程中进行耗时操作
                kafkaTemplate.send(TOPIC, JSONObject.toJSONString(selectClass));

            }
            return result > 0 ? true : false;
        }catch (Exception e) {
            return false;
        }
    }

    //Kafka消费者
    @KafkaListener(groupId = "id0",topics = TOPIC)
    public void consumer(ConsumerRecord<?, String> record) {
        //解析读取的消息
        String value = record.value();
        JSONObject sJson = JSONObject.parseObject(value);
        SelectClass selectClass = JSON.toJavaObject(sJson,SelectClass.class);
        //进行选课记录持久化、日志记录等耗时操作
        selectClassMapper.addSelectClass(selectClass.getClassId(),selectClass.getStudentId(),0);
        logger.info("successful select:"+ selectClass.getClassId()+ ":" + selectClass.getStudentId());
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
