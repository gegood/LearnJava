package com.tyy.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {
        // 创建 Configuration
        Configuration configuration = new Configuration().configure();
        // 获取 SessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        // 获取 Session
        Session session = sessionFactory.openSession();

        // 创建实体类
        People people = new People();
//        people.setId(10);
        people.setName("四娃");
        session.save(people);

        // 利用 hibernate 提交事务
        session.beginTransaction().commit();
        session.close();
    }
}
