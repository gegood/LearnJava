package com.tyy;

import com.tyy.hibernate.People;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class PeopleTest {

    @Test
    public void testa() {
        // 创建 Configuration
        Configuration configuration = new Configuration().configure();
        // 获取 SessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        // 获取 Session
        Session session = sessionFactory.openSession();

        // 创建实体类
        People people = new People();

//        people.setId(10);
        people.setName("七哈");
        session.save(people);

        // 利用 hibernate 提交事务
        session.beginTransaction().commit();

        People getpeople = session.find(People.class, 12);
        System.out.println(getpeople);

        session.close();
    }
}
