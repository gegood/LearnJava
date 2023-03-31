package com.tyy.repositories;

import com.tyy.pojo.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * 继承 PagingAndSortingRepository
 * 自动拥有分页和排序的方法
 */
public interface CustomerPagingAndSortRepository extends PagingAndSortingRepository<Customer, Long> {

    Optional<Customer> findById(Long id);

}
