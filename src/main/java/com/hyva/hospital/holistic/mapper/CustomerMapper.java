package com.hyva.hospital.holistic.mapper;

import com.hyva.hospital.holistic.entities.Customers;
import com.hyva.hospital.holistic.pojo.CustomersPojo;

public class CustomerMapper {
    public static Customers mapPojoToEntity(CustomersPojo pojo) {
        Customers customers = new Customers();
        customers.setId(pojo.getId());
        customers.setFirstName(pojo.getFirstName());
        customers.setLastName(pojo.getLastName());
        customers.setEmail(pojo.getEmail());
        customers.setPhoneNumber(pojo.getPhoneNumber());
        customers.setMobileNumber(pojo.getMobileNumber());
        customers.setAge( pojo.getAge() );
        customers.setCity(pojo.getCity());
        customers.setMaritalStatus( pojo.getMaritalStatus() );
        customers.setOccupation( pojo.getOccupation() );
        customers.setGender(pojo.getGender());
        customers.setStateName(pojo.getStateName());
        customers.setCountryName(pojo.getCountryName());
        customers.setAddress(pojo.getAddress());
        customers.setZipCode(pojo.getZipCode());
        customers.setNotes(pojo.getNotes());
        customers.setDate( pojo.getDate() );
        customers.setUhid( pojo.getUhid() );
        customers.setHistory( pojo.getHistory());
        customers.setCheifComplaints( pojo.getCheifComplaints() );
        return customers;
    }

}
