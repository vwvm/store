package com.example.demo.controller;

import com.example.demo.feign.SrFeignService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @RequestMapping("/addId")
    public String addId(){
        System.out.println("下单成功");
        String add = srFeignService.addId(1);
        return "hello word" + add;
    }
}
