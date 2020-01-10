package com.example.demo.common;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.example.demo.domain.aop")
@EnableAspectJAutoProxy
public class AopConfig {
}
