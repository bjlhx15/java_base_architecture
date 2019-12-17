package com.github.bjlhx15.mybatis.springboot.base.entity.auto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 账户金额
 * </p>
 *
 * @author lihongxu
 * @since 2019-08-26
 */
@ApiModel(value="AccountBalance对象", description="账户金额")
public class AccountBalance implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "版本")
    private Integer version;

    @ApiModelProperty(value = "金额")
    private Integer balance;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdDatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
    public LocalDateTime getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(LocalDateTime createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    @Override
    public String toString() {
        return "AccountBalance{" +
            "id=" + id +
            ", name=" + name +
            ", version=" + version +
            ", balance=" + balance +
            ", createdDatetime=" + createdDatetime +
        "}";
    }
}
