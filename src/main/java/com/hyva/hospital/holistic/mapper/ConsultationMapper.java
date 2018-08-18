package com.hyva.hospital.holistic.mapper;

import com.hyva.hospital.holistic.entities.Consultation;
import com.hyva.hospital.holistic.pojo.ConsultationPojo;

public class ConsultationMapper {

    public static Consultation mapPojoToEntity(ConsultationPojo pojo){
        Consultation consultation=new Consultation();
        consultation.setId(pojo.getId());
        consultation.setAddress(pojo.getAddress());
        consultation.setAge(pojo.getAge());
        consultation.setEducation(pojo.getEducation());
        consultation.setEmail(pojo.getEmail());
        consultation.setGender(pojo.getGender());
        consultation.setMaritalStatus(pojo.getMaritalStatus());
        consultation.setMobileNumber(pojo.getMobileNumber());
        consultation.setOccupation(pojo.getOccupation());
        consultation.setPatientName(pojo.getPatientName());
        consultation.setAlternativeNumber(pojo.getAlternativeNumber());
        return consultation;
    }

}
