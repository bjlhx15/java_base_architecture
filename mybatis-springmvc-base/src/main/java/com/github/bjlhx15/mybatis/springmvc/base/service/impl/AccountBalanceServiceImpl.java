package com.github.bjlhx15.mybatis.springmvc.base.service.impl;

import com.github.bjlhx15.mybatis.springmvc.base.model.AccountBalanceVo;
import com.github.bjlhx15.mybatis.springmvc.base.model.auto.AccountBalance;
import com.github.bjlhx15.mybatis.springmvc.base.model.auto.AccountBalanceExample;
import com.github.bjlhx15.mybatis.springmvc.base.repository.AccountBalanceExtMapper;
import com.github.bjlhx15.mybatis.springmvc.base.repository.auto.AccountBalanceMapper;
import com.github.bjlhx15.mybatis.springmvc.base.service.IAccountBalanceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountBalanceServiceImpl implements IAccountBalanceService {
    @Autowired
    private AccountBalanceMapper mapper;
    @Autowired
    private AccountBalanceExtMapper extMapper;

    @Override
    public PageInfo<List<AccountBalance>> selectByExample(AccountBalance record) {
        AccountBalanceExample example = new AccountBalanceExample();
        AccountBalanceExample.Criteria criteria = example.createCriteria();
        if (null != record) {
            if (record.getBalance() != null)
                criteria.andBalanceBetween(10, 1000);
        }
        PageHelper.startPage(1,2);
        List<AccountBalance> balanceList = mapper.selectByExample(example);
        PageInfo<List<AccountBalance>> a=new PageInfo(balanceList);
        return a;
    }

    @Override
    public PageInfo<List<AccountBalanceVo>> select(AccountBalanceVo record) {
        PageHelper.startPage(1,2);
        List<AccountBalanceVo> balanceList = extMapper.select(record);
        PageInfo<List<AccountBalanceVo>> a=new PageInfo(balanceList);
        return a;
    }
}
