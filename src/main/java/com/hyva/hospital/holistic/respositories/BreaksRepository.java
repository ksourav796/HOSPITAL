package com.hyva.hospital.holistic.respositories;

import com.hyva.hospital.holistic.entities.Breaks;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BreaksRepository extends JpaRepository<Breaks, Long> {
    Breaks findById(Long id);
    List<Breaks> findAllByDayContaining(String typeName, Pageable pageable);
    List<Breaks> findAllByDayContaining(String typeName);
    Breaks findFirstByDayContaining(String typeName, Sort sort);
    Breaks findFirstBy(Sort sort);
    List<Breaks> findAllBy(Pageable pageable);
    List<Breaks> findByDayAndStartTimeAndEndTimeAndIdNotIn(String day,String starttime,String endtime,Long id);
    List<Breaks> findByDayAndStartTimeAndEndTime(String day,String starttime,String endtime);
}
