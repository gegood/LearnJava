package com.example.common.dao;

import com.example.common.po.Po;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DaoBase<P extends Po<K>, K> extends JpaRepository<P, K>, JpaSpecificationExecutor<P> {

}
