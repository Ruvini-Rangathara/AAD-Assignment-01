package com.example.demo.util;


import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.ItemDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.OrderDetailDTO;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;

import java.util.ArrayList;
import java.util.List;

public class Convertor {

    public CustomerDTO toCustomerDTO(Customer customer){
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getSalary()
        );
    }

    public Customer toCustomer(CustomerDTO customerDTO){
        return new Customer(
                customerDTO.getId(),
                customerDTO.getName(),
                customerDTO.getAddress(),
                customerDTO.getSalary()
        );
    }

    public Item toItem(ItemDTO itemDTO){
        return new Item(
                itemDTO.getCode(),
                itemDTO.getDescription(),
                itemDTO.getUnitPrice(),
                itemDTO.getQtyOnHand()
        );
    }

    public ItemDTO toItemDTO(Item item){
        return new ItemDTO(
                item.getCode(),
                item.getDescription(),
                item.getUnitPrice(),
                item.getQtyOnHand()
        );
    }

    public OrderDTO toOrderDTO(Order order){
        List<OrderDetail> entityList = order.getItemList();

        List<OrderDetailDTO> dtoList = new ArrayList<>();
        for (OrderDetail orderDetail: entityList) {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO(
                    orderDetail.getOrder().getOrderId(),
                    orderDetail.getItem().getCode(),
                    orderDetail.getQty(),
                    orderDetail.getId()
            );
            dtoList.add(orderDetailDTO);
        }

        return new OrderDTO(
                order.getOrderId(),
                order.getCustomer().getId(),
                order.getDate(),
                dtoList
        );
    }

    public Order toOrder(OrderDTO orderDTO){
        Customer customer = new Customer();
        customer.setId(orderDTO.getCustomerId());

        List<OrderDetail> entityList = new ArrayList<>();
        List<OrderDetailDTO> dtoList = orderDTO.getItemList();

        for (OrderDetailDTO orderDetailDTO : dtoList) {
            Order order = new Order();
            order.setOrderId(orderDetailDTO.getOrderId());

            Item item = new Item();
            item.setCode(orderDetailDTO.getItemCode());

            OrderDetail orderDetail = new OrderDetail(
                    order,
                    item,
                    orderDetailDTO.getQty(),
                    orderDetailDTO.getId()
            );
            entityList.add(orderDetail);
        }


        return new Order(
                orderDTO.getOrderId(),
                customer,
                orderDTO.getDate(),
                entityList
        );
    }

    public OrderDetailDTO toOrderDetailDTO(OrderDetail orderDetail){

        return new OrderDetailDTO(
                orderDetail.getOrder().getOrderId(),
                orderDetail.getItem().getCode(),
                orderDetail.getQty(),
                orderDetail.getId()
        );
    }

    public OrderDetail toOrderDetail(OrderDetailDTO orderDetailDTO){
        Order order = new Order();
        order.setOrderId(orderDetailDTO.getOrderId());

        Item item = new Item();
        item.setCode(orderDetailDTO.getItemCode());

        return new OrderDetail(
                order,
                item,
                orderDetailDTO.getQty(),
                orderDetailDTO.getId()
        );
    }

}
