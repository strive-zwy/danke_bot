package com.danke.controller;

import com.danke.bean.MessageBean;
import com.danke.entity.ApiMessage;
import com.danke.entity.Login;
import com.danke.entity.QqInfo;
import com.danke.exception.ResultBody;
import com.danke.mapper.ApiMessageMapper;
import com.danke.mapper.LoginMapper;
import com.danke.mapper.QqInfoMapper;
import com.danke.utils.KeyUtils;
import love.forte.simbot.bot.BotManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;

/**
 * @author zwy
 * @version 1.0
 * @createTime 2021/12/13 14:15
 * @description: api 接口
 */
@Controller
@RequestMapping("/api")
public class APIKeyController {

    @Autowired
    private BotManager botManager;

    @Qualifier("loginMapper")
    @Autowired
    private LoginMapper loginMapper;

    @Qualifier("qqInfoMapper")
    @Autowired
    private QqInfoMapper qqInfoMapper;

    @Qualifier("apiMessageMapper")
    @Autowired
    private ApiMessageMapper apiMessageMapper;

    @RequestMapping(value = "/getKey", method = RequestMethod.GET)
    @ResponseBody
    public ResultBody getKey(HttpServletRequest request){
        Login login = (Login)request.getSession().getAttribute("login");
        if (login == null) {
            return ResultBody.error("-1","用户未登录");
        }
        String newKey = KeyUtils.getKey();
        login.setApiKey(newKey);
        loginMapper.saveOrUpdate(login);
        request.getSession().setAttribute("login",login);
        return ResultBody.success(newKey);
    }


    @RequestMapping(value = "/postMessage/{apiKey}/{qq}",method = RequestMethod.POST)
    @ResponseBody
    public ResultBody postMessage(@PathVariable(value = "apiKey") String apiKey,
                                  @PathVariable(value = "qq") String qq,
                                  @RequestParam(value = "message", required = true) String message){
        Login login = loginMapper.findOne(
                loginMapper.query().where.apiKey().eq(apiKey).end()
        );
        if (login == null) {
            return ResultBody.error("key不存在");
        }
        QqInfo qqInfo = qqInfoMapper.findOne(
                qqInfoMapper.query().where.qqNumber().eq(qq)
                        .adder().eq(login.getId()).end()
        );
        if (qqInfo == null) {
            return ResultBody.error("QQ号异常，请检查该QQ号是否添加蛋壳为好友或是否在您的QQ列表中！");
        }
        ApiMessage apiMessage = new ApiMessage();
        apiMessage.setMsg(message)
                .setReceiver(qq);
        apiMessageMapper.save(apiMessage);
        // 发送消息
        botManager.getDefaultBot().getSender().SENDER.sendPrivateMsg(qq,message);
        return ResultBody.success();
    }
}
