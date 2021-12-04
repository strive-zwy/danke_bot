package com.danke.listener;

import catcode.CatCodeUtil;
import love.forte.simbot.annotation.*;
import love.forte.simbot.api.message.containers.AccountInfo;
import love.forte.simbot.api.message.containers.BotInfo;
import love.forte.simbot.api.message.events.GroupAddRequest;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.sender.MsgSender;
import love.forte.simbot.api.sender.Setter;
import love.forte.simbot.filter.MatchType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author zwy
 * @version 1.0
 * @createTime 2021/11/27 16:58
 * @description: 群成员管理
 */
@Service
public class GroupMemberListener {

    @OnGroupAddRequest
    public void onRequest(GroupAddRequest groupAddRequest, Setter setter) {
        // 此事件的“申请者”
        AccountInfo accountInfo = groupAddRequest.getRequestAccountInfo();
        // 收到此事件的bot
        BotInfo botInfo = groupAddRequest.getBotInfo();
        // 申请者的申请消息是否对上设置的暗号，对上则同意申请
        if ("暗号".equals(groupAddRequest.getText())) {
            setter.setGroupAddRequest(groupAddRequest.getFlag(), true, false, null);
        }
    }

    /*
     * 禁言全体成员
     * */
    @OnGroup
    @ListenBreak
    @Filter(value = "禁言全体成员", atBot = true, matchType = MatchType.ENDS_WITH)
    public void addKeyword(GroupMsg msg, MsgSender sender){
        if (msg.getAccountInfo().getPermission().isOwnerOrAdmin()) {
            sender.SETTER.setGroupWholeBan(msg.getGroupInfo().getGroupCodeNumber(),true);
            return;
        }
        sender.SENDER.sendGroupMsg(msg,"您不是管理员，没有权限！");
    }

    /*
     * 禁言某个成员
     * */
    @OnGroup
    @ListenBreak
    @Filter(value = "禁言{{qqnumber,[\\s\\S]+}}", atBot = true, matchType = MatchType.REGEX_FIND)
    public void addKeyword(GroupMsg msg, MsgSender sender,
                           @FilterValue("qqnumber") String qqnumber){
        if (msg.getAccountInfo().getPermission().isOwnerOrAdmin()) {
            sender.SENDER.sendGroupMsg(msg,"好哒~");
            CatCodeUtil catCodeUtil = CatCodeUtil.getInstance();
            String at = catCodeUtil.getStringTemplate().at(msg.getAccountInfo().getAccountCodeNumber());
            sender.SETTER.setGroupBan(String.valueOf(msg.getGroupInfo().getGroupCodeNumber()),
                    qqnumber,5, TimeUnit.HOURS);
            sender.SENDER.sendGroupMsg(msg,at + "您已被管理员禁言5小时。请注意群秩序！");
            return;
        }
        sender.SENDER.sendGroupMsg(msg,"您不是管理员，没有权限！");
    }
}
