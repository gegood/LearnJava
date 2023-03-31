package com.tyy.mysql.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CreateDao {
    void create(String data);

}
