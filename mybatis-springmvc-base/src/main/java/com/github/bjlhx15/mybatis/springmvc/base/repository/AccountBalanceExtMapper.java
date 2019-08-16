package com.github.bjlhx15.mybatis.springmvc.base.repository;

import com.github.bjlhx15.mybatis.springmvc.base.model.AccountBalanceVo;
import com.github.bjlhx15.mybatis.springmvc.base.model.auto.AccountBalance;
import com.github.bjlhx15.mybatis.springmvc.base.model.auto.AccountBalanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountBalanceExtMapper {
    List<AccountBalanceVo> select(AccountBalanceVo example);
}