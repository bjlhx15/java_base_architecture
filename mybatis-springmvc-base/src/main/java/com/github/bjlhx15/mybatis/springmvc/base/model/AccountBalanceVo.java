package com.github.bjlhx15.mybatis.springmvc.base.model;

import com.github.bjlhx15.mybatis.springmvc.base.model.auto.AccountBalance;
import com.github.pagehelper.PageInfo;

public class AccountBalanceVo extends AccountBalance {
    private PageInfo pageInfo;

    private String showName;

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }
}