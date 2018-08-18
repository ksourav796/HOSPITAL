package com.hyva.hospital.holistic.mapper;

import com.hyva.hospital.holistic.entities.WorkingPlan;
import com.hyva.hospital.holistic.pojo.WorkingPlanPojo;

import java.util.ArrayList;
import java.util.List;

public class WorkingPlanMapper {
    public static WorkingPlan mapPojoToEntity(WorkingPlanPojo pojo){
        WorkingPlan work=new WorkingPlan();
        work.setId(pojo.getId());
        work.setDay(pojo.getDay());
        work.setStartTime(pojo.getStartTime());
        work.setEndTime(pojo.getEndTime());
        return work;
    }

    public static List<WorkingPlanPojo> mapEntityToPojo(List<WorkingPlan> planList){
        List<WorkingPlanPojo> list=new ArrayList<>();
        for(WorkingPlan ctgry:planList) {
            WorkingPlanPojo pojo = new WorkingPlanPojo();
            pojo.setId(ctgry.getId());
            pojo.setDay(ctgry.getDay());
            pojo.setStartTime(ctgry.getStartTime());
            pojo.setEndTime(ctgry.getEndTime());
            list.add(pojo);
        }
        return list;
    }
}
