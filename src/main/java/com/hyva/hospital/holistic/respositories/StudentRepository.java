package com.hyva.hospital.holistic.respositories;

import com.hyva.hospital.holistic.entities.Customers;
import com.hyva.hospital.holistic.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findByStudentId(Long studentId);
    Student findByStudentName(String studentName);

    Student findByStudentNameAndStudentIdNotIn(String name, Long id);
//    List<Student> findByStudentNameAndIdNotIn(String firstname, Long id);
//    List<Student> findByStudeName(String firstname);


}