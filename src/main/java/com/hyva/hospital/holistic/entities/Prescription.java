package com.hyva.hospital.holistic.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Prescription", uniqueConstraints = @UniqueConstraint(columnNames = {"id"}))

public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String patient;
    @Column(length = 500000)
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
    private String medicines;
    private String unitOfMeasurement;
    private String quantity;

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
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

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }
}
