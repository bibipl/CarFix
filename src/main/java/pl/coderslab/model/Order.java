package pl.coderslab.model;

import java.time.LocalDate;

public class Order {
    int id;
    LocalDate planStartDate;
    LocalDate realStartDate;
    int employeeId;
    String problemDescript;
    String fixDescript;
    int status;
    int carId;
    Float valueServ;
    Float valueParts;
    Float hourPrice;
    Float numOfHours;


    public Order() {
    }

    public Order(LocalDate planStartDate, LocalDate realStartDate, int employeeId, String problemDescript, String fixDescript, int status,
                 int carId, Float valueServ, Float valueParts, Float hourPrice, Float numOfHours) {
        this.planStartDate = planStartDate;
        this.realStartDate = realStartDate;
        this.employeeId = employeeId;
        this.problemDescript = problemDescript;
        this.fixDescript = fixDescript;
        this.status = status;
        this.carId = carId;
        this.valueServ = valueServ;
        this.valueParts = valueParts;
        this.hourPrice = hourPrice;
        this.numOfHours = numOfHours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(LocalDate planStartDate) {
        this.planStartDate = planStartDate;
    }

    public LocalDate getRealStartDate() {
        return realStartDate;
    }

    public void setRealStartDate(LocalDate realStartDate) {
        this.realStartDate = realStartDate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getProblemDescript() {
        return problemDescript;
    }

    public void setProblemDescript(String problemDescript) {
        this.problemDescript = problemDescript;
    }

    public String getFixDescript() {
        return fixDescript;
    }

    public void setFixDescript(String fixDescript) {
        this.fixDescript = fixDescript;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public Float getValueServ() {
        return valueServ;
    }

    public void setValueServ(Float valueServ) {
        this.valueServ = valueServ;
    }

    public Float getValueParts() {
        return valueParts;
    }

    public void setValueParts(Float valueParts) {
        this.valueParts = valueParts;
    }

    public Float getHourPrice() {
        return hourPrice;
    }

    public void setHourPrice(Float hourPrice) {
        this.hourPrice = hourPrice;
    }

    public Float getNumOfHours() {
        return numOfHours;
    }

    public void setNumOfHours(Float numOfHours) {
        this.numOfHours = numOfHours;
    }
}
