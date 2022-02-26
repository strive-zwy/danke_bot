package com.danke.task;

import com.danke.entity.GroupInfo;
import com.danke.entity.QqInfo;
import com.danke.entity.Task;
import com.danke.enums.POrGEnum;
import com.danke.enums.TaskStateEnum;
import com.danke.enums.TaskTypeEnum;
import com.danke.mapper.GroupInfoMapper;
import com.danke.mapper.QqInfoMapper;
import com.danke.mapper.TaskMapper;
import love.forte.simbot.bot.BotManager;
import love.forte.simbot.timer.Cron;
import love.forte.simbot.timer.EnableTimeTask;
import love.forte.simbot.timer.Fixed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
/**
 * @author : zwy
 * @version : 1.0
 * @createTime : 2021/9/30 22:04
 * @Description : 定时任务执行
 */
@Service
@EnableTimeTask
public class MyTask {

    @Autowired
    private BotManager botManager;

    @Qualifier("taskMapper")
    @Autowired
    private TaskMapper taskMapper;

    /*
    * 定时任务，每秒执行
    * */
    @Fixed(value = 1, timeUnit = TimeUnit.SECONDS, delay = 2000)
    public void privateTask() {
        String today = LocalDateTime.now().toLocalDate().toString();
        String time = LocalDateTime.now().toLocalTime().toString().substring(0,8);
        List<Task> list_once_user = taskMapper.listEntity(
                taskMapper.query().where.remindDate().eq(today)
                .state().eq(TaskStateEnum.TASK_EXECUTE.getState())
                .and.pOrG().eq(POrGEnum.USER_TASK.getType())
                .and.type().eq(TaskTypeEnum.TASK_ONCE.getType())
                .and.remindTime().eq(time).end()
        );
        list_once_user.forEach(task -> {
            task.setState(TaskStateEnum.TASK_CANCELED.getState());
            taskMapper.updateById(task);
            QqInfo u = task.findQqInfo();
            botManager.getDefaultBot().getSender().SENDER.sendPrivateMsg(u.getQqNumber(), task.getRemindStr());
            System.out.println("一次性定时任务执行----用户：" + u.getQqNumber() + "---" + LocalDateTime.now().toLocalTime());
        });
        List<Task> list_once_group = taskMapper.listEntity(
                taskMapper.query().where.remindDate().eq(today)
                        .state().eq(TaskStateEnum.TASK_EXECUTE.getState())
                        .and.pOrG().eq(POrGEnum.GROUP_TASK.getType())
                        .and.type().eq(TaskTypeEnum.TASK_ONCE.getType())
                        .and.remindTime().eq(time).end()
        );
        list_once_group.forEach(task -> {
            task.setState(TaskStateEnum.TASK_CANCELED.getState());
            taskMapper.updateById(task);
            GroupInfo g = task.findGroupInfo();
            botManager.getDefaultBot().getSender().SENDER.sendGroupMsg(g.getGroupNumber(), task.getRemindStr());
            System.out.println("一次性定时任务执行----群：" + g.getGroupNumber() + "---" + LocalDateTime.now().toLocalTime());
        });
        List<Task> list_everyday_user = taskMapper.listEntity(
                taskMapper.query().where
                        .state().eq(TaskStateEnum.TASK_EXECUTE.getState())
                        .and.type().eq(TaskTypeEnum.TASK_EVERYDAY.getType())
                        .and.pOrG().eq(POrGEnum.USER_TASK.getType())
                        .and.remindTime().eq(time).end()
        );
        list_everyday_user.forEach(task -> {
            QqInfo u = task.findQqInfo();
            botManager.getDefaultBot().getSender().SENDER.sendPrivateMsg(u.getQqNumber(), task.getRemindStr());
            System.out.println("每日定时任务执行：用户---" + u.getQqNumber() + "---" + LocalDateTime.now().toLocalTime());
        });
        List<Task> list_everyday_group = taskMapper.listEntity(
                taskMapper.query().where
                        .state().eq(TaskStateEnum.TASK_EXECUTE.getState())
                        .and.type().eq(TaskTypeEnum.TASK_EVERYDAY.getType())
                        .and.pOrG().eq(POrGEnum.GROUP_TASK.getType())
                        .and.remindTime().eq(time).end()
        );
        list_everyday_group.forEach(task -> {
            GroupInfo g = task.findGroupInfo();
            botManager.getDefaultBot().getSender().SENDER.sendGroupMsg(g.getGroupNumber(), task.getRemindStr());
            System.out.println("每日群定时任务执行：群号---" + g.getGroupNumber() + "---" + LocalDateTime.now().toLocalTime());
        });
    }

    /*
    * 定时任务，每个工作日每秒执行一次
    * */
    @Cron(value = "*/1 * * ? * MON-FRI", delay = 500)
    public void workdayTask() {
        String time = LocalDateTime.now().toLocalTime().toString().substring(0,8);
        List<Task> list_workday_user = taskMapper.listEntity(
                taskMapper.query().where
                        .state().eq(TaskStateEnum.TASK_EXECUTE.getState())
                        .and.type().eq(TaskTypeEnum.TASK_WORKDAY.getType())
                        .and.pOrG().eq(POrGEnum.USER_TASK.getType())
                        .and.remindTime().eq(time).end()
        );
        list_workday_user.forEach(task -> {
            QqInfo u = task.findQqInfo();
            botManager.getDefaultBot().getSender().SENDER.sendPrivateMsg(u.getQqNumber(), task.getRemindStr());
            System.out.println("工作日定时任务执行：用户---" + u.getQqNumber() + "---" + LocalDateTime.now().toLocalTime());
        });
    }

    /*
    * 定时任务，每个周六周日每秒执行一次
    * */
    @Cron(value = "*/1 * * ? * SAT-SUN", delay = 500)
    public void weekendTask() {
        String time = LocalDateTime.now().toLocalTime().toString().substring(0,8);
        List<Task> list_workday_user = taskMapper.listEntity(
                taskMapper.query().where
                        .state().eq(TaskStateEnum.TASK_EXECUTE.getState())
                        .and.type().eq(TaskTypeEnum.TASK_WEEKEND.getType())
                        .and.pOrG().eq(POrGEnum.USER_TASK.getType())
                        .and.remindTime().eq(time).end()
        );
        list_workday_user.forEach(task -> {
            QqInfo u = task.findQqInfo();
            botManager.getDefaultBot().getSender().SENDER.sendPrivateMsg(u.getQqNumber(), task.getRemindStr());
            System.out.println("周末定时任务执行：用户---" + u.getQqNumber() + "---" + LocalDateTime.now().toLocalTime());
        });
    }

}