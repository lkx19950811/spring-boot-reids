package com.lkx.springbootjedis.pojo;

import javax.persistence.*;
import java.util.Set;

/**
 * @author lee Cather
 * @date 2018-10-09 11:01
 * desc :
 */
@Entity
public class OrderUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    @JoinColumn(name = "orderUser_id")
    private Set<Order> order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Order> getOrder() {
        return order;
    }

    public void setOrder(Set<Order> order) {
        this.order = order;
    }
}
