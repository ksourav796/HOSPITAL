package com.hyva.hospital.holistic.respositories;

import com.hyva.hospital.holistic.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface  InvoiceRepository extends JpaRepository<Invoice,Long> {
    Invoice findById(Long id);
    Invoice findByNameNotInAndId(String Name,Long Id);
    Invoice findByName(String Name);
    Invoice findByAppointmentId(Long id);

}
