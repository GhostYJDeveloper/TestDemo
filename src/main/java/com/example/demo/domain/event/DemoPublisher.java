package com.example.demo.domain.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

public class DemoPublisher implements ApplicationEventPublisherAware {
    @Autowired
    ApplicationContext applicationContext;
    private String msg;

    public DemoPublisher(){

    }

    public DemoPublisher(String msg) {
        this.msg = msg;
    }

    public void publish(String msg) {
        this.msg = msg;
        //applicationContext.publishEvent(new DemoEvent(this,msg));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new DemoEvent(this, msg));
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
