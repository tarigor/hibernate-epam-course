package by.epam.hibernate.entity;

import java.util.Objects;

public class Phone {
    private int id;
    private String phoneNumber;

//    private int employeeId;
//    private Employee employeeByEmployeeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

//    public int getEmployeeId() {
//        return employeeId;
//    }
//
//    public void setEmployeeId(int employeeId) {
//        this.employeeId = employeeId;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Objects.equals(id, phone.id) &&
                Objects.equals(phoneNumber, phone.phoneNumber) ;
//                &&                Objects.equals(employeeId, phone.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber);
    }
//    public int hashCode() {
//        return Objects.hash(id, phoneNumber, employeeId);
//    }

//    public Employee getEmployeeByEmployeeId() {
//        return employeeByEmployeeId;
//    }
//
//    public void setEmployeeByEmployeeId(Employee employeeByEmployeeId) {
//        this.employeeByEmployeeId = employeeByEmployeeId;
//    }
}
