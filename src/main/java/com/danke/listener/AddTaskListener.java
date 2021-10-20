package com.danke.listener;

import com.danke.entity.Task;
import com.danke.entity.UserInfo;
import com.danke.enums.POrGEnum;
import com.danke.enums.TaskTypeEnum;
import com.danke.mapper.TaskMapper;
import com.danke.mapper.UserInfoMapper;
import love.forte.simbot.annotation.*;
import love.forte.simbot.api.message.events.PrivateMsg;
import love.forte.simbot.api.sender.MsgSender;
import love.forte.simbot.filter.MatchType;
import love.forte.simbot.listener.ListenerContext;
import love.forte.simbot.listener.ScopeContext;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 * @author : zwy
 * @version : 1.0
 * @createTime : 2021/9/30 22:04
 * @Description : 用户添加定时任务监听
 */
@Service
public class AddTaskListener {

    @Qualifier("taskMapper")
    @Autowired
    private TaskMapper taskMapper;

    @Qualifier("userInfoMapper")
    @Autowired
    private UserInfoMapper userInfoMapper;


    /*
    * 一次性任务添加
    * {number}{type}后提醒我{str}
    * 比如：10秒后提醒我健康打卡
    * */
    @OnPrivate
    @ListenBreak
    @Filter(value = "{{number,\\d+}}{{type,[\\s\\S]+}}后提醒我{{str,[\\s\\S]+}}",
            matchType = MatchType.REGEX_MATCHES)
    public void addOnceTask(PrivateMsg msg, MsgSender sender,
                        @FilterValue("number") long number,
                        @FilterValue("type") String type,
                        @FilterValue("str") String str){
        sender.SENDER.sendPrivateMsg(msg, "好哒~");
        UserInfo u = userInfoMapper.findOne(
                userInfoMapper.query().where.qqNumber().eq(msg.getAccountInfo().getAccountCodeNumber()).end()
        );
        Task task = new Task();
        task.setRemindStr(u.getNickname()+"，"+str+"时间到了！")
                .setCreatorId(u.getId())
                .setPOrG(POrGEnum.USER_TASK.getType())
                .setRemindDate(LocalDateTime.now().toLocalDate().toString())
                .setType(TaskTypeEnum.TASK_ONCE.getType());
        if ("秒".equals(type)) {
            task.setRemindTime(LocalDateTime.now().toLocalTime().plusSeconds(number).toString().substring(0,8));
        }else if ("分钟".equals(type)) {
            task.setRemindTime(LocalDateTime.now().toLocalTime().plusMinutes(number).toString().substring(0,8));
        }else if ("小时".equals(type)) {
            task.setRemindTime(LocalDateTime.now().toLocalTime().plusHours(number).toString().substring(0,8));
        }
        taskMapper.insert(task);
    }


     /*
     * 一次性任务添加（指定哪一天的哪一时刻）
     * {date}的{time}后提醒我{str}
     * 比如：10-11的21：05提醒我健康打卡
     * */
    @Priority(3)
    @OnPrivate
    @ListenBreak
    @Filter(value = "{{date,[\\s\\S]+}}的{{time,[\\s\\S]+}}提醒我{{str,[\\s\\S]+}}",
            matchType = MatchType.REGEX_MATCHES)
    public void addOnceTask2(PrivateMsg msg, MsgSender sender,
                             @FilterValue("date") String date,
                             @FilterValue("time") String time,
                             @FilterValue("str") String str){
        String remind_time = time.replace("：",":");
        sender.SENDER.sendPrivateMsg(msg, "好哒~");
        UserInfo u = userInfoMapper.findOne(
                userInfoMapper.query().where.qqNumber().eq(msg.getAccountInfo().getAccountCodeNumber()).end()
        );
        Task task = new Task();
        task.setRemindStr(u.getNickname()+"，"+str+"时间到了！")
                .setCreatorId(u.getId())
                .setPOrG(POrGEnum.USER_TASK.getType())
                .setType(TaskTypeEnum.TASK_ONCE.getType());
        if (!isTime(date,"MM-dd")) {
            sender.SENDER.sendPrivateMsg(msg, "日期格式错误！(格式为：09-12)");
            return;
        }
        if (!isTime(remind_time,"HH:mm")) {
            sender.SENDER.sendPrivateMsg(msg, "时间格式错误！(格式为：07:00、15:25)");
            return;
        }
        task.setRemindDate(LocalDateTime.now().getDayOfYear() + "-"+date)
                .setRemindTime(remind_time + ":00");
        taskMapper.insert(task);
    }

    /*
    * 每日任务添加
    * 每天{time}提醒我{str}
    * 比如：每天07:00提醒我健康打卡
    * */
    @Priority(2)
    @OnPrivate
    @ListenBreak
    @Filter(value = "每天的{{time,[\\s\\S]+}}提醒我{{str,[\\s\\S]+}}", matchType = MatchType.REGEX_MATCHES)
    @Filter(value = "每天{{time,[\\s\\S]+}}提醒我{{str,[\\s\\S]+}}", matchType = MatchType.REGEX_MATCHES)
    public void addEverydayTask(PrivateMsg msg, MsgSender sender,
                                @FilterValue("time") String time,
                                @FilterValue("str") String str){
        String remind_time = time.replace("：",":");
        boolean ff = isTime(remind_time, "HH:mm");
        if (ff) {
            sender.SENDER.sendPrivateMsg(msg, "好哒~");
            UserInfo u = userInfoMapper.findOne(
                    userInfoMapper.query().where.qqNumber().eq(msg.getAccountInfo().getAccountCodeNumber()).end()
            );
            Task task = new Task();
            task.setType(TaskTypeEnum.TASK_EVERYDAY.getType())
                    .setRemindDate("NEEDLESS")
                    .setRemindTime(remind_time+":00")
                    .setRemindStr(u.getNickname()+"，"+str+"时间到了！")
                    .setCreatorId(u.getId())
                    .setPOrG(POrGEnum.USER_TASK.getType());
            taskMapper.insert(task);
        } else {
            sender.SENDER.sendPrivateMsg(msg, "时间格式错误！");
        }
    }

    /*
    * 每个工作日任务添加
    * 每个工作日的{time}提醒我{str}
    * 比如：每个工作日的07:00提醒我健康打卡
    * */
    @Priority(2)
    @OnPrivate
    @ListenBreak
    @Filter(value = "每个工作日的{{time,[\\s\\S]+}}提醒我{{str,[\\s\\S]+}}", matchType = MatchType.REGEX_MATCHES)
    public void addWorkdayTask(PrivateMsg msg, MsgSender sender,
                                @FilterValue("time") String time,
                                @FilterValue("str") String str){
        String remind_time = time.replace("：",":");
        boolean ff = isTime(remind_time, "HH:mm");
        if (ff) {
            sender.SENDER.sendPrivateMsg(msg, "好哒~");
            UserInfo u = userInfoMapper.findOne(
                    userInfoMapper.query().where.qqNumber().eq(msg.getAccountInfo().getAccountCodeNumber()).end()
            );
            Task task = new Task();
            task.setType(TaskTypeEnum.TASK_WORKDAY.getType())
                    .setRemindDate("NEEDLESS")
                    .setRemindTime(remind_time+":00")
                    .setRemindStr(u.getNickname()+"，"+str+"时间到了！")
                    .setCreatorId(u.getId())
                    .setPOrG(POrGEnum.USER_TASK.getType());
            taskMapper.insert(task);
        } else {
            sender.SENDER.sendPrivateMsg(msg, "时间格式错误！");
        }
    }

    /*
     * 每个工作日任务添加
     * 每个工作日的{time}提醒我{str}
     * 比如：每个工作日的07:00提醒我健康打卡
     * */
    @Priority(2)
    @OnPrivate
    @ListenBreak
    @Filter(value = "每个周末的{{time,[\\s\\S]+}}提醒我{{str,[\\s\\S]+}}", matchType = MatchType.REGEX_MATCHES)
    public void addWeekendTask(PrivateMsg msg, MsgSender sender,
                               @FilterValue("time") String time,
                               @FilterValue("str") String str){
        String remind_time = time.replace("：",":");
        boolean ff = isTime(remind_time, "HH:mm");
        if (ff) {
            sender.SENDER.sendPrivateMsg(msg, "好哒~");
            UserInfo u = userInfoMapper.findOne(
                    userInfoMapper.query().where.qqNumber().eq(msg.getAccountInfo().getAccountCodeNumber()).end()
            );
            Task task = new Task();
            task.setType(TaskTypeEnum.TASK_WEEKEND.getType())
                    .setRemindDate("NEEDLESS")
                    .setRemindTime(remind_time+":00")
                    .setRemindStr(u.getNickname()+"，"+str+"时间到了！")
                    .setCreatorId(u.getId())
                    .setPOrG(POrGEnum.USER_TASK.getType());
        } else {
            sender.SENDER.sendPrivateMsg(msg, "时间格式错误！");
        }
    }

/*    @OnPrivate
    public void listen(PrivateMsg msg, MsgSender sender){
        sender.SENDER.sendPrivateMsg(msg,msg.getText());
    }*/

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
