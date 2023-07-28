package com.example.demo.dao;

import com.example.demo.entity.SuperEntity;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public interface CrudDao<T extends SuperEntity, ID extends Serializable> extends SuperDao{
    boolean save(T entity, Session session);
    boolean update(T entity, Session session);
    boolean delete(T entity, Session session);
    T search(ID id, Session session);
    List<T> getAll(Session session);
    ID getLastId(Session session);
}
