package com.example.demo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SentinelController {
    @GetMapping("/hello")
    @SentinelResource(value = "hello", blockHandler = "helloBlockHandler")
    public String hello() {
        log.info("hello");
        return "hello";
    }

    public String helloBlockHandler(BlockException e) {
        return "CustomerController invoke blockHandler";
    }
}
