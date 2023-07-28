package com.example.demo.util;


import com.example.demo.entity.Customer;
import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class FactoryConfiguration {
    public static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;


 private FactoryConfiguration() {
     StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
     standardServiceRegistryBuilder.loadProperties("hibernate.properties");
     MetadataSources metadataSources = new MetadataSources(standardServiceRegistryBuilder.build());
     metadataSources.
             addAnnotatedClass(Customer.class).
             addAnnotatedClass(Item.class).
             addAnnotatedClass(Order.class).
             addAnnotatedClass(OrderDetail.class);
     Metadata metadata = metadataSources.getMetadataBuilder().build();
     sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public static FactoryConfiguration getInstance(){
        return factoryConfiguration==null ? factoryConfiguration=new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
