package com.hyva.hospital.holistic.pojo;

import com.hyva.hospital.holistic.entities.Medicine;

import java.util.List;

public class PrescriptionPojo {
    private Long id;
    private String patient;
    private String medicine;
    private String duration;
    private String morning;
    private String afternoon;
    private String night;
    private String afterFood;
    private String beforeFood;
    private String afterFood1;
    private String beforeFood1;
    private String afterFood2;
    private String beforeFood2;
    private Long appointmentId;
    private Medicine prescription;
    private Long medicineId;
    private MedicinePojo medicinePojo;
    public List<MedicineListPojo> medicineList;
    private String unitOfMeasurement;
    private String quantity;
    private String medicines;

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public String getAfterFood1() {
        return afterFood1;
    }

    public void setAfterFood1(String afterFood1) {
        this.afterFood1 = afterFood1;
    }

    public String getBeforeFood1() {
        return beforeFood1;
    }

    public void setBeforeFood1(String beforeFood1) {
        this.beforeFood1 = beforeFood1;
    }

    public String getAfterFood2() {
        return afterFood2;
    }

    public void setAfterFood2(String afterFood2) {
        this.afterFood2 = afterFood2;
    }

    public String getBeforeFood2() {
        return beforeFood2;
    }

    public void setBeforeFood2(String beforeFood2) {
        this.beforeFood2 = beforeFood2;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public List<MedicineListPojo> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<MedicineListPojo> medicineList) {
        this.medicineList = medicineList;
    }

    public MedicinePojo getMedicinePojo() {
        return medicinePojo;
    }

    public void setMedicinePojo(MedicinePojo medicinePojo) {
        this.medicinePojo = medicinePojo;
    }

    public Medicine getPrescription() {
        return prescription;
    }

    public void setPrescription(Medicine prescription) {
        this.prescription = prescription;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMorning() {
        return morning;
    }

    public void setMorning(String morning) {
        this.morning = morning;
    }

    public String getAfternoon() {
        return afternoon;
    }

    public void setAfternoon(String afternoon) {
        this.afternoon = afternoon;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public String getAfterFood() {
        return afterFood;
    }

    public void setAfterFood(String afterFood) {
        this.afterFood = afterFood;
    }

    public String getBeforeFood() {
        return beforeFood;
    }

    public void setBeforeFood(String beforeFood) {
        this.beforeFood = beforeFood;
    }
}
