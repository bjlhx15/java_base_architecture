package com.github.bjlhx15.mybatis.springmvc.base.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class JsonToLIstMapTest {
    String strArr = "[{\"0\":\"zhangsan\",\"1\":\"lisi\",\"2\":\"wangwu\",\"3\":\"maliu\"}," +
            "{\"00\":\"zhangsan\",\"11\":\"lisi\",\"22\":\"wangwu\",\"33\":\"maliu\"}]";
    @Test
    public void test(){
        //第一种方式
        List<Map<String,String>> listObjectFir = (List<Map<String,String>>) JSONArray.parse(strArr);
        System.out.println("1、利用JSONArray中的parse方法来解析json数组字符串");//com.alibaba.fastjson.JSONObject
        System.out.println("类型："+listObjectFir.get(0).getClass());
        for(Map<String,String> mapList : listObjectFir){
            for (Map.Entry entry : mapList.entrySet()){
                System.out.println( entry.getKey()  + "  " +entry.getValue());
            }
        }
        //第二种方式
        List<Map<String,String>> listObjectSec = JSONArray.parseObject(strArr,List.class);
        System.out.println("2、利用JSONArray中的parseObject方法并指定返回类型来解析json数组字符串");//com.alibaba.fastjson.JSONObject
        System.out.println("类型："+listObjectSec.get(0).getClass());
        for(Map<String,String> mapList : listObjectSec){
            for (Map.Entry entry : mapList.entrySet()){
                System.out.println( entry.getKey()  + "  " +entry.getValue());
            }
        }
        //第三种方式
        JSONArray listObjectThir = JSONArray.parseArray(strArr);
        System.out.println("3、利用JSONArray中的parseArray方法来解析json数组字符串");
        System.out.println("类型："+listObjectThir.get(0).getClass());//com.alibaba.fastjson.JSONObject
        for(Object mapList : listObjectThir){
            for (Object entry : ((Map)mapList).entrySet()){
                System.out.println(((Map.Entry)entry).getKey()  + "  " +((Map.Entry)entry).getValue());
            }
        }
        //第四种方式
        List listObjectFour = JSONArray.parseArray(strArr,Map.class);
        System.out.println("4、利用JSONArray中的parseArray方法并指定返回类型来解析json数组字符串");
        System.out.println("类型："+listObjectFour.get(0).getClass());//java.util.HashMap
        for(Object mapList : listObjectFour){
            for (Object entry : ((Map)mapList).entrySet()){
                System.out.println(((Map.Entry)entry).getKey()  + "  " +((Map.Entry)entry).getValue());
            }
        }
        //第五种方式
        JSONArray listObjectFifth = JSONObject.parseArray(strArr);
        System.out.println("5、利用JSONObject中的parseArray方法来解析json数组字符串");
        System.out.println("类型："+listObjectFifth.get(0).getClass());//com.alibaba.fastjson.JSONObject
        for(Object mapList : listObjectFifth){
            for (Object entry : ((Map)mapList).entrySet()){
                System.out.println(((Map.Entry)entry).getKey()  + "  " +((Map.Entry)entry).getValue());
            }
        }
        //第六种方式
        List listObjectSix = JSONObject.parseArray(strArr,Map.class);
        System.out.println("6、利用JSONObject中的parseArray方法并指定返回类型来解析json数组字符串");
        System.out.println("类型："+listObjectSix.get(0).getClass());//java.util.HashMap
        for(Object mapList : listObjectSix){
            for (Object entry : ((Map)mapList).entrySet()){
                System.out.println(((Map.Entry)entry).getKey()  + "  " +((Map.Entry)entry).getValue());
            }
        }
        //第七种方式
        JSONArray listObjectSeven = JSON.parseArray(strArr);
        System.out.println("7、利用JSON中的parseArray方法来解析json数组字符串");
        System.out.println("类型："+listObjectSeven.get(0).getClass());//com.alibaba.fastjson.JSONObject
        for(Object mapList : listObjectSeven){
            for (Object entry : ((Map)mapList).entrySet()){
                System.out.println(((Map.Entry)entry).getKey()  + "  " +((Map.Entry)entry).getValue());
            }
        }
        //第八种方式
        List listObjectEigh = JSONObject.parseArray(strArr,Map.class);
        System.out.println("8、利用JSON中的parseArray方法并指定返回类型来解析json数组字符串");
        System.out.println("类型："+listObjectEigh.get(0).getClass());//java.util.HashMap
        for(Object mapList : listObjectEigh){
            for (Object entry : ((Map)mapList).entrySet()){
                System.out.println(((Map.Entry)entry).getKey()  + "  " +((Map.Entry)entry).getValue());
            }
        }
    }
}