package com.github.bjlhx15.mybatis.springboot.base.service.impl;

import com.github.bjlhx15.mybatis.springboot.base.model.AccountBalanceVo;
import com.github.bjlhx15.mybatis.springboot.base.model.auto.AccountBalance;
import com.github.bjlhx15.mybatis.springboot.base.repository.AccountBalanceExtMapper;
import com.github.bjlhx15.mybatis.springboot.base.repository.auto.AccountBalanceMapper;
import com.github.bjlhx15.mybatis.springboot.base.service.IAccountBalanceService;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class AccountBalanceServiceImpl implements IAccountBalanceService {
    @Autowired
    private AccountBalanceMapper mapper;
    @Autowired
    private AccountBalanceExtMapper extMapper;

    @Override
    public PageInfo<List<AccountBalance>> selectByExample(AccountBalance record) {
        Example example = new Example(AccountBalance.class);

        example.createCriteria()
                .andBetween("balance", 10, 1000)//andBetween 映射的实体类。和传统的条件查询类似。
                .andCondition("version=", 2);//andBetween 数据库的字段。即where后面的条件。应该写sql语句。

        PageHelper.startPage(1, 1);
        List<AccountBalance> balanceList = mapper.selectByExample(example);

        PageInfo<List<AccountBalance>> a = new PageInfo(balanceList);


        return a;
    }

    @Override
    public PageInfo<List<AccountBalanceVo>> select(AccountBalanceVo record) {
        PageHelper.startPage(1, 2);
        List<AccountBalanceVo> balanceList = extMapper.select(record);
        PageInfo<List<AccountBalanceVo>> a = new PageInfo(balanceList);
        return a;
    }
}
