package com.example.demo.dao;


import com.example.demo.dao.custom.impl.CustomerDaoImpl;
import com.example.demo.dao.custom.impl.ItemDaoImpl;
import com.example.demo.dao.custom.impl.OrderDaoImpl;
import com.example.demo.dao.custom.impl.OrderDetailDaoImpl;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory(){}

    public static DaoFactory getInstance(){
        return daoFactory==null ? daoFactory=new DaoFactory() : daoFactory;
    }

    public <T extends SuperDao>T getDao(DaoType daoType){
        switch (daoType){
            case ITEM: return (T) new ItemDaoImpl();
            case CUSTOMER: return (T) new CustomerDaoImpl();
            case ORDER: return (T) new OrderDaoImpl();
            case ORDER_DETAIL: return (T) new OrderDetailDaoImpl();
            default: return null;
        }

    }
}
