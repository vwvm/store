package org.vwvm.paperManagement.api.meail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.vwvm.paperManagement.PaperApplication;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PaperApplication.class)
@ExtendWith(SpringExtension.class)
class MailServiceTest {

    @Autowired
    MailService mailService;

    @Test
    public void sendSimpleMailTest() throws InterruptedException {
        //调用定义的发送文本邮件的方法
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1);
            mailService.sendSimpleMail("1647010108@qq.com", "这是第" + i+1 +"封邮件", "这是邮件内容");
        }

    }

}