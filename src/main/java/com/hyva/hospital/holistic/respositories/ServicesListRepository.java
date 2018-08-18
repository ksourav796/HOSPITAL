package com.hyva.hospital.holistic.respositories;

import com.hyva.hospital.holistic.entities.ServiesProviderslist;
import org.springframework.data.repository.CrudRepository;

public interface ServicesListRepository extends CrudRepository<ServiesProviderslist, Long> {

    ServiesProviderslist findById(Long Id);

}
