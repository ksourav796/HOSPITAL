package com.hyva.hospital.holistic.respositories;

import com.hyva.hospital.holistic.entities.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PosEmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findByEmployeeId(Long employeeId);
    Employee findByEmployeeNameAndEmployeeIdNotIn(String employeeName, Long id);
    Employee findByEmployeeName(String employeeName);
    List<Employee> findAllByStatus(String status);
    List<Employee> findAllByStatusAndEmployeeName(String status, String searchText);
    List<Employee> findAllByEmployeeNameContainingAndStatus(String name, String status, Pageable pageable);
    List<Employee> findAllByEmployeeNameContainingAndStatus(String name, String status);
    Employee findFirstByEmployeeNameAndStatus(String name, String status, Sort sort);
    Employee findFirstByStatus(String status, Sort sort);
    List<Employee> findAllByStatus(String status, Pageable pageable);
}
