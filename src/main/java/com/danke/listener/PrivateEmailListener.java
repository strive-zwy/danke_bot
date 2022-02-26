package com.danke.listener;

import com.danke.utils.EmailUtils;
import love.forte.simbot.annotation.*;
import love.forte.simbot.api.message.events.PrivateMsg;
import love.forte.simbot.api.sender.MsgSender;
import love.forte.simbot.filter.MatchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : zwy
 * @version : 1.0
 * @createTime : 2021/11/9 17:58
 * @Description : 私聊发送邮件监听
 */
@Service
public class PrivateEmailListener {

    @Autowired
    private EmailUtils emailUtils;

    /*
     * 向指定邮箱发送文字邮件
     * 向{{email,[\s\S]+}}发送{{str,[\s\S]+}}
     * 比如：向1222222222@qq.com发送 你好
     * */
    @OnPrivate
    @ListenBreak
    @Priority(2)
    @Filter(value = "向{{email,[\\s\\S]+}}发送{{str,[\\s\\S]+}}", matchType = MatchType.REGEX_MATCHES)
    public void nowWeather(PrivateMsg msg, MsgSender sender,
                           @FilterValue("email") String email,
                           @FilterValue("str") String str){
        sender.SENDER.sendPrivateMsg(msg, "好哒~");
        emailUtils.sendMail(email,msg, str);
        sender.SENDER.sendPrivateMsg(msg, "已发送哦~");
    }

}
