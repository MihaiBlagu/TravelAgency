package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "destination")
public class Destination {

    @Id
    @Column(name = "destination_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long destinationId;

    @Column(nullable = false, unique = true)
    private String name;

    // cascade type all here ensures that once a destination has been deleted
    // all packages containing that destination are also removed
    // orphanRemoval assures that this transaction will not result in an error
    // in the database
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destination", orphanRemoval = true)
    private Set<VacationPack> vacationPacks;

    public Destination() {
    }

    public Destination(String name) {
        this.name = name;
        this.vacationPacks = new HashSet<>();
    }

    public String getName() {
        return this.name;
    }

    public void addPack(VacationPack p) {
        vacationPacks.add(p);
    }
}
