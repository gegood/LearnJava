package com.tyy.repositories;

import com.tyy.pojo.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 继承 CrudRepository<T, K>
 *     T为实体类名
 *     K为主键的类型
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    /*
    增加操作
    增删改操作
    一定需要添加事务支持
    @Transactional
    @Modifying  // 通知Springdata，是增删改的操作
     */
    @Query("insert into Customer(name) select c.name from Customer c where c.id=:id")
    @Transactional
    @Modifying
    int insertCustomerByIdQuery(@Param("id") Long id);

    /*
    修改操作
     */
    @Query("update Customer c set c.addr=:addr where c.id=:id")
    @Transactional
    @Modifying
    int updateCustomerbyIdQuery(@Param("addr") String addr, @Param("id") Long id);


    /*
    查询操作
     */
    @Query("From Customer where name=:name")
    List<Customer> findCustomerByNameQuery(@Param("name") String name);

    /*
    删除操作
     */
    @Query("delete Customer c where c.id=:id")
    @Transactional
    @Modifying
    int deleteCustomerByIdQuery(@Param("id") Long id);



    /*
    原生的SQL，增加 nativeQuery = true
    查询操作
     */
    @Query(value = "select * From cst_customer where cust_name=:name" , nativeQuery = true)
    List<Customer> readCustomerByNameSQL(@Param("name") String name);

}
