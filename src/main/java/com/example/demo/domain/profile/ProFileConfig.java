package com.example.demo.domain.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProFileConfig {
    @Bean
    @Profile("dev")
    public DemoBean devDemoBean(){
        return new DemoBean("我是dev环境下创建的DemoBean");
    }
    @Bean
    @Profile("prod")
    public DemoBean prodDemoBean(){
        return  new DemoBean("我是prod环境下创建的DemoBean");
    }
}
