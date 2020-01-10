package com.example.demo.domain.prepost;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class JSR250WayService {
    @PostConstruct
    public void init(){
        System.out.println("我是JSR250WayService构造函数执行完之后执行的方法");
    }

    public JSR250WayService(){
        System.out.println("初始化构造函数");
    }

    @PreDestroy
    public void destory(){
        System.out.println("我是JSR250WayService的Bean销毁之前调用的方法。");
    }
}
