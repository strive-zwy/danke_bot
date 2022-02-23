package com.danke.bean;

import lombok.Data;

/**
 * @author zwy
 * @version 1.0
 * @createTime 2022/2/23 19:05
 * @description: TODO
 */
@Data
public class UserUpdateBean {

    private Long id;
    private int canAddQqNum;
    private int canAddGroupNum;
    private int role;

}
