package com.github.bjlhx15.mybatis.springboot.base.service.impl;

import com.github.bjlhx15.mybatis.springboot.base.model.AccountBalanceVo;
import com.github.bjlhx15.mybatis.springboot.base.model.auto.AccountBalance;
import com.github.bjlhx15.mybatis.springboot.base.model.auto.AccountBalanceExample;
import com.github.bjlhx15.mybatis.springboot.base.repository.AccountBalanceExtMapper;
import com.github.bjlhx15.mybatis.springboot.base.repository.auto.AccountBalanceMapper;
import com.github.bjlhx15.mybatis.springboot.base.service.IAccountBalanceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class AccountBalanceServiceImpl implements IAccountBalanceService {
    @Autowired
    private AccountBalanceMapper mapper;
    @Autowired
    private AccountBalanceExtMapper extMapper;

    @Transactional
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
        PageHelper.startPage(1,2);
        List<AccountBalanceVo> balanceList2 = extMapper.select(record);
        PageInfo<List<AccountBalanceVo>> a=new PageInfo(balanceList);
        return a;
    }
}
