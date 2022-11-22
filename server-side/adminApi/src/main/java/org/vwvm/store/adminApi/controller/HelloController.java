package org.vwvm.store.adminApi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/hello")
    public String hello(Map<String, Object> map) {
        //通过 map 向前台页面传递数据
        System.out.println("\n \n \n");
        map.put("name", "郑锦鸿");
        return "login";
    }

    @RequestMapping("/login")
    protected ModelAndView loginView(HttpServletRequest request, HttpServletResponse response){


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("message", "Hello World, Hello Kitty");


        return modelAndView;

    }



}