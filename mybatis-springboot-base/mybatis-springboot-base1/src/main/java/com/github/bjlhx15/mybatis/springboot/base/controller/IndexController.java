package com.github.bjlhx15.mybatis.springboot.base.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "主页控制层")
@RestController
@RequestMapping("/")
public class IndexController {

    @ApiOperation(value = "根据用户名获取用户对象", httpMethod = "GET", response = String.class,
            notes = "根据用户名获取用户对象1")
    @RequestMapping(value = "test",method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        return "ok";
    }
    @RequestMapping(value = "test2",method = RequestMethod.GET)
    @ResponseBody
    public Object test2() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "2000");
        return map;
    }
}
