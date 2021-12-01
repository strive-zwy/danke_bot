package com.danke.utils;

import love.forte.simbot.api.message.events.PrivateMsg;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author : zwy
 * @version : 1.0
 * @createTime : 2021/11/9 18:40
 * @Description : 发送邮件工具类
 */
@Component
public class EmailUtils {

    @Value("${spring.mail.username}")
    private String from;
    
    @Resource
    JavaMailSender javaMailSender;

    /**
     * 发送文字邮件
     */
    public void sendMail(String email, PrivateMsg fromUser, String str) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            //设置发件人
            mimeMessageHelper.setFrom(from);
            //设置收件人
            mimeMessageHelper.setTo(email);
            //设置邮件主题
            mimeMessageHelper.setSubject("来自蛋壳QQ发送");
            //设置验证码的样式
            mimeMessageHelper.setText("QQ:"+ fromUser.getAccountInfo().getAccountCode()
                    +"("+fromUser.getAccountInfo().getAccountNickname()+
                    ")向您发送文字邮件：<p>"+str+"</p>",true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}

