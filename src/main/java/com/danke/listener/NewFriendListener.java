package com.danke.listener;

import com.danke.entity.QqInfo;
import com.danke.mapper.QqInfoMapper;
import love.forte.simbot.annotation.OnFriendAddRequest;
import love.forte.simbot.api.message.events.FriendAddRequest;
import love.forte.simbot.api.sender.MsgSender;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.api.sender.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author : zwy
 * @version : 1.0
 * @createTime : 2021/10/16 13:38
 * @Description : 处理好友申请
 */
@Service
public class NewFriendListener {

    @Qualifier("qqInfoMapper")
    @Autowired
    private QqInfoMapper qqInfoMapper;


   /*
   * 处理好友申请，验证信息正确通过好友申请
   * */
    @OnFriendAddRequest
    public void newFriend(FriendAddRequest add, Setter setter, Sender sender){
        QqInfo addedQQ = qqInfoMapper.findOne(
                qqInfoMapper.query().where.qqNumber().eq(add.getAccountInfo().getAccountCode()).end()
        );
        if (addedQQ != null) {
            setter.setFriendAddRequest(add.getFlag(), addedQQ.getNickname(), true,false);
            sender.sendPrivateMsg(add.getAccountInfo(),"(｡･∀･)ﾉﾞ嗨，你已经是蛋壳的好友了！");
            sender.sendPrivateMsg(add.getAccountInfo(),"小管家功能已激活，若是不清楚使用方法请访问官网 www.dankebot.top 查看文档");
        }

    }
}
