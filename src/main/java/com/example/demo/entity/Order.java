package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity(name = "orders")
public class Order implements SuperEntity, Serializable {
    @Id
    private String orderId;

    @ManyToOne
    private Customer customer;

    private Date date;

    @ToString.Exclude
    @OneToMany(targetEntity = OrderDetail.class, mappedBy = "order")
    private List<OrderDetail> itemList = new ArrayList<>();

    public Order(String orderId, Date date, Customer customer) {
        this.orderId = orderId;
        this.date = date;
        this.customer = customer;
    }
}


