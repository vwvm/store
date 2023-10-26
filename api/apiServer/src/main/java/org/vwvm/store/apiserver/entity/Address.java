package org.vwvm.store.apiserver.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 30)
    private Long userId;
    private String province;
    private String city;
    private String street;



}
