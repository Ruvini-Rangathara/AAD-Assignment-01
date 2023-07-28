package com.example.demo.util;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Test {
    static Session session;
    static Transaction transaction;

    public static void main(String[] args) {

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            Customer customer = new Customer();
            customer.setId("C001");
            customer.setName("Ruvini");
            customer.setAddress("Panadura");
            customer.setSalary(50000);

            session.save(customer);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            Item item1 = new Item();
            item1.setCode("I001");
            item1.setDescription("Rice");
            item1.setUnitPrice(200.00);
            item1.setQtyOnHand(9);
            session.save(item1);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            Item item2 = new Item();
            item2.setCode("I002");
            item2.setDescription("Noodles");
            item2.setUnitPrice(550.00);
            item2.setQtyOnHand(9);
            session.save(item2);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            Customer customer = session.get(Customer.class,"C001");
            Item item1 = session.get(Item.class, "I001");
            Item item2 = session.get(Item.class, "I002");

            Order order = new Order("O001",Date.valueOf("2023-7-4"),customer);
            List<OrderDetail> itemList = new ArrayList<>();

            OrderDetail orderDetail1 = new OrderDetail();
            orderDetail1.setOrder(order);
            orderDetail1.setItem(item1);
            orderDetail1.setQty(4);
            orderDetail1.setId(1);

            session.save(orderDetail1);

            itemList.add(orderDetail1);
//            ================================================================

            OrderDetail orderDetail2 = new OrderDetail();
            orderDetail2.setOrder(order);
            orderDetail2.setItem(item2);
            orderDetail2.setQty(4);
            orderDetail2.setId(2);

            session.save(orderDetail2);

            itemList.add(orderDetail1);


            session.save(order);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }



    }
}
