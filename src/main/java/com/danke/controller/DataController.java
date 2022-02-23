package com.danke.controller;

import com.danke.bean.*;
import com.danke.entity.*;
import com.danke.enums.POrGEnum;
import com.danke.exception.ResultBody;
import com.danke.mapper.*;
import com.danke.wrapper.ApiMessageQuery;
import com.danke.wrapper.LoginQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zwy
 * @version 1.0
 * @createTime 2022/1/5 19:56
 * @description: 数据获取接口
 */
@Controller
@RequestMapping("/data")
public class DataController {

    @Qualifier("qqInfoMapper")
    @Autowired
    private QqInfoMapper qqInfoMapper;

    @Qualifier("groupInfoMapper")
    @Autowired
    private GroupInfoMapper groupInfoMapper;

    @Qualifier("keywordMapper")
    @Autowired
    private KeywordMapper keywordMapper;

    @Qualifier("taskMapper")
    @Autowired
    private TaskMapper taskMapper;

    @Qualifier("loginMapper")
    @Autowired
    private LoginMapper loginMapper;

    @Qualifier("apiMessageMapper")
    @Autowired
    private ApiMessageMapper apiMessageMapper;

    @RequestMapping(value = "/getqq", method = RequestMethod.GET)
    @ResponseBody
    public ResultBody getqq(HttpServletRequest request){
        Login login = (Login)request.getSession().getAttribute("login");
        List<QqInfo> qqInfos = qqInfoMapper.listEntity(
                qqInfoMapper.query().where.adder().eq(login.getId()).end()
        );
        List<GroupInfo> groupInfos = groupInfoMapper.listEntity(
                groupInfoMapper.query().where.adder().eq(login.getId()).end()
        );
        List<QQBean> list = new ArrayList<>();
        qqInfos.forEach(qqInfo -> {
            QQBean qqBean = new QQBean();
            BeanUtils.copyProperties(qqInfo,qqBean);
            qqBean.setQq(qqInfo.getQqNumber());
            qqBean.setType(1);
            list.add(qqBean);
        });
        groupInfos.forEach(groupInfo -> {
            QQBean qqBean = new QQBean();
            BeanUtils.copyProperties(groupInfo,qqBean);
            qqBean.setQq(groupInfo.getGroupNumber());
            qqBean.setType(2);
            list.add(qqBean);
        });
        return ResultBody.success(list);
    }

    @RequestMapping(value = "/getKlist", method = RequestMethod.GET)
    @ResponseBody
    public ResultBody getKlist(HttpServletRequest request,
                               @RequestParam Long id){
        List<Keyword> klist = keywordMapper.listEntity(
                keywordMapper.query().where.groupId().eq(id).end()
        );
        return ResultBody.success(klist);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultBody add(@RequestBody AddBean addBean, HttpServletRequest request){
        Login login = (Login)request.getSession().getAttribute("login");
        int num = 0;
        if (login == null) {
            return ResultBody.error("-1","用户未登录");
        }
        if (addBean.getType() == 1) {
            if (login.getCanAddQqNum() > 0) {
                QqInfo qq = new QqInfo();
                qq.setAdder(login.getId())
                        .setAvatar("http://q1.qlogo.cn/g?b=qq&nk=" + addBean.getQq() + "&s=100")
                        .setDescription(addBean.getDescription())
                        .setQqNumber(addBean.getQq());
                qqInfoMapper.save(qq);
                num = login.getCanAddQqNum() - 1;
                login.setCanAddQqNum(num);
            }else {
                return ResultBody.error("-1","您还可以添加的QQ数量为：0");
            }
        }else if (addBean.getType() == 2){
            if (login.getCanAddGroupNum() > 0) {
                GroupInfo groupInfo = new GroupInfo();
                groupInfo.setAdder(login.getId())
                        .setAvatar("http://p.qlogo.cn/gh/" + addBean.getQq() + "/" + addBean.getQq() + "/0")
                        .setDescription(addBean.getDescription())
                        .setGroupNumber(addBean.getQq());
                groupInfoMapper.save(groupInfo);
                num = login.getCanAddGroupNum() - 1;
                login.setCanAddGroupNum(num);
            }else {
                return ResultBody.error("-1","您还可以添加的QQ数量为：0");
            }
        }else {
            Keyword keyword = new Keyword();
            keyword.setKw(addBean.getDescription())
                    .setGroupId(addBean.getQq())
                    .setState(1);
            keywordMapper.save(keyword);
        }
        loginMapper.saveOrUpdate(login);
        request.getSession().setAttribute("login",login);
        return ResultBody.success(num);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultBody delete(@RequestBody DeleteBean deleteBean, HttpServletRequest request){
        Login login = (Login)request.getSession().getAttribute("login");
        int num = 0;
        if (login == null) {
            return ResultBody.error("-1","用户未登录");
        }
        if (deleteBean.getType() == 1) {
            qqInfoMapper.deleteById(deleteBean.getId());
            taskMapper.delete(
                    taskMapper.defaultQuery().where.creatorId().eq(deleteBean.getId())
                    .and.pOrG().eq(POrGEnum.USER_TASK.getType()).end()
            );
            num = login.getCanAddQqNum() + 1;
            login.setCanAddQqNum(num);
        }else if (deleteBean.getType() == 2){
            groupInfoMapper.deleteById(deleteBean.getId());
            keywordMapper.delete(
                    keywordMapper.defaultQuery().where.groupId().eq(deleteBean.getId()).end()
            );
            taskMapper.delete(
                    taskMapper.defaultQuery().where.creatorId().eq(deleteBean.getId())
                            .and.pOrG().eq(POrGEnum.GROUP_TASK.getType()).end()
            );
            num = login.getCanAddGroupNum() + 1;
            login.setCanAddGroupNum(num);
        }else {
            keywordMapper.deleteById(deleteBean.getId());
        }
        loginMapper.saveOrUpdate(login);
        request.getSession().setAttribute("login",login);
        return ResultBody.success(num);
    }

    @RequestMapping(value = "/updateKwState", method = RequestMethod.POST)
    @ResponseBody
    public ResultBody updateKwState(@Validated Long id, HttpServletRequest request){
        Login login = (Login)request.getSession().getAttribute("login");
        if (login == null) {
            return ResultBody.error("-1","用户未登录");
        }
        Keyword kw = keywordMapper.findById(id);
        if (kw.getState() == 1){
            kw.setState(0);
        }else {
            kw.setState(1);
        }
        keywordMapper.saveOrUpdate(kw);
        return ResultBody.success();
    }

    @RequestMapping(value = "/updateMonistor", method = RequestMethod.POST)
    @ResponseBody
    public ResultBody openMonistor(@Validated Long id, HttpServletRequest request){
        Login login = (Login)request.getSession().getAttribute("login");
        if (login == null) {
            return ResultBody.error("-1","用户未登录");
        }
        GroupInfo g = groupInfoMapper.findById(id);
        int currMonistor;
        if (g.getIsMonitor() == 0) {
            currMonistor = 1;
        }else {
            currMonistor = 0;
        }
        g.setIsMonitor(currMonistor);
        groupInfoMapper.saveOrUpdate(g);
        return ResultBody.success(currMonistor);
    }

    @RequestMapping(value = "/getIndex", method = RequestMethod.GET)
    @ResponseBody
    public ResultBody getIndex(HttpServletRequest request){
        int loginCount = loginMapper.count(
                loginMapper.query().select.count.end()
        );
        int apiCount = apiMessageMapper.count(
                apiMessageMapper.query().select.count.end()
        );
        int qqCount = qqInfoMapper.count(
                qqInfoMapper.query().select.count.end()
        );
        int groupCount = groupInfoMapper.count(
                groupInfoMapper.query().select.count.end()
        );
        Map<String,Integer> map = new HashMap<>();
        map.put("loginCount",loginCount);
        map.put("apiCount",apiCount);
        map.put("qqCount",qqCount);
        map.put("groupCount",groupCount);
        return ResultBody.success(map);
    }

    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    @ResponseBody
    public ResultBody getUserList(HttpServletRequest request,
                                  @RequestParam(name = "page" , defaultValue = "1") Integer page,
                                  @RequestParam(name = "size" , defaultValue = "5") Integer size,
                                  @RequestParam(name = "search" , required = false) String search,
                                  @RequestParam(name = "role" , required = false) Integer role){
        int offset = size * (page - 1);
        LoginQuery loginQuery = loginMapper.query().orderBy.id().asc().end();
        search = search.trim();
        if (StringUtils.isNoneBlank(search)) {
            loginQuery.where.nickname().like(search).end();
        }
        if (role != 9) {
            loginQuery.where.role().eq(role).end();
        }
        Integer userTotleCount = loginMapper.count(loginQuery);
        List<Login> userList = loginMapper.listEntity(
                loginQuery.limit(offset,size)
        );
        PageBean<Login> userPage = new PageBean<>();
        userPage.setList(userList);
        userPage.setPagination(userTotleCount,page,size);
        Map<String,Object> map = new HashMap<>();
        map.put("userPage",userPage);
        map.put("search",search);
        map.put("role",role);
        return ResultBody.success(map);
    }

    @RequestMapping(value = "/getApiList", method = RequestMethod.GET)
    @ResponseBody
    public ResultBody getApiList(HttpServletRequest request,
                                 @RequestParam(name = "page" , defaultValue = "1") Integer page,
                                 @RequestParam(name = "size" , defaultValue = "5") Integer size,
                                 @RequestParam(name = "search" , required = false) String search){
        int offset = size * (page - 1);
        ApiMessageQuery apiMessageQuery = apiMessageMapper.query().orderBy.gmtCreate().desc().end();
        if (StringUtils.isNoneBlank(search)) {
            apiMessageQuery.where.receiver().eq(search).or.msg().like(search).end();
        }
        Integer apiTotleCount = apiMessageMapper.count(apiMessageQuery);
        List<ApiMessage> apiList = apiMessageMapper.listEntity(
                apiMessageQuery.limit(offset,size)
        );
        PageBean<ApiMessage> apiPage = new PageBean<>();
        apiPage.setList(apiList);
        apiPage.setPagination(apiTotleCount,page,size);
        Map<String,Object> map = new HashMap<>();
        map.put("apiPage",apiPage);
        map.put("search",search);
        return ResultBody.success(map);
    }

    @RequestMapping(value = "/toUpdateUser", method = RequestMethod.POST)
    @ResponseBody
    public ResultBody toUpdateUser(@RequestBody UserUpdateBean userUpdateBean, HttpServletRequest request){
        Login login = loginMapper.findById(userUpdateBean.getId());
        login.setCanAddGroupNum(userUpdateBean.getCanAddGroupNum())
                .setCanAddQqNum(userUpdateBean.getCanAddQqNum())
                .setRole(userUpdateBean.getRole());
        loginMapper.saveOrUpdate(login);
        return ResultBody.success();
    }

}
