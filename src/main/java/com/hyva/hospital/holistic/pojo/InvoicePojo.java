package com.hyva.hospital.holistic.pojo;

import com.hyva.hospital.holistic.entities.Services;

import java.util.List;

public class InvoicePojo {

    private Long id;
    private String name;
    private int duration;
    private double price;
    private Services invoice;
    private Long serviceId;
    private ServicesPojo servicesPojo;
    public List<InvoiceListPojo> invoiceList;
    private Long appointmentId;

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public List<InvoiceListPojo> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<InvoiceListPojo> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public ServicesPojo getServicesPojo() {
        return servicesPojo;
    }

    public void setServicesPojo(ServicesPojo servicesPojo) {
        this.servicesPojo = servicesPojo;
    }

    public Services getInvoice() {
        return invoice;
    }

    public void setInvoice(Services invoice) {
        this.invoice = invoice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
