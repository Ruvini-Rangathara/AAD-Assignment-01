package com.example.demo.dao.custom.impl;


import com.example.demo.dao.custom.OrderDetailDao;
import com.example.demo.entity.OrderDetail;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {
    @Override
    public boolean save(OrderDetail entity, Session session) {
        session.save(entity);
        return true;
    }

    @Override
    public boolean update(OrderDetail entity, Session session) {
        session.update(entity);
        return true;
    }

    @Override
    public boolean delete(OrderDetail entity, Session session) {
        session.delete(entity);
        return true;
    }

    @Override
    public OrderDetail search(Integer id, Session session) {
        OrderDetail orderDetail = session.get(OrderDetail.class, id);
        return orderDetail;
    }

    @Override
    public List<OrderDetail> getAll(Session session) {
        String hql = "FROM orderDetail ";
        Query query = session.createQuery(hql);
        List<OrderDetail> list = query.list();
        return list;
    }

    @Override
    public Integer getLastId(Session session) {
        String hql = "SELECT id FROM orderDetail ORDER BY id DESC";
        Query query = session.createQuery(hql);
        List<Integer> list = query.list();

        return list.get(0);
    }
}
