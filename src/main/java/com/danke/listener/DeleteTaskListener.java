package com.danke.listener;

import com.danke.entity.Task;
import com.danke.enums.POrGEnum;
import com.danke.enums.TaskStateEnum;
import com.danke.enums.TaskTypeEnum;
import com.danke.mapper.TaskMapper;
import com.danke.mapper.UserInfoMapper;
import love.forte.simbot.annotation.Filter;
import love.forte.simbot.annotation.FilterValue;
import love.forte.simbot.annotation.ListenBreak;
import love.forte.simbot.annotation.OnPrivate;
import love.forte.simbot.api.message.events.PrivateMsg;
import love.forte.simbot.api.sender.MsgSender;
import love.forte.simbot.filter.MatchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author : zwy
 * @version : 1.0
 * @createTime : 2021/10/14 14:55
 * @Description : 删除、查询定时任务监听
 */
@Service
public class DeleteTaskListener {

    @Qualifier("taskMapper")
    @Autowired
    private TaskMapper taskMapper;

    @Qualifier("userInfoMapper")
    @Autowired
    private UserInfoMapper userInfoMapper;


     /*
     * 查询所有定时任务
     * */
    @OnPrivate
    @ListenBreak
    @Filter(value = "查询所有定时任务", matchType = MatchType.EQUALS)
    public void AskAllTask(PrivateMsg msg, MsgSender sender){
        List<Task> list = taskMapper.listEntity(
                taskMapper.query().where.creatorId().eq(
                        userInfoMapper.findOne(
                                userInfoMapper.query().where.qqNumber()
                                        .eq(msg.getAccountInfo().getAccountCodeNumber()).end()
                        ).getId()
                ).state().eq(TaskStateEnum.TASK_EXECUTE.getState())
                .and.pOrG().eq(POrGEnum.USER_TASK.getType()).end()
        );
        String str = getTaskInfo(list);
        sender.SENDER.sendPrivateMsg(msg,str);
    }

    public String getTaskInfo(List<Task> list) {
        StringBuilder str = new StringBuilder();
        str.append("您现在共有 ").append(list.size()).append(" 条正在执行的定时任务:\n");
        str.append("id --- 提醒时间");
        StringBuilder onece_str = new StringBuilder();
        int onece = (int) list.stream().filter(privateTask ->
                TaskTypeEnum.TASK_ONCE.getType().equals(privateTask.getType())).count();
        onece_str.append(" \n一次性任务共").append(onece).append("条:");
        StringBuilder everyday_str = new StringBuilder();
        int everyday = (int) list.stream().filter(privateTask ->
                TaskTypeEnum.TASK_EVERYDAY.getType().equals(privateTask.getType())).count();
        everyday_str.append("\n\n每日任务共").append(everyday).append("条:");
        StringBuilder workday_str = new StringBuilder();
        int workday = (int) list.stream().filter(privateTask ->
                TaskTypeEnum.TASK_WORKDAY.getType().equals(privateTask.getType())).count();
        workday_str.append("\n\n工作日任务共").append(workday).append("条:");
        StringBuilder weekend_str = new StringBuilder();
        int weekend = (int) list.stream().filter(privateTask ->
                TaskTypeEnum.TASK_WEEKEND.getType().equals(privateTask.getType())).count();
        weekend_str.append("\n\n周末任务共").append(weekend).append("条:");
        list.forEach(privateTask -> {
            if (TaskTypeEnum.TASK_ONCE.getType().equals(privateTask.getType())) {
                onece_str.append("\n").append(privateTask.getId()).append(" --- ")
                        .append(privateTask.getRemindDate())
                        .append(" ").append(privateTask.getRemindTime());
            }
            if (TaskTypeEnum.TASK_EVERYDAY.getType().equals(privateTask.getType())) {
                everyday_str.append("\n").append(privateTask.getId()).append(" --- ")
                        .append(privateTask.getRemindTime());
            }
            if (TaskTypeEnum.TASK_WORKDAY.getType().equals(privateTask.getType())) {
                workday_str.append("\n").append(privateTask.getId()).append(" --- ")
                        .append(privateTask.getRemindTime());
            }
            if (TaskTypeEnum.TASK_WEEKEND.getType().equals(privateTask.getType())) {
                weekend_str.append("\n").append(privateTask.getId()).append(" --- ")
                        .append(privateTask.getRemindTime());
            }
        });
        str.append(onece_str).append(everyday_str).append(workday_str)
                .append(weekend_str).append("\n\n若想取消某个任务，请发送命令：取消任务+id（比如：取消任务15）");
//        int customize = (int) list.stream().filter(privateTask -> TaskTypeEnum.TASK_CUSTOMIZE.getType().equals(privateTask.getType())).count();
        return str.toString();
    }


     /*
     * 删除所有定时任务
     * */
    @OnPrivate
    @ListenBreak
    @Filter(value = "清除所有定时任务", matchType = MatchType.EQUALS)
    @Filter(value = "删除所有定时任务", matchType = MatchType.EQUALS)
    public void deleteAllTask(PrivateMsg msg, MsgSender sender){
        //查询有多少条正在执行的定时任务
        int count = taskMapper.count(
                taskMapper.query().where.creatorId().eq(
                        userInfoMapper.findOne(
                                userInfoMapper.query().where.qqNumber().eq(msg.getAccountInfo().getAccountCodeNumber()).end()
                        ).getId()
                ).and.state().eq(TaskStateEnum.TASK_EXECUTE)
                        .and.pOrG().eq(POrGEnum.USER_TASK.getType()).end()
        );
        if (count == 0) {
            sender.SENDER.sendPrivateMsg(msg, "您没有任何定时任务正在执行");
            return;
        }
//        sender.SENDER.sendPrivateMsg(msg, "正在删除......");
        int i = taskMapper.updateBy(
                taskMapper.updater().set.state().is(TaskStateEnum.TASK_CANCELED.getState()).end()
                .where.creatorId().eq(
                        userInfoMapper.findOne(
                                userInfoMapper.query().where.qqNumber().eq(msg.getAccountInfo().getAccountCodeNumber()).end()
                        ).getId()
                ).and.pOrG().eq(POrGEnum.USER_TASK.getType()).end()
        );
        if (i >= 0) {
            sender.SENDER.sendPrivateMsg(msg, "删除成功,共删除 " + count + "条正在执行的定时任务。");
        }
    }


    /*
    * 通过 id 删除指定定时任务
    * */
    @OnPrivate
    @ListenBreak
    @Filter(value = "取消任务{{taskId,\\d+}}", matchType = MatchType.REGEX_MATCHES)
    public void deleteTaskById(PrivateMsg msg, MsgSender sender,
                               @FilterValue("taskId") Long taskId){
        Task task = taskMapper.findOne(
                taskMapper.query().where.id().eq(taskId)
                .and.state().eq(TaskStateEnum.TASK_EXECUTE)
                .and.pOrG().eq(POrGEnum.USER_TASK.getType()).end()
        );
        if (task == null) {
            sender.SENDER.sendPrivateMsg(msg,"该任务不存在或已经取消");
            return;
        }
        task.setState(TaskStateEnum.TASK_CANCELED.getState());
        if (taskMapper.saveOrUpdate(task)) {
            sender.SENDER.sendPrivateMsg(msg,"已取消");
        }
    }
}
