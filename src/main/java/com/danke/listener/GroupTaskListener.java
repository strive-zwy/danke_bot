package com.danke.listener;

import catcode.CatCodeUtil;
import love.forte.simbot.annotation.Filter;
import love.forte.simbot.annotation.FilterValue;
import love.forte.simbot.annotation.ListenBreak;
import love.forte.simbot.annotation.OnGroup;
import love.forte.simbot.api.message.containers.GroupAccountInfo;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.sender.MsgSender;
import love.forte.simbot.filter.MatchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

/**
 * @author : zwy
 * @version : 1.0
 * @createTime : 2021/10/16 14:42
 * @Description : 群成员添加定时任务（只有管理员有权限）
 */
@Service
public class GroupTaskListener {

    /*@Qualifier("groupInfoMapper")
    @Autowired
    private GroupInfoMapper groupInfoMapper;*/

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
            }else {
                sender.SENDER.sendGroupMsg(msg,backmsg+"时间格式错了呦~，(格式为：07:00、15:25)");
            }
        }else {
            sender.SENDER.sendGroupMsg(msg,backmsg+"您不是管理员哦~");
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
