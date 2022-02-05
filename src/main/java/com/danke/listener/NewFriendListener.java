package com.danke.listener;

import com.danke.entity.QqInfo;
import com.danke.mapper.QqInfoMapper;
import love.forte.simbot.annotation.OnFriendAddRequest;
import love.forte.simbot.api.message.events.FriendAddRequest;
import love.forte.simbot.api.sender.MsgSender;
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
    public void newFriend(FriendAddRequest add, MsgSender sender){
        if ("你好蛋壳".equals(add.getText())) {
            sender.SETTER.setFriendAddRequest(add.getFlag(), null, true,false);
            //将新好友信息添加到数据库
            QqInfo user = new QqInfo();
            user.setQqNumber(add.getAccountInfo().getAccountCodeNumber());
//            QqInfoMapper.saveOrUpdate(user);
        }
    }
}
