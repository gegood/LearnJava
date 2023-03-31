package com.tyy.pojo;

import lombok.Data;
import javax.persistence.*;

/**
 * JPA规范提供了 ORM 映射关系的注解
 * @Entity  // 作为 hibernate 实体类
 * @Table(name = "cst_customer") // 映射的表名
 * @id
 * @Column
 */

@Entity  // 作为 hibernate 实体类
@Table(name = "cst_customer") // 映射的表名
@Data
public class Customer {

    /**
     * @id  // 配置主键
     * @GeneratedValue(strategy = GenerationType.IDENTITY) // 配置自增
     * @Column(name = "cust_id") // 配置属性和表的字段的映射
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private long id;

    @Column(name = "cust_name")
    private String name;

    @Column(name = "cust_addr")
    private  String addr;
}
