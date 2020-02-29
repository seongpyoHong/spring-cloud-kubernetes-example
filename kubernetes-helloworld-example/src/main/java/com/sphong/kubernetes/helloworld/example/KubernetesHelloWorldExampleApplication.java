package com.sphong.kubernetes.helloworld.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class KubernetesHelloWorldExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(KubernetesHelloWorldExampleApplication.class, args);
    }

}
