package org.vwvm.paperManagement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h3>store</h3>
 * <p>测试</p>
 *
 * @author : BlackBox
 * @date : 2023-04-02 09:54
 **/
@RestController
public class UserController {

    @GetMapping("/user")
    public String user(){
        return "嘿嘿和";
    }
}
