package com.hyva.hospital.holistic.respositories;

import com.hyva.hospital.holistic.entities.Medplus;
import org.springframework.data.repository.CrudRepository;

public interface MedplusRepository extends CrudRepository<Medplus,Long> {
    Medplus findByMedicine(String Name);
    Medplus findByMedicineAndId(String Name,Long Id);
}
