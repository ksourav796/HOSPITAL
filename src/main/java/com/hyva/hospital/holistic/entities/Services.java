package com.hyva.hospital.holistic.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;



@Entity
public class Services implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String name;
    private int duration;
    private double price;
    private String currency;
    private String description;
    private String availabilitiesType;
    private int flag;
    private int attendantsNumber;
    @OneToOne
    private ServiceCategories id_service_categories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvailabilitiesType() {
        return availabilitiesType;
    }

    public void setAvailabilitiesType(String availabilitiesType) {
        this.availabilitiesType = availabilitiesType;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getAttendantsNumber() {
        return attendantsNumber;
    }

    public void setAttendantsNumber(int attendantsNumber) {
        this.attendantsNumber = attendantsNumber;
    }

    public ServiceCategories getId_service_categories() {
        return id_service_categories;
    }

    public void setId_service_categories(ServiceCategories id_service_categories) {
        this.id_service_categories = id_service_categories;
    }


}
