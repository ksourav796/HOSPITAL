package com.hyva.hospital.holistic.pojo;

import com.hyva.hospital.holistic.entities.Services;
import com.hyva.hospital.holistic.entities.Users;


public class ProvidersPojo {
    private Long id;
    private Users id_users;
    private Services id_services;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private String countryName;
    private Long providerId;
    private String zipCode;
    private String notes;
    private String password;
    private String username;
    private String retypepassword;
    private String calenderType;
    private String flagType;
    private int flag;
    private String serviceName;
    private String[] serviceslist;



    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getId_users() {
        return id_users;
    }

    public void setId_users(Users id_users) {
        this.id_users = id_users;
    }

    public Services getId_services() {
        return id_services;
    }

    public void setId_services(Services id_services) {
        this.id_services = id_services;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRetypepassword() {
        return retypepassword;
    }

    public void setRetypepassword(String retypepassword) {
        this.retypepassword = retypepassword;
    }

    public String getCalenderType() {
        return calenderType;
    }

    public void setCalenderType(String calenderType) {
        this.calenderType = calenderType;
    }

    public String getFlagType() {
        return flagType;
    }

    public void setFlagType(String flagType) {
        this.flagType = flagType;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String[] getServiceslist() {
        return serviceslist;
    }

    public void setServiceslist(String[] serviceslist) {
        this.serviceslist = serviceslist;
    }
}
