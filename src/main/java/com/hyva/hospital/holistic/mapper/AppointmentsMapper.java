package com.hyva.hospital.holistic.mapper;

import com.hyva.hospital.holistic.entities.Appointments;
import com.hyva.hospital.holistic.entities.Customers;
import com.hyva.hospital.holistic.pojo.AppointmentPojo;

import java.util.ArrayList;
import java.util.List;

public class AppointmentsMapper {
    public static Customers mapPojoToEntity(AppointmentPojo pojo){
        Customers customer=new Customers();
        customer.setId(pojo.getId());
        customer.setFirstName(pojo.getFirstName());
        customer.setLastName(pojo.getLastName());
        customer.setGender(pojo.getGender());
        customer.setAge(pojo.getAge());
        customer.setStarttime(pojo.getStarttime());
        customer.setEmail(pojo.getEmail());
        customer.setPhoneNumber(pojo.getPhoneNumber());
        customer.setAddress(pojo.getAddress());
        customer.setZipCode(pojo.getZipCode());
        customer.setNotes(pojo.getNotes());
        customer.setCity(pojo.getCity());
        customer.setStateName(pojo.getStateName());
        customer.setCountryName(pojo.getCountryName());
        return customer;
    }


    public static Appointments mapPojoToAppointEntity(AppointmentPojo pojo){
        Appointments appointments = new Appointments();
        appointments.setId(pojo.getId());
        appointments.setServices(pojo.getServices());
        appointments.setProviders(pojo.getProviderName());
        appointments.setStarttime(pojo.getStarttime());
        appointments.setAppointmentNo(pojo.getAppointmentNo());
        appointments.setInvoiceNo( pojo.getInvoiceNo() );
        appointments.setEndtime(pojo.getEndtime());
        appointments.setRemark(pojo.getRemark());
        appointments.setStarttime(pojo.getStarttime());
        appointments.setHistory( pojo.getHistory() );
        appointments.setCheifComplaints( pojo.getCheifComplaints() );
//        appointments.setServices(pojo.getServicesId());
//        appointments.setServicesProviders(pojo.getServicesProviders());
        appointments.setBookDatetime(pojo.getBook_datetime());
        appointments.setNotes( pojo.getNotes() );
        appointments.setCustomers(pojo.getCustomers());
        appointments.setStatus(pojo.getStatus());
        appointments.setAppointmentCode(pojo.getAppointmentCode());


        return appointments;
    }

    public static List<AppointmentPojo> mapEntiyToPojo(List<Appointments> appList){
        List<AppointmentPojo> list=new ArrayList<>();
        for(Appointments apnt:appList){
            AppointmentPojo pojo=new AppointmentPojo();
            pojo.setId(apnt.getId());
            pojo.setServices(apnt.getServices());
            pojo.setProviderName(apnt.getProviders());
            pojo.setStarttime(apnt.getStarttime());
            pojo.setAppointmentNo(apnt.getAppointmentNo());
            pojo.setInvoiceNo( apnt.getInvoiceNo() );
            pojo.setEndtime(apnt.getEndtime());
            pojo.setRemark(apnt.getRemark());
            pojo.setBook_datetime(apnt.getBookDatetime());
            pojo.setCustomers(apnt.getCustomers());
            pojo.setFirstName(apnt.getCustomers().getFirstName());
            pojo.setStatus(apnt.getStatus());
            pojo.setAppointmentCode(apnt.getAppointmentCode());
            list.add(pojo);
        }
        return  list;
    }


}
