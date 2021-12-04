package com.danke.listener;

import com.danke.bean.WeatherJsonBean;
import com.danke.utils.OkHttpUtils;
import com.google.gson.Gson;
import love.forte.simbot.annotation.*;
import love.forte.simbot.api.message.events.PrivateMsg;
import love.forte.simbot.api.sender.MsgSender;
import love.forte.simbot.filter.MatchType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : zwy
 * @version : 1.0
 * @createTime : 2021/10/19 21:13
 * @Description : 获取天气信息监听
 */
@Service
public class WeatherListener {

    static String httpUrl = "http://api.tianapi.com/txapi/tianqi/index";
    static Map<String,String> map = new HashMap<>();

    /*
     * 获取当前天气信息
     * {city}天气
     * 比如：郑州天气，郑州市天气
     * */
    @OnPrivate
    @Priority(3)
    @ListenBreak
    @Filter(value = "{{city,[\\s\\S]+}}天气", matchType = MatchType.REGEX_MATCHES)
    @Filter(value = "{{city,[\\s\\S]+}}今日天气", matchType = MatchType.REGEX_MATCHES)
    public void nowWeather(PrivateMsg msg, MsgSender sender,
                                @FilterValue("city") String city){
        map.put("key","0fbe2405cca172ce5115e0a8c8a63834");
        map.put("city",city);
        String w = OkHttpUtils.sendGet(httpUrl,map);
        Gson gson = new Gson();
        WeatherJsonBean weather = gson.fromJson(w, WeatherJsonBean.class);
        sender.SENDER.sendPrivateMsg(msg, weather.nowWeather());
    }
    /*
     * 获取明日天气
     * {city}明日天气
     * 比如：郑州明日天气，郑州市明日天气
     * */
    @OnPrivate
    @Priority(2)
    @ListenBreak
    @Filter(value = "{{city,[\\s\\S]+}}明日天气", matchType = MatchType.REGEX_MATCHES)
    public void tomorrowWeather(PrivateMsg msg, MsgSender sender,
                                @FilterValue("city") String city){
        map.put("key","0fbe2405cca172ce5115e0a8c8a63834");
        map.put("city",city);
        String w = OkHttpUtils.sendGet(httpUrl,map);
        Gson gson = new Gson();
        WeatherJsonBean weather = gson.fromJson(w, WeatherJsonBean.class);
        sender.SENDER.sendPrivateMsg(msg, weather.nowWeather());
    }

    /*
     * 获取未来天气（三天）
     * {city}未来天气
     * 比如：郑州未来天气，郑州市未来天气
     * */
    @OnPrivate
    @Priority(1)
    @ListenBreak
    @Filter(value = "{{city,[\\s\\S]+}}未来天气", matchType = MatchType.REGEX_MATCHES)
    public void futureWeather(PrivateMsg msg, MsgSender sender,
                                @FilterValue("city") String city){
        sender.SENDER.sendPrivateMsg(msg, city+ "未来三天天气请查收~");
        map.put("key","0fbe2405cca172ce5115e0a8c8a63834");
        map.put("city",city);
        String w = OkHttpUtils.sendGet(httpUrl,map);
        Gson gson = new Gson();
        WeatherJsonBean weather = gson.fromJson(w, WeatherJsonBean.class);
        List<String> ws = weather.futureWeather();
        ws.forEach(s -> {
            sender.SENDER.sendPrivateMsg(msg, s);
        });
    }

    /*
     * 获取未来五天天气
     * {city}未来五天天气
     * 比如：郑州未来五天天气，郑州市未来五天天气
     * */
    @OnPrivate
    @Priority(1)
    @ListenBreak
    @Filter(value = "{{city,[\\s\\S]+}}未来五天天气", matchType = MatchType.REGEX_MATCHES)
    public void future5Weather(PrivateMsg msg, MsgSender sender,
                                @FilterValue("city") String city){
        sender.SENDER.sendPrivateMsg(msg, city+ "未来五天天气请查收~");
        map.put("key","0fbe2405cca172ce5115e0a8c8a63834");
        map.put("city",city);
        String w = OkHttpUtils.sendGet(httpUrl,map);
        Gson gson = new Gson();
        WeatherJsonBean weather = gson.fromJson(w, WeatherJsonBean.class);
        List<String> ws = weather.future5Weather();
        ws.forEach(s -> {
            sender.SENDER.sendPrivateMsg(msg, s);
        });
    }

}
