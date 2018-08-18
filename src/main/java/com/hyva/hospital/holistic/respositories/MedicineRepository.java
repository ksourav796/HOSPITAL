package com.hyva.hospital.holistic.respositories;

import com.hyva.hospital.holistic.entities.Medicine;
import com.hyva.hospital.holistic.entities.UnitOfMeasurement;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    List<Medicine> findAllByMedicineName(String medicineName);
    Medicine findById(Long Id);
    Medicine findByMedicineName(String Name);
    Medicine findByMedicineNameAndId(String Name,Long Id);
    List<Medicine> findAllByMedicineNameContaining(String typeName, Pageable pageable);
    List<Medicine> findAllByMedicineNameContaining(String typeName);
    Medicine findFirstByMedicineNameContaining(String typeName,Sort sort);
    Medicine findFirstBy(Sort sort);
    List<Medicine> findAllBy(Pageable pageable);
}
