package org.vwvm.store.apiserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vwvm.store.apiserver.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
