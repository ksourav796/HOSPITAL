package com.hyva.hospital.holistic.mapper;

import com.hyva.hospital.holistic.entities.Scheduler;
import com.hyva.hospital.holistic.pojo.SchedulerPojo;

import java.util.ArrayList;
import java.util.List;

public class SchedulerMapper {

    public static Scheduler mapPojoToEntity(SchedulerPojo pojo){
        Scheduler scheduler=new Scheduler();
        scheduler.setId(pojo.getId());
        scheduler.setAddress(pojo.getAddress());
        scheduler.setAge(pojo.getAge());
        scheduler.setEducation(pojo.getEducation());
        scheduler.setEmail(pojo.getEmail());
        scheduler.setGender(pojo.getGender());
        scheduler.setMaritalStatus(pojo.getMaritalStatus());
        scheduler.setMobileNumber(pojo.getMobileNumber());
        scheduler.setOccupation(pojo.getOccupation());
        scheduler.setFirstName(pojo.getFirstName());
        scheduler.setLastName(pojo.getLastName());
        scheduler.setCountry(pojo.getCountry());
        scheduler.setCity(pojo.getCity());
        scheduler.setState(pojo.getState());
        scheduler.setZipCode(pojo.getZipCode());
        scheduler.setStatus(pojo.getStatus());
        scheduler.setStartTime(pojo.getStartTime());
        scheduler.setBook_datetime(pojo.getBook_datetime());
        scheduler.setAlternativeNumber(pojo.getAlternativeNumber());
        return scheduler;
    }

    public static List<SchedulerPojo> mapSchedularEntityToPojo(List<Scheduler> schedulerList){
        List<SchedulerPojo> list=new ArrayList<>();
        for(Scheduler scheduler:schedulerList) {
            SchedulerPojo schedulerPojo = new SchedulerPojo();
            schedulerPojo.setId(scheduler.getId());
            schedulerPojo.setFirstName(scheduler.getFirstName());
            schedulerPojo.setLastName(scheduler.getLastName());
            schedulerPojo.setAddress(scheduler.getAddress());
            schedulerPojo.setAge(scheduler.getAge());
            schedulerPojo.setCity(scheduler.getCity());
            schedulerPojo.setCountry(scheduler.getCountry());
            schedulerPojo.setEducation(scheduler.getEducation());
            schedulerPojo.setEmail(scheduler.getEmail());
            schedulerPojo.setGender(scheduler.getGender());
            schedulerPojo.setMaritalStatus(scheduler.getMaritalStatus());
            schedulerPojo.setAlternativeNumber(scheduler.getAlternativeNumber());
            schedulerPojo.setOccupation(scheduler.getOccupation());
            schedulerPojo.setNotes(scheduler.getNotes());
            schedulerPojo.setZipCode(scheduler.getZipCode());
            schedulerPojo.setState(scheduler.getState());
            schedulerPojo.setStatus(scheduler.getStatus());
            schedulerPojo.setStartTime(scheduler.getStartTime());
            schedulerPojo.setBook_datetime(scheduler.getBook_datetime());
            schedulerPojo.setMobileNumber(scheduler.getMobileNumber());
            list.add(schedulerPojo);
        }
        return list;
    }

}
