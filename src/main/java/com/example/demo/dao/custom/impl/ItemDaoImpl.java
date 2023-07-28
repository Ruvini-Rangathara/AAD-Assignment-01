package com.example.demo.dao.custom.impl;


import com.example.demo.dao.custom.ItemDao;
import com.example.demo.entity.Item;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(Item entity, Session session) {
        session.save(entity);
        return true;
    }

    @Override
    public boolean update(Item entity, Session session) {
        session.update(entity);
        return true;
    }

    @Override
    public boolean delete(Item entity, Session session) {
        session.delete(entity);
        return false;
    }

    @Override
    public Item search(String id, Session session) {
        Item item = session.get(Item.class, id);
        return item;
    }

    @Override
    public List<Item> getAll(Session session) {
        String hql = "FROM item";
        Query query = session.createQuery(hql);
        List<Item> list = query.list();
        return list;
    }

    @Override
    public String getLastId(Session session) {
        String hql = "SELECT id FROM item ORDER BY id DESC";
        Query query = session.createQuery(hql);
        List<String> list = query.list();

        return list.get(0);
    }
}
