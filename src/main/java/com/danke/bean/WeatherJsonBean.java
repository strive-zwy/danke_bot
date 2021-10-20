package com.danke.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zwy
 * @version : 1.0
 * @createTime : 2021/10/20 21:07
 * @Description : TODO
 */
@Data
public class WeatherJsonBean {

    private Integer code;
    private String msg;
    private List<WeatherBean> newslist;

    public String nowWeather(){
        return newslist.get(0).toStr();
    }

    public String tomorrowWeather(){
        return newslist.get(1).toStr();
    }

    public List<String> futureWeather(){
        List<String> weathers = new ArrayList<>();
        weathers.add(newslist.get(1).shortStr());
        weathers.add(newslist.get(2).shortStr());
        weathers.add(newslist.get(3).shortStr());
        return weathers;
    }

    public List<String> future5Weather(){
        List<String> weathers = new ArrayList<>();
        weathers.add(newslist.get(1).shortStr());
        weathers.add(newslist.get(2).shortStr());
        weathers.add(newslist.get(3).shortStr());
        weathers.add(newslist.get(4).shortStr());
        weathers.add(newslist.get(5).shortStr());
        return weathers;
    }


}
