package com.hyva.hospital.holistic.respositories;

import com.hyva.hospital.holistic.entities.Prescription;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrescriptionRepository extends CrudRepository<Prescription ,Long> {
    Prescription findByMedicine(String medicine);
    Prescription findById(Long id);
   List<Prescription> findByAppointmentId(Long id);
//    Prescription findByNameNotInAndId(String Name,Long Id);
}
