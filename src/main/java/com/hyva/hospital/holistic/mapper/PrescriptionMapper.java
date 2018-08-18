package com.hyva.hospital.holistic.mapper;

import com.google.gson.Gson;
import com.hyva.hospital.holistic.entities.Medicine;
import com.hyva.hospital.holistic.entities.Prescription;
import com.hyva.hospital.holistic.pojo.MedicinePojo;
import com.hyva.hospital.holistic.pojo.PrescriptionPojo;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionMapper {
    public static Prescription mapPojoToEntity(PrescriptionPojo pojo){
        Prescription prescription=new Prescription();
        prescription.setMedicine(pojo.getMedicine());
        prescription.setDuration(pojo.getDuration());
        prescription.setAfterFood(pojo.getAfterFood());
        prescription.setBeforeFood(pojo.getBeforeFood());
        prescription.setMorning(pojo.getMorning());
        prescription.setNight(pojo.getNight());
        prescription.setAfternoon(pojo.getAfternoon());
        prescription.setAppointmentId(pojo.getAppointmentId());
        prescription.setPatient(pojo.getPatient());
        prescription.setId(pojo.getId());
        prescription.setAfterFood1(pojo.getAfterFood1());
        prescription.setBeforeFood1(pojo.getBeforeFood1());
        prescription.setBeforeFood2(pojo.getBeforeFood2());
        prescription.setAfterFood2(pojo.getAfterFood2());
        prescription.setUnitOfMeasurement(pojo.getUnitOfMeasurement());
        prescription.setQuantity(pojo.getQuantity());
        Gson json =new Gson();
        prescription.setMedicines(json.toJson(pojo.getMedicineList()));
        return prescription;
    }


    public static List<PrescriptionPojo> mapEntityToPojo(List<Prescription> List){
        List<PrescriptionPojo> list=new ArrayList<>();
        for(Prescription prescription:List) {
            PrescriptionPojo pojo = new PrescriptionPojo();
            pojo.setId(prescription.getId());
            pojo.setMedicine(prescription.getMedicine());
            pojo.setAfterFood(prescription.getAfterFood());
            pojo.setAfterFood1(prescription.getAfterFood1());
            pojo.setAfterFood2(prescription.getAfterFood2());
            pojo.setAfternoon(prescription.getAfternoon());
            pojo.setAppointmentId(prescription.getAppointmentId());
            pojo.setBeforeFood(prescription.getBeforeFood());
            pojo.setBeforeFood1(prescription.getBeforeFood1());
            pojo.setBeforeFood2(prescription.getBeforeFood2());
            pojo.setDuration(prescription.getDuration());
            pojo.setMedicineId(prescription.getId());
            pojo.setMorning(prescription.getMorning());
            pojo.setNight(prescription.getNight());
            pojo.setPatient(prescription.getPatient());
            pojo.setQuantity(prescription.getQuantity());
            pojo.setUnitOfMeasurement(prescription.getUnitOfMeasurement());
            pojo.setMedicines(prescription.getMedicines());
            list.add(pojo);
        }
        return list;
    }
}
