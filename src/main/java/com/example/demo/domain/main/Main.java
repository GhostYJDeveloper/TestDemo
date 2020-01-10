package com.example.demo.domain.main;

import com.example.demo.common.AopConfig;
import com.example.demo.domain.annotation.DemoConfig;
import com.example.demo.domain.annotation.DemoService;
import com.example.demo.domain.aop.DemoAnnotationService;
import com.example.demo.domain.aop.DemoMethodService;
import com.example.demo.domain.aware.AwareConfig;
import com.example.demo.domain.aware.AwareService;
import com.example.demo.domain.conditional.ConditionConfig;
import com.example.demo.domain.conditional.ListService;
import com.example.demo.domain.el.ElConfig;
import com.example.demo.domain.event.DemoPublisher;
import com.example.demo.domain.event.EventConfig;
import com.example.demo.domain.prepost.BeanWayService;
import com.example.demo.domain.prepost.JSR250WayService;
import com.example.demo.domain.prepost.PrePostConfig;
import com.example.demo.domain.profile.DemoBean;
import com.example.demo.domain.profile.ProFileConfig;
import com.example.demo.domain.taskexecutor.AsyncTaskService;
import com.example.demo.domain.taskexecutor.TaskExecutorConfig;
import com.example.demo.domain.taskscheduler.SchedulerTaskService;
import com.example.demo.domain.taskscheduler.TaskSchedulerConfig;
import org.springframework.beans.factory.Aware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;

public class Main {
    public static void main(String[] args) {
//        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AopConfig.class);
//
//        DemoAnnotationService demoAnnotationService=context.getBean(DemoAnnotationService.class);
//        DemoMethodService demoMethodService=context.getBean(DemoMethodService.class);
//        demoAnnotationService.add();
//        demoMethodService.add();
//        context.close();

//        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(ElConfig.class);
//        ElConfig resourceService=context.getBean(ElConfig.class);
//        resourceService.outputResource();
//        context.close();

//        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(PrePostConfig.class);
//        BeanWayService beanWayService=context.getBean(BeanWayService.class);
//        JSR250WayService jsr250WayService=context.getBean(JSR250WayService.class);
//        context.close();

//        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
//        context.getEnvironment().setActiveProfiles("prod");
//        context.register(ProFileConfig.class);
//        context.refresh();
//        DemoBean demoBean=context.getBean(DemoBean.class);
//        System.out.println(demoBean.getContent());
//        context.close();

        //AnnotationConfigApplicationContext是通过另一种形式，而不是xml的配置方式启动spring容器，这边是非web运行的方式
        //如果是web方式，项目下自带的被打上SpringBootApplication注解的DemoApplication应该会把它做的事做一遍
        //AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
        //三种调用的方式，第一种是通过Bean来创建对象 通过有参构造函数，并且继承ApplicationEventPublisherAware来发布事件
        //第二种和第一种的发布形式一样，不过是通过new来手动创建对象
        //第三种是通过无参构造函数的方式创建，通过调用ApplicationContext的方法来发布事件，
        // 要使用第三种，首先把EventConfig中的@Bean的方法去掉，在DemoPublisher中加上@Component注解
//        DemoPublisher demoPublisher = context.getBean(DemoPublisher.class);
        //DemoPublisher demoPublisher=new DemoPublisher("你好，我是DemoPublisher推送来的消息，请你接收并做下一步处理。");
//        demoPublisher.publish("你好，我是DemoPublisher推送来的消息，请你接收并做下一步处理。");
        //context.close();

//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AwareConfig.class);
//        AwareService awareService=context.getBean(AwareService.class);
//        awareService.outputResult();
//        context.close();

//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
////        AsyncTaskService asyncTaskService=context.getBean(AsyncTaskService.class);
////        for (int i=0;i<10;i++){
////            asyncTaskService.test1(i);
////            asyncTaskService.test2(i);
////        }
////        //asyncTaskService.run();
////        context.close();

//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskSchedulerConfig.class);
//        SchedulerTaskService schedulerTaskService=context.getBean(SchedulerTaskService.class);
//        context.close();

//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConditionConfig.class);
//        ListService listService=context.getBean(ListService.class);
//        System.out.println(context.getEnvironment().getProperty("os.name")+"系统下的列表命令为"+listService.showListCmd());
//        context.close();
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
//        DemoService demoService=context.getBean(DemoService.class);
//        demoService.outputResult();
//        context.close();

//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HelloServiceAutoConfiguration.class);
//        HelloService helloService=context.getBean(HelloService.class);
//        System.out.println(helloService.sayHello());
//        context.close();
    }
}
