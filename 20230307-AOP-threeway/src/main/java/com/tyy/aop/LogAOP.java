package com.tyy.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAOP {

    /**
     * 前置通知，@Before的参数为目标通知类的表达式
     * JoinPoint 用来获取目标函数的参数及对象等信息
     */
    @Before("execution(public void com.tyy.beaop.SayHello.*(..))")
    public void MyBefore(JoinPoint jp){
        System.out.println("我是注解方式的前置通知");
        System.out.println("method="+jp.getSignature().getName()+",args数量="+jp.getArgs().length+",target="+jp.getTarget());
    }

    /**
     * 功能：后置通知
     * 注解形式实现AOP通知时，参数不能随便写，否则和目标函数对应不上，会报错
     * @param jp ：切入点目标对象
     * @param returningValue 返回值
     */
    @AfterReturning(pointcut = "execution(public void com.tyy.beaop.SayHello.sayhello())",returning="returningValue")
    public void MyAfterReturning(JoinPoint jp,Object returningValue){
        System.out.println("我是注解方式的后置通知");
        System.out.println("返回值是："+returningValue);
    }
}
