package com.tyy.test;

import com.tyy.config.SpringDataJpaConfig;
import com.tyy.pojo.Customer;
import com.tyy.repositories.CustomerPagingAndSortRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = SpringDataJpaConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringdataJpaPagingAndSortTest {

    @Autowired
    private CustomerPagingAndSortRepository repository;

    /**
     * 分页查询，需要传入Page，
     * Page的构建需要 1. 从那页开始 2. 一共查询几条
     */
    @Test
    public void testPaging(){

        Page<Customer> all = repository.findAll(PageRequest.of(0, 2));

        System.out.println(all.getTotalPages());
        System.out.println(all.getTotalElements());
        System.out.println(all.getContent());
    }

    /**
     * 排序
     */
    @Test
    public void testSort(){

        /*
        字符串硬编码实现排序
         */
        // 降序
//        Sort sort = Sort.by("id").descending();
        // 升序
//        Sort sort = Sort.by("id").ascending();

        /*
        类型安全的形式
         */
        Sort.TypedSort<Customer> sortType = Sort.sort(Customer.class);
        Sort sort = sortType.by(Customer::getId).descending();

        Iterable<Customer> all = repository.findAll(sort);

        System.out.println(all);
    }

}
