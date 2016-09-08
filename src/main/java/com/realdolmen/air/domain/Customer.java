package com.realdolmen.air.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@NamedQueries({
    @NamedQuery(name = Customer.FIND_BY_EMAIL2, query = "SELECT c FROM Customer c WHERE c.email = :email")
})
public class Customer extends User {
    public static final String FIND_BY_EMAIL2 = "Customer.findByEmail";

    @Size(min = 1, max = 255)
    private String firstName;

    @Size(min = 1, max = 255)
    private String lastName;

    @OneToMany(mappedBy = "customer")
    private List<Booking> bookings;

    public Customer(){
        super(Role.CUSTOMER);
    }

    public Customer(String firstName, String lastName) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
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

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
