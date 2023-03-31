package com.tyy.pojo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * 直接使用 Hibernate
 *
 * SessionFactory  固定写法
 * Session  固定写法
 * Transaction  开启事务
 *
 */

public class HibernateTest {

    private SessionFactory sf;
    private Session se;

    @Before
    public void init(){
        // 创建 Configuration
        Configuration configuration = new Configuration().configure();
        // 获取 SessionFactory
        sf = configuration.buildSessionFactory();
        // 获取 Session
        se = sf.openSession();
    }

    @After
    public void end(){
        // 关闭 Session
        se.close();
    }

    /**
     * 插入操作
     */
    @Test
    public void create(){

        // 开启一个事务
        Transaction tx = se.beginTransaction();

        // 创建实体类
        Customer customer = new Customer();
        customer.setName("小吴");

        // 插入操作，insert
        se.save(customer);

        // 提交事务
        tx.commit();

    }

    /**
     * 查询操作
     */
    @Test
    public void read(){

        Customer customer = se.find(Customer.class, 12L);
        System.out.println(customer);

    }

    /**
     * 修改数据操作
     */
    @Test
    public void update(){

        // 开启一个事务
        Transaction tx = se.beginTransaction();

        // 创建实体类
        Customer customer = new Customer();
        customer.setName("小吴");
        // customer.setId(2L);

        // 如果找不到，则报错，不插入不修改
        // se.update(customer);

        // 如果设置了主键，则先寻找，找不到报错，
        // 如果没有设置主键，则直接插入
        se.saveOrUpdate(customer);
        // 提交事务
        tx.commit();

    }

    /**
     * 删除操作
     */
    @Test
    public void delete(){

        // 开启一个事务
        Transaction tx = se.beginTransaction();

        // 创建实体类
        Customer customer = new Customer();
        // 一定得设置主键
        customer.setId(2L);

        se.remove(customer);

        // 提交事务
        tx.commit();

    }

    /**
     * 使用 Hibernate 提供的 HQL 语句实现查找
     */
    @Test
    public void readHql(){

        // 开启一个事务
        Transaction tx = se.beginTransaction();

        // 写一个 HQL 语句
        String hql = "FROM Customer WHERE id=:id";

        List<Customer> resultList = se.createQuery(hql, Customer.class)
                .setParameter("id",3L)
                .getResultList();
        System.out.println(resultList);

        // 提交事务
        tx.commit();

    }



}
