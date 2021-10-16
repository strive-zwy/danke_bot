package com.danke.utils;

import com.danke.entity.Task;
import com.danke.enums.TaskTypeEnum;

import java.util.List;

/**
 * @author : zwy
 * @version : 1.0
 * @createTime : 2021/10/16 17:21
 * @Description : TODO
 */
public class TaskStrUtils {

    public static String getTaskInfo(List<Task> list) {
        StringBuilder str = new StringBuilder();
        str.append("您现在共有 ").append(list.size()).append(" 条正在执行的定时任务:\n");
        str.append("id --- 提醒时间");
        StringBuilder onece_str = new StringBuilder();
        int onece = (int) list.stream().filter(privateTask ->
                TaskTypeEnum.TASK_ONCE.getType().equals(privateTask.getType())).count();
        onece_str.append(" \n一次性任务共").append(onece).append("条:");
        StringBuilder everyday_str = new StringBuilder();
        int everyday = (int) list.stream().filter(privateTask ->
                TaskTypeEnum.TASK_EVERYDAY.getType().equals(privateTask.getType())).count();
        everyday_str.append("\n\n每日任务共").append(everyday).append("条:");
        StringBuilder workday_str = new StringBuilder();
        int workday = (int) list.stream().filter(privateTask ->
                TaskTypeEnum.TASK_WORKDAY.getType().equals(privateTask.getType())).count();
        workday_str.append("\n\n工作日任务共").append(workday).append("条:");
        StringBuilder weekend_str = new StringBuilder();
        int weekend = (int) list.stream().filter(privateTask ->
                TaskTypeEnum.TASK_WEEKEND.getType().equals(privateTask.getType())).count();
        weekend_str.append("\n\n周末任务共").append(weekend).append("条:");
        list.forEach(privateTask -> {
            if (TaskTypeEnum.TASK_ONCE.getType().equals(privateTask.getType())) {
                onece_str.append("\n").append(privateTask.getId()).append(" --- ")
                        .append(privateTask.getRemindDate())
                        .append(" ").append(privateTask.getRemindTime());
            }
            if (TaskTypeEnum.TASK_EVERYDAY.getType().equals(privateTask.getType())) {
                everyday_str.append("\n").append(privateTask.getId()).append(" --- ")
                        .append(privateTask.getRemindTime());
            }
            if (TaskTypeEnum.TASK_WORKDAY.getType().equals(privateTask.getType())) {
                workday_str.append("\n").append(privateTask.getId()).append(" --- ")
                        .append(privateTask.getRemindTime());
            }
            if (TaskTypeEnum.TASK_WEEKEND.getType().equals(privateTask.getType())) {
                weekend_str.append("\n").append(privateTask.getId()).append(" --- ")
                        .append(privateTask.getRemindTime());
            }
        });
        str.append(onece_str).append(everyday_str).append(workday_str)
                .append(weekend_str).append("\n\n若想取消某个任务，请发送命令：取消任务+id（比如：取消任务15）");
//        int customize = (int) list.stream().filter(privateTask -> TaskTypeEnum.TASK_CUSTOMIZE.getType().equals(privateTask.getType())).count();
        return str.toString();
    }
}
