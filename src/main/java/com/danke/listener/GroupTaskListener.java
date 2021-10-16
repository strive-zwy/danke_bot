package com.danke.listener;

import catcode.CatCodeUtil;
import com.danke.entity.GroupInfo;
import com.danke.entity.Task;
import com.danke.enums.POrGEnum;
import com.danke.enums.TaskStateEnum;
import com.danke.enums.TaskTypeEnum;
import com.danke.mapper.GroupInfoMapper;
import com.danke.mapper.TaskMapper;
import com.danke.utils.TaskStrUtils;
import love.forte.simbot.annotation.*;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.sender.MsgSender;
import love.forte.simbot.filter.MatchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author : zwy
 * @version : 1.0
 * @createTime : 2021/10/16 14:42
 * @Description : 群成员添加定时任务（只有管理员有权限）
 */
@Service
public class GroupTaskListener {

    @Qualifier("groupInfoMapper")
    @Autowired
    private GroupInfoMapper groupInfoMapper;

    @Qualifier("taskMapper")
    @Autowired
    private TaskMapper taskMapper;

    /*
     * 每日任务添加
     * 每天{time}提醒我{str}
     * 比如：每天07:00提醒我健康打卡
     * */
    @OnGroup
    @ListenBreak
    @Filter(value = "每天的{{time,[\\s\\S]+}}提醒大家{{str,[\\s\\S]+}}",
            atBot = true, matchType = MatchType.REGEX_FIND)
    public void addOnceTask(GroupMsg msg, MsgSender sender,
                            @FilterValue("time") String time,
                            @FilterValue("str") String str){
        String remind_time = time.replace("：",":");
        boolean ff = isTime(remind_time, "HH:mm");
        CatCodeUtil catCodeUtil = CatCodeUtil.getInstance();
        String backmsg = catCodeUtil.getStringTemplate().at(msg.getAccountInfo().getAccountCodeNumber());
        if (msg.getPermission().isOwnerOrAdmin()) {
            if (ff) {
                sender.SENDER.sendGroupMsg(msg,backmsg+"好哒~");
                GroupInfo g = groupInfoMapper.findOne(
                        groupInfoMapper.query().where
                                .groupNumber().eq(msg.getGroupInfo().getGroupCodeNumber()).end()
                );
                Task task = new Task();
                String atAll = catCodeUtil.getStringTemplate().atAll();
                task.setType(TaskTypeEnum.TASK_EVERYDAY.getType())
                        .setRemindDate("NEEDLESS")
                        .setRemindTime(remind_time+":00")
                        .setRemindStr(atAll+str+"时间到了！")
                        .setCreatorId(g.getId())
                        .setPOrG(POrGEnum.GROUP_TASK.getType());
                taskMapper.insert(task);
            }else {
                sender.SENDER.sendGroupMsg(msg,backmsg+"时间格式错了呦~，(格式为：07:00、15:25)");
            }
        }else {
            sender.SENDER.sendGroupMsg(msg,backmsg+"您不是管理员哦~");
        }
    }

    /*
     * 查询所有定时任务
     * */
    @OnGroup
    @ListenBreak
    @Filter(value = "查询所有定时任务", atBot = true, matchType = MatchType.ENDS_WITH)
    public void AskAllTask(GroupMsg msg, MsgSender sender){
        List<Task> list = taskMapper.listEntity(
                taskMapper.query().where.creatorId().eq(
                        groupInfoMapper.findOne(
                                groupInfoMapper.query().where.groupNumber()
                                        .eq(msg.getGroupInfo().getGroupCodeNumber()).end()
                        ).getId()
                ).state().eq(TaskStateEnum.TASK_EXECUTE.getState())
                        .and.pOrG().eq(POrGEnum.GROUP_TASK.getType()).end()
        );
        String str = TaskStrUtils.getTaskInfo(list);
        sender.SENDER.sendGroupMsg(msg,str);
    }

    /*
     * 删除所有定时任务
     * */
    @OnGroup
    @ListenBreak
    @Filter(value = "清除所有定时任务", atBot = true, matchType = MatchType.ENDS_WITH)
    @Filter(value = "删除所有定时任务", atBot = true, matchType = MatchType.ENDS_WITH)
    public void deleteAllTask(GroupMsg msg, MsgSender sender){
        //查询有多少条正在执行的定时任务
        int count = taskMapper.count(
                taskMapper.query().where.creatorId().eq(
                        groupInfoMapper.findOne(
                                groupInfoMapper.query().where
                                        .groupNumber().eq(msg.getGroupInfo().getGroupCodeNumber()).end()
                        ).getId()
                ).and.state().eq(TaskStateEnum.TASK_EXECUTE)
                        .and.pOrG().eq(POrGEnum.GROUP_TASK.getType()).end()
        );
        CatCodeUtil catCodeUtil = CatCodeUtil.getInstance();
        String at = catCodeUtil.getStringTemplate().at(msg.getAccountInfo().getAccountCodeNumber());
        if (count == 0) {
            sender.SENDER.sendGroupMsg(msg, at+"您没有任何定时任务正在执行");
            return;
        }
//        sender.SENDER.sendPrivateMsg(msg, "正在删除......");
        int i = taskMapper.updateBy(
                taskMapper.updater().set.state().is(TaskStateEnum.TASK_CANCELED.getState()).end()
                        .where.creatorId().eq(
                        groupInfoMapper.findOne(
                                groupInfoMapper.query().where
                                        .groupNumber().eq(msg.getGroupInfo().getGroupCodeNumber()).end()
                        ).getId()
                ).and.pOrG().eq(POrGEnum.GROUP_TASK.getType()).end()
        );
        if (i >= 0) {
            sender.SENDER.sendPrivateMsg(msg, "删除成功,共删除 " + count + "条正在执行的定时任务。");
        }
    }


    /*
     * 通过 id 删除指定定时任务
     * */
    @OnGroup
    @ListenBreak
    @Filter(value = "取消任务{{taskId,\\d+}}", atBot = true,
            matchType = MatchType.REGEX_FIND)
    public void deleteTaskById(GroupMsg msg, MsgSender sender,
                               @FilterValue("taskId") Long taskId){
        Task task = taskMapper.findOne(
                taskMapper.query().where.id().eq(taskId)
                        .and.state().eq(TaskStateEnum.TASK_EXECUTE)
                        .and.pOrG().eq(POrGEnum.GROUP_TASK.getType()).end()
        );
        CatCodeUtil catCodeUtil = CatCodeUtil.getInstance();
        String at = catCodeUtil.getStringTemplate().at(msg.getAccountInfo().getAccountCodeNumber());
        if (task == null) {
            sender.SENDER.sendGroupMsg(msg,at+"该任务不存在或已经取消");
            return;
        }
        task.setState(TaskStateEnum.TASK_CANCELED.getState());
        if (taskMapper.saveOrUpdate(task)) {
            sender.SENDER.sendGroupMsg(msg,"已取消");
        }
    }


    // 判断字符串是否是时间格式
    public boolean isTime(String time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            sdf.parse(time);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
