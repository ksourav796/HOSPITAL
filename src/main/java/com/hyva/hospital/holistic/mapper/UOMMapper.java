package com.hyva.hospital.holistic.mapper;

import com.hyva.hospital.holistic.entities.UnitOfMeasurement;
import com.hyva.hospital.holistic.pojo.UnitOfMeasurementPojo;

import java.util.ArrayList;
import java.util.List;

public class UOMMapper {
    public static UnitOfMeasurement mapPojoToEntity(UnitOfMeasurementPojo pojo){
        UnitOfMeasurement unitOfMeasurement=new UnitOfMeasurement();
        unitOfMeasurement.setUnitOfMeasurementId(pojo.getUnitOfMeasurementId());
        unitOfMeasurement.setUnitOfMeasurementName(pojo.getUnitOfMeasurementName());
        unitOfMeasurement.setUnitOfMeasurementStatus(pojo.getUnitOfMeasurementStatus());
        unitOfMeasurement.setUnitOfMeasurementDescription(pojo.getUnitOfMeasurementDescription());
        return unitOfMeasurement;
    }


    public static List<UnitOfMeasurementPojo> mapEntityToPojo(List<UnitOfMeasurement> UOMList){
        List<UnitOfMeasurementPojo> list=new ArrayList<>();
        for(UnitOfMeasurement uom:UOMList) {
            UnitOfMeasurementPojo unitOfMeasurementPojo = new UnitOfMeasurementPojo();
            unitOfMeasurementPojo.setUnitOfMeasurementDescription(uom.getUnitOfMeasurementDescription());
            unitOfMeasurementPojo.setUnitOfMeasurementId(uom.getUnitOfMeasurementId());
            unitOfMeasurementPojo.setUnitOfMeasurementName(uom.getUnitOfMeasurementName());
            unitOfMeasurementPojo.setUnitOfMeasurementStatus(uom.getUnitOfMeasurementStatus());
            list.add(unitOfMeasurementPojo);
        }
        return list;
    }
}
