package by.epam.hibernate.entity;

import java.util.Objects;
import java.util.Scanner;

public class Phone {
    private int id;
    private String phoneNumber;

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

    public void setParameters(Scanner scanner, String phoneType) {
        System.out.println("Input a " + phoneType + " phone number:");
        this.phoneNumber = scanner.nextLine();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return id == phone.id &&
                Objects.equals(phoneNumber, phone.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber);
    }
}
