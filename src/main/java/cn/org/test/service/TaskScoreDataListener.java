package cn.org.test.service;

import cn.org.test.common.TaskScore;
import cn.org.test.mapper.ScoreReportMapper;
import cn.org.test.pojo.ScoreReport;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lyy on 2020/11/9 上午1:16
 */

// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去

public class TaskScoreDataListener extends AnalysisEventListener<TaskScore> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskScoreDataListener.class);

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */

    private static final int BATCH_COUNT = 5;

    List<TaskScore> list = new ArrayList<TaskScore>();

    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param
     * @param courseService
     */

    private CourseService courseService;
    private Integer taskId;
    private Integer classId;
    public TaskScoreDataListener(CourseService courseService,Integer taskId,Integer classId) throws IOException {
        this.courseService = courseService;
        this.taskId = taskId;
        this.classId = classId;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */

    @Override

    public void invoke(TaskScore data, AnalysisContext context) {

        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));

        list.add(data);

        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM

        if (list.size() >= BATCH_COUNT) {

            saveData();

            // 存储完成清理 list

            list.clear();

        }

    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */

    @Override

    public void doAfterAllAnalysed(AnalysisContext context) {

        // 这里也要保存数据，确保最后遗留的数据也存储到数据库

        saveData();

        LOGGER.info("所有数据解析完成！");

    }

    /**
     * 加上存储数据库
     */

    private void saveData() {

        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        Iterator itr = list.iterator();
        while(itr.hasNext()) {
            TaskScore taskScore = (TaskScore)itr.next();
            courseService.saveTaskScore(taskScore,taskId,classId);
            courseService.updateStuTaskScore(taskScore,taskId);
        }

//        demoDAO.save(list);
        System.out.println("save in -------------------"+list);
        LOGGER.info("存储数据库成功！");

    }

}
