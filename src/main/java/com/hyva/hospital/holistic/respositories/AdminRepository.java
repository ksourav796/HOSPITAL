package com.hyva.hospital.holistic.respositories;

import com.hyva.hospital.holistic.entities.Users;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository <Users, Long>{
    Users findById(Long Id);
    Users findByFirstName(String firstName);
    Users findByFirstNameAndId(String firstName, Long Id);
}
