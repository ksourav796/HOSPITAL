package com.hyva.hospital.holistic.respositories;

import com.hyva.hospital.holistic.entities.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByUsernameAndPassword(String  userName, String Password);
    Users findByEmail(String Email);
    Users findByUsername(String name);
    Users findById(Long Id);

    List<Users> findByFirstNameContaining(String firstName);

    List<Users> findByOrderByIdDesc();
    Users findFirstBy(Sort sort);
    List<Users> findAllBy(Pageable pageable);
    List<Users> findByFirstNameAndIdNotIn(String firstname,Long id);
    List<Users> findByFirstName(String firstname);


}
