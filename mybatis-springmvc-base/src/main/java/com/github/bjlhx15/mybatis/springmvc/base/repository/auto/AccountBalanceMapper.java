package com.github.bjlhx15.mybatis.springmvc.base.repository.auto;

import com.github.bjlhx15.mybatis.springmvc.base.model.auto.AccountBalance;
import com.github.bjlhx15.mybatis.springmvc.base.model.auto.AccountBalanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountBalanceMapper {
    long countByExample(AccountBalanceExample example);

    int deleteByExample(AccountBalanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountBalance record);

    int insertSelective(AccountBalance record);

    List<AccountBalance> selectByExample(AccountBalanceExample example);

    AccountBalance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountBalance record, @Param("example") AccountBalanceExample example);

    int updateByExample(@Param("record") AccountBalance record, @Param("example") AccountBalanceExample example);

    int updateByPrimaryKeySelective(AccountBalance record);

    int updateByPrimaryKey(AccountBalance record);
}