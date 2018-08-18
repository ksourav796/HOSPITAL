package com.hyva.hospital.holistic.respositories;

import com.hyva.hospital.holistic.entities.Customers;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository  extends CrudRepository<Customers, Long> {

    Customers findById(Long Id);
//    Customers findByFirstName(String firstName);
//    Customers findByFirstNameAndId(String First_name,Long Id);
    List<Customers> findAllByFirstNameContaining(String SearchText);
    List<Customers> findByOrderByIdDesc();
    List<Customers> findByFirstNameAndIdNotIn(String firstname,Long id);
    List<Customers> findByFirstName(String firstname);
}
