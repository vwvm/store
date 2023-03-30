package org.vwvm.store.myApplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h3>store</h3>
 * <p></p>
 *
 * @author : BlackBox
 * @date : 2023-03-23 23:33
 **/
@RestController
public class MyController {

    @GetMapping("/test")
    public String add(){
        return "add";
    }
}
