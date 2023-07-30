package com.example.demo.service.custom.impl;


import com.example.demo.dao.DaoFactory;
import com.example.demo.dao.DaoType;
import com.example.demo.dao.custom.ItemDao;
import com.example.demo.dao.custom.OrderDao;
import com.example.demo.dao.custom.OrderDetailDao;
import com.example.demo.dto.ItemDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.OrderDetailDTO;
import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
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
    private final ItemDao itemDao;
    private final OrderDetailDao orderDetailDao;


    public OrderServiceImpl() {
        convertor=new Convertor();
        orderDao= DaoFactory.getInstance().getDao( DaoType.ORDER );
        orderDetailDao = DaoFactory.getInstance().getDao(DaoType.ORDER_DETAIL);
        itemDao = DaoFactory.getInstance().getDao(DaoType.ITEM);
    }

    @Override
    public boolean save(OrderDTO dto) {
        System.out.println("Service OrderDTO");
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        System.out.println();



        try{
            int i =1;
            for (OrderDetailDTO orderDetailDTO: dto.getItemList()) {
                OrderDetail orderDetail = convertor.toOrderDetail(orderDetailDTO);
                boolean save = orderDetailDao.save(orderDetail, session);


                System.out.println("\nIn service , is saved orderDetail "+ i+"    "+save+"\n");
                i++;
            }
            System.out.println();


            int j =1;
            for (OrderDetailDTO orderDetailDTO: dto.getItemList()) {
                Item item = itemDao.search(orderDetailDTO.getItemCode(),session);
                item.setQtyOnHand(item.getQtyOnHand()-orderDetailDTO.getQty());
                boolean updated = itemDao.update(item, session);


                System.out.println("\nIn service , is updated item qty "+j +updated+"\n");
                j++;
            }

            boolean save = orderDao.save(convertor.toOrder(dto), session);
            System.out.println("\nIn service , is saved order "+save+"\n");

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
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        try{
//            orderDao.update(convertor.toOrder(dto),session);
//            transaction.commit();
//            return true;
//        }catch(Exception e){
//            transaction.rollback();
//            return false;
//        }finally {
//            session.close();
//        }
        return false;
    }

    @Override
    public boolean delete(OrderDTO dto) {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        try{
//            orderDao.delete(convertor.toOrder(dto),session);
//            transaction.commit();
//            return true;
//        }catch(Exception e){
//            transaction.rollback();
//            return false;
//        }finally {
//            session.close();
//        }

        return false;
    }

    @Override
    public OrderDTO search(String id) {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        try{
//            Order order = orderDao.search(id, session);
//            transaction.commit();
//            return convertor.toOrderDTO(order);
//        }catch(Exception e){
//            transaction.rollback();
//            return null;
//        }finally {
//            session.close();
//        }

        return null;
    }

    @Override
    public List<OrderDTO> getAll() {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        try{
//            List<Order> orderList = orderDao.getAll(session);
//            transaction.commit();
//
//            List<OrderDTO> list = new ArrayList<>();
//            for (Order order:orderList) {
//                list.add(convertor.toOrderDTO(order));
//            }
//
//            return list;
//        }catch(Exception e){
//            transaction.rollback();
//            return null;
//        }finally {
//            session.close();
//        }
        return null;
    }

    @Override
    public String getLastId() {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        try{
//            String lastId = orderDao.getLastId(session);
//            transaction.commit();
//            return lastId;
//        }catch(Exception e){
//            transaction.rollback();
//            return null;
//        }finally {
//            session.close();
//        }

        return null;
    }
}
