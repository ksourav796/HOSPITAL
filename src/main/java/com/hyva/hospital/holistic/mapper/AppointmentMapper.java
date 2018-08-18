package com.hyva.hospital.holistic.mapper;

import com.hyva.hospital.holistic.entities.Appointments;
import com.hyva.hospital.holistic.pojo.AppointmentPojo;

public class AppointmentMapper {
    public static Appointments mapPojoToEntity(AppointmentPojo pojo){
        Appointments appointment=new Appointments();
        appointment.setId(pojo.getId());
        appointment.setBookDatetime(pojo.getBook_datetime());
        appointment.setBookingDate(pojo.getBooking_date());
        appointment.setCompletedDate(pojo.getCompleted_date());
        appointment.setEndDatetime(pojo.getEnd_datetime());
        appointment.setEndtime(pojo.getEndtime());
        appointment.setFlag(pojo.getFlag());
        appointment.setHash(pojo.getHash());
        appointment.setIdGoogleCalendar(pojo.getId_google_calendar());
        appointment.setServices(pojo.getServices());
        appointment.setProviders(pojo.getProviderName());
        appointment.setCustomers(pojo.getCustomers());
        appointment.setIsUnavailable(pojo.getIs_unavailable());
        appointment.setLab(pojo.getLab());
        appointment.setLocation(pojo.getLocation());
        appointment.setNotes(pojo.getNotes());
        appointment.setNurseName(pojo.getNurse_name());
        appointment.setOtherFee(pojo.getOther_fee());
        appointment.setProfile(pojo.getProfile());
        appointment.setProfileFee(pojo.getProfile_fee());
        appointment.setRemark(pojo.getRemark());
        appointment.setReportDate(pojo.getReport_date());
        appointment.setStartDatetime(pojo.getStart_datetime());
        appointment.setStarttime(pojo.getStarttime());
        appointment.setAppointmentNo(pojo.getAppointmentNo());
        return appointment;
    }
}
