package com.hyva.hospital.holistic.respositories;

import com.hyva.hospital.holistic.entities.WorkingPlan;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkingPlanRepository extends JpaRepository<WorkingPlan, Long> {
    WorkingPlan findById(Long Id);
    List<WorkingPlan> findByDay(String day);
    List<WorkingPlan> findAllByDayContaining(String typeName, Pageable pageable);
    List<WorkingPlan> findAllByDayContaining(String typeName);
    WorkingPlan findFirstByDayContaining(String typeName, Sort sort);
    WorkingPlan findFirstBy(Sort sort);
    List<WorkingPlan> findAllBy(Pageable pageable);
    List<WorkingPlan> findByDayAndIdNotIn(String day,Long id);
}
