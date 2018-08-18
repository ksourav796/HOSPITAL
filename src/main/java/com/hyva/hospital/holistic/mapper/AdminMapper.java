package com.hyva.hospital.holistic.mapper;

import com.hyva.hospital.holistic.entities.Users;
import com.hyva.hospital.holistic.pojo.AdminsPojo;

public class AdminMapper {
    public static Users mapPojoToEntity(AdminsPojo pojo){
        Users admin=new Users();
        admin.setId(pojo.getId());
        admin.setFirstName(pojo.getFirstName());
        admin.setLastName(pojo.getLastName());
        admin.setEmail(pojo.getEmail());
        admin.setPhoneNumber(pojo.getPhoneNumber());
        admin.setMobileNumber(pojo.getMobileNumber());
        admin.setAddress(pojo.getAddress());
        admin.setZipCode(pojo.getZipCode());
        admin.setNotes(pojo.getNotes());
        admin.setCity(pojo.getCity());
        admin.setState(pojo.getState());
        return admin;
    }
}
