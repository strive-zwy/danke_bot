package com.danke.bean;

import lombok.Data;

/**
 * @author zwy
 * @version 1.0
 * @createTime 2022/1/7 16:55
 * @description: TODO
 */
@Data
public class QQBean {

    private Long id;
    private Long qq;
    private String avatar;
    private String description;
    private Integer type;
    private Integer isMonitor;
}
