package com.example.demo.dao.custom.impl;

import com.example.demo.dao.custom.CustomerDao;
import com.example.demo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public boolean save(Customer entity, Session session) {
        session.save(entity);
        return true;
    }

    @Override
    public boolean update(Customer entity, Session session) {
        session.update(entity);
        return true;
    }

    @Override
    public boolean delete(Customer entity, Session session) {
        session.delete(entity);
        return true;
    }

    @Override
    public Customer search(String id, Session session) {
        Customer customer = session.get(Customer.class, id);
        return customer;
    }

    @Override
    public List<Customer> getAll(Session session) {
        String hql = "FROM customer";
        Query query = session.createQuery(hql);
        List<Customer> list = query.list();
        return list;
    }

    @Override
    public String getLastId(Session session) {
        String hql = "SELECT id FROM customer ORDER BY id DESC";
        Query query = session.createQuery(hql);
        List<String> list = query.list();

        return list.get(0);
    }
}
