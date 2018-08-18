package com.hyva.hospital.holistic.respositories;

import com.hyva.hospital.holistic.entities.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository  extends CrudRepository<Patient, Long> {


    Patient findById(Long Id);
}
