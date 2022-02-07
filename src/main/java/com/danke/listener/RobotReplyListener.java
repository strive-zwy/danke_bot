package com.danke.listener;

import com.danke.bean.RobotReplyJsonBean;
import com.danke.utils.OkHttpUtils;
import com.google.gson.Gson;
import love.forte.simbot.annotation.*;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.message.events.PrivateMsg;
import love.forte.simbot.api.sender.MsgSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zwy
 * @version 1.0
 * @createTime 2021/12/1 19:00
 * @description: 机器人回复监听
 */
@Service
public class RobotReplyListener {

    static String httpUrl = "http://api.tianapi.com/robot/index";
    static Map<String,String> map = new HashMap<>();

    /*
     * 获取机器人智能回复 - 私聊
     * */
    @OnPrivate
    @Priority(4)
    public void getRobotReply(PrivateMsg msg, MsgSender sender){
        map.put("key","0fbe2405cca172ce5115e0a8c8a63834");
        map.put("question",msg.getText());
        map.put("uniqueid",msg.getAccountInfo().getAccountCode());
        String r = OkHttpUtils.sendGet(httpUrl,map);
        Gson gson = new Gson();
        RobotReplyJsonBean reply = gson.fromJson(r, RobotReplyJsonBean.class);
        sender.SENDER.sendPrivateMsg(msg, reply.getNewslist().get(0).getReply());
    }

    /*
     * 获取机器人智能回复 - 群聊
     * */
    @OnGroup
    @Priority(4)
    @Filter(atBot = true)
    public void getGroupRobotReply(GroupMsg msg, MsgSender sender){
        map.put("key","0fbe2405cca172ce5115e0a8c8a63834");
        map.put("question",msg.getText());
        map.put("uniqueid",msg.getAccountInfo().getAccountCode());
        String r = OkHttpUtils.sendGet(httpUrl,map);
        Gson gson = new Gson();
        RobotReplyJsonBean reply = gson.fromJson(r, RobotReplyJsonBean.class);
        sender.SENDER.sendGroupMsg(msg, reply.getNewslist().get(0).getReply());
    }


}
