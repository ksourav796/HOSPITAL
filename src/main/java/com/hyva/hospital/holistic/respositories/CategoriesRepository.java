package com.hyva.hospital.holistic.respositories;

import com.hyva.hospital.holistic.entities.ServiceCategories;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<ServiceCategories, Long> {

    ServiceCategories findById(Long Id);
    ServiceCategories findByName(String Name);
    ServiceCategories findByNameAndId(String Name, Long Id);
    List<ServiceCategories> findAllByNameContaining(String typeName, Pageable pageable);
    List<ServiceCategories> findAllByNameContaining(String typeName);
    ServiceCategories findFirstByNameContaining(String typeName, Sort sort);
    ServiceCategories findFirstBy(Sort sort);
    List<ServiceCategories> findAllBy(Pageable pageable);

}
