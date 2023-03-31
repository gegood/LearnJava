package com.tyy.mysql.service;

import com.tyy.mysql.dao.CreateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateService {

    @Autowired
    CreateDao createDao;

    public String create(String data){

        createDao.create(data);

        return "Service 层接收成功， 内容" + data ;
    }
}
