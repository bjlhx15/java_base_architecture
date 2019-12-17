package com.github.bjlhx15.mybatis.springboot.base.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.bjlhx15.mybatis.springboot.base.entity.auto.AccountBalance;
import com.github.bjlhx15.mybatis.springboot.base.service.IAccountBalanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 账户金额 前端控制器
 * </p>
 *
 * @author lihongxu
 * @since 2019-08-26
 */

@Api(tags="用户控制")
@RestController
@RequestMapping("/base/account-balance")
public class AccountBalanceController {

    @Autowired
    IAccountBalanceService accountBalanceService;


    @ApiOperation(value = "用户列表", httpMethod = "GET",
            notes = "用户列表-分页")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<IPage<AccountBalance>> test() {

        IPage<AccountBalance> page=new Page<>(1,2);

        IPage<AccountBalance> iPage = accountBalanceService.page(page);
        return ResponseEntity.ok(iPage);
    }
}
