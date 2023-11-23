package org.vwvm.store.apiserver.service;

import org.vwvm.store.apiserver.entity.UserRegistrationInformation;

import java.util.List;

public interface UserRegistrationInformationServer {


    List<UserRegistrationInformation> findAll();

    UserRegistrationInformation save(UserRegistrationInformation user);

    String myToString();
}
