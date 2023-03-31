package com.tyy.pojo;

import org.junit.Before;
import org.junit.Test;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * 直接使用 JPA 接口，没有用 Spring data
 *
 * EntityManagerFactory  相当于 Hibernate 的 SessionFactory
 * EntityManager  相当于 Hibernate 的 Session
 * EntityTransaction 相当于 Hibernate 的 Transaction
 *
 */

public class JPATest {

    private EntityManagerFactory ef;
    private EntityManager em;

    @Before
    public void before(){
        /**
         * 读取了配置单元
         */

        // 对应于获取 SessionFactory
        ef = Persistence.createEntityManagerFactory("hibernateJPA");
        // 对应于获取 Session
        em = ef.createEntityManager();
    }

    /**
     * 插入操作
     */
    @Test
    public void create(){

        // 开启一个事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // 创建实体类
        Customer customer = new Customer();
        customer.setName("老白");

        // 插入操作，insert
        em.persist(customer);

        // 提交事务
        tx.commit();

    }

    /**
     * 查询操作
     */
    @Test
    public void read(){

        Customer customer = em.find(Customer.class, 1L);
        System.out.println(customer);

    }

    /**
     * 修改数据操作
     */
    @Test
    public void update(){

        // 开启一个事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // 创建实体类
        Customer customer = new Customer();
        customer.setName("小王");

        // 如果没设置主键，则直接插入
        // 如果设置了主键，没找到，也直接插入
        customer.setId(2L);

        em.merge(customer);
        // 提交事务
        tx.commit();

    }

    /**
     * 删除操作
     */
    @Test
    public void delete(){

        // 开启一个事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // JPA 对象有4大状态，只能删除持久化状态的数据，就是从数据库里查出来的。
        Customer customer = em.find(Customer.class, 5L);

        em.remove(customer);

        // 提交事务
        tx.commit();

    }

    /**
     * 使用 JPA 提供的 JPQL 语句实现查找
     */
    @Test
    public void readJPql(){

        // 开启一个事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // 写一个 HQL 语句
        String jpql = "FROM Customer WHERE id=:id";

        List<Customer> resultList = em.createQuery(jpql, Customer.class)
                .setParameter("id", 3L)
                .getResultList();

        System.out.println(resultList);

        // 提交事务
        tx.commit();

    }

    /**
     * 使用 JPA 提供的 JPQL 语句实现修改
     */
    @Test
    public void readJPql02(){

        // 开启一个事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // 写一个 HQL 语句
        String jpql = "UPDATE Customer SET name=:name WHERE id=:id";

        em.createQuery(jpql)
                .setParameter("id", 3L)
                .setParameter("name","小黑")
                .executeUpdate();

        // 提交事务
        tx.commit();

    }



}
