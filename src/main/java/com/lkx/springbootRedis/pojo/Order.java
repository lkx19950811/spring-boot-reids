package com.lkx.springbootRedis.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @author lee Cather
 * @date 2018-10-08 14:35
 * desc :
 */
@Entity(name = "orderTable")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String oderName;

    private String orderNum;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderUser_id",referencedColumnName = "id")
    private OrderUser orderUser;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOderName() {
        return oderName;
    }

    public void setOderName(String oderName) {
        this.oderName = oderName;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public OrderUser getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(OrderUser orderUser) {
        this.orderUser = orderUser;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", oderName='" + oderName + '\'' +
                ", orderNum='" + orderNum + '\'' +
                '}';
    }
}
