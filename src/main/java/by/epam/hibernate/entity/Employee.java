package by.epam.hibernate.entity;

import java.util.Collection;
import java.util.Objects;
import java.util.Scanner;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private Integer salary;
    private Collection<Phone> phonesById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setParameters(Scanner scanner) {
        System.out.println("Input a first name:");
        this.firstName = scanner.nextLine();
        System.out.println("Input a last name:");
        this.lastName = scanner.nextLine();
        System.out.println("Input a salary");
        this.salary = scanner.nextInt();
        System.out.println("Do");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(salary, employee.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, salary);
    }

    public Collection<Phone> getPhonesById() {
        return phonesById;
    }

    public void setPhonesById(Collection<Phone> phonesById) {
        this.phonesById = phonesById;
    }

    @Override
    public String toString() {
        System.out.print("id:"+id+" First Name:"+firstName + " Last Name:"+lastName+" Salary:"+salary + " Phones:");
        phonesById.forEach(phone -> System.out.print(phone.getPhoneNumber()+" "));
        return "";
    }
}
