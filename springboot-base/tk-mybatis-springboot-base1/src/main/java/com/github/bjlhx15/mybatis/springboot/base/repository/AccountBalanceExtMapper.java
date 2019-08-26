package com.github.bjlhx15.mybatis.springboot.base.repository;

import com.github.bjlhx15.mybatis.springboot.base.model.AccountBalanceVo;

import java.util.List;

public interface AccountBalanceExtMapper {
    List<AccountBalanceVo> select(AccountBalanceVo example);
}