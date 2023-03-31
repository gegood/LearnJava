package com.tyy.boot.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 对 swagger 的学习使用

@RestController
@RequestMapping("/hello")
@Api(tags = {"标记一个类为 swagger 文档资源aaa"})
@ApiModel(value = "对类的说明",description = "类的详细说明")
public class HelloCon {

    @ApiModelProperty(value = "对类的字段的说明，sum")
    private int sum;

    @GetMapping("one")
    @ApiOperation(value = "one方法的一些描述", notes = "详细描述")
    public String handle01(){
        return "Hello, Spring Boot 2!";
    }

    @GetMapping("add")
    @ApiOperation(value = "add方法的一些描述", notes = "详细描述")
    public int handle02(@ApiParam(value = "参数a的说明", required = true) int a, int b){
        return a+b;
    }

}