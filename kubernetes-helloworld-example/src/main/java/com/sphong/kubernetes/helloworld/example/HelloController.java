package com.sphong.kubernetes.helloworld.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/")
    public String hello() {
        return "Hello World";
    }

    @RequestMapping("/services")
    public List<String> services() {
        return this.discoveryClient.getServices();
    }

}