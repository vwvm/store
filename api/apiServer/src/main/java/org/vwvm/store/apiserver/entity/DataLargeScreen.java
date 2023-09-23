package org.vwvm.store.apiserver.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class DataLargeScreen {

    @Id
    @GeneratedValue
    private Long id;
}
