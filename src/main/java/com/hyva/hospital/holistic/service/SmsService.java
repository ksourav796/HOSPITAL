/**
 * Created by harshitha on 18/06/06.
 */
package com.hyva.hospital.holistic.service;
import com.hyva.hospital.holistic.entities.SMSServer;
import com.hyva.hospital.holistic.respositories.SMSServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

@Service
public class SmsService {
    @Autowired
    SMSServerRepository smsServerRepository;

    public  String sendSms(String appNo, String mobileNumber,String message) throws IOException {
        String sentance = message+"AppointmentNo is"+ appNo.toString();
        SMSServer smsServer = new SMSServer();
        smsServer=smsServerRepository.findOne(1L);
        if(smsServer!=null) {
            URL url = new URL(smsServer.getSmsUrl() + "?method=sms" + "&api_key=" + smsServer.getApiKey() + "&to=" + mobileNumber + "&sender=" + smsServer.getSenderId() + "&message=" + sentance);
            URLConnection conn = url.openConnection();
            conn.getInputStream();
        }
        return null;
    }
}
