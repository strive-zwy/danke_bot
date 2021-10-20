package com.danke.bean;

import lombok.Data;

/**
 * @author : zwy
 * @version : 1.0
 * @createTime : 2021/10/20 21:08
 * @Description : TODO
 */
@Data
public class WeatherBean {

    private String area;
    private String date;
    private String week;
    private String weather;
    private String real;
    private String lowest;
    private String highest;
    private String wind;
    private String windspeed;
    private String windsc;
    private String sunrise;
    private String sunset;
    private String moonrise;
    private String moondown;
    private String pcpn;
    private String pop;
    private String uv_index;

    private String tips;

    public String toStr() {
        return area + date + "（"+ week +"）天气：" +
                weather + '\n' +
                "当前温度：" + real + '\n' +
                "最低温度：" + lowest + '\n' +
                "最高温度：" + highest + '\n' +
                "风向：" + wind + '\n' +
                "风速：" + windspeed + '\n' +
                "风力：" + windsc + '\n' +
                "日出时间：" + sunrise + '\n' +
                "日落时间：" + sunset + '\n' +
                "月升时间：" + moonrise + '\n' +
                "月落时间：" + moondown + '\n' +
                "降雨量：" + pcpn + '\n' +
                "降雨概率：" + pop + '\n' +
                "紫外线强度指数：" + uv_index + '\n' +
                "tips：" + tips;
    }

    public String shortStr(){
        return area + date + "（"+ week +"）天气：" +
                weather + '\n' +
                "最低温度：" + lowest + '\n' +
                "最高温度：" + highest + '\n' +
                "风向：" + wind + '\n' +
                "风力：" + windsc + '\n' +
                "降雨概率：" + pop + '\n';
    }
}
