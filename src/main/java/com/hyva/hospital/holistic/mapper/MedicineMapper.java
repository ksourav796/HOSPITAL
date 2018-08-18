package com.hyva.hospital.holistic.mapper;

import com.hyva.hospital.holistic.entities.Medicine;
import com.hyva.hospital.holistic.pojo.MedicinePojo;

import java.util.ArrayList;
import java.util.List;

public class MedicineMapper {
    public static Medicine mapPojoToEntity(MedicinePojo pojo){
        Medicine medicine=new Medicine();
        medicine.setId(pojo.getId());
        medicine.setMedicineName(pojo.getMedicineName());
        medicine.setCode(pojo.getCode());
        medicine.setUnitOfMeasurement(pojo.getUnitOfMeasurement());
        return medicine;
    }

    public static List<MedicinePojo> mapMedicineEntityToPojo(List<Medicine> List){
        List<MedicinePojo> list=new ArrayList<>();
        for(Medicine medicine:List) {
            MedicinePojo pojo = new MedicinePojo();
            pojo.setId(medicine.getId());
            pojo.setMedicineName(medicine.getMedicineName());
            pojo.setUnitOfMeasurement(medicine.getUnitOfMeasurement());
            pojo.setCode(medicine.getCode());
            list.add(pojo);
        }
        return list;
    }
}
