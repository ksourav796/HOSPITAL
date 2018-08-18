package com.hyva.hospital.holistic.pojo;

/**
 * Created by admin on 22-Nov-17.
 */
public class SmsConfiguratorDto {
    private Long formsetupId;
    private String message;
    private String name;
    private String phoneNumber;
    private String type;
    private String appointmentNo;

    public String getAppointmentNo() {
        return appointmentNo;
    }

    public void setAppointmentNo(String appointmentNo) {
        this.appointmentNo = appointmentNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getFormsetupId() {
        return formsetupId;
    }

    public void setFormsetupId(Long formsetupId) {
        this.formsetupId = formsetupId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
