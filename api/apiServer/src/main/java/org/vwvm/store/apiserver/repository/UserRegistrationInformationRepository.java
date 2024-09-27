package org.vwvm.store.apiserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vwvm.store.apiserver.entity.UserRegistrationInformation;

public interface UserRegistrationInformationRepository extends JpaRepository<UserRegistrationInformation, Long> {
}
