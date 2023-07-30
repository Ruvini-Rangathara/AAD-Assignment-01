package com.example.demo.servlet;


import com.example.demo.dto.ItemDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.OrderDetailDTO;
import com.example.demo.entity.OrderDetail;
import com.example.demo.service.ServiceFactory;
import com.example.demo.service.ServiceType;
import com.example.demo.service.custom.ItemService;
import com.example.demo.service.custom.OrderDetailService;
import com.example.demo.service.custom.OrderService;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

    private ItemService itemService;
    private OrderService orderService;
    private OrderDetailService orderDetailService;

    @Override
    public void init()  {
        itemService = ServiceFactory.getInstance().getService(ServiceType.ITEM_SERVICE);
        orderService = ServiceFactory.getInstance().getService(ServiceType.ORDER_SERVICE);
        orderDetailService = ServiceFactory.getInstance().getService(ServiceType.ORDER_DETAIL_SERVICE);

    }



    // Validation method for OrderDTO
    public boolean isValid(OrderDTO orderDTO) {
        return isValidOrderId(orderDTO.getOrderId()) && isValidCustomerId(orderDTO.getCustomerId())
                && orderDTO.getDate() != null && isValidOrderDetails(orderDTO.getItemList());
    }

    // Validate the order ID format (O001, O002, etc.)
    private boolean isValidOrderId(String orderId) {
        Pattern pattern = Pattern.compile("^O\\d{3}$");
        return pattern.matcher(orderId).matches();
    }

    // Validate the customer ID format (C001, C002, etc.)
    private boolean isValidCustomerId(String customerId) {
        Pattern pattern = Pattern.compile("^C\\d{3}$");
        return pattern.matcher(customerId).matches();
    }

    // Validate the item code format (I001, I002, etc.)
    private boolean isValidItemCode(String itemCode) {
        Pattern pattern = Pattern.compile("^I\\d{3}$");
        return pattern.matcher(itemCode).matches();
    }

    // Validate the list of OrderDetails
    private boolean isValidOrderDetails(List<OrderDetailDTO> orderDetails) {
        if (orderDetails == null || orderDetails.isEmpty()) {
            return false;
        }

        for (OrderDetailDTO detail : orderDetails) {
            if (detail == null || !isValidOrderId(detail.getOrderId())
                    || !isValidItemCode(detail.getItemCode()) || detail.getQty() <= 0) {
                return false;
            }
        }

        return true;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do post");
        Jsonb jsonb = JsonbBuilder.create();
        OrderDTO orderDTO = jsonb.fromJson(req.getReader(), OrderDTO.class);

        System.out.println(orderDTO.toString()+"\n");

        if(isValid(orderDTO)){
            if(orderService.save(orderDTO)){
                System.out.println("Order saved");
            }else {
                System.out.println("Order not saved");
            }
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }


}
