package com.example.demo.util;


import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.ItemDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.OrderDetailDTO;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;

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
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customerDTO.getId());

        return new OrderDTO(
                order.getOrderId(),
                customerDTO,
                order.getDate(),
                order.getItemList()
        );
    }

    public Order toOrder(OrderDTO orderDTO){
        Customer customer = new Customer();
        customer.setId(customer.getId());

        return new Order(
                orderDTO.getOrderId(),
                customer,
                orderDTO.getDate(),
                orderDTO.getItemList()
        );
    }

    public OrderDetailDTO toOrderDetailDTO(OrderDetail orderDetail){
        return new OrderDetailDTO(
                toOrderDTO(orderDetail.getOrder()),
                toItemDTO(orderDetail.getItem()),
                orderDetail.getQty(),
                orderDetail.getId()
        );
    }

    public OrderDetail toOrderDetail(OrderDetailDTO orderDetailDTO){
        return new OrderDetail(
                toOrder(orderDetailDTO.getOrderDTO()),
                toItem(orderDetailDTO.getItemDTO()),
                orderDetailDTO.getQty(),
                orderDetailDTO.getId()
        );
    }

}
