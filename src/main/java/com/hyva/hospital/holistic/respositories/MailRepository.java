package com.hyva.hospital.holistic.respositories;

import com.hyva.hospital.holistic.entities.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<Mail, Long> {
    Mail findByUserName(String name);
}
