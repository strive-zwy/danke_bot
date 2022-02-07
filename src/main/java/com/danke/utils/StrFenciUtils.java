package com.danke.utils;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.Analyzer;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zwy
 * @version 1.0
 * @createTime 2021/11/30 14:48
 * @description: 语句分词工具类 - IK分词器
 */
public class StrFenciUtils {

    public static List<String> fenci(String str) throws Exception{
        List<String> strings = new ArrayList<>();
        //创建分词对象
        Analyzer anal=new IKAnalyzer(true);
        StringReader reader=new StringReader(str);
        //分词
        TokenStream ts=anal.tokenStream("", reader);
        CharTermAttribute term=ts.getAttribute(CharTermAttribute.class);
        ts.reset();
        //遍历分词数据
        while(ts.incrementToken()){
            strings.add(term.toString());
            }
        reader.close();
        return strings;
    }
}
