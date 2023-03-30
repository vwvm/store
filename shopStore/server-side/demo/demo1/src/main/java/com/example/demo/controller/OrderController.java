package com.example.demo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.demo.feign.SrFeignService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h3>store</h3>
 * <p></p>
 *
 * @author : BlackBox
 * @date : 2023-03-19 14:54
 **/
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    SrFeignService srFeignService;


    @RequestMapping("/add")
    public String add(){
        System.out.println("下单成功");
        String add = srFeignService.add();
        return "hello word" + add;
    }

    @SentinelResource(value = "addId",blockHandler = "addIdBlockHandler")
    @RequestMapping("/addId")
    public String addId(){
        System.out.println("下单成功");
        String add = srFeignService.addId(1);
        return "hello word" + add;
    }

    public String addIdBlockHandler(BlockException e){
        String add = srFeignService.addId(1);
        return "error" + add;
    }
}
