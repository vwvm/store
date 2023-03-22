package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <h3>store</h3>
 * <p>配置Feign接口</p>
 *
 * @author : BlackBox
 * @date : 2023-03-21 21:44
 **/
@FeignClient(name = "demo2", path = "/stock")
public interface SrFeignService {

    @RequestMapping("/abatement")
    String add();

    @RequestMapping("/abatement/{id}")
    String addId(@PathVariable("id") Integer id);
}
