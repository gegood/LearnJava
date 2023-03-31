package com.tyy.test;

import com.tyy.config.SpringDataJpaConfig;
import com.tyy.pojo.Customer;
import com.tyy.repositories.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 基于 JUnit4 spring 单元测试
 * 固定写法
 */

//@ContextConfiguration("/spring.xml")
@ContextConfiguration(classes = SpringDataJpaConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringdataJpaTest {

    @Autowired
    private CustomerRepository repository;

    /**
     * 查询操作
     */
    @Test
    public void read(){
        Optional<Customer> byId = repository.findById(12L);

        System.out.println(byId.get());
    }

    /**
     * 插入操作,更新操作
     */
    @Test
    public void create(){
        Customer customer = new Customer();
        customer.setName("华仔");

        // save 返回插入的实体
        System.out.println(repository.save(customer));
    }

    /**
     * 查询
     * 如果是游离状态，会自动帮你查询
     * 删除时一定要设置主键的值，不然不能删除
     */
    @Test
    public void delete(){
        Customer customer = new Customer();
        customer.setName("张三");
        customer.setId(4L);

        repository.delete(customer);
    }

    /**
     * findAllById 方法返回一个List
     */
    @Test
    public void findAllById(){

        Iterable<Customer> allById = repository.findAllById(Arrays.asList(1L, 3L, 6L));
        System.out.println(allById);
    }

    /**
     * findAll 查找所有
     */
    @Test
    public void findAll(){

        Iterable<Customer> all = repository.findAll();
        System.out.println(all);
    }

    /**
     * deleteAll : 删除了所有数据
     */
    @Test
    public void deleteAll(){

        repository.deleteAll();
    }

    /**
     * 自定义持久化操作的测试——查询
     * 通过name字段查询相应的记录
     * 在接口层定义方法，通过注解写具体的SQL语句
     */
    @Test
    public void findCustomerByNameQuery(){
        String name = "关羽";

        List<Customer> customerByNameQuery = repository.findCustomerByNameQuery(name);
        System.out.println(customerByNameQuery);
    }

    /**
     * 自定义持久化操作的测试——修改
     * 通过id字段查询相应的记录，修改其 addr字段
     * 在接口层定义方法，通过注解写具体的SQL语句
     */
    @Test
    public void updateCustomerbyIdQuery(){
        String addr = "赤岗";
        Long id = 14L;
        int result = repository.updateCustomerbyIdQuery(addr, id);
        System.out.println(result);
    }

    /**
     * 自定义持久化操作的测试——删除
     * 通过id字段查询相应的记录，删除
     * 在接口层定义方法，通过注解写具体的SQL语句
     */
    @Test
    public void deleteCustomerByIdQuery(){
        Long id = 13L;
        int result = repository.deleteCustomerByIdQuery(id);
        System.out.println(result);
    }

    /**
     * 自定义持久化操作的测试——删除
     * 通过id字段查询相应的记录，插入该记录的name字段
     * 在接口层定义方法，通过注解写具体的SQL语句
     */
    @Test
    public void insertCustomerByIdQuery(){
        Long id = 14L;
        int result = repository.insertCustomerByIdQuery(id);
        System.out.println(result);
    }

    /**
     * 自定义持久化操作——原生的SQL语句
     */
    @Test
    public void readCustomerByNameSQL(){

        String name = "关羽";
        List<Customer> customers = repository.readCustomerByNameSQL(name);

        System.out.println(customers);
    }

}
