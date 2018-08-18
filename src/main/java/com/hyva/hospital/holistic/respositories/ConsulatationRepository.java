package com.hyva.hospital.holistic.respositories;

import com.hyva.hospital.holistic.entities.Consultation;
import org.springframework.data.repository.CrudRepository;

public interface ConsulatationRepository extends CrudRepository<Consultation,Long> {

    Consultation findById(Long Id);
    Consultation findByPatientName(String Name);
    Consultation findByPatientNameAndId(String Name,Long Id);

}
