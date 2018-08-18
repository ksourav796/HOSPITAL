
package com.hyva.hospital.holistic.pojo;

import com.hyva.hospital.holistic.entities.ServiceCategories;

public class ServicesPojo {
    private Long id;
    private String name;
    private int duration;
    private double price;
    private String currency;
    private String description;
    private String availabilities_type;
    private int attendants_number;
    private String flag;
    private ServiceCategories id_service_categories;
    private Long categoryId;
    private CategoriesPojo categoriesPojo;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvailabilities_type() {
        return availabilities_type;
    }

    public void setAvailabilities_type(String availabilities_type) {
        this.availabilities_type = availabilities_type;
    }

    public int getAttendants_number() {
        return attendants_number;
    }

    public void setAttendants_number(int attendants_number) {
        this.attendants_number = attendants_number;
    }

    public ServiceCategories getId_service_categories() {
        return id_service_categories;
    }

    public void setId_service_categories(ServiceCategories id_service_categories) {
        this.id_service_categories = id_service_categories;
    }

    public CategoriesPojo getCategoriesPojo() {
        return categoriesPojo;
    }

    public void setCategoriesPojo(CategoriesPojo categoriesPojo) {
        this.categoriesPojo = categoriesPojo;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
