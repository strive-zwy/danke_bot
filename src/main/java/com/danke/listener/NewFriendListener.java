package com.danke.listener;

import com.danke.entity.UserInfo;
import com.danke.mapper.UserInfoMapper;
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
 * @Description : 处理好友申请监听
 */
@Service
public class NewFriendListener {

    @Qualifier("userInfoMapper")
    @Autowired
    private UserInfoMapper userInfoMapper;


   /*
   * 处理好友申请，验证信息正确通过好友申请
   * */
    @OnFriendAddRequest
    public void newFriend(FriendAddRequest add, MsgSender sender){
        if ("你好蛋壳".equals(add.getText())) {
            sender.SETTER.setFriendAddRequest(add.getFlag(), null, true,false);
            //将新好友信息添加到数据库
            UserInfo user = new UserInfo();
            user.setQqNumber(add.getAccountInfo().getAccountCodeNumber());
            userInfoMapper.saveOrUpdate(user);
        }
    }
}
