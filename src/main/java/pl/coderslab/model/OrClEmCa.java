package pl.coderslab.model;

public class OrClEmCa {
    Order order;
    Client client;
    Employee employee;
    Car car;

    public OrClEmCa() {
    }

    public OrClEmCa(Order order, Client client, Employee employee, Car car) {
        this.order = order;
        this.client = client;
        this.employee = employee;
        this.car = car;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}

