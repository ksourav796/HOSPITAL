/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hyva.hospital.holistic.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity

public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long employeeId;
    private String employeeCode;
    private String employeeName;
    private String employeeAddr;
    private String employeePhone;
    private String image;
    @Temporal(TemporalType.DATE)
    private Date employeeDOJ;
    @Temporal(TemporalType.DATE)
    private Date employeeDOB;
    private String gender;
    private String status;
    private boolean deliveryFlag;
    private boolean waiterFlag;

    @Temporal(TemporalType.DATE)
    private Date effectiveDate;

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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeAddr() {
        return employeeAddr;
    }

    public void setEmployeeAddr(String employeeAddr) {
        this.employeeAddr = employeeAddr;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public Date getEmployeeDOJ() {
        return employeeDOJ;
    }

    public void setEmployeeDOJ(Date employeeDOJ) {
        this.employeeDOJ = employeeDOJ;
    }

    public Date getEmployeeDOB() {
        return employeeDOB;
    }

    public void setEmployeeDOB(Date employeeDOB) {
        this.employeeDOB = employeeDOB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
