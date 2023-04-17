package org.vwvm.paperManagement.api.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h3>store</h3>
 * <p>错误响应</p>
 *
 * @author : BlackBox
 * @date : 2023-04-02 14:32
 **/
@RestController
@RequestMapping("/error")
public class ErrorController {

    @ResponseBody
    @GetMapping("/403")
    public String error403(){
        return "权限不足";
    }



}
