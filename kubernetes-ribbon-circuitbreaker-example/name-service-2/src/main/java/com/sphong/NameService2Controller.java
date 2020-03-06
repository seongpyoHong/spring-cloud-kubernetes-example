package com.sphong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NameService2Controller {
    private final String hostName = System.getenv("HOSTNAME");

    @GetMapping("/")
    public String ribbonPing() {
        return this.hostName;
    }

    @GetMapping("/name")
    public String getName(
            @RequestParam(value = "delay", defaultValue = "0") int delayValue) {
        delay(delayValue);
        return this.hostName;
    }

    private void delay(int delayValue) {
        try {
            Thread.sleep(delayValue);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
