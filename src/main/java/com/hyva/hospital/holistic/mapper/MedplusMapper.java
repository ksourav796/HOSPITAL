package com.hyva.hospital.holistic.mapper;

import com.hyva.hospital.holistic.entities.Medplus;
import com.hyva.hospital.holistic.pojo.MedPlusPojo;

public class MedplusMapper {

    public static Medplus mapPojoToEntity(MedPlusPojo pojo){
        Medplus medplus=new Medplus();
        medplus.setMedicine(pojo.getMedicine());
        medplus.setQuantity(pojo.getQuantity());
        medplus.setId(pojo.getId());
        return medplus;
    }
}
