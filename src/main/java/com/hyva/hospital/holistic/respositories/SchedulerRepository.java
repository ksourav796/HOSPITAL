package com.hyva.hospital.holistic.respositories;

import com.hyva.hospital.holistic.entities.Scheduler;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface SchedulerRepository extends JpaRepository<Scheduler,Long> {

    Scheduler findById(Long Id);
    Scheduler findByFirstNameAndIdNotIn(String Name,Long Id);
    Scheduler findByFirstName(String Name);
    List<Scheduler> findAllByStatus(String status);
    List<Scheduler> findAllByStatusAndFirstName(String status, String searchText);
    List<Scheduler> findAllByFirstNameContainingAndStatus(String name, String status, Pageable pageable);
    List<Scheduler> findAllByFirstNameContainingAndStatus(String name, String status);
    Scheduler findFirstByFirstNameAndStatus(String name, String status, Sort sort);
    Scheduler findFirstByStatus(String status, Sort sort);
    List<Scheduler> findAllByStatus(String status, Pageable pageable);

}
