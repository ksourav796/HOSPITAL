package com.hyva.hospital.holistic.respositories;
import com.hyva.hospital.holistic.entities.ServicesProviders;
import com.hyva.hospital.holistic.entities.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProvidersRepository extends JpaRepository<ServicesProviders, Long> {

    ServicesProviders findById(Long Id);
    List<ServicesProviders> findAllByIdUsersIn(List<Users> name, Pageable pageable);
    List<ServicesProviders> findAllByIdUsersIn(List<Users> name);
    ServicesProviders findFirstByIdUsersIn(List<Users> name, Sort sort);
    ServicesProviders findFirstBy(Sort sort);
    List<ServicesProviders> findAllBy(Pageable pageable);



}
