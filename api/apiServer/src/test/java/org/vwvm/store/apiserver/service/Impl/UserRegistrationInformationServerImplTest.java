package org.vwvm.store.apiserver.service.Impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.vwvm.store.apiserver.ApiServerApplication;
import org.vwvm.store.apiserver.entity.UserRegistrationInformation;
import org.vwvm.store.apiserver.service.UserRegistrationInformationServer;

@SpringBootTest(classes = ApiServerApplication.class)
@ExtendWith(SpringExtension.class)
class UserRegistrationInformationServerImplTest {

    @Autowired
    UserRegistrationInformationServer userRegistrationInformationServer;

    @Test
    void findAll() {
        userRegistrationInformationServer.findAll();
    }

    @Test
    void save() {
        UserRegistrationInformation userRegistrationInformation = new UserRegistrationInformation();
        userRegistrationInformation.setName("这是测试的用户");
        userRegistrationInformation.setTelephone("12345678910");
        userRegistrationInformation.setProfession("测试的公司");
        userRegistrationInformationServer.save(userRegistrationInformation);
    }

    @Test
    void myToString() {
    }
}
