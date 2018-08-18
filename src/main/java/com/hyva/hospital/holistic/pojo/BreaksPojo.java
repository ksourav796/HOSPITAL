package com.hyva.hospital.holistic.pojo;

import com.hyva.hospital.holistic.entities.Users;
import com.hyva.hospital.holistic.entities.WorkingPlan;

public class BreaksPojo {
    private Long id;
    private Users ea_users;
    private String day;
    private String startTime;
    private String endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getEa_users() {
        return ea_users;
    }

    public void setEa_users(Users ea_users) {
        this.ea_users = ea_users;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public WorkingPlan getWorkingPlan() {
        return workingPlan;
    }

    public void setWorkingPlan(WorkingPlan workingPlan) {
        this.workingPlan = workingPlan;
    }

    private WorkingPlan workingPlan;
}
