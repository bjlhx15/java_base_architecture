package com.github.bjlhx15.mybatis.springmvc.base.controller;

import com.github.bjlhx15.mybatis.springmvc.base.model.AccountBalanceVo;
import com.github.bjlhx15.mybatis.springmvc.base.model.auto.AccountBalance;
import com.github.bjlhx15.mybatis.springmvc.base.service.IAccountBalanceService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IAccountBalanceService service;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity test() {
        PageInfo<List<AccountBalance>> listPageInfo = service.selectByExample(null);
        return ResponseEntity.ok(listPageInfo);
    }

    @RequestMapping(value = "listvo", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity testvo() {
        PageInfo<List<AccountBalanceVo>> listPageInfo = service.select(null);
        return ResponseEntity.ok(listPageInfo);
    }
}
