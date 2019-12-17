package ${package}.po;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 基本PO字段
 */
@TableName(value = "base")
public class BasePO implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //使用@TableLogic注解实现逻辑删除
    @TableLogic
    @TableField(value = "delflag", exist = true)
    protected Integer delflag = 0;

    @TableField(value = "remark", exist = true)
    protected String remark ;


    @TableField(value = "status", exist = true)
    protected Integer status = 1 ;

    //基础类型Date 不推荐使用 推荐使用jdk8 LocalDateTime
    //根式转换参看 https://www.cnblogs.com/bjlhx/p/6927138.html
//    @TableField(value = "created_datetime", exist = true)
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    protected Date createdDatetime;

    @TableField(value = "created_datetime", exist = true)
    @JsonSerialize(using = ${package}.converter.LocalDateTimeConverter.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime createdDatetime;

    @TableField(value = "updated_datetime", exist = true)
    @JsonSerialize(using = ${package}.converter.LocalDateTimeConverter.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime updatedDatetime;

    public BasePO() {
    }

    public BasePO(Long id, Integer delflag, LocalDateTime createdDatetime, LocalDateTime updatedDatetime) {
        this.id = id;
        this.delflag = delflag;
        this.createdDatetime = createdDatetime;
        this.updatedDatetime = updatedDatetime;
    }

    public BasePO(Long id, Integer delflag, String remark, Integer status, LocalDateTime createdDatetime, LocalDateTime updatedDatetime) {
        this.id = id;
        this.delflag = delflag;
        this.remark = remark;
        this.status = status;
        this.createdDatetime = createdDatetime;
        this.updatedDatetime = updatedDatetime;
    }

    //getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }

    public LocalDateTime getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(LocalDateTime createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public LocalDateTime getUpdatedDatetime() {
        return updatedDatetime;
    }

    public void setUpdatedDatetime(LocalDateTime updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BasePO{" +
                "id=" + id +
                ", delflag=" + delflag +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", createdDatetime=" + createdDatetime +
                ", updatedDatetime=" + updatedDatetime +
                '}';
    }
}