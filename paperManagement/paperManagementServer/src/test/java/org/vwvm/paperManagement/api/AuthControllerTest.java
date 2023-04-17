package org.vwvm.paperManagement.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.vwvm.paperManagement.PaperApplication;
import org.vwvm.paperManagement.commons.vo.ResultsVO;

import java.io.IOException;

@SpringBootTest(classes = PaperApplication.class)
@ExtendWith(SpringExtension.class)
class AuthControllerTest {

    AuthController authController = new AuthController();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void captcha() throws IOException {
        ResultsVO captcha = authController.captcha();
        System.out.println(captcha);

    }
}