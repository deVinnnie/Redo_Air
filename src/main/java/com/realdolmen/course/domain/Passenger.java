package com.realdolmen.course.domain;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = Passenger.FIND_ALL, query = "SELECT p FROM Passenger p"),
        @NamedQuery(name = Passenger.FIND_ALL_LAST_NAMES, query = "SELECT p.id.lastName FROM Passenger p"),
        @NamedQuery(name = Passenger.TOTAL_FREQUENT_FLYER_MILES, query = "SELECT SUM(p.frequentFlyerMiles) FROM Passenger p"),
        @NamedQuery(name = Passenger.FIND_TICKETS_BY_PASSENGER_ID, query = "SELECT p.tickets FROM Passenger p WHERE p.id = :id"),
        @NamedQuery(name = Passenger.DELETE_ALL, query = "DELETE FROM Passenger p WHERE p.tickets IS EMPTY")
})
public class Passenger implements Serializable{
    public static final String FIND_ALL = "Passenger.findAll";
    public static final String FIND_ALL_LAST_NAMES = "Passenger.findAllLastNames";
    public static final String TOTAL_FREQUENT_FLYER_MILES = "Passenger.totalFrequentFlyerMiles";
    public static final String FIND_TICKETS_BY_PASSENGER_ID = "Passenger.findTicketsByPassengerId";
    public static final String DELETE_ALL = "Passenger.deleteAll";


    @EmbeddedId
    private PassengerId id;

    @Version
    private Long version;

    @Size(max = 50)
    private String firstName;

    @Min(0)
    private Integer frequentFlyerMiles = 0;

    @NotNull
    @Past
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth = new Date();

    @NotNull
    @Enumerated(EnumType.STRING)
    private PassengerType type;

    @Past
    @Temporal(TemporalType.DATE)
    private Date lastFlight;

    @Embedded
    private Address address;

    @ElementCollection
    private List<CreditCard> cards;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    private byte[] picture;

    @ElementCollection
    private List<String> preferences = new ArrayList<>();

    @Transient
    private int age;

    @OneToMany(mappedBy = "passenger")
    private List<Ticket> tickets;

    @Embedded
    @ValidAccountNumber
    private AccountNumber accountNumber;

    public Passenger() {
    }

    public Passenger(PassengerId id, String firstName, Date dateOfBirth, PassengerType type) {
        this.id = id;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.type = type;
    }

    public PassengerId getId() {
        return id;
    }

    public void setId(PassengerId id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getFrequentFlyerMiles() {
        return frequentFlyerMiles;
    }

    public void setFrequentFlyerMiles(Integer frequentFlyerMiles) {
        this.frequentFlyerMiles = frequentFlyerMiles;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public PassengerType getType() {
        return type;
    }

    public void setType(PassengerType type) {
        this.type = type;
    }

    public Date getLastFlight() {
        return lastFlight;
    }

    public void setLastFlight(Date lastFlight) {
        this.lastFlight = lastFlight;
    }

    public List<CreditCard> getCards() {
        return cards;
    }

    public void setCards(List<CreditCard> cards) {
        this.cards = cards;
    }

    public int getAge() {
        Calendar c = Calendar.getInstance();

        Calendar birth = Calendar.getInstance();
        birth.setTime(dateOfBirth);

        int years = c.get(Calendar.YEAR) - birth.get(Calendar.YEAR);

        if(birth.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)){
            years++;
        }

        return years;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Passenger{" + firstName + ' ' + id.getLastName() + '}';
    }

    public AccountNumber getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(AccountNumber accountNumber) {
        this.accountNumber = accountNumber;
    }
}
