package pl.coderslab.model;

public class OrdCarEmpl {

    Car car;
    Order order;
    Employee employee;

    public OrdCarEmpl() {
    }

    public OrdCarEmpl(Car car, Order order, Employee employee) {
        this.car = car;
        this.order = order;
        this.employee = employee;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
