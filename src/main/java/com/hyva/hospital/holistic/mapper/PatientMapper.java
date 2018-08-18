package com.hyva.hospital.holistic.mapper;

import com.google.gson.Gson;
import com.hyva.hospital.holistic.entities.Patient;
import com.hyva.hospital.holistic.pojo.patientPojo;

public class PatientMapper {

    public static Patient mapPojoToEntity(patientPojo pojo){
        Patient patient=new Patient();
        patient.setId(pojo.getId());
        patient.setAppetite(pojo.getAppetite());
        patient.setAssociatedComplaints(pojo.getAssociatedComplaints());
        patient.setBheda(pojo.getBheda());
        patient.setBmi(pojo.getBmi());
        patient.setBowels(pojo.getBowels());
        patient.setBp(pojo.getBp());
        patient.setChikitsa(pojo.getChikitsa());
        patient.setCnspns(pojo.getCnspns());
        patient.setCustomers(pojo.getCustomers());
        patient.setCvs(pojo.getCvs());
        patient.setDiagnosis(pojo.getDiagnosis());
        patient.setDifferentialDiagnosis(pojo.getDifferentialDiagnosis());
        patient.setDob(pojo.getDob());
        patient.setFamilyHistory(pojo.getFamilyHistory());
        patient.setHeight(pojo.getHeight());
        patient.setHetu(pojo.getHetu());
        patient.setHfpc(pojo.getHfpc());
        patient.setInvestigation(pojo.getInvestigation());
        patient.setJointmobility(pojo.getJointmobility());
        patient.setMedicineHistory(pojo.getMedicineHistory());
        patient.setMicturation(pojo.getMicturation());
        patient.setOBGHistory(pojo.getOBGHistory());
        patient.setOccupationalHistory(pojo.getOccupationalHistory());
        patient.setPallor(pojo.getPallor());
        patient.setPastHistoryOfPatient(pojo.getPastHistoryOfPatient());
        patient.setPrayogshaleyaparikshana(pojo.getPrayogshaleyaparikshana());
        patient.setPrognosis(pojo.getPrognosis());
        patient.setPulse(pojo.getPulse());
        patient.setRepiratoryrate(pojo.getRepiratoryrate());
        patient.setRs(pojo.getRs());
        patient.setSadhyasadhatva(pojo.getSadhyasadhatva());
        patient.setSleep(pojo.getSleep());
        patient.setSympton(pojo.getSympton());
        patient.setTemperature(pojo.getTemperature());
        patient.setTreatment(pojo.getTreatment());
        patient.setVyadhi(pojo.getVyadhi());
        patient.setWeight(pojo.getWeight());
        patient.setMedicine(pojo.getMedicine());
        patient.setDuration(pojo.getDuration());
        patient.setMorning(pojo.getMorning());
        patient.setAfternoon(pojo.getAfternoon());
        patient.setAfternoon(pojo.getAfternoon());
        patient.setNight(pojo.getNight());
        patient.setAfterFood(pojo.getAfterFood());
        patient.setBeforeFood(pojo.getBeforeFood());
        Gson json =new Gson();
        patient.setMedicines(json.toJson(pojo.getMedicineList()));
        return patient;
    }
}
