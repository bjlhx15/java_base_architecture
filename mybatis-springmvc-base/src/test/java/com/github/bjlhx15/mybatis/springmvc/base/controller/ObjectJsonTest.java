package com.github.bjlhx15.mybatis.springmvc.base.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.List;

/**
 * 数据是 对象 测试
 */
public class ObjectJsonTest {

    @Test
    public void testtoJSONString(){
        Person person = new Person("aa", 1);
        String s1 = JSON.toJSONString(person);
        System.out.println(s1);
        String s2 = JSONObject.toJSONString(person);
        System.out.println(s2);
        String s3 = JSONArray.toJSONString(person);
        System.out.println(s3);
    }

    @Test
    public void testparseObject(){
        Person person = new Person("aa", 1);
        String s1 = JSON.toJSONString(person);
        System.out.println(s1);

        Person p1 = JSON.parseObject(s1, Person.class);
        JSONObject jsonObject = JSON.parseObject(s1);
        System.out.println(p1);
        Person p2 =  JSONObject.parseObject(s1, Person.class);
        System.out.println(p2);
        Person p3 =  JSONArray.parseObject(s1, Person.class);
        System.out.println(p3);
    }

    /**
     * 失败
     */
    @Test
    public void testparseArray(){
        Person person = new Person("aa", 1);
        String s1 = JSON.toJSONString(person);
        System.out.println(s1);

        //失败：转数组 必须是 list
        List<Person> p1 = JSON.parseArray(s1, Person.class);
        System.out.println(p1);
        //失败：转数组 必须是 list
        List<Person> p2 =  JSONObject.parseArray(s1, Person.class);
        System.out.println(p2);
        //失败：转数组 必须是 list
        List<Person> p3 =  JSONArray.parseArray(s1, Person.class);
        System.out.println(p3);
    }
}