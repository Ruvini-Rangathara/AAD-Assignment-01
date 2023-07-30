package com.example.demo.dto;

import com.example.demo.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrderDTO implements SuperDTO{
    private String orderId;
    private String customerId;
    private Date date;
    private List<OrderDetailDTO> itemList;
}
