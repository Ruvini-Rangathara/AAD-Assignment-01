package com.example.demo.service.custom.impl;


import com.example.demo.dao.DaoFactory;
import com.example.demo.dao.DaoType;
import com.example.demo.dao.custom.CustomerDao;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Customer;
import com.example.demo.service.custom.CustomerService;
import com.example.demo.util.Convertor;
import com.example.demo.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private final Convertor convertor;
    private final CustomerDao customerDao;


    public CustomerServiceImpl() {
        convertor=new Convertor();
        customerDao= DaoFactory.getInstance().getDao( DaoType.CUSTOMER );
    }


    @Override
    public boolean save(CustomerDTO dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            customerDao.save(convertor.toCustomer(dto),session);
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
    public boolean update(CustomerDTO dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            customerDao.update(convertor.toCustomer(dto),session);
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
    public boolean delete(CustomerDTO dto) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            customerDao.delete(convertor.toCustomer(dto),session);
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
    public CustomerDTO search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Customer customer = customerDao.search(id, session);
            transaction.commit();
            return convertor.toCustomerDTO(customer);
        }catch(Exception e){
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<CustomerDTO> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            List<Customer> customerList = customerDao.getAll(session);
            transaction.commit();

            List<CustomerDTO> list = new ArrayList<>();
            for (Customer customer:customerList) {
                list.add(convertor.toCustomerDTO(customer));
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
            String lastId = customerDao.getLastId(session);
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
