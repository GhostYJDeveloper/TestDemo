package com.example.demo.domain.prepost;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrePostConfig {
    @Bean(initMethod ="init",destroyMethod = "destory")
    public BeanWayService beanWayService(){
        return new BeanWayService();
    }

    @Bean
    public JSR250WayService jsr250WayService(){
        return  new JSR250WayService();
    }
}
