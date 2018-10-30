package pl.coderslab.model;

import java.time.LocalDate;

public class Car {
    private int id;
    private String model;
    private String brand;
    private int yearProd;
    private String registration;
    private LocalDate nextReview;
    private int ownerId;

    // java.sql.Date sqlDate = new java.sql.Date(now.getTime()); date util to date sql
    // constructors - id by default=0;


    public Car() {
    }

    public Car(String model, String brand, int yearProd, String registration, LocalDate nextReview, int ownerId) {
        this.model = model;
        this.brand = brand;
        this.yearProd = yearProd;
        this.registration = registration;
        this.nextReview = nextReview;
        this.ownerId = ownerId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYearProd() {
        return yearProd;
    }
    public void setYearProd(int yearProd) {
        this.yearProd = yearProd;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }
    public LocalDate getNextReview() {
        return nextReview;
    }

    public void setNextReview(LocalDate nextReview) {
        this.nextReview = nextReview;
    }
    public int getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

}
