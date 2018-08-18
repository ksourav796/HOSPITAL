package com.hyva.hospital.holistic.pojo;

import com.hyva.hospital.holistic.entities.Customers;

import java.util.List;


public class patientPojo {

    private Long id;
    private String pulse;
    private String repiratoryrate;
    private String bp;
    private String temperature;
    private String pallor;
    private String weight;
    private String height;
    private String dob;
    private String bmi;
    private String rs;
    private String cvs;
    private String cnspns;
    private String jointmobility;
    private String prayogshaleyaparikshana;
    private String hetu;
    private String bheda;
    private String vyadhi;
    private String sadhyasadhatva;
    private String bowels;
    private String appetite;
    private String micturation;
    private String sleep;
    private String chikitsa;
    private Customers customers;
    private String investigation;
    private String differentialDiagnosis;
    private String diagnosis;
    private String prognosis;
    private String treatment;
    private String sympton;
    private String hfpc;
    private String associatedComplaints;
    private String medicineHistory;
    private String familyHistory;
    private String pastHistoryOfPatient;
    private String OBGHistory;
    private String occupationalHistory;
    private String medicine;
    private String duration;
    private String morning;
    private String afternoon;
    private String night;
    private String afterFood;
    private String beforeFood;

    public List<MedicineListPojo> getMedicineList() {
        return medicineList;
    }

    public void setMedicineList(List<MedicineListPojo> medicineList) {
        this.medicineList = medicineList;
    }

    private MedicinePojo medicinePojo;
    public List<MedicineListPojo> medicineList;

    public MedicinePojo getMedicinePojo() {
        return medicinePojo;
    }

    public void setMedicinePojo(MedicinePojo medicinePojo) {
        this.medicinePojo = medicinePojo;
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

    private String nextVisit;

    public String getNextVisit() {
        return nextVisit;
    }

    public void setNextVisit(String nextVisit) {
        this.nextVisit = nextVisit;
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

    public String getMorning() {
        return morning;
    }

    public void setMorning(String morning) {
        this.morning = morning;
    }

    public String getAfternoon() {
        return afternoon;
    }

    public void setAfternoon(String afternoon) {
        this.afternoon = afternoon;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public String getRepiratoryrate() {
        return repiratoryrate;
    }

    public void setRepiratoryrate(String repiratoryrate) {
        this.repiratoryrate = repiratoryrate;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPallor() {
        return pallor;
    }

    public void setPallor(String pallor) {
        this.pallor = pallor;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public String getCvs() {
        return cvs;
    }

    public void setCvs(String cvs) {
        this.cvs = cvs;
    }

    public String getCnspns() {
        return cnspns;
    }

    public void setCnspns(String cnspns) {
        this.cnspns = cnspns;
    }

    public String getJointmobility() {
        return jointmobility;
    }

    public void setJointmobility(String jointmobility) {
        this.jointmobility = jointmobility;
    }

    public String getPrayogshaleyaparikshana() {
        return prayogshaleyaparikshana;
    }

    public void setPrayogshaleyaparikshana(String prayogshaleyaparikshana) {
        this.prayogshaleyaparikshana = prayogshaleyaparikshana;
    }

    public String getHetu() {
        return hetu;
    }

    public void setHetu(String hetu) {
        this.hetu = hetu;
    }

    public String getBheda() {
        return bheda;
    }

    public void setBheda(String bheda) {
        this.bheda = bheda;
    }

    public String getVyadhi() {
        return vyadhi;
    }

    public void setVyadhi(String vyadhi) {
        this.vyadhi = vyadhi;
    }

    public String getSadhyasadhatva() {
        return sadhyasadhatva;
    }

    public void setSadhyasadhatva(String sadhyasadhatva) {
        this.sadhyasadhatva = sadhyasadhatva;
    }

    public String getBowels() {
        return bowels;
    }

    public void setBowels(String bowels) {
        this.bowels = bowels;
    }

    public String getAppetite() {
        return appetite;
    }

    public void setAppetite(String appetite) {
        this.appetite = appetite;
    }

    public String getMicturation() {
        return micturation;
    }

    public void setMicturation(String micturation) {
        this.micturation = micturation;
    }

    public String getSleep() {
        return sleep;
    }

    public void setSleep(String sleep) {
        this.sleep = sleep;
    }

    public String getChikitsa() {
        return chikitsa;
    }

    public void setChikitsa(String chikitsa) {
        this.chikitsa = chikitsa;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public String getInvestigation() {
        return investigation;
    }

    public void setInvestigation(String investigation) {
        this.investigation = investigation;
    }

    public String getDifferentialDiagnosis() {
        return differentialDiagnosis;
    }

    public void setDifferentialDiagnosis(String differentialDiagnosis) {
        this.differentialDiagnosis = differentialDiagnosis;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPrognosis() {
        return prognosis;
    }

    public void setPrognosis(String prognosis) {
        this.prognosis = prognosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getSympton() {
        return sympton;
    }

    public void setSympton(String sympton) {
        this.sympton = sympton;
    }

    public String getHfpc() {
        return hfpc;
    }

    public void setHfpc(String hfpc) {
        this.hfpc = hfpc;
    }

    public String getAssociatedComplaints() {
        return associatedComplaints;
    }

    public void setAssociatedComplaints(String associatedComplaints) {
        this.associatedComplaints = associatedComplaints;
    }

    public String getMedicineHistory() {
        return medicineHistory;
    }

    public void setMedicineHistory(String medicineHistory) {
        this.medicineHistory = medicineHistory;
    }

    public String getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory;
    }

    public String getPastHistoryOfPatient() {
        return pastHistoryOfPatient;
    }

    public void setPastHistoryOfPatient(String pastHistoryOfPatient) {
        this.pastHistoryOfPatient = pastHistoryOfPatient;
    }

    public String getOBGHistory() {
        return OBGHistory;
    }

    public void setOBGHistory(String OBGHistory) {
        this.OBGHistory = OBGHistory;
    }

    public String getOccupationalHistory() {
        return occupationalHistory;
    }

    public void setOccupationalHistory(String occupationalHistory) {
        this.occupationalHistory = occupationalHistory;
    }
}
