package com.hyva.hospital.holistic.mapper;

import com.hyva.hospital.holistic.entities.ServicesProviders;
import com.hyva.hospital.holistic.pojo.ProvidersPojo;

import java.util.ArrayList;
import java.util.List;

public class ProvidersMapper {
    public static ServicesProviders mapPojoToEntity(ProvidersPojo pojo){
        ServicesProviders services=new ServicesProviders();
        services.setId(pojo.getId());
        services.setIdUsers(pojo.getId_users());
        return services;
    }
    public static List<ProvidersPojo> mapEntityToPojo(List<ServicesProviders> providers) {
        List<ProvidersPojo> list = new ArrayList<>();
        for (ServicesProviders pojo : providers) {
            ProvidersPojo providersPojo=new ProvidersPojo();
            providersPojo.setId(pojo.getId());
            providersPojo.setId_users(pojo.getIdUsers());
            list.add(providersPojo);
        }
        return list;

    }
}
