package com.danke.controller;

import com.danke.entity.Login;
import com.danke.mapper.LoginMapper;
import com.danke.utils.KeyUtils;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthQqRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zwy
 * @version 1.0
 * @createTime 2021/12/24 21:46
 * @description: 登录逻辑处理
 */

@Slf4j
@Controller
public class LoginController {

    @Qualifier("loginMapper")
    @Autowired
    private LoginMapper loginMapper;

    @RequestMapping("/loginByQQ")
    public void renderAuth(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    @RequestMapping("/ceshi")
    public String cceshi(AuthCallback callback,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        Login login = loginMapper.findById(2);
        log.info("用户token ---- " + login.getToken());
        log.info("用户昵称 ---- " + login.getNickname());
        request.getSession().setAttribute("login",login);
        if (login.getRole() == 1) {
            return "redirect:/admin";
        }
        return "redirect:/console";
    }

    @RequestMapping("/callback")
    public String login(AuthCallback callback,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        AuthRequest authRequest = getAuthRequest();
        AuthResponse<AuthUser> resp = authRequest.login(callback);
        if (resp.ok()) {
            Login login = loginMapper.findOne(
                    loginMapper.query().where.token().eq(resp.getData().getToken().getAccessToken()).end()
            );
            if (login == null) {
                // 用户第一次登录
                login = new Login();
                String newKey = KeyUtils.getKey();
                login.setApiKey(newKey)
                        .setAvatar(resp.getData().getAvatar())
                        .setLocation(resp.getData().getLocation())
                        .setNickname(resp.getData().getNickname())
                        .setToken(resp.getData().getToken().getAccessToken());
                login = loginMapper.save(login);
            }else {
                // 每次登录更新用户信息，防止更换头像等信息
                login.setAvatar(resp.getData().getAvatar())
                        .setNickname(resp.getData().getNickname())
                        .setLocation(resp.getData().getLocation());
                loginMapper.saveOrUpdate(login);
                if (login.getRole() == 1) {
                    request.getSession().setAttribute("login",login);
                    return "redirect:/admin";
                }
            }
            log.info("用户token ---- " + login.getToken());
            log.info("用户昵称 ---- " + login.getNickname());
            log.info(resp.getData().getRawUserInfo().toJSONString());
            request.getSession().setAttribute("login",login);
            return "redirect:/console";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        request.getSession().removeAttribute("login");
        return "redirect:/";
    }

    private AuthRequest getAuthRequest() {
        return new AuthQqRequest(AuthConfig.builder()
                .clientId("101989978")
                .clientSecret("63fe75926012888513873710f0dfc10f")
                .redirectUri("http://www.dankebot.top/callback")
                .build());
    }
}