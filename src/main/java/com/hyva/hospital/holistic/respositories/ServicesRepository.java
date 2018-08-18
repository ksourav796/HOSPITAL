package com.hyva.hospital.holistic.respositories;

import com.hyva.hospital.holistic.entities.Services;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicesRepository extends JpaRepository<Services, Long> {
    Services findById(Long Id);

    Services findByName(String Name);

    Services findByNameNotInAndId(String Name, Long Id);
    List<Services> findAllByNameContaining(String typeName, Pageable pageable);
    List<Services> findAllByNameContaining(String typeName);
    Services findFirstByNameContaining(String typeName, Sort sort);
    Services findFirstBy(Sort sort);
    List<Services> findAllBy(Pageable pageable);

}

