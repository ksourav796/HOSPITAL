package com.hyva.hospital.holistic.mapper;

import com.google.gson.Gson;
import com.hyva.hospital.holistic.entities.Invoice;
import com.hyva.hospital.holistic.pojo.InvoicePojo;

import java.util.ArrayList;
import java.util.List;

public class InvoiceMapper {
    public static Invoice mapPojoToEntity(InvoicePojo pojo) {
        Invoice invoice = new Invoice();
        invoice.setId(pojo.getId());
        invoice.setName(pojo.getName());
        invoice.setPrice(pojo.getPrice());
        Gson json =new Gson();
        invoice.setServices(json.toJson(pojo.getInvoiceList()));
        invoice.setAppointmentId(pojo.getAppointmentId());
        return invoice;
    }


    public static List<InvoicePojo> mapEntityToPojo(List<Invoice> List){
        List<InvoicePojo> list=new ArrayList<>();
        for(Invoice invoice:List) {
            InvoicePojo pojo = new InvoicePojo();
            pojo.setId(invoice.getId());
            pojo.setPrice(invoice.getPrice());
            pojo.setAppointmentId(invoice.getAppointmentId());
            pojo.setDuration(invoice.getDuration());
            pojo.setName(invoice.getName());
            list.add(pojo);
        }
        return list;
    }
}
