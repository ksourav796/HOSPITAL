package com.hyva.hospital.holistic.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class SecretariesProviders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long idUsersSecretary;
    private Long idUsersProvider;

    public Long getIdUsersSecretary() {
        return idUsersSecretary;
    }

    public void setIdUsersSecretary(Long idUsersSecretary) {
        this.idUsersSecretary = idUsersSecretary;
    }

    public Long getIdUsersProvider() {
        return idUsersProvider;
    }

    public void setIdUsersProvider(Long idUsersProvider) {
        this.idUsersProvider = idUsersProvider;
    }
}
