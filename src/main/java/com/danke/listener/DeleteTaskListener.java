package com.danke.listener;

import com.danke.entity.Task;
import com.danke.enums.POrGEnum;
import com.danke.enums.TaskStateEnum;
import com.danke.enums.TaskTypeEnum;
import com.danke.mapper.TaskMapper;
import com.danke.mapper.UserInfoMapper;
import com.danke.utils.TaskStrUtils;
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
        String str = TaskStrUtils.getTaskInfo(list);
        sender.SENDER.sendPrivateMsg(msg,str);
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
