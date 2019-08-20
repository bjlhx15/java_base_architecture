package com.github.bjlhx15.mybatis.springmvc.base.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
/**
 * 数据是 数组 测试
 */
public class ArrayJsonTest {

    @Test
    public void testtoJSONString(){
        Person person = new Person("aa", 1);
        List<Person> list =new ArrayList<>();
        list.add(person);

        String s1 = JSON.toJSONString(list);
        System.out.println(s1);
        String s2 = JSONObject.toJSONString(list);
        System.out.println(s2);
        String s3 = JSONArray.toJSONString(list);
        System.out.println(s3);
    }

    //失败
    @Test
    public void testparseObject(){
        Person person = new Person("aa", 1);
        List<Person> list =new ArrayList<>();
        list.add(person);

        String s1 = JSON.toJSONString(list);
        System.out.println(s1);

        //失败：转对象 必须是 {}
        Person p1 = JSON.parseObject(s1, Person.class);
        System.out.println(p1);
        //失败：转对象 必须是 {}
        Person p2 =  JSONObject.parseObject(s1, Person.class);
        System.out.println(p2);
        //失败：转对象 必须是 {}
        Person p3 =  JSONArray.parseObject(s1, Person.class);
        System.out.println(p3);
    }

    @Test
    public void testparseArray(){
        Person person = new Person("aa", 1);
        List<Person> list =new ArrayList<>();
        list.add(person);

        String s1 = JSON.toJSONString(list);
        System.out.println(s1);

        List<Person> p1 = JSON.parseArray(s1, Person.class);
        System.out.println(p1);
        List<Person> p2 =  JSONObject.parseArray(s1, Person.class);
        System.out.println(p2);
        List<Person> p3 =  JSONArray.parseArray(s1, Person.class);
        System.out.println(p3);
    }
}