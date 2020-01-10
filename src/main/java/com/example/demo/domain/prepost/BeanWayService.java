package com.example.demo.domain.prepost;

public class BeanWayService {
    public void init(){
        System.out.println("我是BeanWayService构造函数执行完之后执行的方法");
    }

    public BeanWayService(){
        super();//调用父类构造函数，不需要显示添加此方法，会默认调用
        System.out.println("初始化构造函数");
    }

    public void destory(){
        System.out.println("我是BeanWayService的Bean销毁之前调用的方法。");
    }
}
