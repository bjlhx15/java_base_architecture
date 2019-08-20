package com.github.bjlhx15.mybatis.springmvc.base.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 非泛型示例
 */
public class JSONObjectJsonTest {

    //数据是 对象
    @Test
    public void testparseObject1(){
        Person person = new Person("aa", 1);
        String s1 = JSON.toJSONString(person);
        System.out.println(s1);

        JSONObject p1 = JSON.parseObject(s1);
        System.out.println(p1);
        System.out.println(p1.getString("name"));
        JSONObject p2 =  JSONObject.parseObject(s1);
        System.out.println(p2);
        JSONObject p3 =  JSONArray.parseObject(s1);
        System.out.println(p3);
    }

    //数据是 对象
    @Test
    public void testparseObject2(){
        Person person = new Person("aa", 1);
        String s1 = JSON.toJSONString(person);
        System.out.println(s1);

        Object p1 = JSON.parse(s1);
        System.out.println(p1.getClass());
        System.out.println(p1);

        Object p2 =  JSONObject.parse(s1);
        System.out.println(p2.getClass());
        System.out.println(p2);

        Object p3 =  JSONArray.parse(s1);
        System.out.println(p3.getClass());
        System.out.println(p3);
    }

    //数据是 数组
    @Test
    public void testparseArray1(){
        Person person = new Person("aa", 1);
        List<Person> list =new ArrayList<>();
        list.add(person);

        String s1 = JSON.toJSONString(list);
        System.out.println(s1);

        JSONArray p1 = JSON.parseArray(s1);
        System.out.println(p1);
        JSONArray p2 =  JSONObject.parseArray(s1);
        System.out.println(p2);
        JSONArray p3 =  JSONArray.parseArray(s1);
        System.out.println(p3);
    }

    //数据是 数组
    @Test
    public void testparseArray2(){
        Person person = new Person("aa", 1);
        List<Person> list =new ArrayList<>();
        list.add(person);

        String s1 = JSON.toJSONString(list);
        System.out.println(s1);

        JSONArray p1 = (JSONArray)JSON.parse(s1);
        System.out.println(p1.getClass());
        System.out.println(p1);

        Object p2 =  JSONObject.parse(s1);
        System.out.println(p2.getClass());
        System.out.println(p2);

        Object p3 =  JSONArray.parse(s1);
        System.out.println(p3.getClass());
        System.out.println(p3);
    }
}