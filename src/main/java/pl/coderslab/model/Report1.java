package pl.coderslab.model;

public class Report1 {
    private String name;
    private Float numOfHours;

    public Report1() {
    }

    public Report1(String name, String surname, Float numOfHours) {
        this.name = name;
        this.numOfHours = numOfHours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getNumOfHours() {
        return numOfHours;
    }

    public void setNumOfHours(Float numOfHours) {
        this.numOfHours = numOfHours;
    }
}

