package com.hyva.hospital.holistic.mapper;

import com.hyva.hospital.holistic.entities.Services;
import com.hyva.hospital.holistic.pojo.ServicesPojo;

import java.util.ArrayList;
import java.util.List;

public class ServiceMapper {
    public static Services mapPojoToEntity(ServicesPojo pojo){
        Services services =new Services();
        services.setId(pojo.getId());
        services.setAttendantsNumber(pojo.getAttendants_number());
        services.setAvailabilitiesType(pojo.getAvailabilities_type());
        services.setCurrency(pojo.getCurrency());
        services.setDescription(pojo.getDescription());
        services.setDuration(pojo.getDuration());
        services.setName(pojo.getName());
        services.setPrice(pojo.getPrice());
        services.setFlag(Integer.parseInt(pojo.getFlag()));
        services.setId_service_categories(pojo.getId_service_categories());
        return services;
    }

    public static List<ServicesPojo> mapServiceEntityToPojo(List<Services> List){
        List<ServicesPojo> list=new ArrayList<>();
        for(Services services:List) {
            ServicesPojo pojo = new ServicesPojo();
            pojo.setId(services.getId());
            pojo.setAttendants_number(services.getAttendantsNumber());
            pojo.setAvailabilities_type(services.getAvailabilitiesType());
            pojo.setCurrency(services.getCurrency());
            pojo.setDescription(services.getDescription());
            pojo.setDuration(services.getDuration());
            pojo.setName(services.getName());
            pojo.setPrice(services.getPrice());
            pojo.setFlag(String.valueOf(services.getFlag()));
            if(services.getId_service_categories()!=null) {
                pojo.setCategoryId( services.getId_service_categories().getId() );
            }
            pojo.setId_service_categories(services.getId_service_categories());
            list.add(pojo);
        }
        return list;
    }
}
