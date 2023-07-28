package com.example.demo.service.custom.impl;

import com.example.demo.dao.DaoFactory;
import com.example.demo.dao.DaoType;
import com.example.demo.dao.custom.OrderDetailDao;
import com.example.demo.dto.OrderDetailDTO;
import com.example.demo.entity.OrderDetail;
import com.example.demo.service.custom.OrderDetailService;
import com.example.demo.util.Convertor;
import com.example.demo.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailService {

    private final Convertor convertor;
    private final OrderDetailDao orderDetailDao;


    public OrderDetailServiceImpl() {
        convertor=new Convertor();
        orderDetailDao= DaoFactory.getInstance().getDao( DaoType.ORDER_DETAIL );
    }


    @Override
    public boolean save(OrderDetailDTO dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            orderDetailDao.save(convertor.toOrderDetail(dto),session);
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
    public boolean update(OrderDetailDTO dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            orderDetailDao.update(convertor.toOrderDetail(dto),session);
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
    public boolean delete(OrderDetailDTO dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            orderDetailDao.delete(convertor.toOrderDetail(dto),session);
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
    public OrderDetailDTO search(Integer id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            OrderDetail orderDetail = orderDetailDao.search(id, session);
            transaction.commit();
            return convertor.toOrderDetailDTO(orderDetail);
        }catch(Exception e){
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<OrderDetailDTO> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            List<OrderDetail> orderDetailList = orderDetailDao.getAll(session);
            transaction.commit();

            List<OrderDetailDTO> list = new ArrayList<>();
            for (OrderDetail orderDetail:orderDetailList) {
                list.add(convertor.toOrderDetailDTO(orderDetail));
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
    public Integer getLastId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Integer lastId = orderDetailDao.getLastId(session);
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
