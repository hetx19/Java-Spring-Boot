package com.spring.core;

import com.spring.core.controller.DemoController;
import com.spring.core.repository.DemoRepository;
import com.spring.core.service.DemoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DemoClient {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        DemoController demoController = context.getBean(DemoController.class);
        System.out.println(demoController.Hello());

        DemoService demoService = context.getBean(DemoService.class);
        System.out.println(demoService.Hello());

        DemoRepository demoRepository = context.getBean(DemoRepository.class);
        System.out.println(demoRepository.Hello());
    }
}
