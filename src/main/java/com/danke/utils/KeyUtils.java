package com.danke.utils;

import java.util.UUID;

/**
 * @author zwy
 * @version 1.0
 * @createTime 2021/12/4 21:13
 * @description: TODO
 */
public class KeyUtils {

    // 生成 32 位唯一 key
    public static String getKey(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static void main(String[] args) {
        String k = getKey();
        System.out.println(k);
    }
}
