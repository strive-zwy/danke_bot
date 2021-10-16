package com.danke.task;

import com.danke.entity.GroupInfo;
import com.danke.entity.Task;
import com.danke.entity.UserInfo;
import com.danke.enums.POrGEnum;
import com.danke.enums.TaskStateEnum;
import com.danke.enums.TaskTypeEnum;
import com.danke.mapper.GroupInfoMapper;
import com.danke.mapper.TaskMapper;
import com.danke.mapper.UserInfoMapper;
import love.forte.simbot.api.message.results.FriendList;
import love.forte.simbot.api.message.results.GroupList;
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

    @Qualifier("userInfoMapper")
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Qualifier("groupInfoMapper")
    @Autowired
    private GroupInfoMapper groupInfoMapper;

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
            UserInfo u = task.findUserInfo();
            botManager.getDefaultBot().getSender().SENDER.sendPrivateMsg(u.getQqNumber(), task.getRemindStr());
            System.out.println("一次性定时任务执行----用户：" + u.getQqNumber() + "---" + LocalDateTime.now().toLocalTime());
        });
        List<Task> list_everyday_user = taskMapper.listEntity(
                taskMapper.query().where
                        .state().eq(TaskStateEnum.TASK_EXECUTE.getState())
                        .and.type().eq(TaskTypeEnum.TASK_EVERYDAY.getType())
                        .and.pOrG().eq(POrGEnum.USER_TASK.getType())
                        .and.remindTime().eq(time).end()
        );
        list_everyday_user.forEach(task -> {
            UserInfo u = task.findUserInfo();
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
//    @Cron(value = "*/1 * * ? * MON-WED", delay = 500)
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
            UserInfo u = task.findUserInfo();
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
            UserInfo u = task.findUserInfo();
            botManager.getDefaultBot().getSender().SENDER.sendPrivateMsg(u.getQqNumber(), task.getRemindStr());
            System.out.println("周末定时任务执行：用户---" + u.getQqNumber() + "---" + LocalDateTime.now().toLocalTime());
        });
    }

/*    @Fixed(value = 1, timeUnit = TimeUnit.HOURS, repeat = 1)
    public void task() {
        System.out.println("执行定时任务！！！");
        FriendList friendList = botManager.getDefaultBot().getSender().GETTER.getFriendList();
        System.out.println("friendList----"+friendList.getResults().size());
        friendList.getResults().forEach(friendInfo -> {
            System.out.println(friendInfo.getAccountNickname());
            UserInfo user = new UserInfo();
            user.setQqNumber(friendInfo.getAccountCodeNumber());
            userInfoMapper.save(user);
        });
        GroupList groupList = botManager.getDefaultBot().getSender().GETTER.getGroupList();
        System.out.println("groupList----"+groupList.getResults().size());
        groupList.getResults().forEach(groupInfo -> {
            String gname = groupInfo.getGroupName();
            Long gcode = groupInfo.getGroupCodeNumber();
            System.out.println(gname);
            System.out.println(gcode);
            GroupInfo group = new GroupInfo();
            group.setGroupNumber(gcode)
                    .setGroupName(gname);
            groupInfoMapper.insert(group);
        });
    }*/


}