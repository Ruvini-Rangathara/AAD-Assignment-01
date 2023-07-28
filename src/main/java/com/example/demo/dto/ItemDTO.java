package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ItemDTO implements SuperDTO{
    private String code;
    private String description;
    private double unitPrice;
    private int qtyOnHand;
}
