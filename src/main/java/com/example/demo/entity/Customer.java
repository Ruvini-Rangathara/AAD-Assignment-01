package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity(name = "customer")
public class Customer implements SuperEntity, Serializable {
    @Id
    private String id;
    private String name;
    private String address;
    private double salary;

    @ToString.Exclude
    @OneToMany(targetEntity = Order.class,mappedBy = "customer")
    List<Order> orderList = new ArrayList<>();

    public Customer(String id, String name, String address, double salary) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }
}
