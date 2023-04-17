package org.vwvm.store.api.controller;


import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
@CrossOrigin
@Api(value = "提供用户的登录和管理功能", tags = "用户管理")
@RequestMapping("/user")
public class UserController {


    @GetMapping("/te")
    public Date te(String msg) {
        Date date = new Date();
        try (FileWriter fileWriter = new FileWriter("log.txt", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(String.valueOf(date));
            bufferedWriter.write("她启动了: " + msg + "\n ");
        } catch (Exception e) {
        }

        return date;
    }

    @GetMapping("/start")
    public Date start() {
        Date date = new Date();
        try (FileWriter fileWriter = new FileWriter("log.txt", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(String.valueOf(date));
            bufferedWriter.write("她点了开始游戏\n");
        } catch (Exception e) {
        }

        return date;
    }

    @PostMapping("/start")
    public Date poStart(@RequestBody HashMap<String, String> map) {
        System.out.println(map);
        Date date = new Date();
        try (FileWriter fileWriter = new FileWriter("log.txt", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(String.valueOf(date));
            bufferedWriter.write("她点了" + map + "\n");
        } catch (Exception e) {
        }

        return date;
    }

    @GetMapping("/to100")
    public Date to100() {
        Date date = new Date();
        try (FileWriter fileWriter = new FileWriter("log.txt", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(String.valueOf(date));
            bufferedWriter.write("她到了100\n");
        } catch (Exception e) {
        }

        return date;
    }

    @GetMapping("/to200")
    public Date to200() {
        Date date = new Date();
        try (FileWriter fileWriter = new FileWriter("log.txt", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(String.valueOf(date));
            bufferedWriter.write("她到了200\n");
        } catch (Exception e) {
        }
        return date;
    }

}
