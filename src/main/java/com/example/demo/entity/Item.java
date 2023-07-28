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
@Entity(name = "item")
public class Item implements SuperEntity, Serializable {
    @Id
    private String code;
    private String description;
    private double unitPrice;
    private int qtyOnHand;

    @ToString.Exclude
    @OneToMany(targetEntity = OrderDetail.class, mappedBy = "item")
    private List<OrderDetail> orderDetailList = new ArrayList<>();

    public Item(String code, String description, double unitPrice, int qtyOnHand) {
        this.code = code;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
    }
}
