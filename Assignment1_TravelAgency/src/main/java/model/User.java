package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    // need cascade type merge because we don't want to persist the user
    // when adding a new booking, we just want to add the new pack
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_pack",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "pack_id") }
    )
    Set<VacationPack> bookedPacks;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.bookedPacks = new HashSet<>();
    }

    public String getPassword() {
        return password;
    }

    public void addBookedPack(VacationPack p){
        bookedPacks.add(p);
    }

    public Set<VacationPack> getBookedPacks() {
        return bookedPacks;
    }
}
