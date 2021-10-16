package com.danke.enums;

/**
 * @author : zwy
 * @version : 1.0
 * @createTime : 2021/9/30 21:24
 * @Description : 任务状态枚举
 */
public enum TaskStateEnum {

    TASK_EXECUTE(0,"任务正常执行"),
    TASK_CANCELED(1,"任务已取消");

    private Integer state;
    private String des;

    public Integer getState() {
        return state;
    }

    public String getDes() {
        return des;
    }

    TaskStateEnum(Integer state, String des) {
        this.state = state;
        this.des = des;
    }

    public static String desOfState(int state){
        for (TaskStateEnum u : TaskStateEnum.values()) {
            if (u.getState() == state){
                return u.getDes();
            }
        }
        return "";
    }
}
