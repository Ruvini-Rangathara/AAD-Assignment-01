package com.example.demo.service;


import com.example.demo.service.custom.impl.CustomerServiceImpl;
import com.example.demo.service.custom.impl.ItemServiceImpl;
import com.example.demo.service.custom.impl.OrderDetailServiceImpl;
import com.example.demo.service.custom.impl.OrderServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory(){}

    public static ServiceFactory getInstance(){
        return serviceFactory==null ? serviceFactory=new ServiceFactory() : serviceFactory;
    }

    public <T>T getService(ServiceType serviceType){
        switch (serviceType){
            case ITEM_SERVICE: return (T) new ItemServiceImpl();
            case CUSTOMER_SERVICE: return (T) new CustomerServiceImpl();
            case ORDER_SERVICE: return (T) new OrderServiceImpl();
            case ORDER_DETAIL_SERVICE: return (T) new OrderDetailServiceImpl();
            default: return null;
        }

    }
}
