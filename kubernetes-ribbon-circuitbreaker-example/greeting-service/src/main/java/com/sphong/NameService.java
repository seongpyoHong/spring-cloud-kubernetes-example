package com.sphong;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NameService {
    private final RestTemplate restTemplate;

    public NameService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getFallbackName", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")})
    public String getName1(int delay) {
        return this.restTemplate.getForObject(
                String.format("http://name-service-1/name?delay=%d", delay), String.class);
    }

    @HystrixCommand(fallbackMethod = "getFallbackName", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")})
    public String getName2(int delay) {
        return this.restTemplate.getForObject(
                String.format("http://name-service-2/name?delay=%d",delay),String.class);
    }

    private String getFallbackName(int delay) {
        return "fallback";
    }
}
