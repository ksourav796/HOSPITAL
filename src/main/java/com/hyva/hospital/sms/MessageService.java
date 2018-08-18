package com.hyva.hospital.sms;
import com.hyva.hospital.holistic.entities.FormSetUp;
import com.hyva.hospital.holistic.entities.SMSServer;
import com.hyva.hospital.holistic.entities.SmsConfigurator;
import com.hyva.hospital.holistic.mapper.UserMapper;
import com.hyva.hospital.holistic.pojo.SMSServerDTO;
import com.hyva.hospital.holistic.pojo.SmsConfiguratorDto;
import com.hyva.hospital.holistic.respositories.PosFormSetupRepository;
import com.hyva.hospital.holistic.respositories.SMSServerRepository;
import com.hyva.hospital.holistic.respositories.SmsConfiguratorRepository;
import com.hyva.hospital.holistic.service.SmsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    PosFormSetupRepository posFormSetupRepository;
    @Autowired
    SmsConfiguratorRepository smsConfiguratorRepository;
    @Autowired
    SMSServerRepository smsServerRepository;
    @Autowired
    SmsService smsService;

    @Transactional
    public String getMobNumber(SmsConfiguratorDto smsConfiguratorDto) throws IOException {
        FormSetUp formterms =posFormSetupRepository.findAllByTypename(smsConfiguratorDto.getType());
        SmsConfigurator smsConfigurator=smsConfiguratorRepository.findOne(formterms.getSmsId());
        if (StringUtils.equals(smsConfigurator.getEnableSms(),"true")) {
            smsService.sendSms(smsConfiguratorDto.getAppointmentNo(), smsConfiguratorDto.getPhoneNumber(), smsConfigurator.getMessage());
        }
        return smsConfiguratorDto.getType();
    }

    public SmsConfiguratorDto saveSmsConfig(SmsConfiguratorDto smsConfiguratorDto) {
        FormSetUp formsetup = posFormSetupRepository.findOne(smsConfiguratorDto.getFormsetupId());
        try {
                SmsConfigurator smsConfigurator = smsConfiguratorRepository.findOne(formsetup.getSmsId());
                smsConfigurator.setMessage(smsConfiguratorDto.getMessage());
                smsConfiguratorRepository.save(smsConfigurator);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return smsConfiguratorDto;
    }
    public List<SMSServerDTO> getSmsServerList() {
        List<SMSServer> list = smsServerRepository.findAll();
        List<SMSServerDTO> smsServerDTOList = UserMapper.mapSMSServerEntityToPojo(list);
        return smsServerDTOList;
    }
    public SMSServer saveSMSDetails(SMSServerDTO smsServerDTO) {
        SMSServer smsServer = UserMapper.mapSMSServerPojoToEntity(smsServerDTO);
        smsServerRepository.save(smsServer);
        return smsServer;
    }
    public List<SMSServerDTO> editSMSDetails() {
        List<SMSServer> smsServer = new ArrayList<>();
        smsServer = smsServerRepository.findAll();
        List<SMSServerDTO> smsServerList = UserMapper.mapSMSServerEntityToPojo(smsServer);
        return smsServerList;
    }
    public void deleteSMSDetails(Long id) {
        smsServerRepository.delete(id);
    }
}
