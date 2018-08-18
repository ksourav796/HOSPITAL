package com.hyva.hospital.holistic.pojo;

import java.util.Date;

public class EmployeeDTO {
    private Long EmployeeId;
    private String EmployeeCode;
    private String EmployeeName;
    private String EmployeeAddr;
    private String EmployeePhone;
    private Date EmployeeDOJ;
    private Date EmployeeDOB;
    private String Gender;
    private String status;
    private boolean waiterFlag;
    private boolean deliveryFlag;
    private String EmployeeAccountMaster;
    private Date effectiveDate;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isWaiterFlag() {
        return waiterFlag;
    }

    public void setWaiterFlag(boolean waiterFlag) {
        this.waiterFlag = waiterFlag;
    }

    public boolean isDeliveryFlag() {
        return deliveryFlag;
    }

    public void setDeliveryFlag(boolean deliveryFlag) {
        this.deliveryFlag = deliveryFlag;
    }

    public String getEmployeeAccountMaster() {
        return EmployeeAccountMaster;
    }

    public void setEmployeeAccountMaster(String employeeAccountMaster) {
        EmployeeAccountMaster = employeeAccountMaster;
    }

    public Long getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(Long employeeId) {
        EmployeeId = employeeId;
    }

    public String getEmployeeCode() {
        return EmployeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        EmployeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getEmployeeAddr() {
        return EmployeeAddr;
    }

    public void setEmployeeAddr(String employeeAddr) {
        EmployeeAddr = employeeAddr;
    }

    public String getEmployeePhone() {
        return EmployeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        EmployeePhone = employeePhone;
    }

    public Date getEmployeeDOJ() {
        return EmployeeDOJ;
    }

    public void setEmployeeDOJ(Date employeeDOJ) {
        EmployeeDOJ = employeeDOJ;
    }

    public Date getEmployeeDOB() {
        return EmployeeDOB;
    }

    public void setEmployeeDOB(Date employeeDOB) {
        EmployeeDOB = employeeDOB;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
