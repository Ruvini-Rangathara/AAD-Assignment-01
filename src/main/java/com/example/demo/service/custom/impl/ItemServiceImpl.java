package com.example.demo.service.custom.impl;


import com.example.demo.dao.DaoFactory;
import com.example.demo.dao.DaoType;
import com.example.demo.dao.custom.ItemDao;
import com.example.demo.dto.ItemDTO;
import com.example.demo.entity.Item;
import com.example.demo.service.custom.ItemService;
import com.example.demo.util.Convertor;
import com.example.demo.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    private final Convertor convertor;
    private final ItemDao itemDao;


    public ItemServiceImpl() {
        convertor=new Convertor();
        itemDao= DaoFactory.getInstance().getDao( DaoType.ITEM );
    }


    @Override
    public boolean save(ItemDTO dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            itemDao.save(convertor.toItem(dto),session);
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
    public boolean update(ItemDTO dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            itemDao.update(convertor.toItem(dto),session);
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
    public boolean delete(ItemDTO dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            itemDao.delete(convertor.toItem(dto),session);
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
    public ItemDTO search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Item item = itemDao.search(id, session);
            transaction.commit();
            return convertor.toItemDTO(item);
        }catch(Exception e){
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<ItemDTO> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            List<Item> itemList = itemDao.getAll(session);
            transaction.commit();

            List<ItemDTO> list = new ArrayList<>();
            for (Item item:itemList) {
                list.add(convertor.toItemDTO(item));
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
            String lastId = itemDao.getLastId(session);
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
