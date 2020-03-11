package com.sphong.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyBean {

    private final MyConfig myConfig;

    private final DummyConfig dummyConfig;

    public MyBean(MyConfig myConfig, DummyConfig dummyConfig) {
        this.myConfig = myConfig;
        this.dummyConfig = dummyConfig;
    }

    @Scheduled(fixedDelay = 5000)
    public void hello() {
        System.out.println("The first message is: " + this.myConfig.getMessage());
        System.out.println("The other message is: " + this.dummyConfig.getMessage());
    }
}
