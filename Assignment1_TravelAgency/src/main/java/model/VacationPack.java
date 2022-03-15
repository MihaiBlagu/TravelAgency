package model;

import utils.Status;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "vacation_pack")
public class VacationPack {

    @Id
    @Column(name = "pack_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long packId;

    @Column(nullable = false, unique = true)
    private String name;

    // cascade type merge here ensures that when a package is persisted
    // the destination entity is attached to the transaction
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private Integer maxNbOfPeople;

    @Column(nullable = false)
    private Integer currentNbOfPeople;

    @Column(nullable = false)
    private Status status;

    @Column
    private String extras;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "bookedPacks")
    private Set<User> users;

    public VacationPack() {
    }

    public VacationPack(String name, Destination destination, Double price, LocalDate startDate, LocalDate endDate,
                        Integer maxNbOfPeople, String extras, Status status) {
        this.name = name;
        this.destination = destination;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxNbOfPeople = maxNbOfPeople;
        this.extras = extras;
        this.status = status;
        this.currentNbOfPeople = 0;
    }

    public VacationPack(String name, Destination destination, Double price, LocalDate startDate, LocalDate endDate,
                        Integer maxNbOfPeople, Status status, String extras) {
        this.name = name;
        this.destination = destination;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxNbOfPeople = maxNbOfPeople;
        this.status = status;
        this.extras = extras;
    }

    public Long getId() {
        return this.packId;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Status getStatus() {
        return status;
    }

    public Destination getDestination() {
        return destination;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Integer getMaxNbOfPeople() {
        return maxNbOfPeople;
    }

    public Integer getCurrentNbOfPeople() {
        return currentNbOfPeople;
    }

    public String getExtras() {
        return extras;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setMaxNbOfPeople(Integer maxNbOfPeople) {
        this.maxNbOfPeople = maxNbOfPeople;
    }

    public void setCurrentNbOfPeople(Integer currentNbOfPeople) {
        this.currentNbOfPeople = currentNbOfPeople;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public void addNewUser(User user) {
        this.currentNbOfPeople++;
        users.add(user);

        if(this.currentNbOfPeople < maxNbOfPeople){
            this.status = Status.IN_PROGRESS;
        }
        if(this.currentNbOfPeople.equals(maxNbOfPeople)){
            this.status = Status.BOOKED;
        }
    }
}
