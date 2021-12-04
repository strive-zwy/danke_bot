package com.danke.bean;

import lombok.Data;

import java.util.List;

/**
 * @author zwy
 * @version 1.0
 * @createTime 2021/11/30 16:20
 * @description: 获取机器人回复 json 数据
 */
@Data
public class RobotReplyJsonBean {

    private Integer code;
    private String msg;
    private List<RobotReplyBean> newslist;
}
