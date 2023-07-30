package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrderDetailDTO implements SuperDTO{
    private String orderId;
    private String itemCode;
    private int qty;
    private int id;

}
