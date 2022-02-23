package com.danke.controller;

import com.danke.entity.Login;
import com.danke.mapper.ApiMessageMapper;
import com.danke.mapper.GroupInfoMapper;
import com.danke.mapper.LoginMapper;
import com.danke.mapper.QqInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zwy
 * @version 1.0
 * @createTime 2021/12/4 21:46
 * @description: 页面控制
 *
 */
@Controller
public class SysController {

    @Qualifier("loginMapper")
    @Autowired
    private LoginMapper loginMapper;

    @Qualifier("apiMessageMapper")
    @Autowired
    private ApiMessageMapper apiMessageMapper;

    @Qualifier("qqInfoMapper")
    @Autowired
    private QqInfoMapper qqInfoMapper;

    @Qualifier("groupInfoMapper")
    @Autowired
    private GroupInfoMapper groupInfoMapper;

    @RequestMapping("/")
    public String indexPage(HttpServletRequest request){
        return "index";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        Login login = (Login)request.getSession().getAttribute("login");
        if (login == null) {
            return "login";
        }
        if (login.getRole() == 1) {
            return "admin";
        }
        return "console";
    }

    @RequestMapping("/readDoc")
    public String readDoc(HttpServletRequest request){
        return "readDoc";
    }

    @RequestMapping("/console")
    public String console(HttpServletRequest request, Model model){
        Login login = (Login)request.getSession().getAttribute("login");
        if (login == null) {
            model.addAttribute("error","没有登录，请先登录！");
            return "redirect:/login";
        }
        return "console";
    }

    @RequestMapping("/toUse")
    public String toUse(HttpServletRequest request){
        Login login = (Login) request.getSession().getAttribute("login");
        if (login == null) {
            return "index";
        }
        return "redirect:/console";
    }

    @RequestMapping("/admin")
    public String admin(HttpServletRequest request){
        Login login = (Login) request.getSession().getAttribute("login");
        if (login == null || login.getRole() != 1) {
            return "index";
        }
        return "admin";
    }


}
