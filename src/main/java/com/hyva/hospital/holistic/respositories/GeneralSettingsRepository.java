package com.hyva.hospital.holistic.respositories;

import com.hyva.hospital.holistic.entities.Settings;
import org.springframework.data.repository.CrudRepository;

public interface GeneralSettingsRepository extends CrudRepository<Settings, Long> {
    Settings findById(Long Id);
//    Settings findByCompanyNameAndId(String CompanyName,long Id );
}
