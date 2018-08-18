package com.hyva.hospital.holistic.mapper;

import com.hyva.hospital.holistic.entities.ServiceCategories;
import com.hyva.hospital.holistic.pojo.CategoriesPojo;

import java.util.ArrayList;
import java.util.List;

public class CategoriesMapper {
    public static ServiceCategories mapPojoToEntity(CategoriesPojo pojo){
        ServiceCategories serviceCategories =new ServiceCategories();
        serviceCategories.setId(pojo.getId());
        serviceCategories.setName(pojo.getName());
        serviceCategories.setDescription(pojo.getDescription());
        return serviceCategories;
    }

    public static List<CategoriesPojo> mapCategoryEntityToPojo(List<ServiceCategories> ctgryList){
        List<CategoriesPojo> list=new ArrayList<>();
        for(ServiceCategories ctgry:ctgryList) {
            CategoriesPojo pojo = new CategoriesPojo();
            pojo.setId(ctgry.getId());
            pojo.setName(ctgry.getName());
            pojo.setDescription(ctgry.getDescription());
            list.add(pojo);
        }
        return list;
    }
}
