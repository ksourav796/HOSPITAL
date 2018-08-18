package com.hyva.hospital.holistic.mapper;

import com.hyva.hospital.holistic.entities.Breaks;
import com.hyva.hospital.holistic.pojo.BreaksPojo;

import java.util.ArrayList;
import java.util.List;

public class BreaksMapper {
    public static Breaks mapPojoToEntity(BreaksPojo pojo){
        Breaks breaks=new Breaks();
        breaks.setId(pojo.getId());
        breaks.setDay(pojo.getDay());
        breaks.setStartTime(pojo.getStartTime());
        breaks.setEndTime(pojo.getEndTime());
        return breaks;
    }
    public static List<BreaksPojo> mapEntityToPojo(List<Breaks> breaksList){
        List<BreaksPojo> list=new ArrayList<>();
        for(Breaks breaks:breaksList){
            BreaksPojo pojo=new BreaksPojo();
            pojo.setId(breaks.getId());
            pojo.setDay(breaks.getDay());
            pojo.setStartTime(breaks.getStartTime());
            pojo.setEndTime(breaks.getEndTime());
            list.add(pojo);
        }
        return list;
    }
}

