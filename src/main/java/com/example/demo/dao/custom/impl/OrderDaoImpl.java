package com.example.demo.dao.custom.impl;


import com.example.demo.dao.custom.OrderDao;
import com.example.demo.entity.Order;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(Order entity, Session session) {
        session.save(entity);
        return true;
    }

    @Override
    public boolean update(Order entity, Session session) {
        session.update(entity);
        return true;
    }

    @Override
    public boolean delete(Order entity, Session session) {
        session.delete(entity);
        return true;
    }

    @Override
    public Order search(String id, Session session) {
        Order order = session.get(Order.class, id);
        return order;
    }

    @Override
    public List<Order> getAll(Session session) {
        String hql = "FROM orders";
        Query query = session.createQuery(hql);
        List<Order> list = query.list();
        return list;
    }

    @Override
    public String getLastId(Session session) {
        String hql = "SELECT id FROM orders ORDER BY id DESC";
        Query query = session.createQuery(hql);
        List<String> list = query.list();

        return list.get(0);
    }
}
