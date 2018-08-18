package com.hyva.hospital.holistic.respositories;

import com.hyva.hospital.holistic.entities.Country;
import com.hyva.hospital.holistic.pojo.CountryDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CountryRepository extends JpaRepository<Country,Long> {
    Country findByCountryId(Long countryId);
    Country findByCountryName(String countryName);
    List<Country> findAllByStatus(String status);
    Country findByCountryNameAndCountryIdNotIn(String name, Long id);
    List<Country> findAllByCountryNameContainingAndStatus(String typeName, Pageable pageable,String status);
    List<Country> findAllByCountryNameContaining(String typeName);
    Country findFirstByCountryNameContainingAndStatus(String typeName,Sort sort,String status);
    Country findFirstByStatus(String status,Sort sort);
    List<Country> findAllByStatus(String status,Pageable pageable);
}
