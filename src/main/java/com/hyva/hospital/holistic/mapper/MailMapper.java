package com.hyva.hospital.holistic.mapper;

import com.hyva.hospital.holistic.entities.Mail;
import com.hyva.hospital.holistic.pojo.MailDTO;

import java.util.ArrayList;
import java.util.List;

public class MailMapper {
    public static List<MailDTO> mapMailEntityToPojo(List<Mail> mailList){
        List<MailDTO> list=new ArrayList<>();
        for(Mail mail:mailList) {
            MailDTO mailDTO = new MailDTO();
            mailDTO.setId(mail.getId());
            mailDTO.setUserName(mail.getUserName());
            mailDTO.setPassword(mail.getPassword());
            mailDTO.setSmtpHost(mail.getSmtpHost());
            mailDTO.setPort(mail.getPort());
            list.add(mailDTO);
        }
        return list;
    }
}
