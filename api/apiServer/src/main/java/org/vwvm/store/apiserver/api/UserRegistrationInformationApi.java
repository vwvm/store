package org.vwvm.store.apiserver.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vwvm.store.apiserver.entity.UserRegistrationInformation;
import org.vwvm.store.apiserver.service.UserRegistrationInformationServer;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/userRegistrationInformation")
public class UserRegistrationInformationApi {

    private final UserRegistrationInformationServer userRegistrationInformationServer;
    @Autowired
    public UserRegistrationInformationApi(UserRegistrationInformationServer userRegistrationInformationServer){
        this.userRegistrationInformationServer = userRegistrationInformationServer;
    }


    @GetMapping
    public List<UserRegistrationInformation> getAll() {
        return userRegistrationInformationServer.findAll();
    }

    @PostMapping
    public UserRegistrationInformation createUser(@RequestBody UserRegistrationInformation userRegistrationInformation) {
        // save
        System.out.println(userRegistrationInformation);
        return userRegistrationInformationServer.save(userRegistrationInformation);
    }
}
