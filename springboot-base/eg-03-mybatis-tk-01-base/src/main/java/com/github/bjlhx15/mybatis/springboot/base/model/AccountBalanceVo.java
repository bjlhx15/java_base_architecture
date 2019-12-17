package com.github.bjlhx15.mybatis.springboot.base.model;

import com.github.bjlhx15.mybatis.springboot.base.model.auto.AccountBalance;

public class AccountBalanceVo extends AccountBalance {
    private RequestPageInfo pageInfo;

    private String showName;

    public RequestPageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(RequestPageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }
}