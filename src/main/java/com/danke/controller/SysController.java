package com.danke.controller;

import com.danke.entity.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zwy
 * @version 1.0
 * @createTime 2021/12/4 21:46
 * @description:
 *
 */
@Controller
public class SysController {

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


}
