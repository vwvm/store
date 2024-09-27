package org.vwvm.store.apiserver.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vwvm.store.apiserver.entity.UserRegistrationInformation;
import org.vwvm.store.apiserver.repository.UserRegistrationInformationRepository;
import org.vwvm.store.apiserver.service.UserRegistrationInformationServer;

import java.util.List;
@Service
public class UserRegistrationInformationServerImpl implements UserRegistrationInformationServer {

    private final UserRegistrationInformationRepository userRegistrationInformation;
    @Autowired
    public UserRegistrationInformationServerImpl(UserRegistrationInformationRepository userRegistrationInformation) {
        this.userRegistrationInformation = userRegistrationInformation;
    }
    @Override
    public List<UserRegistrationInformation> findAll() {
        return userRegistrationInformation.findAll();
    }
    @Override
    public UserRegistrationInformation save(UserRegistrationInformation user) {
        return userRegistrationInformation.save(user);
    }

    @Override
    public String myToString() {
        return null;
    }
}
