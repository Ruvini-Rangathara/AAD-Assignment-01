package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity(name="orderDetail")
public class OrderDetail implements SuperEntity,Serializable {
    @ManyToOne
    private Order order;

    @ManyToOne
    private Item item;

    private int qty;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


}
