package com.hyva.hospital.holistic.pojo;

public class MedicineListPojo {
    private String medicine;
    private String duration;
    private boolean morning;
    private boolean afternoon;
    private boolean night;
    private String afterFood;
    private String beforeFood;
    private String afterFood1;
    private String beforeFood1;
    private String afterFood2;
    private String beforeFood2;
    private double qty;
    private String uom;
    private String  mrngTablets;
    private String aftnTablets;
    private String nightTablets;

    public String getMrngTablets() {
        return mrngTablets;
    }

    public void setMrngTablets(String mrngTablets) {
        this.mrngTablets = mrngTablets;
    }

    public String getAftnTablets() {
        return aftnTablets;
    }

    public void setAftnTablets(String aftnTablets) {
        this.aftnTablets = aftnTablets;
    }

    public String getNightTablets() {
        return nightTablets;
    }

    public void setNightTablets(String nightTablets) {
        this.nightTablets = nightTablets;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getAfterFood1() {
        return afterFood1;
    }

    public void setAfterFood1(String afterFood1) {
        this.afterFood1 = afterFood1;
    }

    public String getBeforeFood1() {
        return beforeFood1;
    }

    public void setBeforeFood1(String beforeFood1) {
        this.beforeFood1 = beforeFood1;
    }

    public String getAfterFood2() {
        return afterFood2;
    }

    public void setAfterFood2(String afterFood2) {
        this.afterFood2 = afterFood2;
    }

    public String getBeforeFood2() {
        return beforeFood2;
    }

    public void setBeforeFood2(String beforeFood2) {
        this.beforeFood2 = beforeFood2;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isMorning() {
        return morning;
    }

    public void setMorning(boolean morning) {
        this.morning = morning;
    }

    public boolean isAfternoon() {
        return afternoon;
    }

    public void setAfternoon(boolean afternoon) {
        this.afternoon = afternoon;
    }

    public boolean isNight() {
        return night;
    }

    public void setNight(boolean night) {
        this.night = night;
    }

    public String getAfterFood() {
        return afterFood;
    }

    public void setAfterFood(String afterFood) {
        this.afterFood = afterFood;
    }

    public String getBeforeFood() {
        return beforeFood;
    }

    public void setBeforeFood(String beforeFood) {
        this.beforeFood = beforeFood;
    }
}
