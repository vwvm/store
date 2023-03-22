
package com.example.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/stock")
public class SrController {

    @RequestMapping("/abatement")
    public String add(){
        System.out.println("扣减库存");
        return "扣减成功2";
    }

    @RequestMapping("/abatement/{id}")
    public String addId(@PathVariable("id") Integer id) {
        System.out.println("扣减库存" + id);
        return "扣减成功2/" + id;
    }
}
