package com.sphong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GreetingController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private NameService nameService;

    @GetMapping("/services")
    public String getAllService() {
        return discoveryClient.getServices().toString();
    }

    @RequestMapping("/greeting1")
    public String getGreeting1(@RequestParam(value = "delay", defaultValue = "0") int delay) {
        return String.format("Hello from %s!", this.nameService.getName1(delay));
    }

    @RequestMapping("/greeting2")
    public String getGreeting(@RequestParam(value = "delay", defaultValue = "0") int delay) {
        return String.format("Hello from %s!", this.nameService.getName2(delay));
    }
}
