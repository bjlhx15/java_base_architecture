package com.github.bjlhx15.mybatis.springboot.base.controller;

import com.github.bjlhx15.mybatis.springboot.base.common.ResponseResultBody;
import com.github.bjlhx15.mybatis.springboot.base.common.ResultException;
import com.github.bjlhx15.mybatis.springboot.base.entity.Result;
import com.github.bjlhx15.mybatis.springboot.base.entity.ResultStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/response2")
@ResponseResultBody
public class EgResponseController02 {
    private static final HashMap<String, Object> INFO;

    static {
        INFO = new HashMap<String, Object>();
        INFO.put("name", "galaxy");
        INFO.put("age", "70");
    }

    @GetMapping("hello")
    public HashMap<String, Object> hello() {
        return INFO;
    }

    /**
     * 测试重复包裹
     */
    @GetMapping("result")
    public Result<Map<String, Object>> helloResult() {
        return Result.success(INFO);
    }

    @GetMapping("helloError")
    public HashMap<String, Object> helloError() throws Exception {
        throw new Exception("helloError");
    }
    @GetMapping("helloMyError")
    public HashMap<String, Object> helloMyError() throws Exception {
        throw new ResultException(ResultStatus.INTERNAL_ERROR);
    }
}