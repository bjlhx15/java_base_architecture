package com.github.bjlhx15.mybatis.springboot.base.model.auto;

import javax.persistence.*;

@Table(name = "accountbalance")
public class AccountBalance {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 版本
     */
    private Integer version;

    /**
     * 金额
     */
    private Integer balance;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取版本
     *
     * @return version - 版本
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置版本
     *
     * @param version 版本
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 获取金额
     *
     * @return balance - 金额
     */
    public Integer getBalance() {
        return balance;
    }

    /**
     * 设置金额
     *
     * @param balance 金额
     */
    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}