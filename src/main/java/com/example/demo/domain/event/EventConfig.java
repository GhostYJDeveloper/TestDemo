package com.example.demo.domain.event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.demo.domain.event")
public class EventConfig {
    @Bean
    public DemoPublisher demoPublisher() {
        return new DemoPublisher("你好，我是DemoPublisher推送来的消息，请你接收并做下一步处理。");
    }
}
