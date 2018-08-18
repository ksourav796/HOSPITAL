package com.hyva.hospital.holistic.pojo;

import com.hyva.hospital.holistic.entities.CompanyInfo;

import javax.persistence.OneToOne;

public class UnitOfMeasurementPojo {
    private Long UnitOfMeasurementId;
    private String UnitOfMeasurementName;
    private String UnitOfMeasurementDescription;
    private String UnitOfMeasurementStatus;

    public Long getUnitOfMeasurementId() {
        return UnitOfMeasurementId;
    }

    public void setUnitOfMeasurementId(Long unitOfMeasurementId) {
        UnitOfMeasurementId = unitOfMeasurementId;
    }

    public String getUnitOfMeasurementName() {
        return UnitOfMeasurementName;
    }

    public void setUnitOfMeasurementName(String unitOfMeasurementName) {
        UnitOfMeasurementName = unitOfMeasurementName;
    }

    public String getUnitOfMeasurementDescription() {
        return UnitOfMeasurementDescription;
    }

    public void setUnitOfMeasurementDescription(String unitOfMeasurementDescription) {
        UnitOfMeasurementDescription = unitOfMeasurementDescription;
    }

    public String getUnitOfMeasurementStatus() {
        return UnitOfMeasurementStatus;
    }

    public void setUnitOfMeasurementStatus(String unitOfMeasurementStatus) {
        UnitOfMeasurementStatus = unitOfMeasurementStatus;
    }

}
