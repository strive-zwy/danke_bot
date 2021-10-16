package com.danke.enums;

/**
 * @author : zwy
 * @version : 1.0
 * @createTime : 2021/10/11 19:26
 * @Description : 任务类型枚举
 */
public enum TaskTypeEnum {

    TASK_ONCE(0,"一次性任务"),
    TASK_EVERYDAY(1,"每日任务"),
    TASK_WORKDAY(2,"工作日任务"),
    TASK_WEEKEND(3,"周末任务"),
    TASK_CUSTOMIZE(4,"每周自定义任务");

    private Integer type;
    private String des;

    public Integer getType() {
        return type;
    }

    public String getDes() {
        return des;
    }


    TaskTypeEnum(Integer type, String des) {
        this.type = type;
        this.des = des;
    }

    public static String desOfType(int state){
        for (TaskStateEnum u : TaskStateEnum.values()) {
            if (u.getState() == state){
                return u.getDes();
            }
        }
        return "";
    }
}
