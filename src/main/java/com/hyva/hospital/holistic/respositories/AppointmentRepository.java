package com.hyva.hospital.holistic.respositories;

import com.hyva.hospital.holistic.entities.Appointments;
import com.hyva.hospital.holistic.entities.Customers;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointments,Long> {
    Appointments findById(Long Id);
    Appointments findFirstBy(Sort sort);
    List<Appointments> findAllBy(Pageable pageable);
    List<Appointments> findAllByCustomersIn(List<Customers> name, Pageable pageable);
    List<Appointments> findAllByCustomersIn(List<Customers> name);
    Appointments findFirstByCustomersIn(Customers name, Sort sort);
    List<Appointments> findAllByBookDatetime(Date date);
    List<Appointments> findAllByCustomers(Customers customers);
}
