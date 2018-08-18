package com.hyva.hospital.holistic.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class UserSettings implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long idUsers;
    private String username;
    private String password;
    private String salt;
    private String workingPlan;
    private int notifications;
    private int googleSync;
    private String googleToken;
    private String googleCalendar;
    private int syncPastDays;
    private int syncFutureDays;
    private String calendarView;

    public Long getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(Long idUsers) {
        this.idUsers = idUsers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getWorkingPlan() {
        return workingPlan;
    }

    public void setWorkingPlan(String workingPlan) {
        this.workingPlan = workingPlan;
    }

    public int getNotifications() {
        return notifications;
    }

    public void setNotifications(int notifications) {
        this.notifications = notifications;
    }

    public int getGoogleSync() {
        return googleSync;
    }

    public void setGoogleSync(int googleSync) {
        this.googleSync = googleSync;
    }

    public String getGoogleToken() {
        return googleToken;
    }

    public void setGoogleToken(String googleToken) {
        this.googleToken = googleToken;
    }

    public String getGoogleCalendar() {
        return googleCalendar;
    }

    public void setGoogleCalendar(String googleCalendar) {
        this.googleCalendar = googleCalendar;
    }

    public int getSyncPastDays() {
        return syncPastDays;
    }

    public void setSyncPastDays(int syncPastDays) {
        this.syncPastDays = syncPastDays;
    }

    public int getSyncFutureDays() {
        return syncFutureDays;
    }

    public void setSyncFutureDays(int syncFutureDays) {
        this.syncFutureDays = syncFutureDays;
    }

    public String getCalendarView() {
        return calendarView;
    }

    public void setCalendarView(String calendarView) {
        this.calendarView = calendarView;
    }
}
