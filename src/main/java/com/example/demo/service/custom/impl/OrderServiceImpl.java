package com.example.demo.service.custom.impl;


import com.example.demo.dao.DaoFactory;
import com.example.demo.dao.DaoType;
import com.example.demo.dao.custom.OrderDao;
import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.Order;
import com.example.demo.service.custom.OrderService;
import com.example.demo.util.Convertor;
import com.example.demo.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final Convertor convertor;
    private final OrderDao orderDao;


    public OrderServiceImpl() {
        convertor=new Convertor();
        orderDao= DaoFactory.getInstance().getDao( DaoType.ORDER );
    }

    @Override
    public boolean save(OrderDTO dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            orderDao.save(convertor.toOrder(dto),session);
            transaction.commit();
            return true;
        }catch(Exception e){
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(OrderDTO dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            orderDao.update(convertor.toOrder(dto),session);
            transaction.commit();
            return true;
        }catch(Exception e){
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean delete(OrderDTO dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            orderDao.delete(convertor.toOrder(dto),session);
            transaction.commit();
            return true;
        }catch(Exception e){
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public OrderDTO search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Order order = orderDao.search(id, session);
            transaction.commit();
            return convertor.toOrderDTO(order);
        }catch(Exception e){
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<OrderDTO> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            List<Order> orderList = orderDao.getAll(session);
            transaction.commit();

            List<OrderDTO> list = new ArrayList<>();
            for (Order order:orderList) {
                list.add(convertor.toOrderDTO(order));
            }

            return list;
        }catch(Exception e){
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public String getLastId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            String lastId = orderDao.getLastId(session);
            transaction.commit();
            return lastId;
        }catch(Exception e){
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }
}
