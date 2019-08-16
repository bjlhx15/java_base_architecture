package com.github.bjlhx15.mybatis.springmvc.base.service;

import com.github.bjlhx15.mybatis.springmvc.base.model.AccountBalanceVo;
import com.github.bjlhx15.mybatis.springmvc.base.model.auto.AccountBalance;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IAccountBalanceService {
    PageInfo<List<AccountBalance>> selectByExample(AccountBalance record);
    PageInfo<List<AccountBalanceVo>> select(AccountBalanceVo record);
}
