package com.github.bjlhx15.mybatis.springboot.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.bjlhx15.mybatis.springboot.base.entity.auto.AccountBalance;
import com.github.bjlhx15.mybatis.springboot.base.mapper.auto.AccountBalanceMapper;
import com.github.bjlhx15.mybatis.springboot.base.service.IAccountBalanceService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账户金额 服务实现类
 * </p>
 *
 * @author lihongxu
 * @since 2019-08-26
 */
@Service
public class AccountBalanceServiceImpl extends ServiceImpl<AccountBalanceMapper, AccountBalance> implements IAccountBalanceService {
}
