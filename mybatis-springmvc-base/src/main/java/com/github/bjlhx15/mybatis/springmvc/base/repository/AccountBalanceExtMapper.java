package com.github.bjlhx15.mybatis.springmvc.base.repository;

import com.github.bjlhx15.mybatis.springmvc.base.model.AccountBalanceVo;

import java.util.List;

public interface AccountBalanceExtMapper {
    List<AccountBalanceVo> select(AccountBalanceVo example);
}