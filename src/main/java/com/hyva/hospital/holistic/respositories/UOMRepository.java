package com.hyva.hospital.holistic.respositories;

import com.hyva.hospital.holistic.entities.UnitOfMeasurement;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

import java.util.List;

public interface UOMRepository extends JpaRepository<UnitOfMeasurement, Long> {

    UnitOfMeasurement findByUnitOfMeasurementIdAndUnitOfMeasurementName(Long id,String uomname);
    UnitOfMeasurement findByUnitOfMeasurementName(String name);
    UnitOfMeasurement findByUnitOfMeasurementId(Long id);
    List<UnitOfMeasurement> findAllByUnitOfMeasurementNameContaining(String typeName, Pageable pageable);
    List<UnitOfMeasurement> findAllByUnitOfMeasurementNameContaining(String typeName);
    UnitOfMeasurement findFirstByUnitOfMeasurementNameContaining(String typeName,Sort sort);
    UnitOfMeasurement findFirstBy(Sort sort);
    List<UnitOfMeasurement> findAllBy(Pageable pageable);
}
