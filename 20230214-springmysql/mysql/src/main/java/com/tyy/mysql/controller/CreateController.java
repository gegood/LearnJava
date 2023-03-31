package com.tyy.mysql.controller;

import com.tyy.mysql.service.CreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CreateController {

    @Autowired
    CreateService createService;


    @RequestMapping  ("/create")
    public String create(@RequestParam String data){

        return "Controller层接收成功，" + createService.create(data);
    }

}
