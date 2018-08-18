package com.hyva.hospital.holistic.mapper;

import com.hyva.hospital.holistic.entities.Settings;
import com.hyva.hospital.holistic.pojo.GeneralSettingsPojo;

public class GeneralSettingsMapper {
    public static Settings mapPojoToEntity(GeneralSettingsPojo pojo){
        Settings general=new Settings();
        general.setId(pojo.getId());
        general.setCompanyName(pojo.getCompanyName());
        general.setCompanyEmail(pojo.getCompanyEmail());
        general.setCompanyLink(pojo.getCompanyLink());
        general.setDateFormat(pojo.getDateFormat());
        general.setGoogleAnalyticsID(pojo.getGoogleAnalyticsID());
        return general;
    }
}
