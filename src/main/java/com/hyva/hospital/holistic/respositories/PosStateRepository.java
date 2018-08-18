package com.hyva.hospital.holistic.respositories;

import com.hyva.hospital.holistic.entities.State;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PosStateRepository extends JpaRepository<State,Long> {
  List<State> findAllByCountryNameAndStatus(String countryName,String status);
  State findById (Long id);
  State findByStateName(String stateName);
  State findByStateNameAndIdNotIn(String stateName, Long id);
  List<State> findAllByStateCodeContainingOrStateNameContainingAndStatus(String code, String name, String status, Pageable pageable);
  List<State> findAllByStateCodeContainingOrStateNameContainingAndStatus(String code, String name, String status);
  State findFirstByStateCodeContainingOrStateNameContainingAndStatus(String code, String name, String status, Sort sort);
  State findFirstByStatus(String status, Sort sort);
  List<State> findAllByStatus(String status, org.springframework.data.domain.Pageable pageable);
}
