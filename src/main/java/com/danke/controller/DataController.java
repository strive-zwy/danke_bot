package com.danke.controller;

import com.danke.bean.AddBean;
import com.danke.bean.DeleteBean;
import com.danke.bean.QQBean;
import com.danke.entity.GroupInfo;
import com.danke.entity.Keyword;
import com.danke.entity.Login;
import com.danke.entity.QqInfo;
import com.danke.enums.POrGEnum;
import com.danke.exception.DataBody;
import com.danke.exception.ResultBody;
import com.danke.mapper.GroupInfoMapper;
import com.danke.mapper.KeywordMapper;
import com.danke.mapper.QqInfoMapper;
import com.danke.mapper.TaskMapper;
import com.danke.utils.KeyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zwy
 * @version 1.0
 * @createTime 2022/1/5 19:56
 * @description: TODO
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
        if (login == null) {
            return ResultBody.error("-1","用户未登录");
        }
        if (addBean.getType() == 1) {
            QqInfo qq = new QqInfo();
            qq.setAdder(login.getId())
                    .setAvatar("http://q1.qlogo.cn/g?b=qq&nk=" + addBean.getQq() + "&s=100")
                    .setDescription(addBean.getDescription())
                    .setQqNumber(addBean.getQq());
            qqInfoMapper.save(qq);
        }else if (addBean.getType() == 2){
            GroupInfo groupInfo = new GroupInfo();
            groupInfo.setAdder(login.getId())
                    .setAvatar("http://p.qlogo.cn/gh/" + addBean.getQq() + "/" + addBean.getQq() + "/0")
                    .setDescription(addBean.getDescription())
                    .setGroupNumber(addBean.getQq());
            groupInfoMapper.save(groupInfo);
        }else {
            Keyword keyword = new Keyword();
            keyword.setKw(addBean.getDescription())
                    .setGroupId(addBean.getQq())
                    .setState(1);
            keywordMapper.save(keyword);
        }
        return ResultBody.success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultBody delete(@RequestBody DeleteBean deleteBean, HttpServletRequest request){
        Login login = (Login)request.getSession().getAttribute("login");
        if (login == null) {
            return ResultBody.error("-1","用户未登录");
        }
        if (deleteBean.getType() == 1) {
            qqInfoMapper.deleteById(deleteBean.getId());
            taskMapper.delete(
                    taskMapper.defaultQuery().where.creatorId().eq(deleteBean.getId())
                    .and.pOrG().eq(POrGEnum.USER_TASK.getType()).end()
            );
        }else if (deleteBean.getType() == 2){
            groupInfoMapper.deleteById(deleteBean.getId());
            keywordMapper.delete(
                    keywordMapper.defaultQuery().where.groupId().eq(deleteBean.getId()).end()
            );
            taskMapper.delete(
                    taskMapper.defaultQuery().where.creatorId().eq(deleteBean.getId())
                            .and.pOrG().eq(POrGEnum.GROUP_TASK.getType()).end()
            );
        }else {
            keywordMapper.deleteById(deleteBean.getId());
        }
        return ResultBody.success();
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

}
