package com.example.demo.domain.taskexecutor;

import org.springframework.scheduling.annotation.Async;
@Async
public class AsyncTaskService {


    public void run(){
        for(int i=0;i<=10;i++){
            test1(i);
            test2(i);
        }
    }


    public void test1(int i){
        System.out.println("测试1:"+i);
    }


    public void test2(int i)
    {
        System.out.println("测试2:"+i);
    }
}
