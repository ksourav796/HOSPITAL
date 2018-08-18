package com.hyva.hospital.holistic.mapper;

import com.hyva.hospital.holistic.entities.ServiesProviderslist;
import com.hyva.hospital.holistic.pojo.ProvidersPojo;

public class ServicesListProvidersMapper {
    public static ServiesProviderslist mapPojoToEntity(ProvidersPojo pojo){
        ServiesProviderslist services=new ServiesProviderslist();
        services.setId(pojo.getId());

        services.setServices(pojo.getId_services());
        services.setUsers(pojo.getId_users());

        return services;
    }
}
