package com.danke.enums;

/**
 * @author : zwy
 * @version : 1.0
 * @createTime : 2021/10/16 16:11
 * @Description : TODO
 */
public enum POrGEnum {

    USER_TASK(0,"个人任务"),
    GROUP_TASK(1,"群任务");

    private Integer type;
    private String des;

    public Integer getType() {
        return type;
    }

    public String getDes() {
        return des;
    }

    POrGEnum(Integer type, String des) {
        this.type = type;
        this.des = des;
    }

    public static String desOfState(int type){
        for (POrGEnum p : POrGEnum.values()) {
            if (p.type == type){
                return p.getDes();
            }
        }
        return "";
    }

}
