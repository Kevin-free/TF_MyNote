package com.study.jpa.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import	java.util.Date;

import javax.persistence.*;

@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;

    /**
     * 顾客表id
     */
    private Integer customerId;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 价格
     */
    private Integer price;

    public CustomerOrder() {
    }

    public CustomerOrder(Integer customerId, Date createTime, Integer price) {
        this.customerId = customerId;
        this.createTime = createTime;
        this.price = price;
    }

    public CustomerOrder(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getId() {
        return orderId;
    }

    public void setId(Integer id) {
        this.orderId = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return createTime;
    }

    public void setDate(Date date) {
        this.createTime = date;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", createTime=" + createTime +
                ", price='" + price + '\'' +
                '}';
    }
}
