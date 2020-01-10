package com.example.demo.domain.taskscheduler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan("com.example.demo.domain.taskscheduler")
@EnableScheduling
public class TaskSchedulerConfig {

//    @Bean
//    public SchedulerTaskService schedulerTaskService(){
//        return new SchedulerTaskService();
//    }
}
